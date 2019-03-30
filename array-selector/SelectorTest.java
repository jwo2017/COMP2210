import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test for Selector class. **/
   @Test public void minTest() {
      int[] a = {0, 1, 2, 3};
      int expected = 0;
      Assert.assertEquals(expected, Selector.min(a));
   }
   
   /** A test for Selector class. **/
   @Test public void minTest2() {
      int[] a = {3, 2, 1, 0};
      int expected = 0;
      Assert.assertEquals(expected, Selector.min(a));
   }
   
   /** A test for Selector class. **/
   @Test public void minTest3() {
      int[] a = {1, 0, 3, 2};
      int expected = 0;
      Assert.assertEquals(expected, Selector.min(a));
   }
   
   /** A test for Selector class. **/
   @Test public void minTest4() {
      int[] a = {1, 1, 2, 3};
      int expected = 1;
      Assert.assertEquals(expected, Selector.min(a));
   }
   
   /** A test for Selector class. **/
   @Test public void minTest5() {
      int[] a = {0, 1, 2, 3};
      int expected = 0;
      Assert.assertEquals(expected, Selector.min(a));
   }
   
   
   /** A test for Selector class. **/
   @Test public void rangeTest() {
      int[] a = {0, 1, 2, 3};
      int[] expected = {1, 2};
      Assert.assertArrayEquals(expected, Selector.range(a, 1, 2));
   }
   
   /** A test for Selector class. **/
   @Test public void rangeTest2() {
      int[] a = {0, 1, 2, 3, 4, 5, 6, 2, 32, 4, 2, 6, 9, 10};
      int[] expected = {3, 4, 5, 6, 4, 6, 9, 10};
      Assert.assertArrayEquals(expected, Selector.range(a, 3, 10));
   }
   
   @Test public void ceilingTest() {
      int[] a = {0, 1, 2, 3, 4, 5, 6, 2, 32, 4, 2, 6, 9, 10};
      Assert.assertEquals(3, Selector.ceiling(a, 3));
   }
   
   @Test public void ceilingTest2() {
      int[] a = {2, 8, 7, 3, 4};
      Assert.assertEquals(2, Selector.ceiling(a, 1));
   }
   
   @Test public void ceilingTest3() {
      int[] a = {5, 9, 1, 7, 3};
      Assert.assertEquals(7, Selector.ceiling(a, 7));
   }
   
   @Test public void ceilingTest4() {
      int[] a = {8, 7, 6, 5, 4};
      Assert.assertEquals(4, Selector.ceiling(a, 0));
   }
   
   @Test public void floorTest() {
      int[] a = {0, 1, 2, 3, 4, 5, 6, 2, 32, 4, 2, 6, 9, 10};
      Assert.assertEquals(3, Selector.floor(a, 3));
   }
   
   @Test public void floorTest2() {
      int[] a = {2, 8, 7, 3, 4};
      Assert.assertEquals(4, Selector.floor(a, 6));
   }
   
   @Test public void floorTest3() {
      int[] a = {5, 9, 1, 7, 3};
      Assert.assertEquals(5, Selector.floor(a, 6));
   }
   
   @Test public void floorTest4() {
      int[] a = {8, 7, 6, 5, 4};
      Assert.assertEquals(8, Selector.floor(a, 9));
   }
   
   @Test public void kminTest() {
      int[] a = {0, 1, 2, 3, 4, 5, 6, 2, 32, 4, 2, 6, 9, 10};
      Assert.assertEquals(2, Selector.kmin(a, 3));
   }
   
   @Test public void kminTest2() {
      int[] a = {2, 8, 7, 3, 4};
      Assert.assertEquals(2, Selector.kmin(a, 1));
   }
   
   @Test public void kminTest3() {
      int[] a = {5, 9, 1, 7, 3};
      Assert.assertEquals(5, Selector.kmin(a, 3));
   }
   
   @Test public void kminTest4() {
      int[] a = {8, 7, 6, 5, 4};
      Assert.assertEquals(8, Selector.kmin(a, 5));
   }
   
   @Test public void kmaxTest() {
      int[] a = {0, 1, 2, 3, 4, 5, 6, 2, 32, 4, 2, 6, 9, 10};
      Assert.assertEquals(9, Selector.kmax(a, 3));
   }
   
   @Test public void kmaxTest2() {
      int[] a = {2, 8, 7, 3, 4};
      Assert.assertEquals(8, Selector.kmax(a, 1));
   }
   
   @Test public void kmaxTest3() {
      int[] a = {5, 9, 1, 7, 3};
      Assert.assertEquals(5, Selector.kmax(a, 3));
   }
   
   @Test public void kmaxTest4() {
      int[] a = {8, 7, 6, 5, 4};
      Assert.assertEquals(4, Selector.kmax(a, 5));
   }
   
   
   
}
