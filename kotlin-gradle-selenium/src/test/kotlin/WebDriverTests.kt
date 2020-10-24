import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

class WebDriverTests {

    @Test
    fun runWithFirefox() {
        System.setProperty("webdriver.gecko.driver", "D:\\java\\geckodriver.exe")
        val driver: WebDriver = FirefoxDriver()
        //comment the above 2 lines and uncomment below 2 lines to use Chrome
        //System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
        //WebDriver driver = new ChromeDriver();

        val baseUrl = "http://demo.guru99.com/test/newtours/"
        val expectedTitle = "Welcome: Mercury Tours"
        var actualTitle = ""

        driver.get(baseUrl)

        actualTitle = driver.getTitle()

        if (actualTitle.contentEquals(expectedTitle)) {
            println("Test Passed!")
        } else {
            println("Test Failed")
        }

        driver.close()
    }

    @Test
    fun runWithChrome() {
        System.setProperty("webdriver.chrome.driver", "D:\\java\\chromedriver.exe")
        var driver = ChromeDriver()

        val baseUrl = "http://demo.guru99.com/test/newtours/"
        val expectedTitle = "Welcome: Mercury Tours"
        var actualTitle = ""

        driver.get(baseUrl)

        actualTitle = driver.getTitle()

        if (actualTitle.contentEquals(expectedTitle)) {
            println("Test Passed!")
        } else {
            println("Test Failed")
        }

        driver.close()
    }
}