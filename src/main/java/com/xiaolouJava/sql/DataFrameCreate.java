package com.xiaolouJava.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

public class DataFrameCreate {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("DataFrameCreate");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        DataFrame df = sqlContext.read().json("hdfs://192.168.100.135:9000/students.json");

        df.show();
    }
}
