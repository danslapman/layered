package layered

import zio._
import zio.console.Console

object Layered extends App {
  val config: ULayer[Has[Config]] = ZLayer.succeed(Config(HttpConfig(false)))

  val pekaLayer = (config >>> Config.httpLayer >>> HttpClient.fakeClient) ++ Metrics.layer >>> Peka.layer

  val fullLayer = ZEnv.live ++ pekaLayer

  val doBht: RIO[Has[Peka.Service] with Console, String] = for {
    res <- Peka.getSomeButthurt(42)
    _ <- Console.Service.live.putStrLn(res)
  } yield res

  override def run(args: List[String]): UIO[Int] =
    doBht.provideLayer(fullLayer)
      .catchAll(_ => ZIO.succeed(1))
      .as(0)
}
