import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;

public class SelectorTest {


   /** Tests for the min method. **/
   @Test public void minTest() {
      Assert.assertSame(2, Selector.min(HandoutExamples.c1, HandoutExamples.ascendingInteger));
   }
   
   @Test public void minTest1() {
      Assert.assertSame(9, Selector.min(HandoutExamples.c2, HandoutExamples.descendingInteger));
   }
   
   @Test public void minTest2() {
      Assert.assertSame(4, Selector.min(HandoutExamples.c3, HandoutExamples.ascendingInteger));
   }
   
   @Test public void minTest3() {
      Assert.assertEquals(new HandoutExamples.Data("A" , 5).toString() , 
         Selector.min(HandoutExamples.c4, HandoutExamples.ascendingStringData).toString());
   }
   
   @Test public void minTest4() {
      Assert.assertEquals(new HandoutExamples.Data("E" , 1).toString() , 
         Selector.min(HandoutExamples.c4, HandoutExamples.ascendingIntegerData).toString());
   }
   
   
   
   /** Tests for the max method in Selector. **/
   @Test public void maxTest() {
      Assert.assertSame(8, Selector.max(HandoutExamples.c1, HandoutExamples.ascendingInteger));
   }
   
   @Test public void maxTest1() {
      Assert.assertSame(1, Selector.max(HandoutExamples.c2, HandoutExamples.descendingInteger));
   }
   
   @Test public void maxTest2() {
      Assert.assertSame(8, Selector.max(HandoutExamples.c3, HandoutExamples.ascendingInteger));
   }
   
   @Test public void maxTest3() {
      Assert.assertEquals(new HandoutExamples.Data("E" , 1).toString() , 
         Selector.max(HandoutExamples.c4, HandoutExamples.ascendingStringData).toString());
   }
   
   @Test public void maxTest4() {
      Assert.assertEquals(new HandoutExamples.Data("A" , 5).toString() , 
         Selector.max(HandoutExamples.c4, HandoutExamples.ascendingIntegerData).toString());
   }
   
    /** Tests for kmin method. **/
   @Test public void kminTest() {
      Assert.assertSame(2, Selector.kmin(HandoutExamples.c1, 1, HandoutExamples.ascendingInteger));
   }
   
   @Test public void kminTest1() {
      Assert.assertSame(7, Selector.kmin(HandoutExamples.c2, 2, HandoutExamples.descendingInteger));
   }
   
   @Test public void kminTest2() {
      Assert.assertSame(6, Selector.kmin(HandoutExamples.c3, 3, HandoutExamples.ascendingInteger));
   }
   
   @Test public void kminTest3() {
      Assert.assertEquals(new HandoutExamples.Data("D" , 2).toString() , 
         Selector.kmin(HandoutExamples.c4, 4, HandoutExamples.ascendingStringData).toString());
   }
   
   @Test public void kminTest4() {
      Assert.assertEquals(new HandoutExamples.Data("D" , 2).toString() , 
         Selector.kmin(HandoutExamples.c4, 2, HandoutExamples.ascendingIntegerData).toString());
   }
   
   @Test public void kminTest5() {
     
   
      Collection<Integer> myCol = Arrays.<Integer>asList(new Integer[]{5,7});
      
      Assert.assertSame(7 , 
         Selector.kmin(myCol, 2, HandoutExamples.ascendingInteger));
   }

   @Test public void kminTest6() {
     
   
      Collection<Integer> myCol = Arrays.<Integer>asList(new Integer[]{1,3,5,7,9});
      
      Assert.assertSame(9 , 
         Selector.kmin(myCol, 5, HandoutExamples.ascendingInteger));
   }
   
   
   /**Tests for kmax method. **/
   
   @Test public void kmaxTest() {
      Assert.assertSame(8, Selector.kmax(HandoutExamples.c1, 1, HandoutExamples.ascendingInteger));
   }
   
   @Test public void kmaxTest1() {
      Assert.assertSame(3, Selector.kmax(HandoutExamples.c2, 2, HandoutExamples.descendingInteger));
   }
   
   @Test public void kmaxTest2() {
      Assert.assertSame(6, Selector.kmax(HandoutExamples.c3, 3, HandoutExamples.ascendingInteger));
   }
   
   @Test public void kmaxTest3() {
      Assert.assertEquals(new HandoutExamples.Data("B" , 4).toString() , 
         Selector.kmax(HandoutExamples.c4, 4, HandoutExamples.ascendingStringData).toString());
   }
   
