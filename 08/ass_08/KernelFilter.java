import java.awt.Color;

public class KernelFilter {
    private static Picture identity(Picture picture)
    {
      double[][] kernel = {
        {0, 0, 0},
        {0, 1, 0},
        {0, 0, 0}
      };

      return kernel(picture, kernel);
    }

    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture)
    {
      double[][] kernel = {
        {1, 2, 1},
        {2, 4, 2},
        {1, 2, 1}
      };

      for (int r = 0; r < 3; r++)
        for (int c = 0; c < 3; c++)
          kernel[r][c] = 1./16 * kernel[r][c];

      return kernel(picture, kernel);
    }

    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture)
    {
      double[][] kernel = {
        {0, -1, 0},
        {-1, 5, -1},
        {0, -1, 0}
      };

      return kernel(picture, kernel);
    }

    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture)
    {
      double[][] kernel = {
        {-1, -1, -1},
        {-1, 8, -1},
        {-1, -1, -1}
      };

      return kernel(picture, kernel);
    }

    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture)
    {
      double[][] kernel = {
        {-2, -1, 0},
        {-1, 1, 1},
        {0, 1, 2}
      };

      return kernel(picture, kernel);
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture)
    {
      double[][] kernel = new double[9][9];

      for (int r = 0; r < 9; r++)
        for (int c = 0; c < 9; c++)
          if (r == c) kernel[r][c] = 1./9;

      return kernel(picture, kernel);
    }

    // Returns a new picture that applies an arbitrary kernel filter to the given picture.
    private static Picture kernel(Picture picture, double[][] weights)
    {
      int w = picture.width();
      int h = picture.height();
      Picture pic = new Picture(w, h);

      for (int y = 0; y < h; y++)
      {
        for (int x = 0; x < w; x++)
        {
          //Color pixel = kernel(x, y, picture, weights);
          //pic.set(x, y, pixel);
          pic.set(x, y, kernel(x, y, picture, weights));
        }
      }

      return pic;
    }

    private static Color kernel(int x, int y, Picture picture, double[][] weights)
    {
      // picture dimension
      int ph = picture.height();
      int pw = picture.width();

      // kernel dimension
      int wh = weights.length;
      int ww = weights[0].length;

      // kernel center
      int wcy = (int) Math.floor(wh/2.);
      int wcx = (int) Math.floor(ww/2.);

      // elems of the rgb linearcombinations
      int n = wh*ww;
      double[] reds = new double[n];
      double[] greens = new double[n];
      double[] blues = new double[n];
  
      for (int wy = 0; wy < wh; wy++)
      {
        for (int wx = 0; wx < ww; wx++)
        {
          // match picture coordinates with kernel
          int px = Math.floorMod(x - wcx + wx, pw);
          int py = Math.floorMod(y - wcy + wy, ph);

          // actual wight
          double w = weights[wy][wx];

          // fill the linearcombinations
          int offset = wy * ww + wx;
          reds[offset]   = picture.get(px, py).getRed()   * w;
          greens[offset] = picture.get(px, py).getGreen() * w;
          blues[offset]  = picture.get(px, py).getBlue()  * w;
        }
      }

      return reduce(reds, greens, blues);
    }

    private static Color reduce(double[] reds, double[] greens, double[] blues)
    {
      double rr = 0;
      double gg = 0;
      double bb = 0;

      int n = reds.length;
      for (int i = 0; i < n; i++) 
      {
       rr += reds[i]; gg += greens[i]; bb += blues[i];
      }

      int r = (int) Math.round(rr);
      if (r < 0) r = 0;
      if (r > 255) r = 255;

      int g = (int) Math.round(gg);
      if (g < 0) g = 0;
      if (g > 255) g = 255;

      int b = (int) Math.round(bb);
      if (b < 0) b = 0;
      if (b > 255) b = 255;

      return new Color(r, g, b);
    }

    // Test client (ungraded).
    public static void main(String[] args)
    {
      Picture pic = new Picture(args[0]);
      pic.show();
      sharpen(pic).show();
      emboss(pic).show();
      gaussian(pic).show();
      motionBlur(pic).show();
      laplacian(pic).show();
      identity(pic).show();
    }
}
