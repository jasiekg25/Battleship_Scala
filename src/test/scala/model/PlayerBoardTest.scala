package model

import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

class PlayerBoardTest extends FunSuite with BeforeAndAfterEach{
  var playerBoard: PlayerBoard = PlayerBoard(mutable.Map.empty)

  val carrier: Ship = Ship(Direction.HORIZONTAL, Point('A',0, PointType.OCCUPIED), ShipT.CARRIER)
  val battleship: Ship = Ship(Direction.HORIZONTAL, Point('B',1, PointType.OCCUPIED), ShipT.BATTLESHIP)
  val destroyer: Ship = Ship(Direction.VERTICAL, Point('C',3, PointType.OCCUPIED), ShipT.DESTROYER)
  val submarine: Ship = Ship(Direction.VERTICAL, Point('D',2, PointType.OCCUPIED), ShipT.SUBMARINE)
  val patrol_boat: Ship = Ship(Direction.VERTICAL, Point('E',5, PointType.OCCUPIED), ShipT.PATROL_BOAT)
  var shipsSet: mutable.Set[Ship] = mutable.Set.empty
  shipsSet += carrier
  shipsSet += battleship
  shipsSet += destroyer
  shipsSet += submarine
  shipsSet += patrol_boat

  val fleet: Fleet = Fleet(shipsSet)

  override def beforeEach(): Unit = {
    playerBoard = PlayerBoard(mutable.Map.empty)
  }

  test("addShipToMap test"){
    playerBoard.addShipToMap(carrier)
    assert(playerBoard.map.size == 5)
  }

  test("addFleet test"){
    playerBoard.addFleet(fleet)
    assert(playerBoard.map.size == 17)
  }

  test("initializePlayerBoardWithFleet"){
    playerBoard.initailicePlayerBoardWithFleet(fleet)
    assert(playerBoard.map(carrier.startPoint.coordinates) == PointType.OCCUPIED)
    assert(playerBoard.map(('J', 9)) == PointType.WATER)
    assert(!playerBoard.map.contains('K', 5))
  }

  test("shoot test"){
    playerBoard.initailicePlayerBoardWithFleet(fleet)
    playerBoard.shoot(carrier.startPoint.coordinates)
    playerBoard.shoot('J', 9)
    assert(playerBoard.map(carrier.startPoint.coordinates) == PointType.HIT)
    assert(playerBoard.map('J', 9) == PointType.SHOT)
  }

  test("isShipAllHit test"){
    playerBoard.initailicePlayerBoardWithFleet(fleet)
    carrier.forAllPoints(p => {playerBoard.shoot(p.coordinates)})
    assert(playerBoard.isShipAllHit(carrier))
  }

  test("sinkShip test"){
    playerBoard.initailicePlayerBoardWithFleet(fleet)
    playerBoard.sinkShip(destroyer)
    assert(playerBoard.map.count( kv => kv._2 == PointType.SINK) == 3)
  }


}
