package model

import org.scalatest.{BeforeAndAfterEach, FunSuite}


class RegularPlayerTest extends FunSuite with BeforeAndAfterEach{
  var player: Player = RegularPlayer("player")
  val carrier: Ship = Ship(Direction.HORIZONTAL, (('A',0), PointType.OCCUPIED), ShipT.CARRIER)
  val battleship: Ship = Ship(Direction.HORIZONTAL, (('B',1), PointType.OCCUPIED), ShipT.BATTLESHIP)
  val destroyer: Ship = Ship(Direction.VERTICAL, (('C',3), PointType.OCCUPIED), ShipT.DESTROYER)
  val submarine: Ship = Ship(Direction.VERTICAL, (('D',2), PointType.OCCUPIED), ShipT.SUBMARINE)
  val patrol_boat: Ship = Ship(Direction.VERTICAL, (('E',5), PointType.OCCUPIED), ShipT.PATROL_BOAT)
  var shipsSet: Set[Ship] = Set.empty
  shipsSet += carrier
  shipsSet += battleship
  shipsSet += destroyer
  shipsSet += submarine
  shipsSet += patrol_boat

  val fleet: Fleet = Fleet(shipsSet)

  override def beforeEach(): Unit = {
    player = RegularPlayer("player")
  }

  test("addShipToMap test"){
    val updatedPlayer = player.addShipToMap(carrier)
    assert(updatedPlayer.myBoard.size == 5)
  }

  test("addFleet test"){
    val updatedPlayer = player.addFleet(fleet)
    assert(updatedPlayer.myBoard.size == 17)
  }

  test("initializePlayerBoardWithFleet"){
    val updatedPlayer = player.initWithFleet(fleet)
    assert(updatedPlayer.myBoard(carrier.startPoint._1) == PointType.OCCUPIED)
    assert(updatedPlayer.myBoard(('J', 9)) == PointType.WATER)
    assert(!updatedPlayer.myBoard.contains('K', 5))
  }

  test("takeShot test"){
    val updatedPlayer1 = player.initWithFleet(fleet)
    val updatedPlayer2 = updatedPlayer1.takeShot(carrier.startPoint._1)
    val updatedPlayer3 = updatedPlayer2._2.takeShot('J', 9)
    assert(updatedPlayer3._2.myBoard(carrier.startPoint._1) == PointType.HIT)
    assert(updatedPlayer3._2.myBoard('J', 9) == PointType.SHOT)
  }

//  test("isShipAllHit test"){
//    var updatedPlayer = player.initWithFleet(fleet)
//    val pointsToUpdate = for{
//    point <- carrier.getAllPoints
//  } yield updatedPlayer = updatedPlayer.takeShot(point._1)
//    assert(updatedPlayer.isShipAllHit(carrier))
//  }

  test("sinkShip test"){
    var updatedPlayer = player.initWithFleet(fleet)
    updatedPlayer = updatedPlayer.sinkShip(destroyer)
    assert(updatedPlayer.myBoard.count( kv => kv._2 == PointType.SINK) == 3)
  }


}
