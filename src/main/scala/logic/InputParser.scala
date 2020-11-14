package logic

import model.{MainBoard, Player, ShipType}

object InputParser {

  @scala.annotation.tailrec
  def askForDirectionAndCoordinates(shipT: ShipType): Array[Char] = {
    println(
      s"Input ${shipT.name}\'s direction and start coordinates\n" +
        "Required format example: \t HAO or VA0 or ha0 (case insensitivity)\n" +
        s"Remember that you need ${shipT.size} free points for that ship")
    val input: Array[Char] = scala.io.StdIn.readLine().toUpperCase.toCharArray
    if (checkInput("placeShip", input))
      input
    else {
      println("Wrong input, try again correct one!\n")
      askForDirectionAndCoordinates(shipT)
    }
  }

  @scala.annotation.tailrec
  def askForCoordinates(player: Player): Array[Char] = {
    println(
      "Input coordinates \n" +
        "Required format example: \tA0 or a0")
    val input: Array[Char] = scala.io.StdIn.readLine().toUpperCase.toCharArray
    if (checkInput("shotCoordinates", input))
      input
    else {
      println("Wrong coordinates, try again with correct one!")
      askForCoordinates(player)
    }
  }

  def checkInput(inputType: String, input: Array[Char]): Boolean = {
    inputType.toLowerCase() match {
      case "shotcoordinates" => {
        if (input.length == 2) {
          val (x, y) = (input(0), input(1).asDigit)
          MainBoard.pointInBoard(x, y)
        }
        else false

      }
      case "placeship" =>
        if (input.length == 3) {
          input(0) match {
            case 'H' | 'V' => {
              val (x, y) = (input(1), input(2).asDigit)
              MainBoard.pointInBoard(x, y)
            }
            case _ => false
          }

        }
        else false
    }
  }


}
