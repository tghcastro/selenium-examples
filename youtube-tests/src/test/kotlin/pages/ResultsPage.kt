package pages

import driver.DriverWrapper
import driver.LocatorType

class ResultsPage(private val driverWrapper: DriverWrapper) {
    fun resultsDisplayed(): Boolean {
        val locatorTerm = "//ytd-item-section-renderer/div[3]/ytd-video-renderer"
        val resultsList = driverWrapper.findElements(LocatorType.XPATH, locatorTerm)
        return  !resultsList.isNullOrEmpty()
    }
}
