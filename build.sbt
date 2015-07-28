name := "springer-material"

version := "1.0"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq("org.seleniumhq.selenium" % "selenium-java" % "2.46.0",
  "org.scalatest" % "scalatest_2.11" % "2.2.1",
  "org.json4s" % "json4s-native_2.11" % "3.2.11"
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports")

testOptions in Test +=Tests.Cleanup((loader:java.lang.ClassLoader)=>{loader.loadClass("HtmlReport").newInstance})
