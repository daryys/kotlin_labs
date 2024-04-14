package org.example.lab10

import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession

fun main() {
    val spark = SparkSession.builder()
        .appName("RandomSplitExample")
        .master("local[*]")
        .orCreate

    val dataset: Dataset<Row> = spark.read().format("csv")
        .option("header", "true")
        .load("./russian_demography.csv")
    dataset.createOrReplaceTempView("russian_demography")
    spark.sql("select * from russian_demography").show()
    spark.sql("select * from russian_demography where year=1990").show()
    spark.sql("select * from russian_demography where region='Republic of Adygea'").show()
    spark.sql("select * from russian_demography where birth_rate > 14").show()
    spark.sql("select * from russian_demography where birth_rate > 10").show()
    spark.sql("select * from russian_demography where birth_rate > 14 and birth_rate > 10").show()
    spark.sql("select * from russian_demography order by birth_rate").show()
    spark.sql("select * from russian_demography where urbanization > 50").show()
    spark.sql("select * from russian_demography where gdw > 80").show()
    spark.sql("select count(*) from russian_demography").show()

    spark.stop()
}