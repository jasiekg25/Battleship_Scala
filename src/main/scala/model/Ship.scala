package model

case class Ship(direction: Direction.Value, startPoint: Point, typeShip: ShipType) {
  // the startPoint is always closer to  the point (0,0) than an endPoint
  // the board is a positive quarter


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
