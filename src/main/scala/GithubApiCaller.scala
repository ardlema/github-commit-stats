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
    println(s"About to call gihub API to get contributors for $repoName...")
    val result = Await.result(response, 10 seconds)
    val stats = result.status match {
        case 202 => {
          println("Data hasn't been cached. Waiting the background job to be fired...")
          Thread.sleep(5000)
          contributionsStats(repoName)
        }
        case 200 => {
          println(s"Contributors for $repoName fetched OK!!")
          result.body
        }
        case _ => {
          println(s"No contributors fetched for $repoName")
          ""
        }
      }
    stats
  }
}
