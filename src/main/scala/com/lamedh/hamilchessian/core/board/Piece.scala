package com.lamedh.hamilchessian.core.board

sealed abstract class Piece
case object Empty  extends Piece
case object Pawn   extends Piece
case object Rook   extends Piece
case object Knight extends Piece

object Piece {

  def getMovements(piece: Piece): Set[(Int, Int)] = piece match {
    case Empty => Set()
    case Rook => Set()
    case Pawn => Set((0, 3),
               (-2, 2),   (2, 2),
            (-3, 0),          (3, 0),
               (-2,-2),   (2,-2),
                     (0,-3)
    )
    case Knight => Set((-1,  2), (1,  2),
                 (-2,  1),             (2,  1),


                 (-2, -1),             (2, -1),
                       (-1, -2), (1, -2)
    )
  }
}
