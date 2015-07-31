package org.ardlema.githubcommitsstats

object StatsGenerator {
  def main(args: Array[String]) {
    println("Getting contributors for: "+args(0))
    val apiCaller = new GithubApiCaller
    val contributors = GithubApiService.getContributions(args(0), apiCaller)
    println(contributors)
  }
}
