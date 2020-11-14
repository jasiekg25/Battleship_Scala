package logic

import model.{ArtificialEnemy, Direction, MainBoard, Player, PointType, RegularPlayer, Ship, ShipT}


case class Game(player1: Player, player2: Player) {
  def getPlayer(player: Player) = if (player.id == player1.id) player1 else player2

  def secondPlayer(player: Player): Player = if (player.id == player1.id) player2 else player1

  def placePlayerFleet(player: Player): Game = {
    println(s"\nTime to set ${player.id}\'s  fleet")
    var updatedGame = this
    var updatedPlayer = player
    for (shipType <- MainBoard.shipsTypes) {
      val input: Array[Char] = player match {
        case RegularPlayer(_, _, _) => {
          InputParser.askForDirectionAndCoordinates(shipType)
        }
        case ArtificialEnemy(_, _, _, shotPoints) => {
          updatedPlayer = updatedGame.getPlayer(player)
          ArtificialEnemy(updatedPlayer.id, updatedPlayer.fleet, updatedPlayer.myBoard, shotPoints).askForInput("placeship", shipType = shipType)

        }
      }
      updatedGame = updatedGame.setShip(updatedGame.getPlayer(player), shipType.name, input(0).toString, input(1), input(2).asDigit)

    }
    updatedGame
  }

  def play(player: Player): Unit = {
    val input: Array[Char] = player match {
      case RegularPlayer(_, _, _) => {
        println(s"\n${player.id}\'s turn to shoot, give me coordinates")
        InputParser.askForCoordinates(player)
      }
      case ArtificialEnemy(_, _, _, shotPoints) => {
        ArtificialEnemy(player.id, player.fleet, player.myBoard, shotPoints).askForInput("coordinates")

      }
    }

    val (isHit, isWinning, updatedGame) = guessPosition(player, input(0), input(1).asDigit)
    if (isHit) println(s"HIT at position (${input(0)},${input(1)})") else println(s"MISS at position (${input(0)},${input(1)})")
    if (isWinning) println(s"Game Over! \n ${player.id} won!")
    else {
      val nextPlayer = updatedGame.secondPlayer(player)
      updatedGame.play(nextPlayer)
    }

  }

  def setShip(player: Player, typeShip: String, direction: String, x: Char, y: Int): Game = {
    val ship: Ship =
      Ship(
        Direction.fromString(direction),
        ((x, y), PointType.OCCUPIED),
        ShipT.shipType(typeShip)
      )
    val newFleet = player.fleet.addShip(ship)
    val newPlayer = player match {
      case RegularPlayer(id, _, mb) => RegularPlayer(id, newFleet, mb).addShipToMap(ship)
      case ArtificialEnemy(id, _, mb, sp) => ArtificialEnemy(id, newFleet, mb, sp)
    }
    Game(newPlayer, secondPlayer(newPlayer))
  }

  def guessPosition(player: Player, x: Char, y: Int): (Boolean, Boolean, Game) = {
    val enemyPlayer: Player = secondPlayer(player)
    val (isHit, updatedEnemy) = enemyPlayer.takeShot(x, y)
    if (isHit) (isHit, checkIfPlayerWon(player), Game(player, updatedEnemy))
    else (false, false, Game(player, updatedEnemy))

  }

  def checkIfPlayerWon(player: Player): Boolean = {
    val enemyPlayer: Player = secondPlayer(player)
    enemyPlayer.isAllFleetSunken
  }
}
