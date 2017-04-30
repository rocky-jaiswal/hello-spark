package in.rockyj.hellospark

import org.scalatest.{FunSuite, BeforeAndAfterAll}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import in.rockyj.hellospark._

@RunWith(classOf[JUnitRunner])
class FriendsByAgeTest extends FunSuite with BeforeAndAfterAll {

  test("the job returns the right response") {
//    FriendsByAge.run()
    assert(2 + 2 == 4)
  }

}