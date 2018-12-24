import sbt.Keys._

name := "manhattan-distance"
startYear := Some(2018)
scalaVersion := "2.12.8"

libraryDependencies ++= Seq (
   "org.scalatest" %% "scalatest" % "3.0.5" % Test,
)

