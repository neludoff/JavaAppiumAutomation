package Lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can't find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Can't find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Can't find search cancel button",5);
    }

    public void waitForCancelButtonToDissappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Can't find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Can't find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Can't find search result with substring '" + substring + "'.");
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Can't find and click search result with substring '" + substring + "'.",5);
    }

    public void clickByArticleWithTitle(String article_title)
    {
        this.waitForElementAndClick("//*[@text='" + article_title + "']", "Can't find article by title '" + article_title + "'", 5);
    }

    public int getAmountOfFoundArticles()
    {
        try {
            this.waitForElementPresent(
                    SEARCH_RESULT_ELEMENT,
                    "Can't find anything by request ",
                    15
            );
            return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
        } catch(Exception e){
            return 0;
        }
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Can't find empty result element",15 );
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.asserElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public List<WebElement> getArticles()
    {
        return waitForElementsPresent(SEARCH_RESULT_ELEMENT, "Can't find any article",5);
    }
}
