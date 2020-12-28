package Lib.ui.ios;

import Lib.ui.MyListsPageObject;
import io.appium.java_client.AppiumDriver;

public class iOSMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        MY_LIST_ELEMENT = "xpath://*[@content-desc='My lists']";
        SYNC_YOUR_SAVED_ARTICLES = "id:Close";
        IMAGE_ELEMENT = "xpath://XCUIElementTypeImage//..//XCUIElementTypeStaticText";
        SEARCH_BUTTON ="id:Search";
    }

    public iOSMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
