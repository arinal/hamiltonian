package com.truecaller.truechess.app.konsole

import com.truecaller.truechess.core.board.Piece

trait Messages {

  val welcomeMsg = """
     |-~HamilTruenian~-
     |Simple program to solve hamiltonian path of pawn movement.
     |Board below 42 (7x6) blocks will be solved instantly.
     |Board with 42 or more blocks will take minutes.
     |Board of size 100 (10x10) takes 10-20 minutes.
     |Don't mess with sizes, it's easy to end up with solution requiring thousands years of computation!
     |""".stripMargin

  val promptWidthMsg  = (d: Int) => s"Width of the board?  ($d) "
  val promptHeightMsg = (d: Int) => s"Height of the board? ($d) "
  val promptPieceMsg  = (p: Piece) => s"What to put? (Knight (K), Pawn (P), Rook (R)) ($p) "
  val promptXLocationMsg = (d: Int) => s"Where to put the piece's x-location? ($d) "
  val promptYLocationMsg = (d: Int) => s"Where to put the piece's y-location? ($d) "

  val invalidInputMsg = "Please make sure that piece location and board size are valid"
  val processingMsg   = "Start processing.."
  val pathNotFoundMsg = "Path cannot be found"
  val pathFoundMsg    = "Yeay! we found a path :)"
}
