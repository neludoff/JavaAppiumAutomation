package tests.iOS;

import Lib.iOSTestCase;
import Lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExplore();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLandText();
        WelcomePage.clickNextButton();

        WelcomePage.waitLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }

}
