package com.lamedh.hamilchessian.infra.ai

import com.lamedh.ai.fringe.bestfirst.Evaluator
import com.lamedh.hamilchessian.core.board.Location

class DistanceEvaluator extends Evaluator[PathState] {
  override def evaluate(state: PathState): Double = {
    val dist = Location.distance(state.pieceLocation, state.originalLocation)
    state.pathLength * 10 + dist
  }
}

class WarnsdorfEvaluator extends Evaluator[PathState] {
  override def evaluate(state: PathState): Double =
    state.pathLength*10 - state.childStatesCount
}
