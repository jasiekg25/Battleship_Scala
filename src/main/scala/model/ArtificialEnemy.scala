package model

import model.DataTypes.{Board, Coordinates, Point}

import scala.util.Random


case class ArtificialEnemy(
                            id: String,
                            fleet: Fleet = Fleet(),
                            myBoard: Board = Map.empty,
                            alreadyShotCoordinates: List[Coordinates] = List.empty
                          ) extends Player {

  def updatePoint(point: Point): Player = {
    val newBoard = myBoard.updated(point._1, point._2)
    ArtificialEnemy(id, fleet, newBoard, alreadyShotCoordinates)
  }

  def updateListOfPoints(pointsList: Set[Point]): Player = {
    val updatedPointsMap = pointsList.map(p => p._1 -> p._2).toMap
    val notChangedPoints = myBoard.filter { case (k, v) => !updatedPointsMap.contains(k) }
    val newBoard = updatedPointsMap ++ notChangedPoints
    ArtificialEnemy(id,fleet,newBoard, alreadyShotCoordinates)
  }

  def takeShot(coordinates: Coordinates): (Boolean, Player) = {
    if (MainBoard.pointInBoard(coordinates))
      myBoard(coordinates) match {
        case PointType.WATER =>
        {
          val newBoard = myBoard.updated(coordinates, PointType.SHOT)
          (false, ArtificialEnemy(id, fleet, newBoard, alreadyShotCoordinates))
        }

        case PointType.OCCUPIED =>
        {
          val newBoard = myBoard.updated(coordinates, PointType.HIT)
          (true, ArtificialEnemy(id, fleet, newBoard, alreadyShotCoordinates).checkFleet(coordinates))
        }

        case _ =>
          (false, this)
      }

    else (false, this) // never used
  }




  def askForInput(inputType: String, shipType: ShipType = MainBoard.shipsTypes.head): Array[Char] = {
    def intoCharArray(inputType: String, direction: Direction.Value, coordinates: Coordinates): Array[Char] = {
      inputType.toLowerCase match {
        case "placeship" =>
          Array(Direction.toChar(direction), coordinates._1, (coordinates._2 + 48).toChar)
        case "coordinates" =>
          Array(coordinates._1, (coordinates._2 + 48).toChar)

      }
    }
    def giveCoordinates(): Coordinates = {
      var coordinates = ((Random.nextInt(10) + 65).toChar, Random.nextInt(10))
      while (!MainBoard.pointInBoard(coordinates) || alreadyShotCoordinates.contains(coordinates)) {
        coordinates = ((Random.nextInt(10) + 65).toChar, Random.nextInt(10))
      }
      coordinates
    }

    def giveDirection(): Direction.Value = {
      def randomVal = Random.nextInt(1)
      if (randomVal == 0) Direction.HORIZONTAL else Direction.VERTICAL
    }

    def placeShip(shipT: ShipType): (Direction.Value, Coordinates) = {
      def direction() = giveDirection()
      def startPoint() = (giveCoordinates(), PointType.OCCUPIED)
      var ship = Ship(direction(), startPoint(), shipT)
      def newShip = Ship(direction(), startPoint(), shipT)
      while (fleet.isShipOverlappingWithAlreadyContainedShip(ship) || !MainBoard.isShipSuitToBoard(ship)) {
        ship = newShip
      }
      (ship.direction, ship.startPoint._1)
    }


    inputType.toLowerCase match {
      case "coordinates" => {
        val coordinates = giveCoordinates()
        intoCharArray("coordinates", Direction.HORIZONTAL, coordinates)
      }
      case "placeship" => {
        val (direction, coordinates) = placeShip(shipType)
        intoCharArray("placeship", direction, coordinates)
      }
    }
  }

   override def updateShotsList(coordinates: Coordinates): Player ={
    ArtificialEnemy(id, fleet, myBoard, coordinates :: alreadyShotCoordinates)
  }
}
