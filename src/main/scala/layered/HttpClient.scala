package layered

import zio._

object HttpClient {
  trait Service {
    def fetch(uri: String, method: String): Task[String]
  }

  val fakeClient: URLayer[Has[HttpConfig], Has[Service]] =
    ZLayer.fromService[HttpConfig, Service](_ =>
      (uri: String, method: String) => Task(s"Response from $method $uri")
    )
}
