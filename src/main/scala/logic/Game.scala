package logic

import model.{Direction, Player, PointType, RegularPlayer, Ship, ShipT}


case class Game(player1: Player, player2: Player) {
  def getPlayer(player: Player) = if (player.id == player1.id) player1 else player2
  def secondPlayer(player: Player): Player = if(player.id == player1.id) player2 else player1

  def setShip(player: Player, typeShip: String, direction: String, x: Char, y: Int): Game = {
    val ship: Ship =
      Ship(
        Direction.fromString(direction),
        ((x, y), PointType.OCCUPIED),
        ShipT.shipType(typeShip)
      )
    val newFleet = player.fleet.addShip(ship)
    val newPlayer = player match { // todo: when artificialEnemy done, it will need to be rewrite
      case RegularPlayer(id,_,mb) => RegularPlayer(id, newFleet, mb)
    }
    Game(newPlayer, secondPlayer(newPlayer))
  }

  def guessPosition(player: Player, x: Char, y: Int): (Boolean, Game)={
    val enemyPlayer: Player = secondPlayer(player)
    val updatedEnemy = enemyPlayer.takeShot(x,y)
    (checkIfPlayerWon(player), Game(player, updatedEnemy))
  }

  def checkIfPlayerWon(player: Player): Boolean = {
    val enemyPlayer: Player = secondPlayer(player)
    enemyPlayer.isAllFleetSunken()
  }
}
