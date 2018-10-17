package no.uib.ii.inf102.f18.mandatory1;

/**
 * Solves https://uib.kattis.com/problems/uib.bstdebugging
 * 
 * @author Carl August Gjørsvik
 *
 */
public class BSTDebugging {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        
        int n = io.getInt();
        int target = io.getInt();
        
        int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE;
        
        for (int i=0; i<n; i++) {
            int c = io.getInt();
            
            /*
             * if c == m, target is found, and search should stop.
             * min is set when a value less than target was previously examined, 
             * which means in a proper BST, the right subtree should be chosen, 
             * and all following values should be greater than min.
             * Vice versa for max.
             * So values c should be greater than min and less than max.
             * If c == min or c == max, it is not a BST, as all keys should be unique.
             */
            if ((c==target && i<n-1) || c <= min || c >= max) {
                io.println("invalid");
                io.close();
                return;
            }
            
            if (c>target) max = c;
            else min = c;
        }
        
        io.println("valid");
        io.close();
    }
}
