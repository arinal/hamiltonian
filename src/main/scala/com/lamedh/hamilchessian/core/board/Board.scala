package com.lamedh.hamilchessian.core.board

case class Board private (width: Int, height: Int, boardArray: Array[Piece]) {

  def size: Int = width * height
  def pieceAt(location: Location): Piece = boardArray(indexAt(location))
  def emptyAt(location: Location): Boolean = pieceAt(location) == Empty
  def put(piece: Piece, location: Location): Unit =
    boardArray(indexAt(location)) = piece

  def copy(from: Location, to: Location): Board = {
    val fromIdx = indexAt(from)
    val toIdx = indexAt(to)
    boardArray(toIdx) = boardArray(fromIdx)
    this
  }

  override def clone = Board(width, height, boardArray.clone)
  override def hashCode: Int = boardArray.toSeq.hashCode

  override def equals(that: Any): Boolean = that match {
    case Board(w, h, arr) => w == width && h == height && arr.sameElements(boardArray)
    case _ => false
  }

  private def indexAt(location: Location) = location.y * width + location.x
}

object Board {

  def apply(width: Int, height: Int): Board = {
    val array = Array.ofDim[Piece](width * height)
    array.indices.foreach(array(_) = Empty)
    Board(width, height, array)
  }
}
