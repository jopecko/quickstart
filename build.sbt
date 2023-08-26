val Http4sVersion = "0.23.23"
val CirceVersion = "0.14.5"
val MunitVersion = "0.7.29"
val LogbackVersion = "1.4.11"
val MunitCatsEffectVersion = "1.0.7"

// default version string format includes + characters, which is not compatible with docker tags
ThisBuild / dynverSeparator := "-"

lazy val root = (project in file("."))
  .settings(
    organization := "io.github.jopecko",
    name := "quickstart",
    scalaVersion := "2.13.11",
    Compile / mainClass := Some("io.github.jopecko.quickstart.Main"),
    dockerExposedPorts := Seq(8080),
    //dockerAliases ++= Seq(dockerAlias.value.withTag(Option((ThisBuild / version).value))),
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
      "org.http4s"      %% "http4s-ember-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "org.scalameta"   %% "munit"               % MunitVersion           % Test,
      "org.typelevel"   %% "munit-cats-effect-3" % MunitCatsEffectVersion % Test,
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion         % Runtime,
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.13.2" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
    assembly / assemblyMergeStrategy := {
      case "module-info.class" => MergeStrategy.discard
      case x => (assembly / assemblyMergeStrategy).value.apply(x)
    }
  )
  .enablePlugins(JavaAppPackaging, DockerPlugin)
