name := "Battleship_Scala"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
libraryDependencies += "org.scalatestplus" %% "junit-4-13" % "3.2.2.0" % "test"


scalacOptions ++= Seq(
  "-language:postfixOps"
)
