package model

import model.DataTypes.{Board, Coordinates, Point}

case class RegularPlayer(
                          id: String,
                          fleet: Fleet = Fleet(),
                          myBoard: Board = Map.empty,
                          alreadyShotCoordinates: List[Coordinates] = List.empty

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

  def takeShot(coordinates: Coordinates): (Boolean, Player) = {
    if (MainBoard.pointInBoard(coordinates))
      myBoard(coordinates) match {
        case PointType.WATER =>
        {
          val newBoard = myBoard.updated(coordinates, PointType.SHOT)
          (false, RegularPlayer(id, fleet, newBoard))
        }

        case PointType.OCCUPIED =>
        {
          val newBoard = myBoard.updated(coordinates, PointType.HIT)
          (true, RegularPlayer(id, fleet, newBoard).checkFleet(coordinates))
        }

        case _ =>
          (false, this)
      }

    else (false, this) // never used
  }

  override def updateShotsList(coordinates: Coordinates): Player ={
    RegularPlayer(id, fleet, myBoard, coordinates :: alreadyShotCoordinates)
  }

}


