import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void CheckLocalNumber()
    {
        Assert.assertTrue("Return number is 14", this.getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Returned value greater than 45", this.getClassNumber() > 45);
    }
}
