package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.NavigationUI;
import Lib.ui.android.AndroidNavigationUI;
import Lib.ui.ios.iOSNavigationUI;
import io.appium.java_client.AppiumDriver;

public class NavigationUIFactory
{
    public static NavigationUI get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(driver);
        } else {
            return new iOSNavigationUI(driver);
        }
    }
}
