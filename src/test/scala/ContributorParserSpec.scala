package org.ardlema.githubcommitsstats

import org.scalatest._
import play.api.libs.json.{JsValue, Json}

import scala.util.{Failure, Success, Try}

class ContributorParserSpec extends FlatSpec with Matchers {

  trait StandardResponse {

    val contributorsResponse = """
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
    },
    {
      "total": 5,
      "weeks": [
      {
        "w": 1411862400,
        "a": 0,
        "d": 0,
        "c": 0
      },
      {
        "w": 1412467200,
        "a": 0,
        "d": 0,
        "c": 0
      }
      ],
      "author": {
        "login": "manolo",
        "id": 5685669,
        "avatar_url": "https://avatars.githubusercontent.com/u/5685669?v=3",
        "gravatar_id": "",
        "url": "https://api.github.com/users/manolo",
        "html_url": "https://github.com/manolo",
        "followers_url": "https://api.github.com/users/manolo/followers",
        "following_url": "https://api.github.com/users/manolo/following{/other_user}",
        "gists_url": "https://api.github.com/users/manolo/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/manolo/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/manolo/subscriptions",
        "organizations_url": "https://api.github.com/users/manolo/orgs",
        "repos_url": "https://api.github.com/users/manolo/repos",
        "events_url": "https://api.github.com/users/manolo/events{/privacy}",
        "received_events_url": "https://api.github.com/users/manolo/received_events",
        "type": "User",
        "site_admin": false
      }
    },
    {
      "total": 3,
      "weeks": [
      {
        "w": 1411862400,
        "a": 0,
        "d": 0,
        "c": 0
      },
      {
        "w": 1412467200,
        "a": 0,
        "d": 0,
        "c": 0
      },
      {
        "w": 1413072000,
        "a": 0,
        "d": 0,
        "c": 0
      }
      ],
      "author": {
        "login": "amparo",
        "id": 1390424,
        "avatar_url": "https://avatars.githubusercontent.com/u/1390424?v=3",
        "gravatar_id": "",
        "url": "https://api.github.com/users/amparo",
        "html_url": "https://github.com/amparo",
        "followers_url": "https://api.github.com/users/amparo/followers",
        "following_url": "https://api.github.com/users/amparo/following{/other_user}",
        "gists_url": "https://api.github.com/users/amparo/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/amparo/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/amparo/subscriptions",
        "organizations_url": "https://api.github.com/users/amparo/orgs",
        "repos_url": "https://api.github.com/users/amparo/repos",
        "events_url": "https://api.github.com/users/amparo/events{/privacy}",
        "received_events_url": "https://api.github.com/users/amparo/received_events",
        "type": "User",
        "site_admin": false
      }
    }
    ]"""
  }

  "The Contribution Parser" should "extract contributions from a response" in new StandardResponse {
    val contributions = ContributionParser.getContributions(contributorsResponse, "theproject")
    contributions.size should be(3)
  }
}

object Author {
  implicit val authorReads = Json.reads[Author]
  implicit val authorWrites = Json.writes[Author]
}

object ContributionJson {
  implicit val contributionReads = Json.reads[ContributionJson]
  implicit val contributionWrites = Json.writes[ContributionJson]
}

case class Author(login: String)

case class ContributionJson(total: Long, author: Author)

case class Contribution(year: Int, committer: String, project: String, flow1: Long, flow2: Long)

object ContributionParser {

  def getContributions(contributionResponse: String, project: String): Seq[Contribution] = {
    val json = Try(Json.parse(contributionResponse).as[List[ContributionJson]])

    json match {
      case Success(parsedJson) =>  {
        val totalCommits = parsedJson.map(_.total).sum
        for (jsonElement <- parsedJson)
          yield Contribution(2015, jsonElement.author.login, project, jsonElement.total, totalCommits)
      }
      case Failure(ex) => Seq.empty
    }
  }
}
