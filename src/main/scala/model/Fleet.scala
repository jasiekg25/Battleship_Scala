package model

import scala.collection.mutable

case class Fleet(var shipsSet: mutable.Set[Ship] ) {
  def addShip(ship: Ship): mutable.Set[Ship] = {
    if( !isShipOverlappingWithAlreadyContainedShip(ship)){
      shipsSet += ship
      shipsSet
    }
    else
      throw new Exception("You try to add ship which is overlapping with already contained ship")

  }

  def removeShip(ship: Ship): mutable.Set[Ship] = {
    shipsSet -= ship
    shipsSet
  }
  def removeAll: Unit = {
    shipsSet = mutable.Set.empty
  }

  def isShipOverlappingWithAlreadyContainedShip(ship: Ship): Boolean ={
    var isOverlaping: Boolean = false
    for (
      containedShip: Ship <- shipsSet;
      containedPoint: Point <- containedShip.getAllPoints;
      checkedPoint: Point <- ship.getAllPoints;
      if (containedPoint.coordinates._1 == checkedPoint.coordinates._1 &&
        containedPoint.coordinates._2 == checkedPoint.coordinates._2)
      ) isOverlaping = true
    isOverlaping

  }

}
