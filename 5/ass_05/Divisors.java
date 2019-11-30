public class Divisors {

    // Returns the greatest common divisor of a and b.
    public static int gcd(int a, int b) {
      if (a == 0 && b == 0) return 0;

      a = Math.abs(a);
      b = Math.abs(b);

      if (b > a) {
        a = a + b;
        b = a - b;
        a = a - b;
      }

      if (b == 0) return a;

      while (true) {
        int r = a%b;
        if (r == 0) break;
        a = b;
        b = r;
      }
      return b;
    }


    // Returns the least common multiple of a and b.
    public static int lcm(int a, int b) {
      if (a == 0 || b == 0) return 0;

      a = Math.abs(a);
      b = Math.abs(b);

      return (a/gcd(a,b)) * b;
    }

    // Returns true if a and b are relatively prime; false otherwise.
    public static boolean areRelativelyPrime(int a, int b) {
      return gcd(a, b) < 2;
    }

    // Returns the number of integers between 1 and n that are
    // relatively prime with n.
    public static int totient(int n) {
      if (n <= 0) return 0;

      int sum = 0;
      for (int i = 1; i <= n; i++) {
        if (areRelativelyPrime(i, n)) sum++;
      }
      return sum;
    }

    // Takes two integer command-line arguments a and b and prints
    // each function, evaluated in the format (and order) given below.
    public static void main(String[] args) {
      int a = Integer.parseInt(args[0]);
      int b = Integer.parseInt(args[1]);

      StdOut.printf("gcd(%s, %s) = %s\n", a, b, gcd(a, b));
      StdOut.printf("lcm(%s, %s) = %s\n", a, b, lcm(a, b));
      StdOut.printf("areRelativelyPrime(%s, %s) = %s\n", a, b, areRelativelyPrime(a, b));
      StdOut.printf("totient(%s) = %s\n", a, totient(a));
      StdOut.printf("totient(%s) = %s\n", b, totient(b));
    }
}
