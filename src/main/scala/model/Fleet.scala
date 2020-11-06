package model

case class Fleet( var shipsSet: Set[Ship] ) {
  def addShip(ship: Ship): Set[Ship] = {
    shipsSet += ship
    shipsSet
  }

  def removeShip(ship: Ship): Set[Ship] = {
    shipsSet -= ship
    shipsSet
  }


}
