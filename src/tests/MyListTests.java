package tests;

import Lib.CoreTestCase;
import Lib.Platform;
import Lib.ui.*;
import Lib.ui.factories.ArticlePageObjectFactory;
import Lib.ui.factories.MyListsPageObjectFactory;
import Lib.ui.factories.NavigationUIFactory;
import Lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyListTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitile();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        SearchPageObject.clickCancelSearch();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isiOS()){
            MyListPageObject.closeSyncSavedArticlesPopUp();
        }

        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }

        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    // Ex5, Ex11
    @Test
    public void testSaveTwoArticlesToMyList(){

        //
        // Step 1. Save 2 articles into folder
        //
        // Add 1st article to the reading list
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitile();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        SearchPageObject.clickCancelSearch();

        // Go to MyLists/Saved and get article elementId
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isiOS()){
            MyListPageObject.closeSyncSavedArticlesPopUp();
        }

        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }

        // get article subtitle name by XCUIElementTypeImage.
        String first_article_name = MyListPageObject.getElementsIdByImage().get(1).getAttribute("name");

        if(Platform.getInstance().isiOS()){
            MyListPageObject.clickSearchButton();
        }

        // Add 2nd article to reading list
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Indonesian island");

        // String second_article_title = ArticlePageObject.getArticleTitile();
        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        SearchPageObject.clickCancelSearch();

        // Check that 2 articles are in the reading list
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }

        List<WebElement> listOfArticles = SearchPageObject.getArticles();

        assertTrue("There are less than 2 articles in the reading list", listOfArticles.size()>=2);

        // get articles names by by XCUIElementTypeImage
        String first_article_name_after_adding_2nd_article = MyListPageObject.getElementsIdByImage().get(3).getAttribute("name");
        String second_article_name = MyListPageObject.getElementsIdByImage().get(1).getAttribute("name");

        //Assert.assertTrue("First article don't present on in the list", first_article_name == first_article_name_after_adding_2nd_article);
        Assert.assertTrue("First article don't present on in the list", first_article_name.equals(first_article_name_after_adding_2nd_article));

        // Delete 1st article
        MyListPageObject.swipeByArticleToDelete(first_article_title);

        //
        // Step 3. Check that 2nd article is in the list
        String second_article_name_after_delete_1st_article = MyListPageObject.getElementsIdByImage().get(1).getAttribute("name");

        Assert.assertTrue("Second article don't present in the list", second_article_name.equals(second_article_name_after_delete_1st_article));
    }
}