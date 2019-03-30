import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Jonathan Osborne (jwo0010@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  08/26/18
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
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) throws IllegalArgumentException {
      if (a == null || a.length < 1) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) throws IllegalArgumentException {
      if (a == null || a.length < 1) {
         throw new IllegalArgumentException();
      }
      int max = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) throws IllegalArgumentException {
      if (a == null || a.length < 1) {
         throw new IllegalArgumentException();
      }
      
      if (k > a.length || k < 1) {
         throw new IllegalArgumentException();
      }
      
      int[] b = new int[a.length];
      
      for (int i = 0; i < a.length; i++) {
         b[i] = a[i];
      }
      
      Arrays.sort(b);
      int count = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            count++;
         }
      }
      
      if (k > count) {
         throw new IllegalArgumentException();
      }
      
      int number = 0;
      int numberOn = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            numberOn++;
            if (numberOn == k) {
               number = b[i];
            }
         }
      }
      
      if (k == 1) {
         number = b[0];
      }
      return number;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) throws IllegalArgumentException {
      if (a == null || a.length < 1) {
         throw new IllegalArgumentException();
      }
      
      if (k > a.length || k < 1) {
         throw new IllegalArgumentException();
      }
      
      int[] b = new int[a.length];
      
      for (int i = 0; i < a.length; i++) {
         b[i] = a[i];
      }
      
      Arrays.sort(b);
      int count = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            count++;
         }
      }
      
      if (k > count) {
         throw new IllegalArgumentException();
      }
      int number = 0;
      int numberOn = 1;
      for (int i = b.length - 1; i > 0; i--) {
         if (b[i] != b[i - 1]) {
            numberOn++;
            if (numberOn == k) {
               number = b[i - 1];
            }
         }
      }
      
      if (k == 1) {
         number = b[b.length - 1];
      }
      return number;
   }
      


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) 
      throws IllegalArgumentException {
      if (a == null || a.length < 1) {
         throw new IllegalArgumentException();
      }
      int inRange = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= high & a[i] >= low) {
            inRange++;
         }
      }
      int[] rangeOf = new int[inRange];
      int current = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= high & a[i] >= low) {
            rangeOf[current] = a[i];
            current++;
         }
      }
      return rangeOf;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) throws IllegalArgumentException {
      if (a == null || a.length < 1) {
         throw new IllegalArgumentException();
      }
      
      int ceiling = a[0];
      int j = 0;
      while (ceiling < key) {
         j++;
         if (j >= a.length) {
            throw new IllegalArgumentException();
         }
         ceiling = a[j];
      }
      for (int i = j; i < a.length; i++) {
         if (a[i] == key) {
            return key;
         }
         if (a[i] <= ceiling & a[i] >= key) {
            ceiling = a[i];
         }
      }
      return ceiling;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) throws IllegalArgumentException {
      if (a == null || a.length < 1) {
         throw new IllegalArgumentException();
      }
      
      int floor = a[0];
      int j = 0;
      while (floor > key) {
         j++;
         if (j >= a.length) {
            throw new IllegalArgumentException();
         }
         floor = a[j];
         
      }
      for (int i = j; i < a.length; i++) {
         if (a[i] == key) {
            return key;
         }
         if (a[i] >= floor & a[i] <= key) {
            floor = a[i];
         }
      }
      return floor;
   }

}
