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
        "login": "tomasperezv",
        "id": 379269,
        "avatar_url": "https://avatars.githubusercontent.com/u/379269?v=3",
        "gravatar_id": "",
        "url": "https://api.github.com/users/tomasperezv",
        "html_url": "https://github.com/tomasperezv",
        "followers_url": "https://api.github.com/users/tomasperezv/followers",
        "following_url": "https://api.github.com/users/tomasperezv/following{/other_user}",
        "gists_url": "https://api.github.com/users/tomasperezv/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/tomasperezv/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/tomasperezv/subscriptions",
        "organizations_url": "https://api.github.com/users/tomasperezv/orgs",
        "repos_url": "https://api.github.com/users/tomasperezv/repos",
        "events_url": "https://api.github.com/users/tomasperezv/events{/privacy}",
        "received_events_url": "https://api.github.com/users/tomasperezv/received_events",
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
        "login": "witokondoria",
        "id": 5685669,
        "avatar_url": "https://avatars.githubusercontent.com/u/5685669?v=3",
        "gravatar_id": "",
        "url": "https://api.github.com/users/witokondoria",
        "html_url": "https://github.com/witokondoria",
        "followers_url": "https://api.github.com/users/witokondoria/followers",
        "following_url": "https://api.github.com/users/witokondoria/following{/other_user}",
        "gists_url": "https://api.github.com/users/witokondoria/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/witokondoria/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/witokondoria/subscriptions",
        "organizations_url": "https://api.github.com/users/witokondoria/orgs",
        "repos_url": "https://api.github.com/users/witokondoria/repos",
        "events_url": "https://api.github.com/users/witokondoria/events{/privacy}",
        "received_events_url": "https://api.github.com/users/witokondoria/received_events",
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
        "login": "roclas",
        "id": 1390424,
        "avatar_url": "https://avatars.githubusercontent.com/u/1390424?v=3",
        "gravatar_id": "",
        "url": "https://api.github.com/users/roclas",
        "html_url": "https://github.com/roclas",
        "followers_url": "https://api.github.com/users/roclas/followers",
        "following_url": "https://api.github.com/users/roclas/following{/other_user}",
        "gists_url": "https://api.github.com/users/roclas/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/roclas/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/roclas/subscriptions",
        "organizations_url": "https://api.github.com/users/roclas/orgs",
        "repos_url": "https://api.github.com/users/roclas/repos",
        "events_url": "https://api.github.com/users/roclas/events{/privacy}",
        "received_events_url": "https://api.github.com/users/roclas/received_events",
        "type": "User",
        "site_admin": false
      }
    }
    ]"""
  }

  "The Contribution Parser" should "extract contributions from a response" in new StandardResponse {
    val contributions = ContributionParser.getContributions(contributorsResponse)
    contributions.size should be(3)
  }
}

object ContributionJson {
  implicit val contributionReads = Json.reads[ContributionJson]
  implicit val contributionWrites = Json.writes[ContributionJson]
}

object Contributions {
  implicit val contributionsReads = Json.reads[Contributions]
  implicit val contributionsWrites = Json.writes[Contributions]
}

case class ContributionJson(total: Int)

case class Contributions(contributions: Seq[ContributionJson])

case class Contribution(year: String, commiter: String, project: String, flow1: Long, flow2: Long)

object ContributionParser {

  def getContributions(contributionResponse: String): Seq[Contribution] = {
    val json = Try(Json.parse(contributionResponse).as[Contributions])

    json match {
      case Success(parsedJson) => "hola"
      case Failure(ex) => println(s"Exception: $ex")
    }
    Seq.empty
  }
}
