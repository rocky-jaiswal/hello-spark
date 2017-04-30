package in.rockyj.hellospark

import org.scalatest.{FunSuite, BeforeAndAfterAll}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import in.rockyj.hellospark._

@RunWith(classOf[JUnitRunner])
class MainTest extends FunSuite with BeforeAndAfterAll {

  test("a simple test") {
    assert(2 + 3 == 5)
  }

}