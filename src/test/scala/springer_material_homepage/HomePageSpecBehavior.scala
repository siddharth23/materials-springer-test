package springer_material_homepage

import java.io.{File, FileWriter}

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.scalatest.{FlatSpec, Matchers, BeforeAndAfterAll}
import org.openqa.selenium.WebElement
import springer_material_homepage._

/**
 * Created by Siddharth on 7/24/15.
 */
abstract class HomePage extends FlatSpec with Matchers with Configurator with BeforeAndAfterAll {

  implicit def waitForElement(element: By) = {
    try {
      u.until(ExpectedConditions.visibilityOfElementLocated(element))
    }
    catch {
      case e: Exception => e.printStackTrace();
        val fw = new FileWriter(new File(s"screenshots/$element.png"))
        fw.close()
        setCaptureDir("screenshots")
        val file = capture
        captureTo(s"$element.png")
        fail(s"$element not found")
    }
  }
}

trait HomePageSpecBehavior extends HomePage with Matchers {
  this: FlatSpec =>

  def successfulSuggest(dataWithTestName: List[(String, String)]) = {
    for (data <- dataWithTestName) {
      it must " " + data._2 in {
        driver findElement searchId clear()
        driver navigate() refresh()
        enterText(searchId, data._1)
        waitForElement(searchResultId)
        val webElementList = driver.findElement(searchResultId).findElements(By.tagName("li"))
        for (web <- 0 to webElementList.size() - 1) {
          val response = webElementList.get(web).getText.toLowerCase
          def verifyResponse(wildCharacter: Char) {
            val inputData = data._1 split (wildCharacter)
            for (input <- inputData) {
              if (input != "")
                response should include(input toLowerCase)
            }
          }
          if (data._1 contains '?') verifyResponse('?')
          else if (data._1 contains '*') verifyResponse('*')
          else verifyResponse(' ')
        }
      }
    }
  }
}


