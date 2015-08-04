package org.ardlema.githubcommitsstats

object StatsGenerator {

  //TODO: Pick the files name from a properties file
  val contributorsFile = "/home/arodriguez/dev/github-commits-stats/stratio-contributors.csv"
  val topTenCommiters = "/home/arodriguez/dev/github-commits-stats/top-ten-commiters.csv"
  val commitsPerProjects = "/home/arodriguez/dev/github-commits-stats/commits-per-project.csv"
  

  def main(projects: Array[String]) {
    val apiCaller = new GithubApiCaller
    val contributors = for (project <- projects;
                            contributor <- GithubApiService.getContributions(project, apiCaller)) yield contributor

    //Create stratio contributors file
    for (c <- contributors) FileAppender.appendToFile(contributorsFile, c.toString)

    //Create top-ten commiters
    val topTenCommitters = contributors
      .toList
      .groupBy(_.committer)
      .map(e => (e._1, e._2.map(c => c.flow1).sum))
      .toList
      .sortBy(_._2)
      .reverse
      .take(10)
    for (topTenCommitter <- topTenCommitters)
      FileAppender.appendToFile(topTenCommiters, s"${topTenCommitter._1},${topTenCommitter._2}")

    //Create commits per project
    val commitsPerProject = contributors
      .toList
      .groupBy(_.project)
      .map(e => (e._1, e._2(0).flow2))
      .toList

    for (commitPerProject <- commitsPerProject)
      FileAppender.appendToFile(commitsPerProjects, s"${commitPerProject._1},${commitPerProject._2}")
  }
}
