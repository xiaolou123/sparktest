package com.xiaolou123.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * 通用的load和save操作
  */
object GenericLoadSave {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("GenericLoadSave")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val usersDF = sqlContext.read.load("D://BigData//JetBrains//sparktest//src//txts//users.parquet")
    usersDF.write.save("C://Users//33063//Desktop//namesAndColors_scala.parquet")

  }
}
