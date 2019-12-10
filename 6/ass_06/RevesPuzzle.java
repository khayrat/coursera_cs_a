public class RevesPuzzle
{
  // Takes two integer command-line arguments n and k and prints T(n, k).
  public static void main(String[] args)
  {
    int n = Integer.parseInt(args[0]);

    h4(n, "A", "B", "C", "D");
  }

  private static void h4(int n, String from, String t1, String t2, String to)
  {
    if (n == 0) return;
    else if (n == 1) 
    {
      StdOut.println("Move disc " + 1 + " from " + from + " to " + to);
    }
    else if (n == 2) 
    {
      StdOut.println("Move disc " + 1 + " from " + from + " to " + t1);
      StdOut.println("Move disc " + 2 + " from " + from + " to " + to);
      StdOut.println("Move disc " + 1 + " from " + t1 + " to " + to);
    }
    else {
      int k = (int) Math.round(n + 1 - Math.sqrt(2 * n + 1));
      int rest = n - k;

      //StdOut.printf("%d, %d\n", n, k);

      h4(k, from, to, t1, t2);
      hanoi(rest, k, from, t1, to);
      h4(k, t2, from, t1, to);
    }
  }

  private static void hanoi(int n, int k, String from, String temp, String to) {
    if (n == 0) return;
    hanoi(n-1, k, from, to, temp);
    StdOut.println("Move disc " + (n+k) + " from " + from + " to " + to);
    hanoi(n-1, k, temp, from, to);
  }
}
