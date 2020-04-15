package com.xiaolouJava.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.util.List;

/**
 * Parquet数据源之使用编程方式加载数据
 */
public class ParquetLoadData {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("ParquetLoadData");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        // 读取Parquet文件中的数据，创建一个DataFrame
        DataFrame usersDF = sqlContext.read().parquet("D://BigData//JetBrains//sparktest//src//txts//users.parquet");

        // 将DataFrame注册为临时表，然后使用SQL查询需要的数据
        usersDF.registerTempTable("users");
        DataFrame usersNameDF = sqlContext.sql("select name from users");

        // 对查询出来的DataFrame进行transformation操作，处理数据，然后打印出来
        List<String> userNames = usersNameDF.javaRDD().map(new Function<Row, String>() {
            public String call(Row row) throws Exception {
                return "Name: "+ row.getString(0);
            }
        }).collect();

        for(String s : userNames){
            System.out.println(s);
        }

    }
}
