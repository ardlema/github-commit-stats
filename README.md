# github-commit-stats

[![Build Status](https://travis-ci.org/ardlema/github-commit-stats.svg?branch=master)](https://travis-ci.org/ardlema/github-commit-stats)

The aim of this project is to call the github API in order to fetch info about commiters of an organization and create text files to be visualized through Stratio Datavis.

Usage
-----

In the sbt console type:

runMain org.ardlema.githubcommitsstats.StatsGenerator projectList

Where projectList is the list of the projects (separated by empty spaces) you want fetch stats info from.


TODO
----

* Call the API to get all the projects from the organization
* Pick config variables (file names, api time-outs, oauth token...) from a proper configuration file
* Increase test coverage
* Parse contributions date to get the year of the contribution (currently is harcoded)
* Refactor the main class
* Make the coverage badget work
