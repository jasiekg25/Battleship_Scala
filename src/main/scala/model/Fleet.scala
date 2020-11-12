package model

import model.DataTypes.Point
import org.scalacheck.Prop.Exception


case class Fleet(shipsSet: Set[Ship] = Set.empty ) {

  def addShip(ship: Ship): Fleet = {
    if( !isShipOverlappingWithAlreadyContainedShip(ship)){
      Fleet(shipsSet + ship)
    }
    else
      throw new IllegalArgumentException("You try to add ship which is overlapping with already contained ship")

  }

  //  def setShipAs(ship: Ship, shipCondition: PointType.Value): Fleet = {
  //    if(shipsSet.contains(ship)) Fleet(shipsSet.filter(s => s != ship) + ship.clone(sh))
  //    else throw new IllegalArgumentException(s"Fleet does not contain this ship: ${ship.typeShip.name}")
  //  }

  def removeShip(ship: Ship): Fleet = {
    Fleet(shipsSet - ship)

  }
  def removeAll: Fleet = {
    Fleet(Set.empty)
  }

  def isShipOverlappingWithAlreadyContainedShip(ship: Ship): Boolean ={
    var isOverlaping: Boolean = false
    for (
      containedShip: Ship <- shipsSet;
      containedPoint: Point <- containedShip.getAllPoints;
      checkedPoint: Point <- ship.getAllPoints;
      if (containedPoint._1._1 == checkedPoint._1._1 &&
        containedPoint._1._2 == checkedPoint._1._2)
    ) isOverlaping = true
    isOverlaping

  }

}
