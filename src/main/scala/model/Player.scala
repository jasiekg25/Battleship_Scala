package model

class Player extends TPlayer {
  override def fleet: Fleet = ???

  override def playerBoard: Map[(Char, Int), PointType.Value] = ???

  override def enemyBoard: Map[(Char, Int), PointType.Value] = ???
}
