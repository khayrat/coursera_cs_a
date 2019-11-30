public class ActivationFunction {

    // Returns the Heaviside function of x.
    public static double heaviside(double x) {
      if (Double.isNaN(x)) return Double.NaN;
      if      (x <  0) return 0.0;
      else if (x == 0) return 0.5;
      else             return 1.0;
    }

    // Returns the sigmoid function of x.
    public static double sigmoid(double x) {
      if (Double.isNaN(x)) return Double.NaN;
      return 1/(1+Math.exp(-x));
    }

    // Returns the hyperbolic tangent of x.
    public static double tanh(double x) {
      if (Double.isNaN(x)) return Double.NaN;
      return (Math.exp(x) - Math.exp(-x))/(Math.exp(x)+Math.exp(-x));
    }

    // Returns the softsign function of x.
    public static double softsign(double x) {
      if (Double.isNaN(x)) return Double.NaN;
      return x/(1+Math.abs(x));
    }

    // Returns the square nonlinearity function of x.
    public static double sqnl(double x) {
      if (Double.isNaN(x)) return Double.NaN;
      if (x <= -2) return -1;
      if (x <   0) return x + x*x/4;
      if (x <   2) return x - x*x/4;
      else /*x >= 2*/ return 1;
    }

    // Takes a double command-line argument x and prints each activation
    // function, evaluated, in the format (and order) given below.
    public static void main(String[] argv) {
      double x = Double.parseDouble(argv[0]);
      StdOut.printf("heaviside(%f) = %f\n", x, heaviside(x));
      StdOut.printf("sigmoid(%f) = %f\n", x, sigmoid(x));
      StdOut.printf("tanh(%f) = %f\n", x, tanh(x));
      StdOut.printf("softsign(%f) = %f\n", x, softsign(x));
      StdOut.printf("sqnl(%f) = %f\n", x, sqnl(x));
    }
}
