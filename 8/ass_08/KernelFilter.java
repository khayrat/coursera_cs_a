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
          Picture subPicture = getPeriodicBoundary(y, x, picture, weights);
          Color pixel = applyFilter(subPicture, weights);

          //Color pixel = getCentral(subPicture);
          //if (! pixel.equals(picture.get(x,y))) StdOut.printf("pixel (%d, %d) not the same\n", x, y);
          //if (pixel.equals(picture.get(x,y))) StdOut.printf("pixel (%d, %d) are the same\n", x, y);

          pic.set(x, y, pixel);
        }
      }

      //if (! pic.equals(picture)) StdOut.printf("picture not the same\n");

      return pic;
    }

    private static Color getCentral(Picture picture)
    {
      int h = picture.height();
      int w = picture.width();

      // calculate center
      int cx = w / 2;
      int cy = h / 2;

    //  StdOut.printf("dim: (%d, %d), center: (%d, %d)\n", w, h, cx, cy);
     
      return picture.get(cx, cy); 
    }

    private static Picture getPeriodicBoundary(int y, int x, Picture picture, double[][] weights)
    {
      int hk = weights.length;
      int wk = weights[0].length;
      int hp = picture.height();
      int wp = picture.width();
      Picture subPicture = new Picture(wk, hk);

      // calculate center
      int cx = wk / 2;
      int cy = hk / 2;

      for (int yk = 0; yk < hk; yk++)
      {
        for (int xk = 0; xk < wk; xk++)
        {
          int bx = Math.floorMod(x - (cx - xk), wp);
          int by = Math.floorMod(y - (cy - yk), wp);

          subPicture.set(xk, yk, picture.get(bx, by));
/*
          if (x == 0 && y == 0)
          {
            StdOut.printf("xk,yk: (%d, %d), bx,by: (%d, %d)\n", xk, yk, bx, by);
          }
*/
        }
      }

      return subPicture;
    }

    private static Color applyFilter(Picture picture, double[][] kernel)
    {
      int h = kernel.length;
      int w = kernel[0].length;
      Color[] colorAcc = new Color[h*w];

      for (int y = 0; y < h; y++)
      {
        for (int x = 0; x < w; x++)
        {
          Color pixel = picture.get(x,y);

          int r = (int) Math.ceil(pixel.getRed() * kernel[y][x]);
          if (r < 0) r = 0;
          if (r > 255) r = 255;

          int g = (int) Math.ceil(pixel.getGreen() * kernel[y][x]);
          if (g < 0) g = 0;
          if (g > 255) g = 255;

          int b = (int) Math.ceil(pixel.getBlue() * kernel[y][x]);
          if (b < 0) b = 0;
          if (b > 255) b = 255;
    
          colorAcc[y*h+ x] = new Color(r, g, b); 
        }
      }

      return reduce(colorAcc);
    }

    private static Color reduce(Color[] cs)
    {
      int r = 0;
      int g = 0;
      int b = 0;

      for (int i = 0; i < cs.length; i++)
      {
        Color pixel = cs[i];
        r += pixel.getRed();
        g += pixel.getGreen();
        b += pixel.getBlue();
      }

      if (r < 0) r = 0;
      if (r > 255) r = 255;
      if (g < 0) g = 0;
      if (g > 255) g = 255;
      if (b < 0) b = 0;
      if (b > 255) b = 255;

      return new Color(r, g, b);
    }

    // Test client (ungraded).
    public static void main(String[] args)
    {
      Picture pic = new Picture(args[0]);
      pic.show();
      /*
      */
      sharpen(pic).show();
      emboss(pic).show();
      gaussian(pic).show();
      motionBlur(pic).show();
      laplacian(pic).show();
      identity(pic).show();
    }
}
