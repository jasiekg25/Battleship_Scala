package model

import scala.collection.mutable

case class PlayerBoard(
                        map: mutable.Map[Tuple2[Char, Int], PointType.Value] = mutable.Map.empty,
                        mainBoard: MainBoard.type = MainBoard
                      ) {

  def setPoint(point: Point) = {
    map(point.coordinates) = point.pointType
    map
  }

  def addShipToMap(ship: Ship) = {
    if (MainBoard.isShipSuitToBoard(ship)) {
      ship.forAllPoints(setPoint)
      map
    }
    else throw new Exception("Ship is not fit to MainBoard")

  }

  def addFleet(fleet: Fleet) = {
    for (ship <- fleet.shipsSet)
      addShipToMap(ship)
    map
  }

  def initailicePlayerBoardWithFleet(fleet: Fleet) = {
    addFleet(fleet)
    for {
      x <- MainBoard.startX to MainBoard.endX
      y <- MainBoard.startY to MainBoard.endY
      if (!map.contains((x, y)))


    } yield setPoint(Point(x, y, PointType.WATER))
    map
  }

  def shoot(coordinates: (Char, Int)) = { // TODO: show notification
    if (MainBoard.pointInBoard(coordinates))
      map(coordinates) match {
        case PointType.WATER => {
          map(coordinates) = PointType.SHOT
          map

        }
        case PointType.OCCUPIED => {
          map(coordinates) = PointType.HIT
          map
        }
        case _ =>
          map
      }
    map

  }

  def isShipAllHit(ship: Ship): Boolean = { // TODO: show notification
    var hitPoints: Int = 0
    ship.forAllPoints(p => {
      if (map(p.coordinates) == PointType.HIT)
        hitPoints += 1
    })

    hitPoints == ship.typeShip.size
  }

  def isShipSunken(ship: Ship): Boolean = {
    map(ship.startPoint.coordinates) == PointType.SINK
  }

  def isAllFleetSunken(fleet: Fleet): Boolean = {
    var fleetSize = fleet.shipsSet.size
    for(
      ship <- fleet.shipsSet
      if (isShipSunken(ship))
    ) fleetSize -= 1

    fleetSize == 0
  }
  
  def sinkShip(ship: Ship) = {
    ship.forAllPoints(p => map(p.coordinates) = PointType.SINK)
    map
  }

  def checkFleet(fleet: Fleet) = { // TODO: show notification
    for {
      ship <- fleet.shipsSet
      if(isShipAllHit(ship))
    } yield sinkShip(ship)
    map
  }

}

