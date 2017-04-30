package in.rockyj.hellospark

import java.io.File
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/** Compute the average number of friends by age in a social network. */
object FriendsByAge {

  def filePath = {
    val resource = this.getClass.getClassLoader.getResource("fakefriends.csv")
    if (resource == null) sys.error("Please download the dataset as explained in the assignment instructions")
    new File(resource.toURI).getPath
  }

  def parseLine(line: String) = {
    // Split by commas
    val fields = line.split(",")
    // Extract the age and numFriends fields, and convert to integers
    val age = fields(2).toInt
    val numFriends = fields(3).toInt
    // Create a tuple that is our result.
    (age, numFriends)
  }

  def calculateAverage(nums: Iterable[Int]): Double = {
    nums.reduce((a, b) => a + b).toDouble / nums.size
  }

  /** Our main function where the action happens */
  def main(args: Array[String]): Unit = {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("FriendsByAge")
    val sc = new SparkContext(conf)

    // Load each line of the source data into an RDD
    val lines = sc.textFile(filePath)

    // Use our parseLines function to convert to (age, numFriends) tuples
    val rdd = lines.map(parseLine)

    val result = rdd.groupByKey().mapValues(nums => calculateAverage(nums)).collect()

    result.foreach(res => println("Age -> " + res._1 + " Avg. -> " + res._2))
  }

}
