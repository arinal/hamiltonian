package com.truecaller.truechess.app.konsole

import com.truecaller.truechess.core.board._

import scala.collection.mutable

trait Renderer {

  def render(path: List[Board]): String = {
    val builder = new mutable.StringBuilder
    path.foreach(board => builder.append(render(board) + "\n"))
    builder.toString
  }

  def render(piece: Piece): String = piece match {
    case Empty  => "▐▍"
    case Pawn   => "♟ "
    case Rook   => "♜ "
    case Knight => "♞ "
  }

  def renderSimple(board: Board): String = {
    val builder = new mutable.StringBuilder
    board.boardArray.foreach(p => builder.append(render(p)))
    s"[${builder.toString}]"
  }

  def render(board: Board): String = {
    var i = 0
    val builder = new mutable.StringBuilder
    for (p <- board.boardArray) {
      val newLine = if ((i + 1) % board.width == 0) "\n\n" else ""
      builder.append(s" ${render(p)} $newLine")
      i += 1
    }
    builder.toString
  }

  def render(iter: Long, board: Board): String = s"${renderSimple(board)}: #$iter"
}
