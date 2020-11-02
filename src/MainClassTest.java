import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void CheckLocalNumber()
    {
        Assert.assertTrue("Return number is 14", this.getLocalNumber() == 15);
    }
}
