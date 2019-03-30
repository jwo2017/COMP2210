import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Jonathan Osborne (jwo0010@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version September 6, 2018
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) 
      throws IllegalArgumentException {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      T min = coll.iterator().next();
      
      for (T elem : coll) {
         if (comp.compare(elem, min) < 0)  {
            min = elem;
         }
      }
   
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp)
      throws IllegalArgumentException {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      T max = coll.iterator().next();
      
      for (T elem : coll) {
         if (comp.compare(max, elem) < 0)  {
            max = elem;
         }
      }
   
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) 
      throws IllegalArgumentException, NoSuchElementException {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      
      List<T> temp = new ArrayList<T>();
      temp.addAll(coll);
      java.util.Collections.sort(temp, comp);
      int index = 0;
      if (k == 1) {
         return temp.get(0);
      }
      
      int count = 1;
      for (int i = 1; i < temp.size(); i++) {
         if (comp.compare(temp.get(i), temp.get(i - 1)) != 0) {
            count++;
         }
      }
      
      if (k > count) {
         throw new NoSuchElementException();
      }
      
      int numberOn = 1;
      for (int i = 1; i < temp.size(); i++) {
         if (comp.compare(temp.get(i), temp.get(i - 1)) != 0) {
            numberOn++;
            if (numberOn == k) {
               index = i;
            }
         }
      }
     
      return temp.get(index);
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) 
      throws IllegalArgumentException, NoSuchElementException {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      
      List<T> temp = new ArrayList<T>();
      temp.addAll(coll);
      java.util.Collections.sort(temp, comp);
      int index = 0;
      
      int count = 1;
      for (int i = 1; i < temp.size(); i++) {
         if (comp.compare(temp.get(i), temp.get(i - 1)) != 0) {
            count++;
         }
      }
      
      if (k > count) {
         throw new NoSuchElementException();
      }
      
      int numberOn = 1;
      for (int i = temp.size() - 1; i > 0; i--) {
         if (comp.compare(temp.get(i), temp.get(i - 1)) != 0) {
            numberOn++;
            if (numberOn == k) {
               index = i - 1;
            }
         }
      }
      
      if (k == 1) {
         index = temp.size() - 1;
      }
     
      return temp.get(index);
   }   

   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) 
                                         throws IllegalArgumentException, 
                                         NoSuchElementException {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
   
                                         
      Collection<T> range = new ArrayList<T>();
                                         
      for (T elem : coll) {
         if (comp.compare(elem, low) >= 0 & comp.compare(elem, high) <= 0) {
            range.add(elem);
         }
      }
      
      if (coll.isEmpty() || range.isEmpty()) {
         throw new NoSuchElementException();
      }
      return range;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) 
       throws IllegalArgumentException, NoSuchElementException {
       
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
   
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      Iterator<T> myItr = coll.iterator();
      T currC = myItr.next(); //currC is curr ceiling; initialized to 
                            //first element in the collection using 
                            //iterator.next.
                            
      if (coll.size() == 1) {
         if (comp.compare(currC, key) >= 0) {
            return currC;
         }
         else {
            throw new NoSuchElementException();
         }
      }
      
      if (comp.compare(currC, key) < 0) {
         do { 
            
            currC = myItr.next();
         } while (comp.compare(currC, key) < 0 && myItr.hasNext());
      }
      
      if (!myItr.hasNext()) {
         throw new NoSuchElementException();
      }
      
      for (T elem : coll) {
         if (comp.compare(elem, key) == 0) {
            return key;
         }
         else if (comp.compare(elem, key) >= 0 
               & comp.compare(elem, currC) <= 0) {
            currC = elem;
         }
        
      }
      return currC;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, 
      Comparator<T> comp) 
      throws NoSuchElementException, IllegalArgumentException {
      
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
   
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      Iterator<T> myItr = coll.iterator();
      T currF = myItr.next();
      
      if (coll.size() == 1) {
         if (comp.compare(currF, key) <= 0) {
            return currF;
         }
         else {
            throw new NoSuchElementException();
         }
      }
      
      if (comp.compare(currF, key) > 0) {
         do { 
            
            currF = myItr.next();
            if (comp.compare(currF, key) == 0) {
               return key;
            }
         } while (comp.compare(currF, key) > 0 && myItr.hasNext());
      }
      
      if (!myItr.hasNext() && comp.compare(currF, key) > 0) {
         throw new NoSuchElementException();
      }
      
      for (T elem : coll) {
         if (comp.compare(elem, key) == 0) {
            return key;
         }
         else if (comp.compare(elem, key) <= 0
               & comp.compare(elem, currF) >= 0) {
            currF = elem;
         }
        
      }
      return currF;
   }
   

}
