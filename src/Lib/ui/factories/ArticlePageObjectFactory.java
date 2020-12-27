package Lib.ui.factories;

import Lib.Platform;
import Lib.ui.ArticlePageObject;
import Lib.ui.android.AndroidArticlePageObject;
import Lib.ui.ios.iOSArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class ArticlePageObjectFactory
{
    public static ArticlePageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else {
            return new iOSArticlePageObject(driver);
        }
    }
}
