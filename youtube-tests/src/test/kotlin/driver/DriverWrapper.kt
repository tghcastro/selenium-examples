package driver

import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.interactions.Actions
import java.util.concurrent.TimeUnit

class DriverWrapper {

    //TODO: Add in configuration file
    private val secondsExplicitlyWait: Long = 5L
    private val secondsExplicitlyPollElement: Long = 1L
    private val secondsToWaitPage: Long = 10L
    private val secondsToWaitFindElement: Long = 10L
    private val startMaximized: Boolean = true

    private var insideFrame: Boolean = false
    private var webDriver: WebDriver

    init {
        this.loadConfiguration()
        webDriver = startDriver()
        webDriver.manage().timeouts().implicitlyWait(secondsToWaitFindElement, TimeUnit.SECONDS)
        webDriver.manage().timeouts().pageLoadTimeout(secondsToWaitPage, TimeUnit.SECONDS)
        if (startMaximized) {
            webDriver.manage().window().maximize()
        }
    }

    fun closeWindow() {
        this.webDriver.close()
    }

    fun quit() {
        this.webDriver.quit()
    }

    fun openUrl(url: String): String? {
        this.webDriver.get(url)
        println("Opened page: ${this.webDriver.currentUrl}")
        return this.webDriver.currentUrl
    }

    fun isDisplayed(locatorType: LocatorType, locatorTerm: String): Boolean {
        return try {
            val element = this.findElement(locatorType, locatorTerm)
            element.isDisplayed
        } catch (ex: ElementNotVisibleException) {
            false
        } catch (ex: ElementNotInteractableException) {
            false
        }
    }

    fun fillText(locatorTerm: String, valueToFill: String) {
        fillText(LocatorType.ID, locatorTerm, valueToFill)
    }

    fun fillText(locatorType: LocatorType, locatorTerm: String, valueToFill: String) {
        val element = this.findElement(locatorType, locatorTerm)
        element.clear()
        element.sendKeys(valueToFill)

//        val element = this.findElement(LocatorType.ID, locatorTerm)
//        WebDriverWait(webDriver, secondsExplicitlyWait)
//            .until(ExpectedConditions.elementToBeClickable(element))
//            .let {
//                it.clear()
//                it.sendKeys(valueToFill)
//            }


//        FluentWait(this)
//            .withTimeout(Duration.ofSeconds(secondsExplicitlyWait))
//            .pollingEvery(Duration.ofSeconds(secondsExplicitlyPollElement))
//            .ignoring(InvalidElementStateException::class.java, ElementNotInteractableException::class.java)
//            .run {
//                val element = findElement(LocatorType.ID, locatorTerm)
//                // element.clear()
//                element.sendKeys(valueToFill)
//            }
    }

    fun click(locatorTerm: String) {
        val element = this.findElement(LocatorType.ID, locatorTerm)
        element.click()
    }

    fun click(locator: LocatorType, locatorTerm: String) {
        val element = this.findElement(locator, locatorTerm)
        element.click()
    }

    fun findElement(locatorTerm: String): WebElement {
        return this.findElement(LocatorType.ID, locatorTerm)
    }

    fun findElement(locator: LocatorType, locatorTerm: String): WebElement {
        var element: WebElement? = null
        element = when (locator) {
            LocatorType.ID -> this.webDriver.findElement(By.id(locatorTerm))
            LocatorType.XPATH -> this.webDriver.findElement(By.ByXPath(locatorTerm))
            LocatorType.CSS_SELECTOR -> this.webDriver.findElement(By.ByCssSelector(locatorTerm))
            LocatorType.TAG_NAME -> this.webDriver.findElement(By.ByTagName(locatorTerm))
            else -> throw Exception("The type $locator doesn't exist")
        }

        var action = Actions(webDriver)
        action.moveToElement(element)

        return element;
    }

    fun findElements(locator: LocatorType, locatorTerm: String): List<WebElement>? {
        var elements: List<WebElement>? = null
        elements = when (locator) {
            LocatorType.ID -> this.webDriver.findElements(By.id(locatorTerm))
            LocatorType.XPATH -> this.webDriver.findElements(By.ByXPath(locatorTerm))
            LocatorType.CSS_SELECTOR -> this.webDriver.findElements(By.ByCssSelector(locatorTerm))
            LocatorType.TAG_NAME -> this.webDriver.findElements(By.ByTagName(locatorTerm))
            else -> throw Exception("The type $locator doesn't exist")
        }

        if (elements.isNotEmpty()) {
            val action = Actions(webDriver)
            action.moveToElement(elements[0])
        }

        return elements;
    }

    fun switchToFrame(nameOrId: String) {
        this.webDriver.switchTo().frame(nameOrId)
        this.insideFrame = true
    }

    fun switchToDefaultContent() {
        if (isInsideFrame()) {
            this.webDriver.switchTo().defaultContent()
            this.insideFrame = false
        }
    }

    fun isInsideFrame(): Boolean {
        return this.insideFrame
    }


    private fun loadConfiguration(): Unit {
        // TODO: Load configurations here
    }

    private fun startDriver(): WebDriver {
        //return startFirefox();
        return startChrome();
    }

    private fun startFirefox(): FirefoxDriver {
        System.setProperty("webdriver.gecko.driver", "D:\\java\\geckodriver.exe")
        val firefoxOptions = FirefoxOptions()
        val firefox = FirefoxDriver(firefoxOptions);
        return firefox
    }

    private fun startChrome(): ChromeDriver {
        System.setProperty("webdriver.chrome.driver", "D:\\java\\chromedriver.exe")
        val chromeOptions = ChromeOptions()
        val chrome = ChromeDriver(chromeOptions);
        return chrome
    }


}