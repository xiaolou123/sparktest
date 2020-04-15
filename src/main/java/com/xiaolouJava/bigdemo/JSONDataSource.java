package com.xiaolouJava.bigdemo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON数据源
 * @author Administrator
 *
 */
public class JSONDataSource {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("JSONDataSource");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        // 针对json文件，创建DataFrame（针对json文件创建DataFrame）
        DataFrame studentScoresDF = sqlContext.read().json("hdfs://192.168.100.135:9000/spark-study/students.json");

        // 针对学生成绩信息的DataFrame，注册临时表，查询分数大于80分的学生的姓名
        // （注册临时表，针对临时表执行sql语句）
        studentScoresDF.registerTempTable("student_scores");
        DataFrame goodStudenNamesDF = sqlContext.sql("select name from student_scores where score >= 80");

        List<String> goodStudentName = goodStudenNamesDF.javaRDD().map(new Function<Row, String>() {
            public String call(Row row) throws Exception {
                return row.getString(0);
            }
        }).collect();

        // 然后针对JavaRDD<String>,创建DataFrame
        List<String> studentInfoJSONs = new ArrayList<String>();
        studentInfoJSONs.add("{\"name\":\"Leo\",\"age\":18}");
        studentInfoJSONs.add("{\"name\":\"Marry\",\"age\":17}");
        studentInfoJSONs.add("{\"name\":\"Jack\",\"age\":19}");
        JavaRDD<String> studentInfoJSONsRDD = sc.parallelize(studentInfoJSONs);
        DataFrame studentInfoDF = sqlContext.read().json(studentInfoJSONsRDD);

    }
}
