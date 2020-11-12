package model

object PointType extends Enumeration {
  val WATER, HIT, SHOT, OCCUPIED, SINK = Value
}
object DataTypes {
  type Coordinates = (Char, Int)
  type Point = (Coordinates, PointType.Value)
  type Board = Map[Coordinates, PointType.Value]

}


case class ShipType(name: String, size: Int)
object ShipT {
  val CARRIER = ShipType("CARRIER", 5)
  val BATTLESHIP = ShipType("BATTLESHIP", 4)
  val DESTROYER = ShipType("DESTROYER", 3)
  val SUBMARINE = ShipType("SUBMARINE", 3)
  val PATROL_BOAT = ShipType("PATROL_BOAT", 2)

  def shipType(name: String) = name.toUpperCase() match {
    case "CARRIER" => ShipT.CARRIER
    case "BATTLESHIP" => ShipT.BATTLESHIP
    case "DESTROYER" => ShipT.DESTROYER
    case "SUBMARINE" => ShipT.SUBMARINE
    case "PATROL_BOAT" => ShipT.PATROL_BOAT
  }
}

object Direction extends Enumeration {
  val HORIZONTAL, VERTICAL = Value

  def fromString(direction: String) = direction.toUpperCase match {
    case "HORIZONTAL" => Direction.HORIZONTAL
    case "VERTICAL" => Direction.VERTICAL
    case "V" => Direction.VERTICAL
    case "H" => Direction.HORIZONTAL
  }
}
