package com.xiaolouJava.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 * 通用的load和save操作
 */
public class GenericLoadSave {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("DataFrameCreate");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        DataFrame usersDF = sqlContext.read().load("D://BigData//JetBrains//sparktest//src//txts//users.parquet");
//        usersDF.printSchema();
//        usersDF.show();

        usersDF.select("name","favorite_color").write()
                .save("C://Users//33063//Desktop//amesAndColors.parquet");


    }
}
