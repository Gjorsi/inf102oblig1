package no.uib.ii.inf102.f18.mandatory1;

public class BSTDebugging {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        
        int n = io.getInt();
        int m = io.getInt();
        
        int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE;
        
        for (int i=0; i<n; i++) {
            int c = io.getInt();
            
            if ((c==m && i<n-1) || c <= min || c >= max) {
                io.println("invalid");
                io.close();
                return;
            }
            
            if (c>m) max = c;
            else min = c;
        }
        
        io.println("valid");
        io.close();
    }
}
