package com.xiaolou123.sortTest

import org.apache.spark.{SparkConf, SparkContext}

object Top3 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("top3").setMaster("local")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("D://BigData//JetBrains//sparktest//top.txt",1)
    val pairs = lines.map{ line => (line.toInt, line)}
    val sortedPairs = pairs.sortByKey(false) //true 升序   false降序
    val sortedNumbers = sortedPairs.map(sortedPair => sortedPair._1)
    val top3Number = sortedNumbers.take(3)
    for(num <- top3Number){
      println(num)
    }
  }

}
