package org.ardlema.githubcommitsstats

import play.api.libs.json.Json

import scala.util.{Failure, Success, Try}

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

case class Contribution(committer: String, project: String, flow1: Long, flow2: Long) {

  //TODO: Parse the year from the contributors response
  val year = 2015

  override def toString = { s"$year,$committer,$project,$flow1,$flow2" }
}

object ContributionParser {

  def getContributions(contributionResponse: String, repoName: String): Seq[Contribution] = {
    val json = Try(Json.parse(contributionResponse).as[List[ContributionJson]])

    json match {
      case Success(parsedJson) =>  {
        val totalCommits = parsedJson.map(_.total).sum
        for (jsonElement <- parsedJson)
        yield Contribution(jsonElement.author.login, repoName, jsonElement.total, totalCommits)
      }
      case Failure(ex) => Seq.empty
    }
  }
}
