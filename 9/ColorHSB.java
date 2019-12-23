public class ColorHSB 
{
    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b)
    {

    }

    // Returns a string representation of this color, using the format (h, s, b).
    public String toString()
    {
      return null;
    }

    // Is this color a shade of gray?
    public boolean isGrayscale()
    {
      return false;
    }

    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB that)
    {
      return 0;
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
