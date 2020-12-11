import Lib.CoreTestCase;
import Lib.ui.ArticlePageObject;
import Lib.ui.MainPageObject;
import Lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

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
    public void testCompareArticleTitle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitile();

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
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
    public void testSwipeArticle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testSaveFirstArticleToMyList(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find element 'More options'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find element 'Add to reading list'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find input field to set articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press 'OK' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Can't close article. Can't find 'X' link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='My lists']"),
                "Can't find navigation button to My list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can't find created folder",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't find saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
        {
            MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Linkin park discography";

            MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";

            MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Can't find anything by request " + search_line,
                15
        );

        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "Can't find any results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "kotlin";
        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";

        String empty_result_label = "//*[@text='No results found']";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Can't find empty result label by the request " + search_line,
                15
        );

        MainPageObject.asserElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationSearchResults()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
               By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find title of the article",
                15
        );

        try {
            driver.rotate(ScreenOrientation.LANDSCAPE);

            String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "text",
                    "Can't find title of the article",
                    15
            );

            Assert.assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_rotation
            );

            driver.rotate(ScreenOrientation.PORTRAIT);
        } catch (Exception e){
            System.out.println("Handling exception. Return screen orientation to portrait mode");
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

        String title_after_2nd_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find title of the article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_2nd_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        driver.runAppInBackground(2);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find article after returning from background.",
                15
        );
    }

    @Test
    public void testSaveTwoArticlesToMyList(){

        //
        // Step 1. Save 2 articles into folder
        //
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Kotlin";
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";

        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        List<WebElement> listOfArticles = MainPageObject.waitForElementsPresent(
                By.xpath(search_result_locator),
                "Can't find any topic by '" + search_line + "'.",
                10
        );

        // Add 1st article to the reading list
        listOfArticles.get(0).click();

        String first_article_title = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find first article title",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find element 'More options'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find element 'Add to reading list'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find input field to set articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press 'OK' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Can't close article. Can't find 'X' link",
                5
        );

        // Add 2nd article to reading list
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        listOfArticles = MainPageObject.waitForElementsPresent(
                By.xpath(search_result_locator),
                "Can't find any topic by '" + search_line + "'.",
                10
        );

        listOfArticles.get(1).click();

        String second_article_title = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find article title",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find element 'More options'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find element 'Add to reading list'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' reading list",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Can't close article. Can't find 'X' link",
                5
        );

        // Check that 2 articles are in the reading list
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@content-desc='My lists']"),
                "Can't find navigation button to My list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can't find created folder",
                10
        );

        listOfArticles = MainPageObject.waitForElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Can't find any topic by '" + search_line + "'.",
                10
        );

        assertTrue("There are less than 2 articles in the reading list '" + name_of_folder + "'", listOfArticles.size()>=2);

        // Delete 1st article
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='" + first_article_title + "']"),
                "Can't find 1st saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='" + first_article_title + "']"),
                "1st saved article still presented in the reading list '" + name_of_folder + "'.",
                5
        );

        //
        // Step 3. Check that 2nd article is in the list
        //
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='" + second_article_title + "']"),
                "2nd saved article is not presented in the reading list '" + name_of_folder + "'.",
                5
        );

        //
        // Step 4. Check that title for 2nd article didn't changed
        //
        String second_article_title_after_delete = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='" + second_article_title + "']"),
                "text",
                "2nd saved article is not presented in the reading list '" + name_of_folder + "'.",
                5
        );

        Assert.assertEquals(
                "Title for 2nd article has been changed",
                second_article_title,
                second_article_title_after_delete);
    }

    @Test
    public void testSimpleRotation()
    {
       int d, a;
        try {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            d = 0;
            a = 3 / d;
             System.out.println("This will not be printed");

        }
        catch (Exception e){
            System.out.println("Exception handling");
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

    // Ex6
    @Test
    public void testAssertTitle()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "chief technology officer";

        MainPageObject.waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'technological issues')]"),
                "Cannot find 'Chief technology officer' topic searching by " + search_line,
                5
        );

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Article doesn't have title"
        );
    }
}
