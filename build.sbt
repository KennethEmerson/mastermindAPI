name := """MastermindApi"""
organization := ""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "RandomForest.be.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "RandomForest.be.binders._"
