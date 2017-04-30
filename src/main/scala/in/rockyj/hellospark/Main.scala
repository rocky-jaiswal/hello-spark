package in.rockyj.hellospark

object Main extends App {

  new Main().sayHello("Scala")

}

class Main {

  def sayHello(name: String): Unit = {
    println(buildGreeting(name))
  }

  def buildGreeting(name: String): String = {
    "Hello " + name + "!"
  }

}