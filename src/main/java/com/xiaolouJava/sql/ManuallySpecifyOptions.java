package com.xiaolouJava.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 * 手动指定数据源类型
 */
public class ManuallySpecifyOptions {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("ManuallySpecifyOptions");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        // 从一个文件类型存成另外一个文件类型
        DataFrame peopleDF = sqlContext.read().format("json").load("D://BigData//JetBrains//sparktest//src//txts//people.json");
        peopleDF.select("name").write().format("parquet").save("C://Users//33063//Desktop//peopleName.parquet");
    }
}
