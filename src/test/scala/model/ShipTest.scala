package model

import model.DataTypes.Point
import org.scalatest.FunSuite

class ShipTest extends FunSuite {

  val carrier: Ship = Ship(Direction.HORIZONTAL, (('A', 0), PointType.OCCUPIED), ShipT.CARRIER)
  val battleship: Ship = Ship(Direction.HORIZONTAL, (('E', 3), PointType.OCCUPIED), ShipT.BATTLESHIP)
  val destroyer: Ship = Ship(Direction.VERTICAL, (('C', 9), PointType.OCCUPIED), ShipT.DESTROYER)
  val submarine: Ship = Ship(Direction.VERTICAL, (('C', 2), PointType.OCCUPIED), ShipT.SUBMARINE)
  val patrol_boat: Ship = Ship(Direction.VERTICAL, (('E', 5), PointType.OCCUPIED), ShipT.PATROL_BOAT)


  def pointA10: Point = (('A', 10), PointType.OCCUPIED)


  test("endPoint test") {
    //    Check horizontal
    assert(carrier.endPoint == (('E', 0), carrier.startPoint._2))
    assert(battleship.endPoint == (('H', 3), battleship.startPoint._2))
    //    Check Vertical
    assert(destroyer.endPoint == (('C', 11), destroyer.startPoint._2))
    assert(patrol_boat.endPoint == (('E', 6), patrol_boat.startPoint._2))
  }

  test("getAllPoints test") {
    assert(carrier.getAllPoints.size == 5)
  }

  //  test("forAllPoints test") {
  //    var set: Set[Point] = Set.empty
  //
  //    def f1(p: Point): Set[Point] = {
  //      set((p._1._1, p._1._2 + 10), p._2)
  //      set
  //    }
  //
  //    carrier.forAllPoints(f1)
  //    assert(set.size == 5)
  //    assert(set.count(p => p._1._2 == 10) == 5)
  //  }
}
