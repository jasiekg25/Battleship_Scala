package game

import model.{Direction, MainBoard, Player, Point, PointType, Ship, ShipT, ShipType}

case class Game(player1: Player, player2: Player) {


  def setShip(player: Player, typeShip: String, direction: String, x: Char, y: Int) = {
    val ship: Ship =
      Ship(
        Direction.direction(direction),
        Point(x, y, PointType.OCCUPIED),
        ShipT.shipType(typeShip)
      )

    player.fleet.addShip(ship)
    player
  }
  def guessPosition(player: Player, x: Char, y: Int): Unit ={
    val enemyPlayer: Player = if(player == player1) player2 else player1
    player.enemyBoard.shoot(x,y)
    player.enemyBoard.checkFleet(enemyPlayer.fleet)
    enemyPlayer.myBoard.shoot(x,y)
    enemyPlayer.myBoard.checkFleet(enemyPlayer.fleet)
    checkWin(player)
  }

  def checkWin(player: Player): Boolean = {
    val enemyPlayer: Player = if(player == player1) player2 else player1
    enemyPlayer.myBoard.isAllFleetSunken(enemyPlayer.fleet)
  }
}

