package org.ardlema.githubcommitsstats

object GithubApiService {

  def getContributions(project: String, apiCaller: GithubApiCaller): Seq[Contribution] = {
    val contributionsResponse = apiCaller.contributionsStats()
    ContributionParser.getContributions(contributionsResponse, project)
  }
}
