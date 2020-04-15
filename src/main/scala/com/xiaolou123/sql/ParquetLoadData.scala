package com.xiaolou123.sql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Parquet数据源之使用编程方式加载数据
  */
object ParquetLoadData {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("ParquetLoadData")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val usersDF = sqlContext.read.parquet("D://BigData//JetBrains//sparktest//src//txts//users.parquet")
    usersDF.registerTempTable("users")
    val userNameDF = sqlContext.sql("select name from users")
    userNameDF.rdd.map{row => "Name: "+ row(0)}.collect().foreach{ userName => println(userName)}
  }
}
