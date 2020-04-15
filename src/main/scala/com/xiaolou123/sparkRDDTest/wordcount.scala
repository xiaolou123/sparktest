package com.xiaolou123.sparkRDDTest

import org.apache.spark.{SparkConf, SparkContext}

object wordcount {

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local")
      .setAppName("wordcount")

    //获取Spark的上下文对象，通过这个对象来操作spark
    val sc=new SparkContext(conf)

    val data=sc.textFile("hdfs://192.168.100.135:9000/word.txt", 2)

    //懒方法lazy
    val result=data.flatMap { _.split(" ") }
      .map{ (_,1) }
      .reduceByKey(_+_)
    //需要执行Action方法,出发计算
    result.foreach{println}
  }

}
