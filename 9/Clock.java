public class Clock {
    int h, m;

    // Creates a clock whose initial time is h hours and m minutes.
    public Clock(int h, int m)
    {
      if (h < 0 || h > 23) throw new IllegalArgumentException("h should be in range 0 <= h <= 23: " + h);
      if (m < 0 || m > 59) throw new IllegalArgumentException("m should be in range 0 <= m <= 59: " + m);

      this.h = h;
      this.m = m;
    }

    // Creates a clock whose initial time is specified as a string, using the format HH:MM.
    public Clock(String s)
    {
      this(
          Integer.parseInt(s.substring(0, s.indexOf(":"))), 
          Integer.parseInt(s.substring(s.indexOf(":")+1))
      );
    }

    // Returns a string representation of this clock, using the format HH:MM.
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      if (h < 10) sb.append("0"); 
      sb.append(h)
        .append(":");
      if (m < 10) sb.append("0"); 
      sb.append(m);

      return sb.toString();
    }

    // Is the time on this clock earlier than the time on that one?
    public boolean isEarlierThan(Clock that)
    {
      if (this.h < that.h) return true;
      if (this.m < that.m) return true;
      else                 return false;
    }

    // Adds 1 minute to the time on this clock.
    public void tic()
    {
      m++;

      if (m > 59)
      {
        h++;
        m = 0;

        if (h > 23) h = 0;
      }

    }

    // Adds Δ minutes to the time on this clock.
    public void toc(int delta)
    {
      m += delta;

      int d = m / 60;
      int r = m % 60;

      if (d > 0)
      {
        h += d;
        m = r;

        h = h % 24;
      }
    }

    // Test client (see below).
    public static void main(String[] args)
    {
      try {
        new Clock(-1, 10);
        throw new IllegalStateException("should throw Exception for (-1,10)");
      } catch (IllegalArgumentException e)
      {
        // expected
      }
      try {
        new Clock(1, 70);
        throw new IllegalStateException("should throw Exception for (1,70)");
      } catch (IllegalArgumentException e)
      {
        // expected
      }

      Clock c1 = new Clock(10, 50);
      Clock c2 = new Clock("10:51");
      StdOut.printf("c1: %s, c2: %s\n", c1, c2);
      boolean isEarlier = c1.isEarlierThan(c2);
      StdOut.printf("c1 is earlier than c2: %b\n", c1.isEarlierThan(c2));
      StdOut.printf("c1 tic\n");
      c1.tic();
      StdOut.printf("c1: %s, c2: %s\n", c1, c2);
      StdOut.printf("c1 is earlier than c2: %b\n", c1.isEarlierThan(c2));
      StdOut.printf("c2 toc 10\n");
      c2.toc(10);
      StdOut.printf("c1: %s, c2: %s\n", c1, c2);
      StdOut.printf("c1 is earlier than c2: %b\n", c1.isEarlierThan(c2));
      Clock c3 = new Clock("22:59");

      StdOut.printf("c3: %s\n", c3);
      StdOut.printf("c3 toc 1440\n");
      c3.toc(1440);
      StdOut.printf("c3: %s\n", c3);

      Clock c4 = new Clock("23:59");
      StdOut.printf("c4: %s\n", c4);
      StdOut.printf("c4 toc 100\n");
      c4.toc(100);
      StdOut.printf("c4: %s\n", c4);

      Clock c5 = new Clock(0,0);
      StdOut.printf("c5: %s\n", c5);
      StdOut.printf("c5 toc 30000\n");
      c5.toc(30000);
      StdOut.printf("c5: %s\n", c5);
    }
}
