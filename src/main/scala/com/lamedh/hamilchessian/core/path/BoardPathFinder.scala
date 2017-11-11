package com.lamedh.hamilchessian.core.path

import com.lamedh.hamilchessian.core.BoardPathFinderAlg
import com.lamedh.hamilchessian.core.board.{Board, Location, Piece}

trait BoardPathFinder extends BoardPathFinderAlg[Board, Location, Piece]
