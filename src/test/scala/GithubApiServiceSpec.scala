package org.ardlema.githubcommitsstats

import org.scalamock.scalatest.MockFactory
import org.scalatest._

class GithubApiServiceSpec extends FlatSpec with ShouldMatchers with MockFactory {

  trait ApiResponse {

    val apiResponse = """
    [
    {
      "total": 2,
      "weeks": [
      {
        "w": 1411862400,
        "a": 0,
        "d": 0,
        "c": 0
      }
      ],
      "author": {
        "login": "ramona",
        "id": 379269,
        "avatar_url": "https://avatars.githubusercontent.com/u/379269?v=3",
        "gravatar_id": "",
        "url": "https://api.github.com/users/ramona",
        "html_url": "https://github.com/ramona",
        "followers_url": "https://api.github.com/users/ramona/followers",
        "following_url": "https://api.github.com/users/ramona/following{/other_user}",
        "gists_url": "https://api.github.com/users/ramona/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/ramona/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/ramona/subscriptions",
        "organizations_url": "https://api.github.com/users/ramona/orgs",
        "repos_url": "https://api.github.com/users/ramona/repos",
        "events_url": "https://api.github.com/users/ramona/events{/privacy}",
        "received_events_url": "https://api.github.com/users/ramona/received_events",
        "type": "User",
        "site_admin": false
      }
    }
    ]"""
  }

  "The GithubApiService" should "call the commiter stats github api" in new ApiResponse {
    val githubApiCaller = stub[GithubApiCaller]
    (githubApiCaller.contributionsStats _).when().returns(apiResponse)
    val contributions = GithubApiService.getContributions("theproject", githubApiCaller)
    contributions.size should be(1)
    contributions should contain(Contribution("ramona", "theproject", 2, 2))
  }
}
