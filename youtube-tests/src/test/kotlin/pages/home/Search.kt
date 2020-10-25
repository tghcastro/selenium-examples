package pages.home

import driver.DriverWrapper
import driver.LocatorType
import pages.ResultsPage

class Search(private val driverWrapper: DriverWrapper) {
    private var searchTerm: String = ""

    fun fillSearchTermWith(searchTerm: String) {
        this.searchTerm = searchTerm
        driverWrapper.fillText(LocatorType.XPATH,"//ytd-searchbox/form/div/div[1]/input", searchTerm)
    }

    fun search() : ResultsPage {
        println("Sarching for $searchTerm")
        driverWrapper.click(LocatorType.XPATH,"//ytd-searchbox/form/button")
        return ResultsPage(driverWrapper)
    }
}