package in.rockyj.hellospark

import java.io.File

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object MinStationTemp {

  def filePath = {
    val resource = this.getClass.getClassLoader.getResource("1800.csv")
    if (resource == null) sys.error("Data file not found!")
    new File(resource.toURI).getPath
  }

  def parseLine(line: String) = {
    val fields = line.split(",")
    val stationId = fields(0)
    val tempType = fields(2)
    val temp = fields(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f

    (stationId, (tempType, temp))
  }

  /** Our main function where the action happens */
  def main(args: Array[String]): Unit = {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("MinStationTemp")
    val sc = new SparkContext(conf)

    // Load each line of the source data into an RDD
    val lines = sc.textFile(filePath)

    // Use our parseLines function to convert to (age, numFriends) tuples
    val rdd = lines.map(parseLine)

    val onlyMin = rdd.filter(line => line._2._1.equalsIgnoreCase("TMIN")).mapValues(pair => pair._2)
    val stationMinTemps = onlyMin.groupByKey()
    val result = stationMinTemps.mapValues(temps => temps.min)

    result.foreach(res => println("Station -> " + res._1 + " Min Temp. -> " + res._2))
  }

}
