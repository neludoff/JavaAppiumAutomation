package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.SearchPageObject;
import Lib.ui.android.AndroidSearchPageObject;
import Lib.ui.ios.iOSSearchPageObject;
import io.appium.java_client.AppiumDriver;

public class SearchPageObjectFactory
{
    public static SearchPageObject get (AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);
        } else {
            return new iOSSearchPageObject(driver);
        }
    }
}
