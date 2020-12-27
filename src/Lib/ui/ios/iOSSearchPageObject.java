package Lib.ui.ios;

import Lib.ui.SearchPageObject;
import io.appium.java_client.AppiumDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static{
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "id:Cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        //SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";

    }

    public  iOSSearchPageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
