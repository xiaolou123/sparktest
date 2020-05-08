package com.xiaolou123.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WindowHotWord {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("WindowHotWord")
    val ssc = new StreamingContext(conf, Seconds(1))

    val searchLogsDStream = ssc.socketTextStream("hadoop01",9999)
    val searchWordsDStream = searchLogsDStream.map{ _.split(" ")(1)}
    val searchWordPairsDStream = searchWordsDStream.map{ searchWord => (searchWord,1)}
    val searchWordCountsDStream = searchWordPairsDStream.reduceByKeyAndWindow(
      (v1:Int, v2:Int) => v1 + v2,
      Seconds(60),
      Seconds(10)
    )
    val finalDStream = searchWordCountsDStream.transform{searchWordCountsRDD =>
      val countSearchWordsRDD = searchWordCountsRDD.map(tuple => (tuple._2, tuple._1))
      val sortedCountSearchWordsRDD = countSearchWordsRDD.sortByKey(false)
      val sortedSearchWordCountsRDD = sortedCountSearchWordsRDD.map(tuple => (tuple._2, tuple._1))
      val top3SearchWordCounts = sortedSearchWordCountsRDD.take(3)
      for(tuple <- top3SearchWordCounts){
        print(tuple)
      }
      searchWordCountsRDD
    }

    finalDStream.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
