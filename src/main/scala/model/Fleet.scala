package model

case class Fleet( shipsSet: Set[Ship] ) {
  def addShip(ship: Ship): Set[Ship] = shipsSet + ship
  def removeShip(ship: Ship): Set[Ship] = shipsSet - ship


}
