package springer_material_homepage

import java.io.{File, FileWriter}

import org.openqa.selenium.firefox.{FirefoxProfile, FirefoxDriver}
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser
import org.openqa.selenium.By

/**
 * Created by Siddharth on 7/24/15.
 */
trait Configurator extends WebBrowser {
  //  val profile = new FirefoxProfile();
  val dc = DesiredCapabilities.firefox();
  dc.setJavascriptEnabled(true)
dc.setCapability("FirefoxDriver.DEFAULT_ENABLE_NATIVE_EVENTS",true)
  implicit val driver = new FirefoxDriver(dc)
  implicit val u = new WebDriverWait(driver, 50)

  implicit def enterText(element: By, text: String): Unit = {
    driver findElement (element) sendKeys (text)
  }
}

