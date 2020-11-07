package model

import org.scalatest.FunSuite

import scala.collection.mutable

class PlayerBoardTest extends FunSuite{
  val playerBoard: PlayerBoard = PlayerBoard(mutable.Map.empty)

  val carrier: Ship = Ship(Direction.HORIZONTAL, Point('A',0, PointType.OCCUPIED), ShipT.CARRIER)


  test("addShipToMap test"){
    playerBoard.addShipToMap(carrier)
    assert(playerBoard.addShipToMap(carrier).size == 5)
  }

}
