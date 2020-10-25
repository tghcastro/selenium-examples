package pages.home

import driver.DriverWrapper
import driver.LocatorType

class InitiateSessionDialog(private val driverWrapper: DriverWrapper) {
    fun isOpen(): Boolean {
        val cssSelector = "paper-dialog.style-scope"
        return driverWrapper.isDisplayed(LocatorType.CSS_SELECTOR, cssSelector)
    }

    fun answerNot(): Unit {
        val locatorTerm = "/html/body/ytd-app/ytd-popup-container/paper-dialog/yt-upsell-dialog-renderer/div/div[3]/div[1]/yt-button-renderer/a"
        driverWrapper.click(LocatorType.XPATH, locatorTerm)
    }

    fun answerNotIfDisplayed() {
        if (isOpen()) {
            this.answerNot()
        }
    }
}