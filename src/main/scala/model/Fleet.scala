package model

case class Fleet( var shipsSet: Set[Ship] ) {
  def addShip(ship: Ship): Set[Ship] = {
    if( !isShipOverlappingWithAlreadyContainedShip(ship)){
      shipsSet += ship
      shipsSet
    }
    else
      throw new Exception("You try to add ship which is overlapping with already contained ship")

  }

  def removeShip(ship: Ship): Set[Ship] = {
    shipsSet -= ship
    shipsSet
  }
  def removeAll: Unit = {
    shipsSet = Set.empty
  }

  def isShipOverlappingWithAlreadyContainedShip(ship: Ship): Boolean ={
    var isOverlaping: Boolean = false
    for (
      containedShip: Ship <- shipsSet;
      containedPoint: Point <- containedShip.getAllPoints;
      checkedPoint: Point <- ship.getAllPoints;
      if (containedPoint.x == checkedPoint.x && containedPoint.y == checkedPoint.y)
      ) isOverlaping = true
    isOverlaping

  }

}
