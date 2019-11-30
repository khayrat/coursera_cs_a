public class AudioCollage {

    // Returns a new array that rescales a[] by a multiplicative factor of alpha.
    public static double[] amplify(double[] a, double alpha) {
      double[] result = new double[a.length];
      
      for (int i=0; i < a.length; i++) 
        result[i] = a[i] * alpha;

      return result;
    }

    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {
      double[] result = new double[a.length];
      int n = a.length;

      for (int i=0; i < n; i++) 
        result[i] = a[n-i-1];

      return result;
    }

    // Returns a new array that is the concatenation of a[] and b[].
    public static double[] merge(double[] a, double[] b) {
      double[] result = new double[a.length + b.length];

      for (int i = 0; i < a.length; i++) 
        result[i] = a[i];

      for (int i = a.length; i < b.length; i++) 
        result[i] = b[i];

      return result;
    }

    // Returns a new array that is the sum of a[] and b[],
    // padding the shorter arrays with trailing 0s if necessary.
    public static double[] mix(double[] a, double[] b)
    {
      double[] min;
      double[] max;

      if (a.length < b.length)
      {
        min = a;
        max = b;
      }
      else 
      {
        min = b;
        max = a;
      }

      double[] result = new double[max.length];

      for (int i = 0; i < min.length; i++)
        result[i] = min[i] + max[i];

      for (int i = min.length; i < max.length; i++)
        result[i] = max[i];

      return result;
    }

    // Returns a new array that changes the speed by the given factor.
    public static double[] changeSpeed(double[] a, double alpha) {

      return null;
    }

    // Creates an audio collage and plays it on standard audio.
    // See below for the requirements.
    public static void main(String[] args) {

    }
}
