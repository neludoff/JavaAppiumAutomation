package Lib.ui.android;

import Lib.ui.NavigationUI;
import io.appium.java_client.AppiumDriver;

public class AndroidNavigationUI extends NavigationUI
{
    static{
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public AndroidNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
