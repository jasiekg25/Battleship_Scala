package model

case class Player (
                    id: String,
                    fleet: Fleet = Fleet(),
                    myBoard: PlayerBoard = PlayerBoard(),
                    enemyBoard: PlayerBoard = PlayerBoard()
                  ) {


}
