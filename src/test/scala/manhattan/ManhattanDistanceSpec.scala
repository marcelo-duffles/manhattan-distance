package manhattan

import org.scalatest.{ Matchers, WordSpec, Inspectors }
import scala.math._

class ManhattanDistanceSpec extends WordSpec with Matchers with Inspectors {

  "The method distance()" when {
    "given a number from 1 to 1089" should {
      "return the manhattan distance to the center" in {
        val coordinates = generateCoordinatesForN(33)
        forAll(coordinates) { coord =>
          val number = coordinates.lastIndexOf(coord) + 1
          number.distance shouldBe distanceOfCoordinates(coord)
        }
      }
    }

    "given a non-positive number" should {
      "throw an IllegalArgumentException" in {
        intercept[IllegalArgumentException] { -1.distance }
      }
    }
  }

  "the method distanceOfCoordinates()" when {
    "given a pair of coordinates" should {
      "return the sum of the absolute values of the coordinates" in {
        val coordinates = Seq((2, 3), (-2, 3), (-2, -3), (2, -3))
        forAll(coordinates) { distanceOfCoordinates(_) shouldBe 2 + 3 }
      }
    }
  }

  /*
   * Returns a sequence representing the coordinates of each number.
   *
   *   Example: the grid 3x3   5 4 3    has coordinates   (-1,1)  (0,1)  (1,1)
   *                           6 1 2                      (-1,0)  (0,0)  (1,0)
   *                           7 8 9                      (-1,-1) (0,-1) (1,-1)
   *
   *   which are represented by
   *   Seq((0,0), (1,0), (1,1), (0,1), (-1,1), (-1,0), (-1,-1), (0,-1), (1,-1))
   */
  private def generateCoordinatesForN(n: Int): Seq[(Int, Int)] = {

    def coordinatesUpTo(limit: Int): Seq[(Int, Int)] => Seq[(Int, Int)] = {
      def start = (s: Seq[(Int, Int)]) => s :+ (s.last._1 + 1, s.last._2)

      def up = (s: Seq[(Int, Int)]) =>
        (s.last._2 + 1 to limit).foldLeft(s) { (acc, y) => acc :+ (s.last._1, y) }

      def left = (s: Seq[(Int, Int)]) =>
        (-limit to s.last._1 - 1).reverse.foldLeft(s) { (acc, x) => acc :+ (x, s.last._2) }

      def down = (s: Seq[(Int, Int)]) =>
        (-limit to s.last._2 - 1).reverse.foldLeft(s) { (acc, y) => acc :+ (s.last._1, y) }

      def right = (s: Seq[(Int, Int)]) =>
        (s.last._1 + 1 to limit).foldLeft(s) { (acc, x) => acc :+ (x, s.last._2) }

      (start andThen up andThen left andThen down andThen right)(_)
    }
    (1 to n).foldLeft(Seq((0, 0))) { (acc, i) => coordinatesUpTo(i)(acc) }
  }
}
