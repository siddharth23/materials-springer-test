package springer_material_homepage

import scala.io.Source
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods.{compact, render, parse}

/**
 * Created by Siddharth on 7/24/15.
 */
object DataProvider {
  implicit val formats = DefaultFormats

  def testData(text: String, filePath: String) = {
    val data = Source.fromFile(filePath)
    val jsonData = parse(data.buffered.mkString)
    val textData = jsonData \\ text
    textData.children.map(x => compact(render(x)).replace("\"", ""))
  }
  def testNameWithData(text: String, filePath: String)={
    testData(text,filePath) zip  testData("testName",filePath)
  }
}
