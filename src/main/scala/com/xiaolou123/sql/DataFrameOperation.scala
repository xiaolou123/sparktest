package com.xiaolou123.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object DataFrameOperation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordcount")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val df = sqlContext.read.json("hdfs://192.168.100.135:9000/students.json")

    df.show()
    df.printSchema()
    df.select("name").show()
    df.select(df("name"),df("age") + 1).show()
    df.filter(df("age")>18).show()
    df.groupBy("age").count().show()
  }

}
