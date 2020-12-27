package Lib.ui;

import Lib.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_MAIN_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Can't find article on page",15);
    }

    public String getArticleTitile()
    {
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Can't find the end of the element",
                    40
            );
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Can't find the end of the element",
                    40
            );
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find element 'More options'",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find element 'Add to reading list'",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_MAIN_INPUT,
                "Can't find input field to set articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_MAIN_INPUT,
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Can't press 'OK' button",
                5
        );
    }

    public void addArticleToMySaved()
    {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can't find option to add article to reading list",
                5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Can't find close article button",
                5
        );
    }
}
