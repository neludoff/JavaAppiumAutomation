package tests;

import Lib.CoreTestCase;
import Lib.ui.MainPageObject;
import Lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    // Get rid of MainPageObject from this class
    private Lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    };

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDissappear();
    }

    @Test
    public void testCompareElementText(){
        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "We see unexpected element text"
        );
    }

    @Test
    public void testSearchCancel(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Cyberpunk",
                "Can't find field 'Input'",
                5
        );

        List<WebElement> elements = MainPageObject.waitForElementsPresent(
                By.xpath("//*[contains(@text,'Cyberpunk')]"),
                "Cannot find any 'Cyberpunk' topic",
                5
        );

        assertTrue(
                "Can't find any searched topic",
                elements.size() > 2
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find clear button",
                5
        );

        assertTrue(
                "Some searched articles presented on a page",
                MainPageObject.waitForElementNotPresent(
                        By.xpath("//*[contains(@text,'Cyberpunk')]"),
                        "Cannot find any 'Cyberpunk' topic",
                        5
                )
        );
    }

    @Test
    public void testCheckWordsInSearchResult(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Can't find field 'Input'",
                5
        );

        List<WebElement> elements = MainPageObject.waitForElementsPresent(
                By.xpath("//*[contains(@text,'Java')]"),
                "Cannot find any 'Java' topic",
                5
        );

        for(WebElement object: elements){
            assertTrue(
                    "Can't find any topic which contains 'Java'",
                    object.getAttribute("text").contains("Java"));
        }
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Can't find any results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "qweqddwqw11";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
