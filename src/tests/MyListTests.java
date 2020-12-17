package tests;

import Lib.CoreTestCase;
import Lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyListTests extends CoreTestCase {

    // Get rid of MainPageObject from this class
    private Lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    };

    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitile();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        // Don't work click on folder name
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
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

        assertEquals(
                "Title for 2nd article has been changed",
                second_article_title,
                second_article_title_after_delete);
    }

}
