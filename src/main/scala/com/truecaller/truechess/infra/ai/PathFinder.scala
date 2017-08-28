package com.truecaller.truechess.infra.ai

import scala.collection.JavaConverters._

import com.truecaller.ai.Searcher
import com.truecaller.truechess.core.board._
import com.truecaller.truechess.core.path.BoardPathFinder

class PathFinder(searcher: Searcher[Board, PathState], boardService: BoardService)
    extends BoardPathFinder {

  override def findPath(board: Board, startLocation: Location,
                        progress: (Long, Board) => Unit): Option[List[Board]] = {
    val start = new PathState(boardService, board, startLocation, startLocation)
    val end = destBoard(board.width, board.height)

    def callback(iter: java.lang.Long, state: PathState): Unit = {
      val every = if (iter <= 300) 1
                  else if (iter <= 1000) 100
                  else if (iter <= 10000) 1000
                  else if (iter <= 100000) 10000
                  else 100000
      if ((iter % every) == 0) progress(iter, state.board)
    }

    val path = searcher.search(start, end, callback)
    if (path.equals(start)) None
    else Some(path.getPath.asScala.map(_.board).toList)
  }

  private def destBoard(width: Int, height: Int) = {
    val coords = for {
      h <- 0 to height
      w <- 0 to width
    } yield (h, w)
    val endSet = coords.foldLeft(Set[(Piece, (Int, Int))]()) {
      case (currSet, (x, y)) => currSet + (Pawn -> (x, y))
    }
    boardService.board(width, height, endSet)
  }
}
