package tests;

import Lib.CoreTestCase;
import Lib.ui.ArticlePageObject;
import Lib.ui.MainPageObject;
import Lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticleTests extends CoreTestCase {

    // Get rid of MainPageObject from this class
    private Lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    };

    @Test
    public void testCompareArticleTitle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitile();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
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
