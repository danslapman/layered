package layered

import zio._

object Metrics {
  trait Service {

  }

  val layer: ULayer[Has[Metrics.Service]] = ZLayer.succeed(new Service {})
}
