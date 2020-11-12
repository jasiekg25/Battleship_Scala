package model

import model.DataTypes.{Board, Coordinates}

trait Player {
  val id: String
  val fleet: Fleet
  val myBoard: Board

  def takeShot(coordinates: Coordinates): Player
  def checkFleet(coordinates: Coordinates): Player
  def isAllFleetSunken(): Boolean
  def initailicePlayerBoardWithFleet(fleet: Fleet): Player
}
