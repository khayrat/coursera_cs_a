public class RandomWalkers {

    public static void main(String[] args) {
      int r = Integer.parseInt(args[0]);
      int trials = Integer.parseInt(args[1]);
      int n = trials;
      double stepsAgg = 0.0;

      while(n-- > 0) {
        int x = 0;
        int y = 0;
        int steps = 0;

        while (Math.abs(x) + Math.abs(y) != r) {

          double p = Math.random();

          if      (p < .25) x--;
          else if (p < .5)  x++;
          else if (p < .75) y++;
          else  /* p < 1 */ y--;

          steps++;
        }
        stepsAgg += steps;
      }

      System.out.println("average number of steps = " + stepsAgg/trials);
    }
}
