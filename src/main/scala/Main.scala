import game.Game
import model.{MainBoard, Player}

object Main {

  val player0: Player = Player("Player0")
  val player1: Player = Player("Player1")
  val game: Game = Game(player0, player1)

  def placePlayersFleet(player: Player) = {
    val enemyPlayer = if (player == player0) player1 else player0
    println(s"\nTime to set ${player.id}\'s  fleet")
    for(shipType <- MainBoard.shipsTypes){
      println(
        s"Input ${shipType.name}\'s direction and start coordinates\n" +
        "Required format example: \t H,A,0 or V,A,0 or Horizontal,A,0 or Vertical,A,0\n" +
        s"Remember that you need ${shipType.size} free points for that ship")
      val input: Array[String] = scala.io.StdIn.readLine().split(",")
      game.setShip(player, shipType.name, input(0), input(1).charAt(0), input(2).toInt)
    }
    player.myBoard.initailicePlayerBoardWithFleet(player.fleet)
    player.enemyBoard.initailicePlayerBoardWithFleet(player.fleet)
  }

  def play(player: Player): Unit = {
    val nextPlayer = if (player == player0) player1 else player0
    println(s"${player.id}'s turn")
    println(
      "Input coordinates \n" +
      "Required format example: \tA,0\n")
    val input: Array[String] = scala.io.StdIn.readLine().split(",")
    game.guessPosition(player, input(0).charAt(0), input(1).toInt)
    if(game.checkWin(player)) println(s"Game Over! \n ${player.id} won!")
    else play(nextPlayer)

  }

  def main(args: Array[String]) = {
    placePlayersFleet(player0)
    placePlayersFleet(player1)
    println(s"${player0.id} is starting")
    play(player0)
  }
}
