
class ContributorParserSpec {

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
    "total": 2,
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

case class Contribution(year: String, commiter: String, project: String, flow1: Long, flow2: Long)
