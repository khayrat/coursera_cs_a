public class ThueMorse {

    public static void main(String[] args) {
      int n = Integer.parseInt(args[0]);

      // create sequence
      boolean[] tms = new boolean[1]; // initialize first element with 'false'

      for (int i = 1; i < n; i = 2*i) {
        // double sequence on each iteration
        boolean[] tmp = new boolean[2*i];

        for (int j = 0; j < 2*i; j++) {
          if (j < i) // copy old
            tmp[j] = tms[j];
          else       // add inverted old
            tmp[j] = !tms[j-i];
        }
        tms = tmp;
      }

      // print matrix
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++)
          System.out.print((tms[r] == tms[c] ? "+" : "-") + "  ");
        System.out.println();
      }
    }
}
