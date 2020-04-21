package layered

import zio._

object HttpClient {
  trait Service {
    def fetch(uri: String, method: String): Task[String]
  }

  val fakeClient: URLayer[HttpConfig, Has[HttpClient.Service]] =
    ZLayer.succeed((uri: String, method: String) => Task(s"Response from $method $uri"))
}
