package com.truecaller.truechess.infra.ai

import com.truecaller.ai.fringe.bestfirst.Evaluator
import com.truecaller.truechess.core.board.Location
import com.truecaller.truechess.core.board.Empty

// work best for pawn
class DistanceEvaluator extends Evaluator[PathState] {
  override def evaluate(state: PathState): Double = {
    val dist = Location.distance(state.pieceLocation, state.originalLocation)
    state.pathLength * 10 + dist
  }
}

// work best for knight
class WarnsdorfEvaluator extends Evaluator[PathState] {
  override def evaluate(state: PathState): Double =
    state.pathLength*10 - state.childStatesCount
}
