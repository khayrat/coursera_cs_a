public class Ramanujan 
{
  // Is n a Ramanujan number?
  public static boolean isRamanujan(long n)
  {
    long qube_root_n = (long) Math.ceil(Math.pow(n, 1./3));

    assert Math.pow(qube_root_n, 3) >= n;

    // choose a
    for (long a = 1; a <= qube_root_n; a++)
    {
      // calc b
      long a_cube = a*a*a;
      long b = (long) Math.ceil(Math.pow(n - a_cube, 1./3));

      // check wheter a**3 + b**3 == n
      if (a_cube + b*b*b == n)
      {
        // choose c
        for (long c = a + 1; c <= qube_root_n; c++)
        {
          // clac d
          long c_cube = c*c*c;
          long d = (long) Math.ceil(Math.pow(n - c_cube, 1./3));

          // check wheter c**3 + d**3 == n
          if (c_cube + d*d*d == n && a != d) return true;
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
