package model

import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

class FleetTest extends FunSuite with BeforeAndAfterEach {
  val fleet: Fleet = Fleet(mutable.Set.empty)

  val carrier: Ship = Ship(Direction.HORIZONTAL, Point('A', 1, PointType.OCCUPIED), ShipT.CARRIER)
  val battleship: Ship = Ship(Direction.HORIZONTAL, Point('F', 2, PointType.OCCUPIED), ShipT.BATTLESHIP)
  val destroyer: Ship = Ship(Direction.VERTICAL, Point('B', 0, PointType.OCCUPIED), ShipT.DESTROYER)
  val submarine: Ship = Ship(Direction.HORIZONTAL, Point('C', 0, PointType.OCCUPIED), ShipT.SUBMARINE)
  val patrol_boat: Ship = Ship(Direction.HORIZONTAL, Point('D', 0, PointType.OCCUPIED), ShipT.PATROL_BOAT)


  override def beforeEach(){
    fleet.removeAll
  }

  test("addShip and removeShip test"){

    fleet addShip(carrier)
    fleet addShip(submarine)
    assert(fleet.shipsSet.size == 2)
    assert(fleet.shipsSet.contains(carrier))
    fleet removeShip(submarine)
    assert(fleet.shipsSet.size == 1)
    assert(!fleet.shipsSet.contains(destroyer))
    assert(!fleet.shipsSet.contains(patrol_boat))
  }

  test("isShipOverlapingWithAlreadyContainedShip test") {
    fleet.addShip(carrier)
    fleet.addShip(battleship)
    assert(fleet.isShipOverlappingWithAlreadyContainedShip(destroyer))
  }
}
