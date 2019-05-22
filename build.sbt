
val majorVersion = SettingKey[String]("major version")
val minorVersion = SettingKey[String]("minor version")
val patchVersion = SettingKey[Option[String]]("patch version")

Global / majorVersion := "0"
Global / minorVersion := "1"
Global / patchVersion := Some("0")

lazy val `google-common-protos-scala` = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Common(),
    name := "google-common-protos-scala",
    version := s"${majorVersion.value}.${minorVersion.value}${patchVersion.value.fold("")(p => s".$p")}",
    resolvers ++= Dependencies.resolvers(),
    libraryDependencies ++= Dependencies(),
    bintrayOrganization := Some("engitano"),
    bintrayPackageLabels := Seq("google", "grpc"),
    Defaults.itSettings,
    PB.targets in Compile := Seq(
      scalapb.gen(flatPackage = true, grpc = false) -> (sourceManaged in Compile).value
    ),
    Compile / PB.includePaths := Seq(
      target.value / "protobuf_external",
      baseDirectory.value / "googleapis",
    ),
    Compile / PB.protoSources := Seq(
      baseDirectory.value /  "googleapis" / "google" / "api",
      baseDirectory.value /  "googleapis" / "google" / "cloud" / "audit",
      baseDirectory.value /  "googleapis" / "google" / "logging" / "type",
      baseDirectory.value /  "googleapis" / "google" / "longrunning",
      baseDirectory.value /  "googleapis" / "google" / "rpc",
      baseDirectory.value /  "googleapis" / "google" / "type"
    ),
    excludeFilter in PB.generate := new SimpleFileFilter(
      (f: File) =>  f.getParent.contains("v1") || f.getParent.contains("v2"))
  )