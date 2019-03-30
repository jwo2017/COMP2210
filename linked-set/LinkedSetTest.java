import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;


public class LinkedSetTest {


   /** A test that always fails. **/
   @Test public void isEmptyTest() {
      LinkedSet<Integer> p = new LinkedSet<Integer>();
      Assert.assertTrue(p.isEmpty());
   }
   
   @Test public void addTest() {
      LinkedSet<Integer> p = new LinkedSet<Integer>();
      Assert.assertTrue(p.add(1));
      p.add(1);
      Assert.assertFalse(p.add(1));
      p.add(2);
   }
   
   @Test public void toStringTest() {
      LinkedSet<Integer> p = new LinkedSet<Integer>();
      Assert.assertEquals("[]", p.toString());
      p.add(1);
      Assert.assertEquals("[1]", p.toString());
      
      p.add(2);
      Assert.assertEquals("[1, 2]", p.toString());
      
      p.add(6);
      Assert.assertEquals("[1, 2, 6]", p.toString());
      
      p.add(4);
      Assert.assertEquals("[1, 2, 4, 6]", p.toString());
   }
   
   
   @Test public void removeTest() {
      LinkedSet<Integer> p = new LinkedSet<Integer>();
      p.add(1);
      p.add(2);
      p.add(6);
      p.add(4);
      p.remove(2);
      Assert.assertEquals("[1, 4, 6]", p.toString());
      Assert.assertFalse(p.remove(3));
      p.remove(1);
      p.remove(6);
      p.remove(4);
      Assert.assertEquals("[]", p.toString());
   }
   
   @Test public void intersectionTest() {
      LinkedSet<Integer> p = new LinkedSet<Integer>();
      LinkedSet<Integer> n = new LinkedSet<Integer>();
      p.add(1);
      p.add(2);
      p.add(6);
      p.add(4);
      n.add(1);
      n.add(56);
      n.add(4);
      n.add(5);
   
      Assert.assertEquals("[1, 4]", n.intersection(p).toString());
      Assert.assertEquals("[1, 4]", p.intersection(n).toString());
      n.add(2);
      Assert.assertEquals("[1, 2, 4]", n.intersection(p).toString());
   }
   
   @Test public void unionTest() {
      LinkedSet<Integer> p = new LinkedSet<Integer>();
      LinkedSet<Integer> n = new LinkedSet<Integer>();
      p.add(1);
      p.add(2);
      p.add(6);
      p.add(4);
      n.add(1);
      n.add(56);
      n.add(4);
      n.add(5);
   
      Assert.assertEquals("[1, 2, 4, 5, 6, 56]", n.union(p).toString());
      n.add(2);
      Assert.assertEquals("[1, 2, 4, 5, 6, 56]", n.union(p).toString());
      LinkedSet<Integer> b = new LinkedSet<Integer>();
      LinkedSet<Integer> s = new LinkedSet<Integer>();
      p.add(8);
      Assert.assertEquals("[1, 2, 4, 5, 6, 8, 56]", n.union(p).toString());
      b.add(1);
      b.add(2);
      b.add(3);
      s.add(1);
      s.add(2);
      s.add(3);
      
      Assert.assertEquals("[1, 2, 3]", b.union(s).toString());
      Assert.assertEquals("[1, 2, 3]", s.union(b).toString());
      s.add(0);
      Assert.assertEquals("[0, 1, 2, 3]", b.union(s).toString());
      LinkedSet<Integer> c = new LinkedSet<Integer>();
      LinkedSet<Integer> d = new LinkedSet<Integer>();
      c.add(1);
      c.add(2);
      c.add(3);
      d.add(3);
      d.add(4);
      d.add(2);
      Assert.assertEquals("[1, 2, 3, 4]", c.union(d).toString());
      LinkedSet<Integer> g = new LinkedSet<Integer>();
      LinkedSet<Integer> h = new LinkedSet<Integer>();
      g.add(1);
      g.add(2);
      g.add(3);
      h.add(6);
      h.add(4);
      h.add(5);
      Assert.assertEquals("[1, 2, 3, 4, 5, 6]", g.union(h).toString());
   }
   
   @Test public void complementTest() {
      LinkedSet<Integer> p = new LinkedSet<Integer>();
      LinkedSet<Integer> n = new LinkedSet<Integer>();
      LinkedSet<Integer> c = new LinkedSet<Integer>();
      p.add(1);
      p.add(2);
      p.add(6);
      p.add(4);
      Assert.assertEquals("[]", n.complement(p).toString());
      Assert.assertEquals("[1, 2, 4, 6]", p.complement(n).toString());
      c.add(1);
      c.add(2);
      c.add(6);
      c.add(4);
      Assert.assertEquals("[]", c.complement(p).toString());
      n.add(1);
      n.add(56);
      n.add(4);
      n.add(5);
   
      Assert.assertEquals("[5, 56]", n.complement(p).toString());
      Assert.assertEquals("[2, 6]", p.complement(n).toString());
      
   }
   
   @Test public void powersetTest() {
      LinkedSet<Integer> p = new LinkedSet<Integer>();
      p.add(1);
      p.add(2);
      p.add(5);
      p.add(4);
      p.add(3);
      Iterator<Set<Integer>> m = p.powerSetIterator();
      Assert.assertEquals("[]", m.next().toString());
      m.next();
      Assert.assertEquals("[2]", m.next().toString());
      Assert.assertEquals("[1, 2]", m.next().toString());
      Assert.assertEquals("[1, 3]", m.next().toString());
      
   }

}
