public class RamanujanTaxi {

    public static void main(String[] args) {
      int n = Integer.parseInt(args[0]);
      double sq_n = Math.sqrt(n);
      boolean found = false;
      int steps = 0;

      for (int a = 1; a <= sq_n; a++) {
        if (found) break;
        long a_3 = a*a*a;
        steps++;
        for (int b = 1; b <=sq_n; b++) {
          if (found) break;
          long b_3 = b*b*b;
          steps++;
          for (int c = 1; c <= sq_n; c++) {
            if (found) break;
            long c_3 = c*c*c;
            steps++;
            for (int d = 1; d <= sq_n; d++) {
              long d_3 = d*d*d;
              steps++;
              if ((a != b && a != c && a != d && b != c && b != d && c != d) && (a_3 + b_3 <= n) && (a_3 + b_3 == c_3 + d_3)) {
                System.out.println("a = " + a +"; b = " + b + "; c = " + c + "; d = " + d);
                //System.out.println("a**3 + b**3 == c**3 + d**3");
                System.out.println("TRACE: a_3 + b_3 = " + (a_3 + b_3));
                System.out.println("steps: " + steps);
                found = true;
                break;
              }
            }
          }
        }
      }
    }
}
