package layered

import zio._

case class HttpConfig(enableRetry: Boolean)
case class Config(http: HttpConfig)

object Config {
  val httpLayer: URLayer[Has[Config], Has[HttpConfig]] = ZLayer.fromFunction(_.get.http)
}

