package model

import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

class FleetTest extends FunSuite with BeforeAndAfterEach {
  var fleet: Fleet = Fleet(Set.empty)

  val carrier: Ship = Ship(Direction.HORIZONTAL, (('A', 1), PointType.OCCUPIED), ShipT.CARRIER)
  val battleship: Ship = Ship(Direction.HORIZONTAL, (('A', 5), PointType.OCCUPIED), ShipT.BATTLESHIP)
  val destroyer: Ship = Ship(Direction.VERTICAL, (('A', 0), PointType.OCCUPIED), ShipT.DESTROYER)
  val submarine: Ship = Ship(Direction.HORIZONTAL, (('C', 0), PointType.OCCUPIED), ShipT.SUBMARINE)
  val patrol_boat: Ship = Ship(Direction.HORIZONTAL, (('D', 0), PointType.OCCUPIED), ShipT.PATROL_BOAT)


  override def beforeEach(){
    fleet = fleet.removeAll

  }

  test("addShip and removeShip test"){

    val fleet1 = fleet addShip(carrier)
    val fleet2 = fleet1 addShip(submarine)
    assert(fleet2.shipsSet.size == 2)
    assert(fleet2.shipsSet.contains(carrier))
    val fleet3 = fleet2 removeShip(submarine)
    assert(fleet3.shipsSet.size == 1)
    assert(!fleet3.shipsSet.contains(destroyer))
    assert(!fleet3.shipsSet.contains(patrol_boat))
  }

  test("isShipOverlapingWithAlreadyContainedShip test") {
    val fleet1 = fleet.addShip(carrier)
    val fleet2 = fleet1.addShip(battleship)
    assert(fleet2.isShipOverlappingWithAlreadyContainedShip(destroyer))
  }
}
