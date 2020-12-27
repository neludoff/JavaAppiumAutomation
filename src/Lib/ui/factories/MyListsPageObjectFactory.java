package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.MyListsPageObject;
import Lib.ui.android.AndroidMyListsPageObject;
import Lib.ui.ios.iOSMyListsPageObject;
import io.appium.java_client.AppiumDriver;

public class MyListsPageObjectFactory
{
    public static MyListsPageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);
        } else {
            return new iOSMyListsPageObject(driver);
        }
    }
}
