import logic.Game
import model.{MainBoard, Player, RegularPlayer, ShipType}

object Main {


  def createPlayer(playerNumber: Int): Player = {
    println(s"Give name for player$playerNumber:")
    val input: String = scala.io.StdIn.readLine()
    RegularPlayer(input).initWithWater()
  }

  def placePlayerFleet(player: Player, game: Game): Game = {
    def askForDirectionAndCoordinates(shipT: ShipType): Array[String] = {
      println(
        s"Input ${shipT.name}\'s direction and start coordinates\n" +
          "Required format example: \t H,A,0 or V,A,0 or Horizontal,A,0 or Vertical,A,0\n" +
          s"Remember that you need ${shipT.size} free points for that ship")
      val input: Array[String] = scala.io.StdIn.readLine().split(",")
      if (checkInput("placeShip", input))
        input.map(i => i.toUpperCase)
      else {
        println("Wrong input, try again correct one!\n")
        askForDirectionAndCoordinates(shipT)
      }
    }

    val enemyPlayer = game.secondPlayer(player)
    println(s"\nTime to set ${player.id}\'s  fleet")
    var updatedGame = game
    for(shipType <- MainBoard.shipsTypes){
      val input: Array[String] = askForDirectionAndCoordinates(shipType)
      updatedGame = updatedGame.setShip(updatedGame.getPlayer(player), shipType.name, input(0), input(1).charAt(0), input(2).toInt)
    }
//    val updatedPlayer = updatedGame.getPlayer(player).initailicePlayerBoardWithFleet(player.fleet)
    updatedGame
  }

  def play(player: Player, game: Game): Unit = {
    def askForCoordinates(player: Player): Array[String] = {
      println(
        "Input coordinates \n" +
          "Required format example: \tA,0\n")
      val input: Array[String] = scala.io.StdIn.readLine().split(",")
      if (checkInput("shotCoordinates", input))
        input.map(i => i.toUpperCase)
      else {
        println("Wrong coordinates, try again with correct one!")
        askForCoordinates(player)
      }
    }

    val nextPlayer = game.secondPlayer(player)
    val input: Array[String] = askForCoordinates(player)
    val (isWinning, updatedGame) = game.guessPosition(player, input(0).charAt(0), input(1).toInt)
    if(isWinning) println(s"Game Over! \n ${player.id} won!")
    else play(nextPlayer, updatedGame)

  }

  def checkInput(inputType: String, input: Array[String]): Boolean = {
    inputType.toLowerCase() match {
      case "shotcoordinates" => {
        if (input.length == 2) {
          val (x, y) = (input(0).toUpperCase.charAt(0), input(1).toInt)
          MainBoard.pointInBoard(x, y)
        }
        else false

      }
      case "placeship" =>
        if (input.length == 3) {
          input(0).toUpperCase match {
            case "H" | "V" | "HORIZONTAL" | "VERTICAL" => {
              val (x, y) = (input(1).toUpperCase.charAt(0), input(2).toInt)
              MainBoard.pointInBoard(x, y)
            }
            case _ => false
          }

        }
        else false

    }
  }

  def main(args: Array[String]) = {
    val player1: Player = createPlayer(1)
    val player2: Player = createPlayer(2)
    val game: Game = Game(player1, player2)
    val updatedGame: Game = placePlayerFleet(player1, game)
    val gameReadyToPlay = placePlayerFleet(player2, updatedGame)
    println(s"${player1.id} is starting")
    play(player1, gameReadyToPlay)
  }
}
