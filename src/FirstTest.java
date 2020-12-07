import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/nvv/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Can't find skip button 'fragment_onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search element",
                5
        );

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Can't find field 'Input'",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5
        );
    }

    @Test
    public void testCancelSearch(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Can't find skip button 'fragment_onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Can't find field 'Input'",
                5
        );

        waitForElementAndClear(
                By.id("search_src_text"),
                "Can't find field 'Input' for clear it",
                5
        );

        waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageButton"),
                "Cannot find return element",
                5
        );

        waitForElementNotPresent(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageButton"),
                "Return element is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Can't find field 'Input'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find search element 'Input'",
                5
        );

        WebElement title_element = waitForElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
                    "Can't find article title",
                    15
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testCompareElementText(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Can't find skip button 'fragment_onboarding_skip_button'",
                5
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "We see unexpected element text"
        );
    }

    @Test
    public void testSearchCancel(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Can't find skip button 'fragment_onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Cyberpunk",
                "Can't find field 'Input'",
                5
        );

        List<WebElement> elements = waitForElementsPresent(
                By.xpath("//*[contains(@text,'Cyberpunk')]"),
                "Cannot find any 'Cyberpunk' topic",
                5
        );

        assertTrue(
                "Can't find any searched topic",
                elements.size() > 2
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find clear button",
                5
        );

        assertTrue(
                "Some searched articles presented on a page",
                waitForElementNotPresent(
                        By.xpath("//*[contains(@text,'Cyberpunk')]"),
                        "Cannot find any 'Cyberpunk' topic",
                        5
                )
        );
    }

    @Test
    public void testCheckWordsInSearchResult(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Can't find skip button 'fragment_onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Can't find field 'Input'",
                5
        );

        List<WebElement> elements = waitForElementsPresent(
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
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Appium",
                "Can't find field 'Input'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find search element 'Input'",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article header",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Can't find the end of the article",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Java";

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find element 'More options'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find element 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find input field to set articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press 'OK' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Can't close article. Can't find 'X' link",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='My lists']"),
                "Can't find navigation button to My list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can't find created folder",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
        {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Linkin park discography";

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Can't find anything by request " + search_line,
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "Can't find any results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "kotlin";
        waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";

        String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Can't find empty result label by the request " + search_line,
                15
        );

        asserElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationSearchResults()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        String title_before_rotation = waitForElementAndGetAttribute(
               By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find title of the article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
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

        String title_after_2nd_rotation = waitForElementAndGetAttribute(
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
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
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
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        String search_line = "Kotlin";
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        List<WebElement> listOfArticles = waitForElementsPresent(
                By.xpath(search_result_locator),
                "Can't find any topic by '" + search_line + "'.",
                10
        );

        // Add 1st article to the reading list
        listOfArticles.get(0).click();

        String first_article_title = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find first article title",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find element 'More options'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find element 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find input field to set articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press 'OK' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Can't close article. Can't find 'X' link",
                5
        );

        // Add 2nd article to reading list
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search element 'Input'",
                5
        );

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                search_line,
                "Can't find field 'Input'",
                5
        );

        listOfArticles = waitForElementsPresent(
                By.xpath(search_result_locator),
                "Can't find any topic by '" + search_line + "'.",
                10
        );

        listOfArticles.get(1).click();

        String second_article_title = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find article title",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find element 'More options'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find element 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' reading list",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Can't close article. Can't find 'X' link",
                5
        );

        // Check that 2 articles are in the reading list
        waitForElementAndClick(
                By.xpath("//*[@content-desc='My lists']"),
                "Can't find navigation button to My list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can't find created folder",
                10
        );

        listOfArticles = waitForElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Can't find any topic by '" + search_line + "'.",
                10
        );

        assertTrue("There are less than 2 articles in the reading list '" + name_of_folder + "'", listOfArticles.size()>=2);

        // Delete 1st article
        swipeElementToLeft(
                By.xpath("//*[@text='" + first_article_title + "']"),
                "Can't find 1st saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + first_article_title + "']"),
                "1st saved article still presented in the reading list '" + name_of_folder + "'.",
                5
        );

        //
        // Step 3. Check that 2nd article is in the list
        //
        waitForElementPresent(
                By.xpath("//*[@text='" + second_article_title + "']"),
                "2nd saved article is not presented in the reading list '" + name_of_folder + "'.",
                5
        );

        //
        // Step 4. Check that title for 2nd article didn't changed
        //
        String second_article_title_after_delete = waitForElementAndGetAttribute(
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

    private List<WebElement> waitForElementsPresent(By by, String error_message, int tiomeoutinseconds){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver, tiomeoutinseconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, int tiomeoutinseconds){
        WebDriverWait wait = new WebDriverWait(driver, tiomeoutinseconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, int timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, int timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, int timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private boolean assertElementHasText(By by, String value, String error_message){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.attributeContains(by,"text",value)
        );
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);

        action
                .press(x,start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x,end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if(already_swiped > max_swipes){
                waitForElementPresent(by, "Can't find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x+element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void asserElementNotPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + ". " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, int timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
