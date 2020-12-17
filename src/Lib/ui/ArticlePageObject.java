package Lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//*[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_MAIN_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Can't find article on page",15);
    }

    public String getArticleTitile()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Can't find the end of the element",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find element 'More options'",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find element 'Add to reading list'",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' overlay",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_MAIN_INPUT),
                "Can't find input field to set articles folder",
                5
        );


        this.waitForElementAndSendKeys(
                By.id(MY_LIST_MAIN_INPUT),
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Can't press 'OK' button",
                5
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can't find navigation button to My list",
                5
        );
    }
}
