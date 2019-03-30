import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class DoubletsTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void HammingDistanceTest() throws FileNotFoundException {
      Doublets n = new Doublets(new FileInputStream(new File("tiny.txt")));
      Assert.assertEquals(1, n.getHammingDistance("cat", "can"));
   }
}
