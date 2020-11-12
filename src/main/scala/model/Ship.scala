package model

import model.DataTypes.Point

case class Ship( direction: Direction.Value, startPoint: Point, typeShip: ShipType) {
  // the startPoint is always closer to  the point (0,0) than an endPoint
  // the board is a positive quarter

  def endPoint: Point = {
    direction match {
      case Direction.HORIZONTAL =>
        (
          ((startPoint._1._1 + typeShip.size - 1).toChar, startPoint._1._2), // coordinates
          startPoint._2 // pointType
        )
      case Direction.VERTICAL =>
        (
          (startPoint._1._1, startPoint._1._2 + typeShip.size - 1), // coordinates
          startPoint._2 // pointType
        )
    }
  }

  def getAllPoints = {
    var pointsSet: Set[Point] = Set.empty
    forAllPoints(p => pointsSet += p)
    pointsSet
  }

  def forAllPoints(f: Point => ()): Unit= {
    def nextPoint(point: Point): Point = {
      direction match {
        case Direction.HORIZONTAL =>
          (
            ((point._1._1 + 1).toChar, point._1._2),
            point._2
          )
        case Direction.VERTICAL =>
          (
            (point._1._1, point._1._2 + 1),
            point._2
          )
      }
    }
    var p: Point = startPoint
    while (p != nextPoint(endPoint)) {
      f(p)
      p = nextPoint(p)
    }
  }


}
