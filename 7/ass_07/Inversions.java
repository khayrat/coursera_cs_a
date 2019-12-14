public class Inversions 
{
    // Return the number of inversions in the permutation a[].
    public static long count(int[] a)
    {
      return -1;
    }

    // Return a permutation of length n with exactly k inversions.
    public static int[] generate(int n, long k)
    {
      int[] permuation = new int[n];
      return permuation;
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
      StdOut.println(inversions);
    }
}