   @Test public void kmaxTest4() {
      Assert.assertEquals(new HandoutExamples.Data("B" , 4).toString() , 
         Selector.kmax(HandoutExamples.c4, 2, HandoutExamples.ascendingIntegerData).toString());
   }
   
   
   @Test public void kmaxTest5() {
     
   
      Collection<Integer> myCol = Arrays.<Integer>asList(new Integer[]{3,7,3,3,1,9,1,1,1,5});
      
      Assert.assertSame(7 , 
         Selector.kmax(myCol, 2, HandoutExamples.ascendingInteger));
   }

    /** Tests for range method. **/
   @Test public void rangeTest() {
      Collection<Integer> ans = Arrays.<Integer>asList(new Integer[]{2,3,4});
      Assert.assertTrue(ans.equals(Selector.range(HandoutExamples.c1, 1, 5, HandoutExamples.ascendingInteger)));
   }
   
   @Test public void rangeTest1() {
      Collection<Integer> ans = Arrays.<Integer>asList(new Integer[]{5,3});
      Assert.assertTrue(ans.equals(Selector.range(HandoutExamples.c2, 5, 3, HandoutExamples.descendingInteger)));
   }
   
   @Test public void rangeTest2() {
      Collection<Integer> ans = Arrays.<Integer>asList(new Integer[]{8,7,6,5,4});
      Assert.assertTrue(ans.equals(Selector.range(HandoutExamples.c3, 4, 8, HandoutExamples.ascendingInteger)));
   }
   /*
   @Test public void rangeTest3() {
   Collection<HandoutExamples.Data> ans = Arrays.<HandoutExamples.Data>asList(new HandoutExamples.Data[]{("B", 4),("C", 3)});
      Assert.assertSame(ans, 
         Selector.range(HandoutExamples.c4, 4, 8, HandoutExamples.ascendingStringData));
   }
   /*
   @Test public void rangeTest4() {
   Collection<Integer> ans = Arrays.<Integer>asList(new Integer[]{2,3,4});
      Assert.assertSame(new HandoutExamples.Data("B" , 4).toString() , 
         Selector.range(HandoutExamples.c4, 2, HandoutExamples.ascendingIntegerData).toString());
   }*/
   
   /* Tests for ceiling method */
   @Test public void ceilingTest() {
      Assert.assertSame(2, Selector.ceiling(HandoutExamples.c1, 1, HandoutExamples.ascendingInteger));
   }
   
   @Test public void ceilingTest1() {
      Assert.assertSame(7, Selector.ceiling(HandoutExamples.c2, 7, HandoutExamples.descendingInteger));
   }
   
   @Test public void ceilingTest2() {
      Assert.assertSame(4, Selector.ceiling(HandoutExamples.c3, 0, HandoutExamples.ascendingInteger));
   }
   /*
   @Test public void ceilingTest3() {
      Assert.assertEquals(new HandoutExamples.Data("B" , 4).toString() , 
         Selector.ceiling(HandoutExamples.c4, ("B", 9), HandoutExamples.ascendingStringData).toString());
   }
   
   @Test public void ceilingTest4() {
      Assert.assertEquals(new HandoutExamples.Data("E" , 1).toString() , 
         Selector.ceiling(HandoutExamples.c4, ("F", 0) HandoutExamples.ascendingIntegerData).toString());
   }*/
   
   /* Tests for floor method */
   @Test public void floorTest() {
      Assert.assertSame(4, Selector.floor(HandoutExamples.c1, 6, HandoutExamples.ascendingInteger));
   }
   
   @Test public void floorTest1() {
      Assert.assertSame(1, Selector.floor(HandoutExamples.c2, 1, HandoutExamples.descendingInteger));
   }
   
   @Test public void floorTest2() {
      Assert.assertSame(8, Selector.floor(HandoutExamples.c3, 9, HandoutExamples.ascendingInteger));
   }
   /*
   @Test public void floorTest3() {
      Assert.assertEquals(new HandoutExamples.Data("B" , 4).toString() , 
         Selector.floor(HandoutExamples.c4, ("B", 9), HandoutExamples.ascendingStringData).toString());
   }
   
   @Test public void floorTest4() {
      Assert.assertEquals(new HandoutExamples.Data("E" , 1).toString() , 
         Selector.floor(HandoutExamples.c4, ("F", 0) HandoutExamples.ascendingIntegerData).toString());
   }*/

}