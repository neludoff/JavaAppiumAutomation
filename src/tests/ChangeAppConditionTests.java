package tests;

import Lib.CoreTestCase;
import Lib.ui.ArticlePageObject;
import Lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitile();

        try {
            this.rotateScreenLandscape();
            String title_after_rotation = ArticlePageObject.getArticleTitile();

            assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_rotation
            );

            this.rotateScreenPortrait();

        } catch (Exception e){
            System.out.println("Handling exception. Return screen orientation to portrait mode");
            this.rotateScreenPortrait();
        }

        String title_after_2nd_rotation = ArticlePageObject.getArticleTitile();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_2nd_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
