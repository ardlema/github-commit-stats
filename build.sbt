name := "github-commits-stats"

organization := "org.ardlema"

version := "0.0.1"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),
  "org.mockito" % "mockito-core" % "1.10.19" % "test",
  "com.typesafe.play" % "play-json_2.11" % "2.3.9",
  "com.typesafe.play" % "play-ws_2.11" % "2.3.9"
)

fork in run := false

initialCommands := "import org.ardlema.githubcommitsstats._"

