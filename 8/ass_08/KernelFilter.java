import java.awt.Color;

public class KernelFilter {
    private static double[][] getMotionBlurKernel() {
      double[][] kernel = new double[9][9];

      for (int r = 0; r < 9; r++)
        for (int c = 0; c < 9; c++)
          if (r == c) kernel[r][c] = 1./9;

      return kernel;
    }

    private static double[][] getGaussianKernel() {
      double[][] kernel = {
        {1, 2, 1},
        {2, 4, 2},
        {1, 2, 1}
      };

      for (int r = 0; r < 3; r++)
        for (int c = 0; c < 3; c++)
          kernel[r][c] = 1./16 * kernel[r][c];

      return kernel;
    }

    private static double[][] getSharpenKernel() {
      double[][] kernel = {
        {0, -1, 0},
        {-1, 5, -1},
        {0, -1, 0}
      };
      return kernel;
    }

    private static double[][] getLaplacianKernel() {
      double[][] kernel = {
        {-1, -1, -1},
        {-1, 8, -1},
        {-1, -1, -1}
      };
      return kernel;
    }

    private static double[][] getEmbossKernel() {
      double[][] kernel = {
        {-2, -1, 0},
        {-1, 1, 1},
        {0, 1, 2}
      };
      return kernel;
    }

    private static double[][] getIdentityKernel() {
      double[][] kernel = {
        {0, 0, 0},
        {0, 1, 0},
        {0, 0, 0}
      };
      return kernel;
    }

    private static Picture identity(Picture picture)
    {
      return kernel(picture, getIdentityKernel());
    }

    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture)
    {
      return kernel(picture, getGaussianKernel());
    }

    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture)
    {
      return kernel(picture, getSharpenKernel());
    }

    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture)
    {
      return kernel(picture, getLaplacianKernel());
    }

    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture)
    {
      return kernel(picture, getEmbossKernel());
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture)
    {
      return kernel(picture, getMotionBlurKernel());
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
          Color pixel = kernel(x, y, picture, weights);

          //Picture subPicture = getPeriodicBoundary(y, x, picture, weights);
          //Color pixel = applyFilter(subPicture, weights);

          //Color pixel = getCentral(subPicture);
          //if (! pixel.equals(picture.get(x,y))) StdOut.printf("pixel (%d, %d) not the same\n", x, y);
          //if (pixel.equals(picture.get(x,y))) StdOut.printf("pixel (%d, %d) are the same\n", x, y);

          pic.set(x, y, pixel);
        }
      }

      //if (! pic.equals(picture)) StdOut.printf("picture not the same\n");

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

      // distance 

      int[] reds = new int[wh*ww];
      int[] greens = new int[wh*ww];
      int[] blues = new int[wh*ww];
  
      //StdOut.printf("kernel for pixel (%d, %d), center: (%d, %d):\n", x, y, wcx, wcy);

      for (int wy = 0; wy < wh; wy++)
      {
        for (int wx = 0; wx < ww; wx++)
        {
          // fill lienearCombination elems
          int px = Math.floorMod(x - wcx + wx, pw);
          int py = Math.floorMod(y - wcy + wy, ph);
          //StdOut.printf(" [(%d, %d) * (%d, %d)] ", wy, wx, py, px);

          double w = weights[wy][wx];
          /*
          */
          int offset = wy * ww + wx;
          int r = (int) Math.round(picture.get(px, py).getRed() * w);
          reds[offset] = r;
          int g = (int) Math.round(picture.get(px, py).getGreen() * w);
          greens[offset] = g;
          int b = (int) Math.round(picture.get(px, py).getBlue() * w);
          blues[offset] = b;
        }
        //StdOut.printf("\n");
      }

      return new Color(
          reduce(reds),
          reduce(greens),
          reduce(blues));
      //return new Color(0,0,0);
    }


    private static int reduce(int[] elems)
    {
      int result = 0;
      for (int i = 0; i < elems.length; i++)
      {
        result += elems[i];
      }

      if (result < 0) result = 0;
      if (result > 255) result = 255;

      return result;
    }

    private static void printPicture(Picture p)
    {
      StdOut.printf("Picture:\n");
      for (int y = 0; y < p.height(); y++)
      {
        for (int x = 0; x < p.width(); x++)
          StdOut.printf(" (%d, %d): [%3s ", y, x, p.get(x,y).toString().substring(25));
        StdOut.printf("\n");
      }    
    }

    private static void printKernel(double[][] k)
    {
      StdOut.printf("Kernel:\n");
      for (int y = 0; y < k.length; y++)
      {
        for (int x = 0; x < k[0].length; x++)
          StdOut.printf(" (%d, %d): [%.3f] ", y, x, k[y][x]);
        StdOut.printf("\n");
      }    
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
      
      /*
      Picture p = new Picture(6,5);
      for (int y = 0; y < p.height(); y++)
        for (int x = 0; x < p.width(); x++)
          p.set(x,y, new Color(0,0,6*y + x));

      printKernel(getGaussianKernel());
      printPicture(p);
      gaussian(p);
      */
    }
}
