public class Inversions 
{
    // Return the number of inversions in the permutation a[].
    public static long count(int[] a)
    {
      long c = 0;
      for (int i=0; i<a.length; i++)
        for (int j=i; j<a.length; j++)
          if (i < j && a[i] > a[j]) c++; 

      return c;
    }

    // Return a permutation of length n with exactly k inversions.
    // assume 0 <= k <= 1/2 * n * (n - 1)
    public static int[] generate(int n, long k)
    {
      if (!(0 <= k) || !(k <= 0.5 * n * (n - 1))) 
          throw new IllegalArgumentException("k should be 0 <= k <= 1/2 * n * (n - 1)");

      int[] p = new int[n];

      // init
      //for (int i = 0; i < n; i++) p[i] = i;

      int offset = 0;

//      StdOut.printf("before 1. loop offset: %d\n", offset);
      while (k >= n - 1 - offset && k > 0)
      {
        p[offset] = n-1 - offset; 
        k = k - (n - 1 - offset);
        offset++;
/*
        StdOut.printf("n: %d, k: %d, offet: %d\n", n, k, offset);

        for (int i=0; i<n; i++)
          StdOut.printf("%d ", p[i]);
        StdOut.println();
*/
      }
      /*
      StdOut.printf("after 1. loop k: %d, offset: %d\n", k, offset);
      for (int i=0; i<n; i++)
        StdOut.printf("%d ", p[i]);
      StdOut.println();
      */

      assert k < (n - 1 - offset); // this means we have to change not until the last element of the sequence or at most n - 2 elements

      // left shift k elements
      
      // dertmine index of element upto where the change should take place
      // [n-1, n-2, ..., n - m, ...., 0
      //   0    1         offset      offset + k

      int v = 1;
      for (int i = offset; i < offset + k; i++)
      {
        p[offset++] = v++;
        k--;
      }

      assert k == 0;

      //StdOut.printf("after left shift k: %d, offset: %d\n", k, offset);

      // place first element
      p[offset++] = 0;

      // place remaining elements
      for (int i = offset; i < n; i++)
      {
        p[i] = v++;

        //StdOut.printf("n: %d, k: %d, offset: %d, v: %d\n", n, k, offset, v);
      }
      /*
        for (int i=0; i<n; i++)
          StdOut.printf("%d ", p[i]);
        StdOut.println();
      */
      return p;
    }

    // Takes an integer n and a long k as command-line arguments,
    // and prints a permutation of length n with exactly k inversions.
    public static void main(String[] args)
    {
      int n = Integer.parseInt(args[0]);
      long k = Integer.parseInt(args[1]);
      
      int[] permutation = generate(n, k);
      long inversions = count(permutation);
      for (int i=0; i<permutation.length; i++)
        StdOut.printf("%d ", permutation[i]);
      StdOut.println();
//      StdOut.println(inversions);
    }
}
