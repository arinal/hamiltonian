package com.lamedh.hamilchessian.core.board

import com.lamedh.hamilchessian.core.BoardAlgebra

class BoardService extends BoardAlgebra[Board, Location, Piece] {

  def board(width: Int, height: Int, pieces: Set[(Piece, (Int, Int))]): Board = {
    val filteredLocs = pieces.flatMap {
      case (p, (x, y)) => Location(x, y, width, height).map(loc => p -> loc)
    }
    initBoard(width, height, filteredLocs)
  }

  override def copyPiece(board: Board, from: Location, to: Location): Board =
    board.clone.copy(from, to)

  override def initBoard(width: Int, height: Int, pieces: Set[(Piece, Location)]): Board = {
    val board = Board(width, height)
    pieces.foreach { case (p, loc) => board.put(p, loc) }
    board
  }

  override def possibleDests(board: Board, pieceLocation: Location): (Piece, Set[Location]) = {
    val piece = board.pieceAt(pieceLocation)
    val dests = Piece.getMovements(piece)
      .flatMap(m => Location.add(pieceLocation, m, board))
    (piece, dests)
  }
}
