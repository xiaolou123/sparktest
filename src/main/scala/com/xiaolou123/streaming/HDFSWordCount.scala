package com.xiaolou123.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object HDFSWordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("HDFSWordCount")
    val ssc = new StreamingContext(conf, Seconds(5))

    val lines = ssc.textFileStream("hdfs://hadoop01:9000/wordcount_dir")
    val words = lines.flatMap{ _.split(" ")}
    val pairs = words.map{ (_,1)}
    val wordCount = pairs.reduceByKey(_ + _)

    wordCount.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
