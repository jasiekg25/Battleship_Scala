package model

case class Ship(direction: Direction.Value, startPoint: Point, typeShip: ShipType) {
  // the startPoint is always closer to  the point (0,0) than an endPoint
  // the board is a positive quarter

  def endPoint: Point = {
    direction match {
      case Direction.HORIZONTAL =>
        Point(
          (startPoint.x + typeShip.size - 1).toChar,
          startPoint.y,
          startPoint.pointType
        )
      case Direction.VERTICAL =>
        Point(
          startPoint.x,
          startPoint.y + typeShip.size - 1,
          startPoint.pointType
        )
    }
  }

  def getAllPoints = {
    var pointsSet: Set[Point] = Set.empty
    forAllPoints(p => pointsSet += p)
    pointsSet
  }

  def forAllPoints(f: Point => ()): Unit = {
    def nextPoint(point: Point): Point = {
      direction match {
        case Direction.HORIZONTAL =>
          Point(
            (point.x + 1).toChar,
            point.y,
            point.pointType
          )
        case Direction.VERTICAL =>
          Point(
            point.x,
            point.y + 1,
            point.pointType
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

object Direction extends Enumeration {
  val HORIZONTAL, VERTICAL = Value
}

case class ShipType(name: String, size: Int)

object ShipT {
  val CARRIER = ShipType("CARRIER", 5)
  val BATTLESHIP = ShipType("BATTLESHIP", 4)
  val DESTROYER = ShipType("DESTROYER", 3)
  val SUBMARINE = ShipType("SUBMARINE", 3)
  val PATROL_BOAT = ShipType("PATROL_BOAT", 2)

}
