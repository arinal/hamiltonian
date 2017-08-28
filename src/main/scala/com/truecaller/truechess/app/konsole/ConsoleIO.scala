package com.truecaller.truechess.app.konsole

import com.truecaller.truechess.core.board.{ Knight, Pawn, Piece, Rook }
import java.util.Scanner

import scala.util.{Success, Try}

trait ConsoleIO {

  private val scanner = new Scanner(System.in)

  trait IO[A] { def read(): Option[A] }

  implicit val intIO: IO[Int] = () => {
    Try(scanner.nextInt()) match {
      case Success(n) => Some(n)
      case _ =>
        scanner.next()
        None
    }
  }

  implicit val pieceIO: IO[Piece] = () => {
    Try(scanner.next()) match {
      case Success("P" | "p") => Some(Pawn)
      case Success("K" | "k") => Some(Knight)
      case Success("R" | "r") => Some(Rook)
      case _ =>
        scanner.next()
        None
    }
  }

  def promptStdin[A](default: A, prompt: A => String)
                 (implicit io: IO[A]): A = {
    print(prompt(default))
    io.read().getOrElse(default)
  }
}
