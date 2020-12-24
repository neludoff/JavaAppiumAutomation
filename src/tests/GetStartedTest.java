package tests;

import Lib.CoreTestCase;
import Lib.Platform;
import Lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid())
        {
            return;
        }

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
