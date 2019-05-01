
lazy val root = (project in file("."))
  .settings(
    name := "smqd-rpt-svr",
    organization := "com.thing2x",
    version := "0.1",
    scalaVersion := "2.12.8",
    scalacOptions in ThisBuild ++= Seq("-feature", "-deprecation"),
  )
  .settings(
    fork in Test := true
  )
  .settings(
    libraryDependencies ++= Dependencies.smqd ++
      Dependencies.test ++
      Dependencies.slick ++
      Dependencies.quartz ++
      Dependencies.h2db ++
      Dependencies.jasperreports ++ Dependencies.poi,
    resolvers += Dependencies.smqdResolver,
    resolvers += Dependencies.jasperreportsResolver,
  )