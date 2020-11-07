package model

import org.scalatest.FunSuite

class ShipTest extends FunSuite{

  val carrier: Ship = Ship(Direction.HORIZONTAL, Point('A',0, PointType.OCCUPIED), ShipT.CARRIER)
  val battleship: Ship = Ship(Direction.HORIZONTAL, Point('E',3, PointType.OCCUPIED), ShipT.BATTLESHIP)
  val destroyer: Ship = Ship(Direction.VERTICAL, Point('C',9, PointType.OCCUPIED), ShipT.DESTROYER)
  val submarine: Ship = Ship(Direction.VERTICAL, Point('C',2, PointType.OCCUPIED), ShipT.SUBMARINE)
  val patrol_boat: Ship = Ship(Direction.VERTICAL, Point('E',5, PointType.OCCUPIED), ShipT.PATROL_BOAT)


  def pointA10 = Point('A', 10, PointType.OCCUPIED)


  test("endPoint test") {
//    Check horizontal
    assert(carrier.endPoint == Point('E',0, carrier.startPoint.pointType))
    assert(battleship.endPoint == Point('H',3, battleship.startPoint.pointType))
//    Check Vertical
    assert(destroyer.endPoint == Point('C', 11, destroyer.startPoint.pointType))
    assert(patrol_boat.endPoint == Point('E', 6, patrol_boat.startPoint.pointType))
  }

  test("getAllPoints test") {
    assert(carrier.getAllPoints.size == 5)
  }

  test("forAllPoints test") {
    var set: Set[Point] = Set.empty
    def f1(p: Point) = {
      set += Point(p.x, p.y + 10, p.pointType)
      set
    }
    carrier.forAllPoints(f1)
    assert(set.size == 5)
    assert(set.count(p => p.y == 10) == 5)
  }
}
