/******************************************************************************
 *  Compilation:  javac Hanoi.java
 *  Execution:    java Hanoi n
 *  
 *  Solves the Towers of Hanoi problem on n discs. The discs are labeled
 *  in increasing order of size from 1 to n and the poles are labeled
 *  A, B, and C.
 *
 *  % java Hanoi 3
 *  Move disc 1 from A to C
 *  Move disc 2 from A to B
 *  Move disc 1 from C to B
 *  Move disc 3 from A to C
 *  Move disc 1 from B to A
 *  Move disc 2 from B to C
 *  Move disc 1 from A to C
 *
 ******************************************************************************/

public class Hanoi {

    // move n smallest discs from one pole to another, using the temp pole
    private static void hanoi(int n, String from, String temp, String to) {
        if (n == 0) return;
        hanoi(n-1, from, to, temp);
        StdOut.println("Move disc " + n + " from " + from + " to " + to);
        hanoi(n-1, temp, from, to);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        hanoi(n, "A", "B", "C");
    }
}


