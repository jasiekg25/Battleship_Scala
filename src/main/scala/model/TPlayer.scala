package model

trait TPlayer {
  def fleet: Fleet
  def playerBoard: Map[Tuple2[Char, Int], PointType.Value]
  def enemyBoard: Map[Tuple2[Char, Int], PointType.Value]
}
