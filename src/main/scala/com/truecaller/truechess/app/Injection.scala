package com.truecaller.truechess.app

import com.truecaller.ai.Searcher
import com.truecaller.ai.fringe.bestfirst.{AStarEvaluator, BestSearchFringe}
import com.truecaller.truechess.core.board._
import com.truecaller.truechess.infra.ai.{PathEvaluator, PathFinder, PathState}

trait Injection {

  private val evaluator = new PathEvaluator
  private val astarEvaluator = new AStarEvaluator[PathState](evaluator)
  private val fringe =
  // new BreadthSearchFringe[PathState](false, false)
  // new DepthSearchFringe[PathState](false, false)
  // new BestSearchFringe[PathState](evaluator))
    new BestSearchFringe[PathState](astarEvaluator, false, false)
  private val searcher = new Searcher[Board, PathState](fringe)

  val boardService = new BoardService
  val pathFinder = new PathFinder(searcher, boardService)
}

