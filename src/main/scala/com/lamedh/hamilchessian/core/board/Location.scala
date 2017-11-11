package com.lamedh.hamilchessian.core.board

case class Location private (x: Int, y: Int)

object Location {

  def apply(x: Int, y: Int, maxX: Int, maxY: Int): Option[Location] =
    if (maxX < 0 || maxY < 0) None
    else if (x < 0 || x >= maxX || y < 0 || y >= maxY) None
    else Some(Location(x, y))

  def apply(x: Int, y: Int, board: Board): Option[Location] =
    apply(x, y, board.width, board.height)

  def mustEmptyAt(x: Int , y: Int, board: Board): Option[Location] =
    apply(x, y, board).filter(board.emptyAt)

  def add(origin: Location, movement: (Int, Int), board: Board): Option[Location] =
    mustEmptyAt(origin.x + movement._1, origin.y + movement._2, board)

  def distance(p1: Location, p2: Location): Double = {
    val dx = (p1.x - p2.x).toDouble
    val dy = (p1.y - p2.y).toDouble
    Math.sqrt(dx * dx + dy * dy)
  }
}
