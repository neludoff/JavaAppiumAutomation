package Lib.ui.android;

import Lib.ui.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTIONS_BUTTON = "xpath://*[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_MAIN_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
