/**
 * Created by Siddharth on 7/24/15.
 */

import org.openqa.selenium.By

package object springer_material_homepage {
  implicit val url = "http://materials.springer.com/"
  implicit val searchId = By id "searchTerm"
  implicit val searchResultId = By id "ui-id-1"
}
