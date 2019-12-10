public class TrinomialDP
{
  // Returns the trinomial coefficient T(n, k).
  public static long trinomial(int n, int k)
  {
    if (k < 0) k = -k;   // property of trinomial triangle - symmetrie: t(n, k) == t(n, -k)
    if (k > n) return 0; // n+k <= 2*n => range(k): k <= n

    long[][] dp = new long[n+1][n+1];
    dp[0][0] = 1; // the root

    for (int i = 1; i <= n; i++)
    {
      for (int j = 0; j <= n; j++) // j represents the k, where  0 <= k <= n
      {
        dp[i][j] = th(i-1, j-1, dp) + th(i-1, j, dp) + th(i-1, j+1, dp);
        if ((i == n) && (j == k)) break;
      }
    }
    return dp[n][k];
  }

  private static long th(int n, int k, long[][] dp) 
  {
    if (k < 0) return th(n, -k, dp); // property of trinomial triangle - symmetrie: t(n, k) == t(n, -k)
    if (k > n) return 0;             // n+k <= 2*n => range(k): k <= n
    return dp[n][k];
  }

  // Takes two integer command-line arguments n and k and prints T(n, k).
  public static void main(String[] args)
  {
    int n = Integer.parseInt(args[0]);
    int k = Integer.parseInt(args[1]);

    StdOut.println(trinomial(n, k));
  }
}
