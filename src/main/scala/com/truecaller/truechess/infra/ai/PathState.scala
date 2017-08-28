package com.truecaller.truechess.infra.ai

import com.truecaller.ai.AbstractState
import com.truecaller.truechess.core.board._

import scala.collection.JavaConverters._

case class PathState(parent: PathState,
                     service: BoardService,
                     board: Board,
                     pieceLocation: Location,
                     originalLocation: Location)
    extends AbstractState[Board, PathState](parent, board) {

  def this(service: BoardService, board: Board, location: Location, original: Location) =
    this(null, service, board, location, original)

  override def getThis: PathState = this
  override def getChildStates: java.lang.Iterable[PathState] = {
    service
      .nextPossibleBoard(board, pieceLocation)
      .map { case (loc, brd) =>
        PathState(this, service, brd, loc, originalLocation)
      }
      .asJava
  }

  override def hashCode: Int = board.hashCode
  override def equals(that: Any): Boolean = that match {
    case PathState(_, _, b, _, _) => board.equals(b)
    case _ => false
  }
}
