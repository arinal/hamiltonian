package com.truecaller.truechess.core.path

import com.truecaller.truechess.core.BoardPathFinderAlg
import com.truecaller.truechess.core.board.{Board, Location, Piece}

trait BoardPathFinder extends BoardPathFinderAlg[Board, Location, Piece]
