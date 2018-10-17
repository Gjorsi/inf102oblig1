package no.uib.ii.inf102.f18.mandatory1;

import java.util.Iterator;

/**
 * A simple singly-linked list queue implementation. <br>
 * Implements Iterable.
 * 
 * It can only add elements and return an iterator. No other options implemented.
 * 
 * @author Carl August Gjørsvik
 *
 * @param <E>
 */
public class IterableQueue<E> implements Iterable<E> {
    node head;
    node tail;
    
    private class node {
        E element;
        node next;
        
        public node(E element) {
            this.element = element;
        }
    }
    
    public void add(E element) {
        if (head == null) head = tail = new node(element);
        else {
            tail.next = new node(element);
            tail = tail.next;
        }
    }

    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    class QueueIterator implements Iterator<E>  {
        node current = head;
        
        public boolean hasNext() {
            return (current != null);
        }

        public E next() {
            node t = current;
            current = current.next;
            return t.element;
        }
    }
}
