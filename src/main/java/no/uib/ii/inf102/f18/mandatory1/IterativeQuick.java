package no.uib.ii.inf102.f18.mandatory1;

import java.util.SplittableRandom;

/**
 * An iterative implementation of quicksort. <br>
 * The methods partition(), swap() and shuffle() are copied from {@link no.uib.ii.inf102.f18.mandatory1.Quick}.
 * 
 * @author Carl August Gjørvik
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class IterativeQuick {
    public static void sort(Comparable[] arr) {
        shuffle(arr);
        IterativeQuicksort(arr);
    }
    
    /**
     * The array is partitioned by partition(), the references to start and end of created sub-arrays are stored in a stack. <br>
     * While the stack is not empty, retrieve the top sub-array, partition it and return new sub-array references to the stack.
     * Discard references to sub-arrays of size 1 or less. <br>
     * Size of sub-array can be zero if lb == ub, as ub is exclusive.
     * @param arr the array to sort (in place)
     */
    private static void IterativeQuicksort(Comparable[] arr) {
        SimpleStack<Integer> stack = new SimpleStack<>();
        
        stack.push(0);
        stack.push(arr.length);
        
        int lb, ub, j;
        
        while (!stack.isEmpty()) {
            ub = stack.pop();
            lb = stack.pop();
            
            if (ub-lb < 2) continue;
            
            j = partition(arr, lb, ub);
            
            stack.push(lb);
            stack.push(j);
            stack.push(j+1);
            stack.push(ub);
        }
        
    }

    private static int partition(Comparable[] arr, int lb, int ub) {
        Comparable pivot = arr[lb];
        int i = lb;
        int j = ub;
        /*
        Mentally, divide array into three (four) parts based on pointers lb, i, j and ub
        At the end of every iteration of the while-loop below, maintain these invariants:
          (0)  arr[lb] contains pivot element
          (1)  arr[lb+1:i+1] values are less than or equal to pivot
          (2)  arr[i+1:j] values are unchecked
          (3)  arr[j:ub] values are at least pivot value or greater
           #   before the swap at the end of loop, j is always in range of the array,
               pointing to a value less than or equal to pivot value

        Note: arr[x:y] denotes the sub-array starting at x (inclusive) ending at y (exclusive)
        */
        while (true) {
            /*
            First while loop: Find next index i where arr[i] >= pivot (incrementing i)
            Second while loop: Find next j where arr[j] <= pivot (decrementing j)
            */
            while (++i < ub && pivot.compareTo(arr[i]) > 0);
            while (pivot.compareTo(arr[--j]) < 0); // && j > lb ); // commented check is redundant (why?)

            /*
            At this point i points to the an element >= pivot, and j points to an element <= pivot.
            We swap them; now our invariants 0-3 hold.

            However, if i >= j, then the array [lb+1:ub] has already been partitioned!
            */
            if (i >= j) break;
            swap(i, j, arr);
        }

        /*
        The array [lb+1:ub] has been partitioned, and by invariants 3 and #, we know
        that swapping the pivot into position j will yield a complete partition of [lb:ub].
        */
        swap(lb, j, arr);
        return j;
    }
    
    private static void swap(int i, int j, Comparable[] arr) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    /**
     * Simple shuffling procedure (Fisher-Yates/Durstenfeld/Knuth)
     * Resulting permutation is chosen uniformly at random
     *
     * @param arr array to be shuffled
     */
    private static void shuffle(Comparable[] arr) {
        SplittableRandom r = new SplittableRandom();
        for (int i = 0; i < arr.length; i++) {
            int j = r.nextInt(arr.length-i) + i;
            swap(i, j, arr);
        }
    }
    
    /**
     * A simple stack implementation to be used by IterativeQuicksort
     * 
     * @author Carl August Gjørsvik
     *
     * @param <E> the type to store in this stack
     */
    private static class SimpleStack<E> {
        Node top;
        
        public void push(E elem) {
            Node t = new Node(elem, top);
            top = t;
        }
        
        public boolean isEmpty() {
            return top == null;
        }

        public E pop() {
            Node t = top;
            top = t.prev;
            return (E) t.elem;
        }
        
        private class Node {
            E elem;
            Node prev;
            public Node(E elem, Node prev) {
                this.elem = elem;
                this.prev = prev;
            }
        }
    }
}
