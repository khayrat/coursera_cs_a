public class Checkerboard {

    public static void main(String[] args) {
      int n = Integer.parseInt(args[0]);
      boolean toggle = false;

      StdDraw.setScale(0, n);
      double halfSide = .5;
      for (double y = halfSide; y < n; y++) {
        for (double x = halfSide; x < n; x++) {
          if (toggle) {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
          }
          else {
            StdDraw.setPenColor(StdDraw.BLUE); 
          }
          StdDraw.filledSquare(x, y, halfSide);
          toggle = !toggle;
        }
        if (n % 2 == 0) toggle = !toggle;
      }
    }
}
