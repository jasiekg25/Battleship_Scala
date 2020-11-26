package model

import model.DataTypes.{Board, Coordinates, Point}

trait Player {
  val id: String
  val fleet: Fleet
  val myBoard: Board
  val alreadyShotCoordinates: List[Coordinates]



  def updatePoint(point: Point): Player
  def updateListOfPoints(value: Set[Point]): Player
  def takeShot(coordinates: Coordinates): (Boolean, Player)




  def addShipToMap(ship: Ship): Player = {
    if (MainBoard.isShipSuitToBoard(ship)) {
      updateListOfPoints(ship.getAllPoints)
    }
    else throw new Exception("Ship is not fit to MainBoard")

  }

  def addFleet(fleet: Fleet): Player = {
    val pointsToUpdate =
      for {
        ship <- fleet.shipsSet
        point <- ship.getAllPoints
      } yield point
    updateListOfPoints(pointsToUpdate)
  }

  def initWithWater(): Player = {

    val waterPointsSet =
      for {
        x <- MainBoard.startX to MainBoard.endX
        y <- MainBoard.startY to MainBoard.endY
        if (!myBoard.contains((x, y)))
      } yield (((x, y), PointType.WATER))
    updateListOfPoints(waterPointsSet.toSet)
  }

  def initWithFleet(fleet: Fleet): Player = {
    var updatedPlayer: Player = this.initWithWater()
    var updatedFleet: Fleet = fleet
    for(s <- fleet.shipsSet){
      updatedFleet = updatedPlayer.fleet.addShip(s)
      updatedPlayer = updatedPlayer.addShipToMap(s)
      updatedPlayer = RegularPlayer(updatedPlayer.id, updatedFleet, updatedPlayer.myBoard, updatedPlayer.alreadyShotCoordinates)
    }
    updatedPlayer
  }



  def isShipAllHit(ship: Ship): Boolean = { // TODO: show notification
    var hitPoints: Int = 0
    ship.forAllPoints(p => {
      if (myBoard(p._1) == PointType.HIT)
        hitPoints += 1
    })

    hitPoints == ship.typeShip.size
  }

  def updateShotsList(coordinates: Coordinates): Player

  def isShipSunken(ship: Ship): Boolean = {
    myBoard(ship.startPoint._1) == PointType.SINK
  }

  def isAllFleetSunken: Boolean = {
    var fleetSize = fleet.shipsSet.size
    for (
      ship <- fleet.shipsSet
      if (isShipSunken(ship))
    ) fleetSize -= 1

    fleetSize == 0
  }

  def sinkShip(ship: Ship): Player = {
    val pointsToUpdate = ship.getAllPoints.map(p => (p._1, PointType.SINK))
    updateListOfPoints(pointsToUpdate)
  }

  def checkFleet(coordinates: Coordinates): Player = { // TODO: show notification
    val shipToCheck = fleet.shipsSet.filter(s => s.getAllPoints.map(p => p._1).contains(coordinates)).head // always one ship
    if(isShipAllHit(shipToCheck)) sinkShip(shipToCheck)
    else this
  }
}
