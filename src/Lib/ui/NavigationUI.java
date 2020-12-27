package Lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
        MY_LISTS_LINK;

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Can't find navigation button to My list",
                5
        );
    }
}
