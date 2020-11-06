package model

import org.scalatest.FunSuite

class FleetTest extends FunSuite{
  val fleet: Fleet = Fleet(Set.empty)
  val carrier: Ship = Ship(Direction.HORIZONTAL, Point(0,0, PointType.OCCUPIED), ShipT.CARRIER)
  val battleship: Ship = Ship(Direction.HORIZONTAL, Point(0,0, PointType.OCCUPIED), ShipT.BATTLESHIP)
  val destroyer: Ship = Ship(Direction.HORIZONTAL, Point(0,0, PointType.OCCUPIED), ShipT.DESTROYER)
  val submarine: Ship = Ship(Direction.HORIZONTAL, Point(0,0, PointType.OCCUPIED), ShipT.SUBMARINE)
  val patrol_boat: Ship = Ship(Direction.HORIZONTAL, Point(0,0, PointType.OCCUPIED), ShipT.PATROL_BOAT)



  test("addShip and removeShip test"){

    fleet addShip(carrier)
    fleet addShip(destroyer)
    fleet addShip(submarine)
    assert(fleet.shipsSet.size == 3)
    assert(fleet.shipsSet.contains(carrier))
    fleet removeShip(destroyer)
    assert(fleet.shipsSet.size == 2)
    assert(!fleet.shipsSet.contains(destroyer))
    assert(!fleet.shipsSet.contains(patrol_boat))

  }
}
