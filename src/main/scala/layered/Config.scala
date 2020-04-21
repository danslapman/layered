package layered

import zio._

case class HttpConfig(enableRetry: Boolean)
case class Config(http: HttpConfig)

object Config {
  val httpLayer: URLayer[Has[Config], HttpConfig] = ZLayer.fromFunctionMany(_.get.http)
}

