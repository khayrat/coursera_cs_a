public class ShannonEntropy {

    public static void main(String[] args) {
      int m = Integer.parseInt(args[0]);
      double n = 0.0;
      double sum = 0.0;
      int[] x = new int[m];

      while (!StdIn.isEmpty()) {
        int i = StdIn.readInt();
        n++;
        x[i-1] += 1;
      }


      for (int i = 0; i < m; i++) {
        double p = x[i] / n;
        if (p != 0) 
          sum += p * (Math.log(p) / Math.log(2));
      }

      StdOut.printf("%.4f\n", -sum); 
      
    }
}
