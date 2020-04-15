package com.xiaolou123.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * 手动指定数据源类型
  */
object ManuallySpecifyOptions {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("ManuallySpecifyOptions")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 从一个文件类型存成另外一个文件类型

    val peopleDF = sqlContext.read.format("json").load("D://BigData//JetBrains//sparktest//src//txts//people.json")
    peopleDF.select("name").write.format("parquet").save("C://Users//33063//Desktop//peopleName.parquet")
  }
}
