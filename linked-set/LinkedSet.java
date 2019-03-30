import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Jonathan Osborne (jwo0010auburn.edu)
 * @version 2018-10-04
 *
 */
public class LinkedSet<T extends Comparable<? super T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
   
      Node n = new Node(element);
      if (contains(element) || element == null) {
         return false;
      }
      
      else if (isEmpty()) {
         front = n;
         rear = n;
         size++;
         return true;
      }
      
      else {
         Node p = front;
         while (p.element.compareTo(element) < 0) {
            if (p == rear) {
               n.prev = rear;
               n.prev.next = n;
               rear = n;
               size++;
               return true;
            }
            p = p.next;
         }
         
         if (p == front) {
            n.next = front;
            front.prev = n;
            front = n;
            size++;
            return true;
         }
         
               
         n.prev = p.prev;
         n.next = p;
         p.prev.next = n;
         p.prev = n;
         
      }
     
      size++;
      return true;
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      if (!contains(element)) {
         return false;
      }
      
      if (size == 1) {
         front = null;
         rear = null;
         size = 0;
         return true;
      }
      
      Node n = new Node(element);
      Node p = front;
      while (!p.element.equals(element)) {
         p = p.next;
      }
      if (p == front) {
         p.next.prev = null;
         front = p.next;
         size--;
         return true;
      }
      if (p == rear) {
         rear = p.prev;
         rear.next = null;
         rear.prev = p.prev.prev;
         size--;
         return true;
      }
      p.element = null;
      p.prev.next = p.next;
      p.next.prev = p.prev;
            
         
         
      
      size--;
      return true;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      Node n = this.front;
      while (n != null) {
         if (n.element.equals(element)) {
            return true;
         }
         n = n.next;
      }
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (s != null && this.size() == s.size()) {
         int count = 0;
         Iterator<T> scan = this.iterator();
         while (scan.hasNext()) {
            T elem = scan.next();
            Iterator<T> scanS = s.iterator();
            while (scanS.hasNext()) {
               T sElem = scanS.next();
               if (elem.equals(sElem)) {
                  count++;
               }
            }
         }
         
         if (count == size) {
            return true;
         }
      }
      
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (s != null && this.size() == s.size()) {
         Node n = this.front;
         Node p = s.front;
         for (int i = 1; i <= this.size(); i++) {
            if (!n.element.equals(p.element)) {
               return false;
            }
            n = n.next;
            p = p.next;
         } 
         return true;
      }
      return false;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s) {
      Set<T> temp = new LinkedSet<T>();
      
      if (this.equals(s)) {
         return this;
      }
      if (this.isEmpty()) {
         return s;
      }
      if (s.isEmpty()) {
         return this;
      } 
      
      Iterator<T> scan = this.iterator();
      Iterator<T> scanS = s.iterator();
      while (scan.hasNext()) {
         T elem = scan.next();
         temp.add(elem);
      }
      while (scanS.hasNext()) {
         T sElem = scanS.next();
         temp.add(sElem);   
      }
   
      return temp;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s) {
      LinkedSet<T> l = new LinkedSet<T>();
      if (this.equals(s)) {
         return s;
      }
      if (this.isEmpty()) {
         return s;
      }
      if (s.isEmpty()) {
         return this;
      } 
      
      Node p = this.front;
      Node m = s.front;
      if (this.size() >= s.size()) {
         while (p != null || m != null) {
            if (m == null) {
               l.addToRear(p.element);
               p = p.next;
            }
            else if (p == null && m != null) {
               l.addToRear(m.element);
               m = m.next;
            }
            
            else if (p.element.compareTo(m.element) < 0) {
               l.addToRear(p.element);
               p = p.next;
            }
            else if (p.element.compareTo(m.element) > 0) {
               l.addToRear(m.element);
               m = m.next;
            }
            else {
               l.addToRear(p.element);
               m = m.next;
               p = p.next;
            }
            
         }
      }
      else {
         while (m != null || p != null) {
            if (p == null) {
               l.addToRear(m.element);
               m = m.next;
            }
            else if (p != null && m == null) {
               l.addToRear(m.element);
               m = m.next;
            }
            else if (p.element.compareTo(m.element) < 0) {
               l.addToRear(p.element);
               p = p.next;
            }
            
            else if (p.element.compareTo(m.element) > 0) {
               l.addToRear(m.element);
               m = m.next;
            }
            else {
               l.addToRear(p.element);
               m = m.next;
               p = p.next;
            }
         }
      }
      
      return l;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      LinkedSet<T> temp = new LinkedSet<T>();
   
      if (this.equals(s)) {
         return this;
      }
      if (this.isEmpty()) {
         return this;
      }
      if (s.isEmpty()) {
         return s;
      }
      
      Iterator<T> scan = this.iterator();
      
      while (scan.hasNext()) {
         T elem = scan.next();
         Iterator<T> scanS = s.iterator();
         while (scanS.hasNext()) {
            T sElem = scanS.next();
            if (elem.equals(sElem)) {
               temp.addToRear(elem);
               break;
            }
         }
      }
      return temp;      
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      LinkedSet<T> l = new LinkedSet<T>();
      if (this.equals(s)) {
         return s;
      }
      if (this.isEmpty()) {
         return this;
      }
      if (s.isEmpty()) {
         return s;
      }
      
      Node p = this.front;
      Node m = s.front;
      if (this.size() >= s.size()) {
         while (p != null) {
            if (m == null) {
               if (s.rear.element.compareTo(p.element) == 0) {
                  l.addToRear(p.element);
                  break;
                  
               }
               else {
                  p = p.next;
               }
            }
            else if (p.element.compareTo(m.element) == 0) {
               l.addToRear(p.element);
               p = p.next;
               m = m.next;
            }
            
            else if (p.element.compareTo(m.element) < 0) {
               p = p.next;
            }
            else if (p.element.compareTo(m.element) > 0) {
               m = m.next;
            }
            else {
               m = m.next;
               p = p.next;
            }
            
         }
      }
      else {
         while (m != null) {
            if (p == null) {
               if (this.rear.element.compareTo(m.element) == 0) {
                  l.addToRear(m.element);
                  break;
                  
               }
               else {
                  m = m.next;
               }
            }
            else if (p.element.compareTo(m.element) == 0) {
               l.addToRear(p.element);
               p = p.next;
               m = m.next;
            }
            else if (p.element.compareTo(m.element) < 0) {
               p = p.next;
            }
            
            else if (p.element.compareTo(m.element) > 0) {
               m = m.next;
            }
            else {
               m = m.next;
               p = p.next;
            }
         }
      }
      
      return l;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      LinkedSet<T> temp = new LinkedSet<T>();
   
      if (this.equals(s)) {
         return temp;
      }
      if (this.isEmpty()) {
         return temp;
      }
      if (s.isEmpty()) {
         return this;
      }
      
      Iterator<T> scan = this.iterator();
      
      while (scan.hasNext()) {
         T elem = scan.next();
         Iterator<T> scanS = s.iterator();
         while (scanS.hasNext()) {
            T sElem = scanS.next();
            if (elem.equals(sElem)) {
               break;
            }
            if (!scanS.hasNext()) {
               temp.addToRear(elem);
            }
         }
      }
      return temp;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      LinkedSet<T> l = new LinkedSet<T>();
      if (this.equals(s)) {
         return l;
      }
      if (this.isEmpty()) {
         return l;
      }
      if (s.isEmpty()) {
         return this;
      }
      
      Set<T> is = this.intersection(s);
      LinkedSet<T> i = new LinkedSet<T>();
      for (T elem : is) {
         i.addToRear(elem);
      }
      
      Node tn = this.front;
      Node in = i.front;
      
      if (i.size() == 0) {
         return this;
      }
      
      while (tn != null) {
         if (in == null) {
            l.addToRear(tn.element);
            tn = tn.next;
         }
         else if (tn.element.compareTo(in.element) == 0) {
            in = in.next;
            tn = tn.next;
         }
         else if (tn.element.compareTo(in.element) > 0) {
            in = in.next;
         }
         else if (tn.element.compareTo(in.element) < 0) {
            l.addToRear(tn.element);
            tn = tn.next;
         }
         else if (tn.element.compareTo(in.element) != 0) {
            l.addToRear(tn.element);
            in = in.next;
            tn = tn.next;
         }
      }
      
      return l;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
    
   
   public Iterator<T> iterator() {
      return new LinkedSetIterator();
   }
   

   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new LinkedSetDescendingIterator();
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
    
   public Iterator<Set<T>> powerSetIterator() {
      return new PowerSetIterator();
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   private boolean addToRear(T element) {
      Node n = new Node(element);
     
      if (element == null) {
         return false;
      }
      
      else if (isEmpty()) {
         front = n;
         rear = n;
         size++;
         return true;
      }
      
      n.prev = this.rear.prev;
      n.prev = rear;
      n.prev.next = n;
      rear = n;
      size++;
      return true;
   }

   ////////////////////
   // Nested classes //
   ////////////////////
   
   private class LinkedSetIterator implements Iterator<T> {
   
      private Node current = new Node();
         
      private LinkedSetIterator() {
         current = front;
         
      }
      
      public boolean hasNext() {
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T elem = current.element;
         current = current.next;
         return elem;
      }
      
   }
   
   private class LinkedSetDescendingIterator implements Iterator<T> {
      private Node current = new Node();
         
      private LinkedSetDescendingIterator() {
         current = rear;
         
      }
      
      public boolean hasNext() {
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }   
         T elem = current.element;
         current = current.prev;
         return elem;
      }
   }
   
   private class PowerSetIterator implements Iterator<Set<T>> {
      private Node current = new Node();
      private int currentSet;
         
      private PowerSetIterator() {
         currentSet = 0;
      }
      
      public boolean hasNext() {
         return currentSet != Math.pow(2, size());
      }
      
      public Set<T> next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }   
         LinkedSet<T> n = new LinkedSet<T>();
         if (currentSet == 0) {
            currentSet++;
            return n;
         }
         Node current = front;
         String bString = Integer.toBinaryString(currentSet);
         for (int i = bString.length() - 1; i >= 0; i--) {
            if (current == null) {
               currentSet++;
               return n;
            }
            
            if (bString.charAt(i) == '1') {
               n.addToRear(current.element);
            }
            
            current = current.next;
            
         }
         currentSet++;
         
         return n;
      }
   }

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
