package pages.home

import driver.DriverWrapper

class HomePage(private val driverWrapper: DriverWrapper) {
    val search: Search = Search(driverWrapper)
        get() {
            driverWrapper.switchToDefaultContent()
            return  field
        }

    val initiateSessionDialog: InitiateSessionDialog = InitiateSessionDialog(driverWrapper)
        get() {
            driverWrapper.switchToDefaultContent()
            return  field
        }

    val cookiesPolicy: CookiesPolicy = CookiesPolicy(driverWrapper)
        get() {
            field.switchToRoot()
            return  field
        }


}
