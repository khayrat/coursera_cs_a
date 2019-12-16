public class Ramanujan 
{
  // Is n a Ramanujan number?
  public static boolean isRamanujan(long n)
  {
    int qube_root_n = (int) Math.round(Math.pow(n, 1./3));
    // choose a
    for (int a = 0; a < qube_root_n; a++)
    {
      // calc b
      long a_cube = (long) Math.pow(a, 3);
      int b = (int) Math.round(Math.pow(n - a_cube, 1./3));

      // check wheter a**3 + b**3 == n
      if (a_cube + Math.pow(b, 3) == n)
      {
        // choose c
        for (int c = a + 1; c < qube_root_n; c++)
        {
          // clac d
          long c_cube = (long) Math.pow(c, 3);
          int d = (int) Math.round(Math.pow(n - c_cube, 1./3));

          // check wheter c**3 + d**3 == n
          if (c_cube + (long) Math.pow(d, 3) == n && a != d) return true;
        }
      }
    }

    return false;
  }

  // Takes a long integer command-line arguments n and prints true if
  // n is a Ramanujan number, and false otherwise.
  public static void main(String[] args)
  {
    long n = Long.parseLong(args[0]);

    StdOut.printf("%b\n", isRamanujan(n));
  }
}
