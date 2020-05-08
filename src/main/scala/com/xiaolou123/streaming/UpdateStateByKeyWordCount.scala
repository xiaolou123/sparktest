package com.xiaolou123.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 基于upudateStateByKey算子实现缓存机制实时wordcount程序
  */
object UpdateStateByKeyWordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("UpdateStateByKeyWordCount")

    // Scala中，创建的是StreamingContext
    val ssc = new StreamingContext(conf, Seconds(5))
    ssc.checkpoint("hdfs://hadoop01:9000/wordcount_checkpoint")

    val lines = ssc.socketTextStream("hadoop01",9999)
    val words = lines.flatMap{ _.split(" ")}
    val pairs = words.map{ word => (word,1) }
    val workCounts = pairs.updateStateByKey((values: Seq[Int], state: Option[Int]) => {
      var newValue = state.getOrElse(0)
      for (value <- values){
        newValue+= value
      }
      Option(newValue)
    })

    workCounts.print()


    ssc.start()
    ssc.awaitTermination()
  }
}
