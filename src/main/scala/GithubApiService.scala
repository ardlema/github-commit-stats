package org.ardlema.githubcommitsstats

object GithubApiService {

  def getContributions(repoName: String, apiCaller: GithubApiCaller): Seq[Contribution] = {
    val contributionsResponse = apiCaller.contributionsStats(repoName)
    ContributionParser.getContributions(contributionsResponse, repoName)
  }
}
