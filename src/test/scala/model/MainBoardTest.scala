package model

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import org.scalatest.FunSuite
import org.scalatestplus.scalacheck.Checkers

class MainBoardTest extends FunSuite{
  test("Board.endX test") {
    assert(MainBoard.endX == 'J')
  }
  test("Board.endY test"){
    assert(MainBoard.endY == 9)
  }

  test("Board.next_x test"){
    assert(MainBoard.next_x('D') contains('E') )
    assert(MainBoard.next_x('J') isEmpty)

  }
  test("Board.previous_x test"){
    assert(MainBoard.previous_x('C') contains('B'))
    assert(MainBoard.previous_x('B') contains('A'))
    assert(MainBoard.previous_x('A') isEmpty)
  }

  test("Board.next_y test"){
    assert(MainBoard.next_y(8) contains(9) )
    assert(MainBoard.next_y(9) isEmpty)

  }
  test("Board.previous_y test"){
    assert(MainBoard.previous_y(5) contains(4))
    assert(MainBoard.previous_y(1) contains(0))
    assert(MainBoard.previous_y(0) isEmpty)
  }
}
