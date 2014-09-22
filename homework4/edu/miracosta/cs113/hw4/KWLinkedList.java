package edu.miracosta.cs113.hw4;
/**
 * Assignment: Week 4 Homework
 * File: KWLinkedList.java
 * 
 * @author Evan Carey
 *         Problem Statement: Complete the class KWLinkedList 
 */

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class KWLinkedList implements a double linked list and
 * a ListIterator.
 * @author Koffman & Wolfgang
 **/
public class KWLinkedList<E> extends AbstractSequentialList<E> {
    // Data Fields

    /** A reference to the head of the list. */
    private Node<E> head = null;
    /** A reference to the end of the list. */
    private Node<E> tail = null;
    /** The size of the list. */
    private int size = 0;

    //Methods
// Insert solution to programming exercise 4, section 8, chapter 2 here
    
    /** Add an object to the beginning of the list.
     * @param obj The object to be added
     */
    public void addFirst(E obj) {
        add(0, obj);
    }
    
    /** Add an object to the end of the list.
     * @param obj The object to be added
     */
    public void addLast(E obj) {
        add(size, obj);
    }
    
    /** Get the object at the beginning of the list.
     * @return The first object in the list
     */
    public E getFirst() {
        return head.data;
    }
    
    /** Get the object at the end of the list.
     * @return The last object in the list
     */
    public E getLast() {
        return tail.data;
    }
// End of added methods for 2.8 #4
    
// Insert solution to programming exercise 3, section 8, chapter 2 here
    
    /** Returns a new KWListIter object at specified index.
     * @param index The index at which the KWListIter will be positioned
     * @return The new KWListIter object, positioned before index
     */
    public ListIterator<E> listIterator(int index) {
        return new KWListIter(index);
    }
    
    /** Returns a new KWListIter object at the beginning of the list.
     * @return The new KWListIter object, positioned before the first element
     */
    @Override
    public Iterator<E> iterator() {
        return new KWListIter(0);
    }
// End of added methods for 2.8 #3
    
    /**
     * Add an item at the specified index.
     * @param index The index at which the object is to be
     *        inserted
     * @param obj The object to be inserted
     * @throws IndexOutOfBoundsException if the index is out
     *         of range (i < 0 || i > size())
     */
    @Override
    public void add(int index, E obj) {
        listIterator(index).add(obj);
    }

    /**
     * Get the element at position index.
     * @param index Position of item to be retrieved
     * @return The item at index
     */
    @Override
    public E get(int index) {
        return listIterator(index).next();
    }

    /**
     * Return the size of the list
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    // Inner Classes
    /** 
     * A Node is the building block for a double-linked list.
     */
    private static class Node<E> {

        /** The data value. */
        private E data;
        /** The link to the next node. */
        private Node<E> next = null;
        /** The link to the previous node. */
        private Node<E> prev = null;

        /**
         * Construct a node with the given data value.
         * @param dataItem The data value
         */
        private Node(E dataItem) {
            data = dataItem;
        }
    } //end class Node

    /** Inner class to implement the ListIterator interface. */
    private class KWListIter implements ListIterator<E> {

        /** A reference to the next item. */
        private Node<E> nextItem;
        /** A reference to the last item returned. */
        private Node<E> lastItemReturned;
        /** The index of the current item. */
        private int index = 0;

