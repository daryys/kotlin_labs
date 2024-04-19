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
    spark.sql("select year, urbanization from russian_demography where region='Nizhny Novgorod Oblast'").show()
    spark.sql("select region, birth_rate from russian_demography where birth_rate < 9").show()
    spark.sql("select region, birth_rate from russian_demography where birth_rate > 20").show()
    spark.sql("select region, death_rate from russian_demography where death_rate > 20 order by death_rate desc").show()
    spark.sql("select * from russian_demography where urbanization > 60").show()
    spark.sql("select count(*) from russian_demography where urbanization > 60").show()
    spark.sql("select avg(Float(urbanization)) from russian_demography where year = 1990").show()
    spark.sql("select count(*) from russian_demography").show()

    spark.stop()
}