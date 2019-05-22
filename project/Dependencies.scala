import sbt._

object Dependencies {

  def resolvers(): Seq[MavenRepository] = Seq(Resolver.sonatypeRepo("releases"))

  def apply(): Seq[ModuleID] = Seq(
//    "com.thesamet.scalapb"  %% "scalapb-runtime-grpc"            % scalapb.compiler.Version.scalapbVersion,
    "com.thesamet.scalapb"  %% "scalapb-runtime"                 % scalapb.compiler.Version.scalapbVersion,
    "com.thesamet.scalapb"  %% "scalapb-runtime"                 % scalapb.compiler.Version.scalapbVersion % "protobuf",
  )
}
