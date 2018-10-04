package no.uib.ii.inf102.f18.mandatory1;

import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> implements IIndexPQ<Key> {

    private final int NMAX;
    private int size;
    private int[] mpq;
    private int[] inv;
    private Key[] keys;
    
    @SuppressWarnings("unchecked")
    public IndexMinPQ(int maxSize) {
        if (maxSize<0) throw new IllegalArgumentException("Size of priority queue cannot be less than zero.");
        
        NMAX = maxSize;
        keys = (Key[]) new Comparable[maxSize];
        mpq = new int[maxSize+1];
        inv = new int[maxSize+1];
        
        for (int i=1; i<=NMAX; i++) {
            inv[i] = -1;
        }
    }

    public void add(int index, Key key) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        if (contains(index)) throw new IllegalArgumentException("index is already set. Use changeKey() instead");
        
        mpq[++size] = index;
        inv[index] = size;
        keys[index] = key;
        swim(size);
        
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }
    
    private void sink(int k) {
        int left = k*2;
        int right = left+1;
        
        if (left > size) return;
        if (right > size) {
            if (less(k, left)) swap(k, left);
        }
        
        if (less(right, left) && less(k, left)) {
            swap(k, left);
            sink(left);
            return;
        } else if (less(right, left) && less(k, right)) {
            swap(k, right);
            sink(right);
            return;
        } else return;
        
    }
    
    private boolean less(int i, int j) {
        return keys[mpq[i]].compareTo(keys[mpq[j]]) < 0;
    }
    
    private void swap(int i, int j) {
        int temp = mpq[i];
        mpq[i] = mpq[j];
        mpq[j] = temp;
        inv[mpq[i]] = i;
        inv[mpq[j]] = j;
    }

    public void changeKey(int index, Key key) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Cannot change an index which does not exist.");
        
        keys[index] = key;
        swim(inv[index]);
        sink(inv[index]);
    }

    public boolean contains(int index) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        return inv[index] != -1;
    }

    
    public void delete(int index) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Cannot delete an index which does not exist.");
        
        int i = inv[index];
        swap(i, size--);
        swim(i);
        sink(i);
        keys[index] = null;
        inv[index] = -1;
    }

    
    public Key getKey(int index) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Given index is not associated with any key.");
        
        // ????
        return keys[index];
    }

    
    public Key peekKey() {
        if (size==0) return null;
        return keys[mpq[1]];
    }

    
    public int peek() {
        return mpq[1];
    }

    
    public int poll() {
        return 0;
    }

    
    public int size() {
        return size;
    }

    
    public boolean isEmpty() {
        return size==0;
    }

}
