import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void CheckLocalNumber() {
        Assert.assertEquals("Return number is 14", 14, this.getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Returned value smaller or equal 45", this.getClassNumber() > 45);
    }

    @Test
    public void testGetClassString() {
        Assert.assertTrue("String doesn't contain 'Hello' or 'hello' words", this.getClassString().contains("hello") || this.getClassString().contains("Hello"));

    }
}
