package com.truecaller.truechess.infra.ai

import com.truecaller.ai.fringe.bestfirst.Evaluator
import com.truecaller.truechess.core.board.Location
import com.truecaller.truechess.core.board.Pawn

class PathEvaluator extends Evaluator[PathState] {

  override def evaluate(state: PathState): Double = {
    val dist = Location.distance(state.pieceLocation, state.originalLocation)
    val count = state.board.boardArray.count(_ == Pawn)
    count * 10 + dist
  }
}
