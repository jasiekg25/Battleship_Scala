package model

import model.DataTypes.Coordinates


case class BoardType(name: String, size: Int)
object BoardSizeType {
  val LARGE: BoardType = BoardType("LARGE", 30)
  val MEDIUM: BoardType = BoardType("MEDIUM", 20)
  val SMALL: BoardType = BoardType("SMALL", 10)
}


object MainBoard { // x in (A ... J), y in (0, 9)
  val shipsTypes: List[ShipType] = List(ShipT.CARRIER, ShipT.BATTLESHIP, ShipT.DESTROYER, ShipT.SUBMARINE, ShipT.PATROL_BOAT)
  val boardSizeType: BoardType = BoardSizeType.SMALL // todo: changeable size


  def startX = 'A'
  def endX: Char = (64 + boardSizeType.size).toChar
  def startY = 0
  def endY: Int = boardSizeType.size - 1


  def xInBoard(x: Char): Boolean = (x >= startX && x <= endX)
  def yInBoard(y: Int): Boolean = (y >= startY && y <= endY)
  def pointInBoard(coordinates: Coordinates): Boolean = xInBoard(coordinates._1) && yInBoard(coordinates._2)
  def isShipSuitToBoard(ship: Ship): Boolean =
    pointInBoard(ship.startPoint._1) && pointInBoard(ship.endPoint._1)


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
