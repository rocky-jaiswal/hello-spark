package in.rockyj.hellospark

import org.scalatest.{FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import in.rockyj.hellospark._

@RunWith(classOf[JUnitRunner])
class FriendsByAgeTest extends FunSuite {

  test("the average calculation is right") {
    val ages = List(1, 2, 3, 4, 5, 6)
    println(FriendsByAge.calculateAverage(ages))
    assert(FriendsByAge.calculateAverage(ages) == 3.5)
  }

}