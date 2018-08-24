package com.kaishu.test

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[3]").setAppName("test")
    val sc = new SparkContext(conf)

    sc.textFile("D:\\Project\\tm-sh\\test.txt")
        .map(_.split("\t")(2))
        .filter {
            case x: String if x.startsWith("ks_user_rank_map") => x < "ks_user_rank_map_2018_08_16"
            case x: String if x.startsWith("ks_content_library") => x < "ks_content_library_2018_08_16"
            case x: String if x.startsWith("kaishu_baby_achieve") => x < "kaishu_baby_achieve_2018_08_16"
            case _ => false
        }
        .collect().foreach(println)

  }
}
