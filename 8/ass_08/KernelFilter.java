import java.awt.Color;

public class KernelFilter {
    private static double[][] gausianKernel = null;

    private static double[][] getGaussianKernel() {
      if (gausianKernel == null)
      {
        double[][] kernel = {
          {1, 2, 1},
          {2, 4, 2},
          {1, 2, 1}
        };

        for (int r = 0; r < 3; r++)
          for (int c = 0; c < 3; c++)
            kernel[r][c] = 1./16 * kernel[r][c];

        gausianKernel = kernel;
      }
      return gausianKernel;
    }

    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture)
    {
      return kernel(picture, getGaussianKernel());
    }

    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture)
    {
      return null;
    }

    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture)
    {
      return null;
    }

    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture)
    {
      return null;
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture)
    {
      return null;
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
          pic.set(x, y, pixel);
        }
      }
      return pic;
    }

    private static Picture getPeriodicBoundary(int y, int x, Picture picture, double[][] weights)
    {
      int h = weights.length;
      int w = weights[0].length;
      int wp = picture.width();
      int hp = picture.height();
      Picture subPicture = new Picture(w, h);

      // calculate center
      int cx = w / 2;
      int cy = h / 2;

      // upper left corner
      int ulx = Math.floorMod(x - cx, wp); 
      int uly = Math.floorMod(y - cy, hp); 
      // lower right corner
      int lrx = Math.floorMod(x + (w-cx), wp); 
      int lry = Math.floorMod(y + (h-cy), hp); 

      for (int sy = uly, i=0; sy <= lry; sy++, i++)
      {
        for (int sx = ulx, j=0; sx < lrx; sx++, j++)
        {
          subPicture.set(j, i, picture.get(sx, sy));  
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
    
          colorAcc[y*r+ x] = new Color(r, g, b); 
        }
      }

      return reduce(colorAcc);
    }

    private static Color getPixel(int kr, int kc, int r, int c, Picture picture)
    {
      // check left
       
      // check right
      // check top
      // check down
      return null;
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
    }
}
