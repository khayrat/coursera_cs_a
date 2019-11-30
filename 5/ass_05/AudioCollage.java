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

      for (int i = 0; i < b.length; i++) 
        result[a.length + i] = b[i];

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
    public static double[] changeSpeed(double[] a, double alpha) 
    {
      int samples = (int) (a.length / alpha);
      double[] result = new double[samples];

      for (int i = 0; i < samples; i++)
        result[i] = a[(int) (i * alpha)];

      return result;
    }

    // Creates an audio collage and plays it on standard audio.
    // See below for the requirements.
    public static void main(String[] args) {
      int duration = 10 * StdAudio.SAMPLE_RATE;
      String[] files = {"beatbox.wav", "buzzer.wav", "chimes.wav", "cow.wav", "dialup.wav"};
      double[][] sounds = new double[files.length][];

      for (int i = 0; i < files.length; i++) {
        sounds[i] = StdAudio.read(files[i]);
      }

      double[] sound = mix(sounds[3], 
                           reverse(mix(amplify(sounds[1], 2), 
                                       mix(sounds[4], 
                                           merge(amplify(sounds[0], 2), 
                                                 changeSpeed(sounds[2], 2))))));
     
        for (int j = 0; j < duration; j++) {
          int n = sound.length;
          double tone = sound[j % n];
          if (tone >  1.0) tone =  1.0;
          if (tone < -1.0) tone = -1.0;
          StdAudio.play(tone);
        }
      StdAudio.close();
    }
}
