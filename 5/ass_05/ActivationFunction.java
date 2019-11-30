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
      return 1.0/(1.0+Math.exp(-x));
    }

    // Returns the hyperbolic tangent of x.
    public static double tanh(double x) {
      if (Double.isNaN(x)) return Double.NaN;
      //if x≥20, then tanh(x) should return 1.0; 
      //if x≤−20, then tanh(x) should return -1.0.
      if      (x >= 20) return  1.0;
      else if (x <= 20) return -1.0;
      return (Math.exp(x) - Math.exp(-x))/(Math.exp(x)+Math.exp(-x));
    }

    // Returns the softsign function of x.
    public static double softsign(double x) {
      if (Double.isNaN(x)) return Double.NaN;
      if       (x == Double.POSITIVE_INFINITY) return  1.0;
      else if  (x == Double.NEGATIVE_INFINITY) return -1.0;
      return x/(1.0+Math.abs(x));
    }

    // Returns the square nonlinearity function of x.
    public static double sqnl(double x) {
      if (Double.isNaN(x)) return Double.NaN;
      if (x <= -2) return -1.0;
      if (x <   0) return x + x*x/4;
      if (x <   2) return x - x*x/4;
      else /*x >= 2*/ return 1.0;
    }

    // Takes a double command-line argument x and prints each activation
    // function, evaluated, in the format (and order) given below.
    public static void main(String[] argv) {
      double x = Double.parseDouble(argv[0]);
      StdOut.printf("heaviside(%s) = %s\n", x, heaviside(x));
      StdOut.printf("sigmoid(%s) = %s\n", x, sigmoid(x));
      StdOut.printf("tanh(%s) = %s\n", x, tanh(x));
      StdOut.printf("softsign(%s) = %s\n", x, softsign(x));
      StdOut.printf("sqnl(%s) = %s\n", x, sqnl(x));
    }
}
