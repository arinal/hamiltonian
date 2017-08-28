package com.truecaller.truechess.core

trait BoardAlgebra[Board, Location, Piece] {
  def copyPiece(board: Board, from: Location, to: Location): Board
  def possibleDests(board: Board, pieceLocation: Location): (Piece, Set[Location])
  def initBoard(width: Int, height: Int, pieces: Set[(Piece, Location)]): Board

  def nextPossibleBoard(board: Board, pieceLocation: Location): Set[(Location, Board)] = {
    val (_, dests) = possibleDests(board, pieceLocation)
    dests.map(dest => dest -> copyPiece(board, pieceLocation, dest))
  }
}

trait BoardPathFinderAlg[Board, Location, Piece] {
  def findPath(board: Board, startLocation: Location,
               progress: (Long, Board) => Unit): Option[List[Board]]
}
