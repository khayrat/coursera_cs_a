public class ColorHSB 
{
    private int h, s, b;

    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b)
    {
      if (h < 0 || h > 359) throw new IllegalArgumentException("h should be in the range 0 <= h <= 359: " + h);
      if (s < 0 || s > 100) throw new IllegalArgumentException("s should be in the range 0 <= s <= 100: " + s);
      if (b < 0 || b > 100) throw new IllegalArgumentException("b should be in the range 0 <= b <= 100: " + b);

      this.h = h;
      this.s = s;
      this.b = b;
    }

    // Returns a string representation of this color, using the format (h, s, b).
    public String toString()
    {
      return new StringBuilder("(")
        .append(h).append(", ")
        .append(s).append(", ")
        .append(b).append(")")
        .toString();
    }

    // Is this color a shade of gray?
    public boolean isGrayscale()
    {
      return s == 0 || b == 0;
    }

    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB that)
    {
      if (that == null) throw new IllegalArgumentException("compare to null object");

      int hDiff = this.h - that.h;
      int sDiff = this.s - that.s;
      int bDiff = this.b - that.b;

      return Math.min(hDiff*hDiff, (360 - Math.abs(hDiff))*(360 - Math.abs(hDiff))) + sDiff*sDiff + bDiff*bDiff;
    }

    // Sample client (see below).
    public static void main(String[] args)
    {
      int h = Integer.parseInt(args[0]);
      int s = Integer.parseInt(args[1]);
      int b = Integer.parseInt(args[2]);
      ColorHSB hsb = new ColorHSB(h, s, b);

      String closestName = null;
      ColorHSB closestHSB = null;
      int minDistance = Integer.MAX_VALUE;

      while (!StdIn.isEmpty())
      {
        String currentName = StdIn.readString();
        int hh = StdIn.readInt();
        int ss = StdIn.readInt();
        int bb = StdIn.readInt();
        ColorHSB currentHSB = new ColorHSB(hh, ss, bb);

        int currentDistance = hsb.distanceSquaredTo(currentHSB);
        if (currentDistance < minDistance)
        {
          minDistance = currentDistance;
          closestName = currentName;
          closestHSB  = currentHSB;
        }
      }
      StdOut.printf("%s %s\n", closestName, closestHSB);
    }
}
