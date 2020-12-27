package Lib.ui.android;

import Lib.ui.MyListsPageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        MY_LIST_ELEMENT = "xpath://*[@content-desc='My lists']";
    }

    public AndroidMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
