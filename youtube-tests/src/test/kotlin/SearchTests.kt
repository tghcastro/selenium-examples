import driver.DriverWrapper
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import pages.home.HomePage

@DisplayName("Search feature tests")
class SearchTests(private val testInfo: TestInfo) {

    private var driverWrapper: DriverWrapper = DriverWrapper()

    @BeforeEach
    fun beforeEach() {
        println("Running test suite: ${testInfo.displayName}")
        driverWrapper.openUrl("https://www.youtube.com/")
    }

    @AfterEach
    fun afterEach() {
        driverWrapper.quit()
    }

    @Test
    @DisplayName("Search for an existent term should display the results")
    fun searchForAnExistentTerm_ShouldDisplayTheResults(scenarioInfo: TestInfo) {
        println("Running test scenario: ${scenarioInfo.displayName}")

        val searchTerm = "Selenium Webdriver"

        val homePage = HomePage(driverWrapper);
        homePage.initiateSessionDialog.answerNotIfDisplayed()
        homePage.cookiesPolicy.acceptIfDisplayed()
        homePage.search.fillSearchTermWith(searchTerm)
        val resultsPage = homePage.search.search()

        assertTrue(resultsPage.resultsDisplayed())
    }
}