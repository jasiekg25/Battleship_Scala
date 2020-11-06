package model

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import org.scalatest.FunSuite
import org.scalatestplus.scalacheck.Checkers

class BoardTest extends FunSuite{
  test("Board.endX test") {
    assert(Board.endX == 'J')
  }
  test("Board.endY test"){
    assert(Board.endY == 9)
  }

  test("Board.next_x test"){
    assert(Board.next_x('D') contains('E') )
    assert(Board.next_x('J') isEmpty)

  }
  test("Board.previous_x test"){
    assert(Board.previous_x('C') contains('B'))
    assert(Board.previous_x('B') contains('A'))
    assert(Board.previous_x('A') isEmpty)
  }

  test("Board.next_y test"){
    assert(Board.next_y(8) contains(9) )
    assert(Board.next_y(9) isEmpty)

  }
  test("Board.previous_y test"){
    assert(Board.previous_y(5) contains(4))
    assert(Board.previous_y(1) contains(0))
    assert(Board.previous_y(0) isEmpty)
  }
}
