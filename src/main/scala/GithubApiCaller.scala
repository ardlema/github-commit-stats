package org.ardlema.githubcommitsstats

import com.ning.http.client.AsyncHttpClientConfig
import play.api.libs.ws.ning.{NingWSClient, NingAsyncHttpClientConfigBuilder}
import play.api.libs.ws.{DefaultWSClientConfig, WS}

import scala.concurrent.Await
import scala.concurrent.duration._

class GithubApiCaller {

  //If you want to fetch info from private repos you should provide your oauth token
  //TODO: pass-in this guy through command line or fetch it from a config file
  val accessToken = ""

  def contributionsStats(repoName: String): String = {
    val clientConfig = new DefaultWSClientConfig()
    val asyncClient = new NingAsyncHttpClientConfigBuilder(clientConfig).build()
    val builder = new AsyncHttpClientConfig.Builder(asyncClient)
    val secureDefaultsWithSpecificOptions = builder.build()
    implicit val implicitClient = new NingWSClient(secureDefaultsWithSpecificOptions)
    val addAccessToken = if (accessToken.isEmpty) "" else accessToken
    val response = WS.clientUrl(s"https://api.github.com/repos/stratio/$repoName/stats/contributors$addAccessToken").get()
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
