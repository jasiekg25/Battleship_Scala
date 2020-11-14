import logic.{Game, InputParser}
import model.{ArtificialEnemy, Direction, Fleet, Player, PointType, RegularPlayer, Ship, ShipT}

object Main {
  def createRegularPlayer(playerNumber: Int): Player = {
    println(s"Give name for player$playerNumber:")
    val input: String = scala.io.StdIn.readLine()
    RegularPlayer(input).initWithWater()
  }

  def createArtificialEnemy(computerID: String): Player = {
    ArtificialEnemy(computerID, alreadyShotCoordinates = List.empty).initWithWater()
  }

  def startGame(): Unit = {
    println("If you want to play with computer enter 1, if you want play with someone else enter 2")
    val gameType: Int = scala.io.StdIn.readLine().charAt(0).asDigit

    val player1: Player = createRegularPlayer(1)
    val player2: Player =
      if (gameType == 2) createRegularPlayer(2)
      else createArtificialEnemy("Computer")

    val game: Game = Game(player1, player2)
    val updatedGame: Game = game.placePlayerFleet(player1)
    val gameReadyToPlay = updatedGame.placePlayerFleet(player2)
    println(s"${player1.id} is starting")
    gameReadyToPlay.play(gameReadyToPlay.getPlayer(player1))
  }

  def main(args: Array[String]) = {
    //    //    tests
    //        val carrier: Ship = Ship(Direction.HORIZONTAL, (('A', 0), PointType.OCCUPIED), ShipT.CARRIER)
    //        val battleship: Ship = Ship(Direction.HORIZONTAL, (('B', 1), PointType.OCCUPIED), ShipT.BATTLESHIP)
    //        val destroyer: Ship = Ship(Direction.VERTICAL, (('C', 3), PointType.OCCUPIED), ShipT.DESTROYER)
    //        val submarine: Ship = Ship(Direction.VERTICAL, (('D', 2), PointType.OCCUPIED), ShipT.SUBMARINE)
    //        val patrol_boat: Ship = Ship(Direction.VERTICAL, (('E', 5), PointType.OCCUPIED), ShipT.PATROL_BOAT)
    //        var shipsSet: Set[Ship] = Set(carrier, battleship, destroyer, submarine, patrol_boat)
    //        val fleet: Fleet = Fleet(shipsSet)
    //
    val player1: Player = createArtificialEnemy("Computer 1")
    val player2: Player = createArtificialEnemy("Computer 2")
    val game: Game = Game(player1, player2)
    val updatedGame: Game = game.placePlayerFleet(player1)
    val gameReadyToPlay = updatedGame.placePlayerFleet(player2)
    println(s"${player1.id} is starting")
    gameReadyToPlay.play(gameReadyToPlay.getPlayer(player1))

    //    startGame()
  }
}
