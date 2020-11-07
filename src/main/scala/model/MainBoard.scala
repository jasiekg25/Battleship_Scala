package model

case class Board(name: String, size: Int)
object BoardType {
  val LARGE: Board = Board("LARGE", 30)
  val MEDIUM: Board = Board("MEDIUM", 20)
  val SMALL: Board = Board("SMALL", 10)
}


object MainBoard { // x in (A ... J), y in (0, 9)
  val shipsTypes: List[ShipType] = List(ShipT.CARRIER, ShipT.BATTLESHIP, ShipT.DESTROYER, ShipT.SUBMARINE, ShipT.PATROL_BOAT)
  val boardType: Board = BoardType.SMALL // todo: changeable size


  def startX = 'A'
  def endX: Char = (64 + boardType.size).toChar
  def startY = 0
  def endY: Int = boardType.size - 1


  def xInBoard(x: Char): Boolean = (x >= startX && x <= endX)
  def yInBoard(y: Int): Boolean = (y >= startY && y <= endY)
  def pointInBoard(coordinates: (Char, Int)): Boolean = xInBoard(coordinates._1) && yInBoard(coordinates._2)
  def isShipSuitToBoard(ship: Ship): Boolean =
    pointInBoard(ship.startPoint.coordinates) && pointInBoard(ship.endPoint.coordinates)


  def next_x(x: Char): Option[Char] = {
    if (xInBoard(x) && xInBoard((x + 1).toChar)) Some((x + 1).toChar)
    else None
  }

  def previous_x(x: Char): Option[Char] = {
    if (xInBoard(x) && xInBoard((x - 1).toChar)) Some((x - 1).toChar)
    else None
  }

  def next_y(y: Int): Option[Int] = {
    if(yInBoard(y) && yInBoard(y + 1)) Some(y+1)
    else None
  }

  def previous_y(y:Int): Option[Int] = {
    if(yInBoard(y) && yInBoard(y - 1)) Some(y-1)
    else None
  }

}
