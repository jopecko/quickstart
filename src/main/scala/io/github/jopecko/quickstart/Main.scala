package io.github.jopecko.quickstart

import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple {
  val run = QuickstartServer.run[IO]
}