        /**
         * Construct a KWListIter that will reference the ith item.
         * @param i The index of the item to be referenced
         */
        public KWListIter(int i) {
            // Validate i parameter.
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException(
                        "Invalid index " + i);
            }
            lastItemReturned = null; // No item returned yet.
            // Special case of last item.
            if (i == size) {
                index = size;
                nextItem = null;
            } else { // Start at the beginning
                nextItem = head;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        /**
         * Construct a KWListIter that is a copy of another KWListIter
         * @param other The other KWListIter
         */
        @SuppressWarnings("unused")
        public KWListIter(KWListIter other) {
            KWListIter itr = new KWListIter(0);
            itr.index = other.index;
            itr.lastItemReturned = other.lastItemReturned;
            itr.nextItem = other.nextItem;
        }

        /**
         * Indicate whether movement forward is defined.
         * @return true if call to next will not throw an exception
         */
        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        /** Move the iterator forward and return the next item.
        @return The next item in the list
        @throws NoSuchElementException if there is no such object
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /**
         * Indicate whether movement backward is defined.
         * @return true if call to previous will not throw an exception
         */
        @Override
        public boolean hasPrevious() {
            return (nextItem == null && size != 0)
                    || nextItem.prev != null;
        }

        /**
         * Return the index of the next item to be returned by next
         * @return the index of the next item to be returned by next
         */
        @Override
        public int nextIndex() {
            return index;
        }

        /**
         * Return the index of the next item to be returned by previous
         * @return the index of the next item to be returned by previous
         */
        @Override
        public int previousIndex() {
            return index - 1;
        }

        /**
         * Move the iterator backward and return the previous item.
         * @return The previous item in the list
         * @throws NoSuchElementException if there is no such object
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) { // Iterator past the last element
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        /**
         * Add a new item between the item that will be returned
         * by next and the item that will be returned by previous.
         * If previous is called after add, the element added is
         * returned.
         * @param obj The item to be inserted
         */
        @Override
        public void add(E obj) {
            if (head == null) { // Add to an empty list.
                head = new Node<E>(obj);
                tail = head;
            } else if (nextItem == head) { // Insert at head.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 1
                // Link nextItem to the new node.
                nextItem.prev = newNode; // Step 2
                // The new node is now the head.
                head = newNode; // Step 3
            } else if (nextItem == null) { // Insert at tail.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link the tail to the new node.
                tail.next = newNode; // Step 1
                // Link the new node to the tail.
                newNode.prev = tail; // Step 2
                // The new node is the new tail.
                tail = newNode; // Step 3
            } else { // Insert into the middle.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link it to nextItem.prev.
                newNode.prev = nextItem.prev; // Step 1
                nextItem.prev.next = newNode; // Step 2
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 3
                nextItem.prev = newNode; // Step 4
            }
            // Increase size and index and set lastItemReturned.
            size++;
            index++;
            lastItemReturned = null;
        } // End of method add.

// Insert solution to programming exercise 1, section 8, chapter 2 here
        
        /** Remove the last element returned by next() or previous() 
         * @throws IllegalStateException if neither next() nor previous() have been called
         */
        public void remove() throws IllegalStateException {
            if (lastItemReturned != null) {
                
                // unlink lastItemReturned from next
                if (lastItemReturned.next != null) { 
                    lastItemReturned.next.prev = lastItemReturned.prev;
                } else { // lastItemReturned is the tail
                    tail = lastItemReturned.prev;
                    if (tail == null) { // lastItemReturned is also the head (only item in list)
                        head = null;
                    } else {
                        tail.next = null;
                    }
                }
                
                // unlink lastItemReturned from prev
                if (lastItemReturned.prev != null) { 
                    lastItemReturned.prev.next = lastItemReturned.next;
                } else { // lastItemReturned is the head
                    head = lastItemReturned.next;
                    if (head == null) { // lastItemReturned is also the tail (only item in list)
                        tail = null;
                    } else {
                        head.prev = null;
                    }
                }
                
                lastItemReturned = null;
                size--;
                index--;
            } else {
                throw new IllegalStateException();
            }
        }
        
// Insert solution to programming exercise 2, section 8, chapter 2 here
        /** Replace the last element return by next() or previous() with specified element
         * @param The object to replace the the last element returned
         * @throws IllegalStateException
         */
        public void set(E obj) throws IllegalStateException {
            if (lastItemReturned != null) {
                lastItemReturned.data = obj;
            } else {
                throw new IllegalStateException();
            }
        }
    } //end class KWListIter

// Insert solution to programming exercise 1, section 7, chapter 2 here
    /**Find the index of first occurrence of specified object in list.
     * @param o The object being searched for
     * @return The index of the first occurrence of the object or -1 if the object is not found
     */
    @Override
    public int indexOf(Object o) {
        KWListIter iter = (KWListIter) listIterator(0);
        while (iter.hasNext()) {
            if (o.equals(iter.next())) {
                return iter.nextIndex();
            }
        }
        return -1;
    }
    
// Insert solution to programming exercise 2, section 7, chapter 2 here
    /**Find the index of last occurrence of specified object in list.
     * @param o The object being searched for
     * @return The index of the last occurrence of the object or -1 if the object is not found
     */
    @Override
    public int lastIndexOf(Object o) {
        KWListIter iter = (KWListIter) listIterator(size);
        while (iter.hasPrevious()) {
            if (o.equals(iter.previous())) {
                return iter.previousIndex();
            }
        }
        return -1;
    }
    
// Insert solution to programming exercise 3, section 7, chapter 2 here
    /**Find the index of the minimum item in list, assuming each item implements the Comparable interface
     * @return The index of the minimum item in list or -1 if list is empty
     * @throws ClassCastException if items in list do not implement Comparable
     */
    @SuppressWarnings("unchecked")
    public int indexOfMin() throws ClassCastException {
        KWListIter iter = (KWListIter) listIterator(0);
        Comparable<E> min = null;
        if (iter.hasNext()) { // initialize min to first element
            min = (Comparable<E>) iter.next();
        } else {
            return -1; // list is empty
        }
        E next = null;
        while (iter.hasNext()) {
            next = iter.next();
            if (min.compareTo(next) > 0) {
                min = (Comparable<E>) next;
            }
        }
        return indexOf(min);
    }
    
// Insert solution to programming exercise 1, section 6, chapter 2 here
    /**
     * Create and manipulate the double-linked list shown in Figure 2.20
     */
    public static void insertAndRemoveExample() {
        // create initial nodes
        Node<String> tom = new Node<String>("Tom");
        Node<String> dick = new Node<String>("Dick");
        Node<String> harry = new Node<String>("Harry");
        Node<String> sam = new Node<String>("Sam");
        
        // create initial links
        Node<String> head = tom;
        tom.next = dick;
        dick.prev = tom;
        dick.next = harry;
        harry.prev = dick;
        harry.next = sam;
        sam.prev = harry;
        Node<String> tail = sam;
        
        // a. Insert "Bill" before "Tom"
        Node<String> bill = new Node<String>("Bill");
        bill.next = tom;
        tom.prev = bill;
        head = bill;
        
        // b. Insert "Sue" before "Sam"
        Node<String> sue = new Node<String>("Sue");
        sue.next = sam;
        sue.prev = sam.prev;
        sam.prev.next = sue;
        sam.prev = sue;
        
        // c. Remove "Bill"
        head = head.next;
        head.prev = null;
        bill.next = null;
        
        // d. Remove "Sam"
        tail = tail.prev;
        tail.next = null;
        sam.prev = null;
    }
}
/*</listing>*/