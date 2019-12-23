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

      if (n < 0) 
          throw new IllegalArgumentException("n should be >= 0");

      int[] p = new int[n];

      if (n == 0) return p;

      int offset = 0;

      while (k >= n - 1 - offset && k > 0)
      {
        p[offset] = n-1 - offset; 
        k = k - (n - 1 - offset);
        offset++;
      }

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

      // place first element
      p[offset++] = 0;

      // place remaining elements
      for (int i = offset; i < n; i++)
      {
        p[i] = v++;
      }

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
    }
}
