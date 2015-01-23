val originalJvmOptions = sys.process.javaVmArguments.filter(
  a => Seq("-Xmx", "-Xms", "-XX").exists(a.startsWith)
)

val baseSettings = Seq(
  scalaVersion := "2.11.5",
  scalacOptions ++= (
    "-deprecation" ::
    "-unchecked" ::
    "-Xlint" ::
    "-language:existentials" ::
    "-language:higherKinds" ::
    "-language:implicitConversions" ::
    Nil
  ),
  watchSources ~= { _.filterNot(f => f.getName.endsWith(".swp") || f.getName.endsWith(".swo") || f.isDirectory) },
  javaOptions ++= originalJvmOptions,
  shellPrompt := { state =>
    val branch = if(file("../.git").exists){
      "git branch".lines_!.find{_.head == '*'}.map{_.drop(1)}.getOrElse("")
    } else ""
    s"[${scala.Console.CYAN}${Project.extract(state).currentRef.project}${scala.Console.RESET} :${scala.Console.GREEN}$branch${scala.Console.RESET}] " + "$ "
  },
  incOptions := incOptions.value.withNameHashing(true),
  resolvers ++= Seq(Opts.resolver.sonatypeReleases)
)

lazy val root = Project(
  "studyplay2", file(".")
).enablePlugins(PlayScala).settings(
  baseSettings: _*
).settings(
  libraryDependencies ++= Seq(
    "jp.co.bizreach" %% "play2-handlebars" % "0.1.0"
  )
)

