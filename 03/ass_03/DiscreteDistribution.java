public class DiscreteDistribution {

    public static void main(String[] args) {
      int m = Integer.parseInt(args[0]);
      int[] a = new int[args.length - 1];

      for (int i = 1; i < args.length; i++)
        a[i-1] = Integer.parseInt(args[i]);

/*
      for (int i = 0; i < a.length; i++)
        System.out.print(a[i] + " ");
      System.out.println();
*/

      // Define the cumulative sums Si=a1+a2+…+ai  and S0=0
      int[] S = new int[a.length+1];
      for (int i = 1; i < S.length; i++)
        for (int j = 0; j < i; j++)
          S[i] += a[j];

/*
      for (int i = 0; i < S.length; i++)
        System.out.print(S[i] + " ");
      System.out.println();
      System.out.println(S.length);
*/
      while (m-- > 0) {
//      boolean found = false;
        // Pick a random integer r uniformly between 0 and Sn−1
        int r = (int) (Math.random() * (S[S.length - 1]));
        //System.out.print(" (" + r + ") ");

        // Find the unique index i between 1 and n such that Si−1≤r<Si
        for (int i = 1; i < S.length; i++) {
          if ((S[i-1] <= r) && (r < S[i])) {
            System.out.print(i + " ");
//           found = true;
            break;
          }
//          if (i == S.length - 1 && !found) System.out.println("not found for r: " + r);
        }
      }
      System.out.println();
    }
}
