package model

import model.DataTypes.{Board, Coordinates, Point}

case class RegularPlayer(
                          id: String,
                          fleet: Fleet = Fleet(),
                          myBoard: Board = Map.empty,
                          //                          enemyBoard: PlayerBoard = PlayerBoard()
                        ) extends Player {


  def updatePoint(point: Point): Player = {
    val newBoard = myBoard.updated(point._1, point._2)
    RegularPlayer(id, fleet, newBoard)
  }

  def updateListOfPoints(pointsList: Set[Point]): Player = {
    val updatedPointsMap = pointsList.map(p => p._1 -> p._2).toMap
    val notChangedPoints = myBoard.filter { case (k, v) => !updatedPointsMap.contains(k) }
    val newBoard = updatedPointsMap ++ notChangedPoints
    RegularPlayer(id,fleet,newBoard)
  }

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

  def takeShot(coordinates: (Char, Int)): Player = { // TODO: show notification
    if (MainBoard.pointInBoard(coordinates))
      myBoard(coordinates) match {
        case PointType.WATER =>
        {
          val newBoard = myBoard.updated(coordinates, PointType.SHOT)
          RegularPlayer(id, fleet, newBoard)
        }

        case PointType.OCCUPIED =>
        {
          val newBoard = myBoard.updated(coordinates, PointType.HIT)
          RegularPlayer(id, fleet, newBoard).checkFleet(coordinates)
        }

        case _ =>
          this
      }

    else this // never used
  }

  def isShipAllHit(ship: Ship): Boolean = { // TODO: show notification
    var hitPoints: Int = 0
    ship.forAllPoints(p => {
      if (myBoard(p._1) == PointType.HIT)
        hitPoints += 1
    })

    hitPoints == ship.typeShip.size
  }

  def isShipSunken(ship: Ship): Boolean = {
    myBoard(ship.startPoint._1) == PointType.SINK
  }

  def isAllFleetSunken(): Boolean = {
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


