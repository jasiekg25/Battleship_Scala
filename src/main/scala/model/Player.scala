package model

import model.DataTypes.{Board, Coordinates}

trait Player {
  val id: String
  val fleet: Fleet
  val myBoard: Board


  def addShipToMap(ship: Ship): Player
  def takeShot(coordinates: Coordinates): Player
  def checkFleet(coordinates: Coordinates): Player
  def isAllFleetSunken(): Boolean
  def initWithWater(): Player
  def initWithFleet(fleet: Fleet): Player
}
