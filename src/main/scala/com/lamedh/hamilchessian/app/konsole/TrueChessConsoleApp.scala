package com.lamedh.hamilchessian.app.konsole

import com.lamedh.hamilchessian.app.Injection
import com.lamedh.hamilchessian.core.board.{Board, Location, Pawn, Piece}

trait TrueChessConsoleApp extends Injection
    with ConsoleIO
    with Renderer
    with Messages {

  def start() = (for {
      _               <- printLn(welcomeMsg)
      inputs          <- readInputs()
      (w, h, p, x, y) =  inputs
      startLoc        <- extractLocation(w, h, x, y)
      _               <- lnprintln(processingMsg)
      path            <- findPath(w, h, p, startLoc)
      _               <- lnprintln(pathFoundMsg)
    } yield path)
    .fold(println, println)

  def readInputs(): Either[String, (Int, Int, Piece, Int, Int)] =
    (promptStdin(          5, promptWidthMsg),
     promptStdin(          5, promptHeightMsg),
     promptStdin[Piece](Pawn, promptPieceMsg),
     promptStdin(          0, promptXLocationMsg),
     promptStdin(          0, promptYLocationMsg)) match {
      case (w, h, p, x, y) => Right((w, h, p, x, y))
    }

  def extractLocation(width: Int, height: Int, startX: Int, startY: Int): Either[String, Location] =
    Location(startX, startY, width, height) match {
      case Some(start) => Right(start)
      case _           => Left(invalidInputMsg)
    }

  def findPath(width: Int, height: Int, piece: Piece, start: Location): Either[String, String] = {
    val board = boardService.initBoard(width, height, Set((piece, start)))
    pathFinder.findPath(board, start, printProgress) match {
      case Some(path) => lnstring(render(path))
      case None       => lnstring(pathNotFoundMsg).swap
    }
  }

  def printProgress(iter: Long, board: Board) = println(render(iter, board))

  def stringln(message: String)   = Right(message + "\n")
  def lnstring(message: String)   = Right("\n" + message)
  def lnstringln(message: String) = Right("\n" + message + "\n")

  def printLn(message: String)   = stringln(message).map(print)
  def lnprint(message: String)   = lnstring(message).map(print)
  def lnprintln(message: String) = lnstringln(message).map(print)
}
