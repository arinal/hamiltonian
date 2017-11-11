package com.lamedh.hamilchessian.app

import com.lamedh.ai.Searcher
import com.lamedh.ai.fringe.bestfirst.{AStarEvaluator, BestSearchFringe}
import com.lamedh.hamilchessian.core.board.{Board, BoardService}
import com.lamedh.hamilchessian.infra.ai.{PathFinder, PathState, WarnsdorfEvaluator}

trait Injection {

  // private val evaluator = new PathEvaluator
  private val evaluator = new WarnsdorfEvaluator

  private val fringe =
    // new BreadthSearchFringe[PathState](false, false)
    // new DepthSearchFringe[PathState](false, false)
    // new BestSearchFringe[PathState](evaluator, false, false)
    new BestSearchFringe[PathState](new AStarEvaluator[PathState](evaluator), false, false)

  private val searcher = new Searcher[Board, PathState](fringe)

  val boardService = new BoardService
  val pathFinder = new PathFinder(searcher, boardService)
}
