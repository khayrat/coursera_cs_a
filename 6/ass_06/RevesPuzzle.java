public class RevesPuzzle
{
  // Takes two integer command-line arguments n and k and prints T(n, k).
  public static void main(String[] args)
  {
    int n = Integer.parseInt(args[0]);
    double kf = n + 1 - Math.sqrt(2 * n + 1);
    int k = (int) Math.round(kf);
    int rest = n - k;

//    StdOut.printf("%d, %d\n", n, k);

    hanoi4(k, "A", "C", "D", "B");
    hanoi(rest, k, "A", "C", "D");
    hanoi4(k, "B", "A", "C", "D");
  }

  private static void hanoi4(int n, String from, String tempA, String tempB, String to) {
      if (n == 0) return;
      else if (n == 1) StdOut.println("Move disc " + n + " from " + from + " to " + to);
      else if (n == 2) 
      {
        StdOut.println("Move disc " + 1 + " from " + from + " to " + tempA);
        StdOut.println("Move disc " + 2 + " from " + from + " to " + to);
        StdOut.println("Move disc " + 1 + " from " + tempA + " to " + to);
      }
      else if (n == 3) 
      {
        StdOut.println("Move disc " + 1 + " from " + from + " to " + tempA);
        StdOut.println("Move disc " + 2 + " from " + from + " to " + tempB);
        StdOut.println("Move disc " + 3 + " from " + from + " to " + to);
        StdOut.println("Move disc " + 2 + " from " + tempB + " to " + to);
        StdOut.println("Move disc " + 1 + " from " + tempA + " to " + to);
      }
      else // n > 3
      {
        hanoi4(n-1, from, tempA, to, tempB);
        StdOut.println("Move disc " + n + " from " + from + " to " + to);
        hanoi4(n-1, tempB, from, tempA, to);
      }
  }

  private static void hanoi(int n, int k, String from, String temp, String to) {
      if (n == 0) return;
      hanoi(n-1, k, from, to, temp);
      StdOut.println("Move disc " + (n+k) + " from " + from + " to " + to);
      hanoi(n-1, k, temp, from, to);
  }

}
