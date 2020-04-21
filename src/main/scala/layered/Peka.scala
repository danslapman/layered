package layered

import zio._

object Peka {
  trait Service {
    def getSomeButthurt(bht: Int): Task[String]
  }

  def getSomeButthurt(bht: Int): RIO[Has[Service], String] =
    ZIO.accessM(_.get.getSomeButthurt(bht))

  val layer: URLayer[Has[HttpClient.Service], Has[Peka.Service]] =
    ZLayer.fromFunction(httpCl => (bht: Int) => httpCl.get.fetch("yoba://peka.pshhh", "GET")
      .map(response => s"$response took $bht"))
}