public class Divisors {

    // Returns the greatest common divisor of a and b.
    public static int gcd(int a, int b) {
      return 1;
    }

    // Returns the least common multiple of a and b.
    public static int lcm(int a, int b) {
      return 1;
    }

    // Returns true if a and b are relatively prime; false otherwise.
    public static boolean areRelativelyPrime(int a, int b) {
      return false;
    }

    // Returns the number of integers between 1 and n that are
    // relatively prime with n.
    public static int totient(int n) {
      return 1;
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
