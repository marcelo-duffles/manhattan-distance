package manhattan

object App extends App {
  if (args.length != 1) {
    Console.err.println("Usage: sbt run <number>")
    sys.exit(1)
  }
  val number = args(0).toInt
  println(s"The manhattan distance of $number is ${number.distance}")
}
