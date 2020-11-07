package model

import scala.collection.mutable

case class PlayerBoard(
                        map: mutable.Map[Tuple2[Char, Int], PointType.Value]
                      ) {

  def setPoint(point: Point) = {
    map((point.x, point.y)) = point.pointType
    map
  }

  def addShipToMap(ship: Ship): mutable.Map[Tuple2[Char, Int], PointType.Value] = {
    ship.forAllPoints(setPoint)
    map
  }

  def addFleet(fleet: Fleet) = {
    for (ship <- fleet.shipsSet)
      addShipToMap(ship)
    map
  }
}

