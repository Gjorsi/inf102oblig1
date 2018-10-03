package no.uib.ii.inf102.f18.mandatory1;

public class IndexMinPQ<Key extends Comparable<Key>> implements IIndexPQ<Key> {

    private final int NMAX;
    private int size;
    private int[] pq;
    private Key[] keys;
    
    @SuppressWarnings("unchecked")
    public IndexMinPQ(int maxSize) {
        if (maxSize<0) throw new IllegalArgumentException("Size of priority queue cannot be less than zero.");
        
        NMAX = maxSize;
        keys = (Key[]) new Comparable[maxSize+1];
        pq = new int[maxSize+1];
    }

    public void add(int index, Key key) {
        
    }

    public void changeKey(int index, Key key) {
        // TODO Auto-generated method stub
    }

    public boolean contains(int index) {
        // TODO Auto-generated method stub
        return false;
    }

    
    public void delete(int index) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        
    }

    
    public Key getKey(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    
    public Key peekKey() {
        // TODO Auto-generated method stub
        return null;
    }

    
    public int peek() {
        // TODO Auto-generated method stub
        return 0;
    }

    
    public int poll() {
        // TODO Auto-generated method stub
        return 0;
    }

    
    public int size() {
        return size;
    }

    
    public boolean isEmpty() {
        return size==0;
    }

}
