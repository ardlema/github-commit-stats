package org.ardlema.githubcommitsstats

object StatsGenerator {

  //TODO: Pick the file name from a properties file
  val fileName = "/home/arodriguez/dev/github-commits-stats/stratio-contributors.txt"

  def main(projects: Array[String]) {
    val apiCaller = new GithubApiCaller
    for (project <- projects;
         contributor <- GithubApiService.getContributions(project, apiCaller))
         FileAppender.appendToFile(fileName, contributor.toString)
  }
}
