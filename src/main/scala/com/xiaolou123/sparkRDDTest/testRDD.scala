package com.xiaolou123.sparkRDDTest

import org.apache.spark.{SparkConf, SparkContext}

object testRDD {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ReduceByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val d2 = sc.parallelize(
      Array(("cc",32),("bb",32),("cc",22),("aa",18),(
        "bb",6),("dd",16),("ee",104),("cc",1),("ff",13),("gg",68),("bb",44))
    )

    val d3 = d2.sortByKey(true)
    d3.foreach{ sortedLine => println(sortedLine)}
  }
}
