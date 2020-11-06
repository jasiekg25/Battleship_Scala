package model

trait Player {
  def fleet: Fleet
  def board = Board
  def enemyBoard: Map[Tuple2[Char, Int], PointType.Value]
}
