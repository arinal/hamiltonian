package com.lamedh.hamilchessian.app.konsole

import com.lamedh.hamilchessian.core.board.Piece

trait Messages {

  val welcomeMsg = """
     |-~Hamiltonian Chess Pathfinder~-
     |Simple program to solve hamiltonian path of chess movement.
     |Cross your fingers and hopefully a linear time complexity is matched.
     |Don't mess with sizes, it's easy to end up with solution requiring thousands years of computation and mess your precious lifetime :)
     |""".stripMargin

  val promptWidthMsg  = (d: Int) => s"Width of the board?  ($d) "
  val promptHeightMsg = (d: Int) => s"Height of the board? ($d) "
  val promptPieceMsg  = (p: Piece) => s"What to put? (Knight (K), Pawn (P), Rook (R)) ($p) "
  val promptXLocationMsg = (d: Int) => s"Where to put piece's x location? ($d) "
  val promptYLocationMsg = (d: Int) => s"Where to put piece's y location? ($d) "

  val invalidInputMsg = "Please make sure that piece location and board size are valid"
  val processingMsg   = "Start processing.."
  val pathNotFoundMsg = "Path cannot be found"
  val pathFoundMsg    = "Yeay! we've found a path :)"
}
