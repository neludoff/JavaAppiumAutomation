package tests;

import Lib.CoreTestCase;
import Lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyListTests extends CoreTestCase {
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

        MyListPageObject.openFolderByName(name_of_folder);

        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    // Ex5
    @Test
    public void testSaveTwoArticlesToMyList(){

        //
        // Step 1. Save 2 articles into folder
        //
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Kotlin");

        List<WebElement> listOfArticles = SearchPageObject.getArticles();

        // Add 1st article to the reading list
        String name_of_folder = "Kotlin lists";
        listOfArticles.get(0).click();
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitile();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        // Add 2nd article to reading list
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Kotlin");
        listOfArticles = SearchPageObject.getArticles();
        listOfArticles.get(1).click();

        String second_article_title = ArticlePageObject.getArticleTitile();

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        // Check that 2 articles are in the reading list
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openMyLists();
        MyListsPageObject.openFolderByName(name_of_folder);

        listOfArticles = SearchPageObject.getArticles();

        assertTrue("There are less than 2 articles in the reading list '" + name_of_folder + "'", listOfArticles.size()>=2);

        // Delete 1st article
        MyListsPageObject.swipeByArticleToDelete(first_article_title);

        //
        // Step 3. Check that 2nd article is in the list
        //
        MyListsPageObject.waitForArticleToAppearByTitle(second_article_title);

        //
        // Step 4. Check that title for 2nd article didn't changed
        //
        SearchPageObject.clickByArticleWithTitle(second_article_title);
        String second_article_title_after_delete = ArticlePageObject.getArticleTitile();

        assertEquals(
                "Title for 2nd article has been changed",
                second_article_title,
                second_article_title_after_delete);
    }
}
