import scala.math._

package object manhattan {

  implicit class Number(val n: Int) extends AnyVal {

    /*
     * Manhattan distance algorithm following the solution described in the README file
     */
    def distance: Int = {

      def innerSquareSide = {
        val s = floor(sqrt(n)).toInt
        s % 2 match {
          case 0 => s - 1
          case 1 => sqrt(n) - s match {
            case 0 => s - 2
            case _ => s
          }
        }
      }

      def outerSegment(inner: Int) = inner + 1

      def coordinatesFromInnerSquare = { (inner: Int) =>
        val offset = n - pow(inner, 2).toInt
        val segment = outerSegment(inner)
        (offset / segment, offset % segment) match {
          case (0, i) => (segment / 2, -segment / 2 + i)
          case (1, i) => (segment / 2 - i, segment / 2)
          case (2, i) => (-segment / 2, segment / 2 - i)
          case (_, i) => (-segment / 2 + i, -segment / 2)
        }
      }

      def distanceOfAPositiveInteger: PartialFunction[Int, Int] = {
        case n if n > 1  => (coordinatesFromInnerSquare andThen distanceOfCoordinates)(innerSquareSide)
        case n if n == 1 => 0
      }

      def illegalArg: PartialFunction[Int, Int] = {
        case n if n < 1 => throw new IllegalArgumentException(s"$n is not a positive integer")
      }

      (distanceOfAPositiveInteger orElse illegalArg)(n)
    }
  }

  def distanceOfCoordinates(c: (Int, Int)): Int = abs(c._1) + abs(c._2)
}
