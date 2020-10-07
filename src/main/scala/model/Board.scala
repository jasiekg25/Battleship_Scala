package model

case class BoardType(name: String, size: Int)

object BoardT {
  val LARGE = BoardType("LARGE", 30)
  val MEDIUM = BoardType("MEDIUM", 20)
  val SMALL = BoardType("SMALL", 10)
}

object Board { // x in (A ... J), y in (1, 10)
  val shipsTypes: List[ShipType] = List(ShipT.CARRIER, ShipT.BATTLESHIP, ShipT.DESTROYER, ShipT.SUBMARINE, ShipT.PATROL_BOAT)
  val boardType = BoardT.SMALL // todo: changeable size

  def startX = 'A'
  def endX = (64 + boardType.size) toChar
  def startY = 0
  def endY = boardType.size


  def xInBoard(x: Char): Boolean = (x > startX && x < endX)
  def yInBoard(y: Int): Boolean = (y > startY && y < endY)

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
