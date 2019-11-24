public class RandomWalker {

    public static void main(String[] args) {
      int r = Integer.parseInt(args[0]);
      int x = 0;
      int y = 0;
      int steps = 0;

      System.out.println("(" + x + ", " + y +")");
      while (Math.abs(x) + Math.abs(y) != r) {

        double p = Math.random();

        if (p <=.25) x--;
        else if (p <= .5) x++;
        else if (p <= .75) y++;
        else y--;

        steps++;
        System.out.println("(" + x + ", " + y +")");
      }
      System.out.println("steps = " + steps);
    }

}
