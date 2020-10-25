package pages.home

import driver.DriverWrapper
import driver.LocatorType

class CookiesPolicy(private val driverWrapper: DriverWrapper) {
    fun switchToRoot(): Unit {
        driverWrapper.switchToFrame("iframe")
    }

    fun isOpen(): Boolean {
        val locatorTerm = "/html/body/div/c-wiz/div[2]/div/div/div"
        return driverWrapper.isDisplayed(LocatorType.XPATH, locatorTerm)
    }

    fun accept() {
        return driverWrapper.click("introAgreeButton")
    }

    fun acceptIfDisplayed() {
        if (isOpen()) {
            this.accept()
        }
    }
}
