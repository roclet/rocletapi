import com.typesafe.sbt.packager.archetypes.ServerLoader

name := """marginmentorapi"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, DebianPlugin, DockerPlugin,JavaServerAppPackaging)
  .disablePlugins(PlayLogback)



scalaVersion := "2.11.8"

val PhantomVersion = "1.29.6"

maintainer := "Boniface Kabaso <boniface@kabaso.com>"
packageSummary in Linux := "Margin Mentor REST API"
packageDescription := "MarginMentorAPI Backend "
serverLoading in Debian := ServerLoader.Systemd

bashScriptExtraDefines ++= Seq(
  """addJava "-Xms16g"""",
  """addJava "-Xmx16g"""",
  """addJava "-Xmn8g"""",
  """addJava "-Xss1024k"""",
  """addJava "-XX:+UseConcMarkSweepGC"""",
  """addJava "-XX:+UseParNewGC"""",
  """addJava "-XX:CMSInitiatingOccupancyFraction=75"""",
  """addJava "-XX:+UseCMSInitiatingOccupancyOnly"""",
  """addJava "-XX:+ScavengeBeforeFullGC"""",
  """addJava "-XX:TargetSurvivorRatio=80"""",
  """addJava "-XX:SurvivorRatio=8"""",
  """addJava "-XX:+UseBiasedLocking"""",
  """addJava "-XX:MaxTenuringThreshold=15"""",
  """addJava "-XX:ParallelGCThreads=4"""",
  """addJava "-XX:+OptimizeStringConcat"""",
  """addJava "-XX:+UseStringCache"""",
  """addJava "-XX:+DisableExplicitGC""""
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.websudos" % "phantom-dsl_2.11" % PhantomVersion,
  "com.websudos" % "phantom-reactivestreams_2.11" % PhantomVersion,
  "com.chuusai" %% "shapeless" % "2.3.2"
)

libraryDependencies += "io.monix" %% "monix" % "2.2.4"
libraryDependencies += "com.jason-goodwin" % "authentikat-jwt_2.11" % "0.4.5"
libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "com.github.t3hnar" % "scala-bcrypt_2.11" % "3.0"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.5"
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.6"
libraryDependencies += "com.google.maps" % "google-maps-services" % "0.1.19"
libraryDependencies += "org.apache.commons" % "commons-email" % "1.4"
libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "3.6.0"
libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"
libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.6"
libraryDependencies += "com.github.karelcemus" %% "play-redis" % "1.2.0"
libraryDependencies += "com.github.romix.akka" %% "akka-kryo-serialization" % "0.5.0"
libraryDependencies += "com.esotericsoftware.kryo" % "kryo" % "2.24.0"
libraryDependencies += "com.github.nscala-time" % "nscala-time_2.11" % "2.16.0"
libraryDependencies += "com.typesafe.play" % "play-logback_2.11" % "2.5.14"



routesGenerator := InjectedRoutesGenerator
libraryDependencies += filters

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "softprops-maven" at "http://dl.bintray.com/content/softprops/maven",
  "Brando Repository" at "http://chrisdinn.github.io/releases/",
  "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging" at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Twitter Repository" at "http://maven.twttr.com",
  "Websudos releases" at "https://dl.bintray.com/websudos/oss-releases/",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)
