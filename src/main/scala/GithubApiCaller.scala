package org.ardlema.githubcommitsstats

import com.ning.http.client.AsyncHttpClientConfig
import play.api.libs.ws.ning.{NingWSClient, NingAsyncHttpClientConfigBuilder}
import play.api.libs.ws.{DefaultWSClientConfig, WS}

import scala.concurrent.Await
import scala.concurrent.duration._

class GithubApiCaller {

  def contributionsStats(repoName: String): String = {
    val clientConfig = new DefaultWSClientConfig()
    val asyncClient = new NingAsyncHttpClientConfigBuilder(clientConfig).build()
    val builder = new AsyncHttpClientConfig.Builder(asyncClient)
    val secureDefaultsWithSpecificOptions = builder.build()
    implicit val implicitClient = new NingWSClient(secureDefaultsWithSpecificOptions)
    val response = WS.clientUrl(s"https://api.github.com/repos/stratio/$repoName/stats/contributors").get()
    val result = Await.result(response, 2 seconds)
    val stats = result.status match {
        case 202 => {
          Thread.sleep(2000)
          contributionsStats(repoName)
        }
        case 200 => result.body
        case _ => ""
      }
    stats
  }
}
