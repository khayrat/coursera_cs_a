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

    private static Picture applyFilter(Picture picture, double[][] kernel)
    {
      int h = picture.height();
      int w = picture.width();
      Picture pic = new Picture(w, h);
    
      for (int r = 0; r < h; r++)
      {
        for (int c = 0; c < w; c++)
        {
          pic.set(r, c, applyFilter(r, c, picture, kernel)); 
        }
      }

      return pic;
    }

    private static Color applyFilter(int row, int cln, Picture picture, double[][] kernel)
    {
      int n = kernel.length;
      Color[] colors = new Color[n*n];
      for (int kr = 0; kr < n; kr++)
      {
        for (int kc = 0; kc < n; kc++)
        {
          Color pixel = getPixel(kr, kc, row, cln, picture);

          int r = (int) Math.ceil(pixel.getRed() * kernel[kr][kc]);
          if (r < 0) r = 0;
          if (r > 255) r = 255;

          int g = (int) Math.ceil(pixel.getGreen() * kernel[kr][kc]);
          if (g < 0) g = 0;
          if (g > 255) g = 255;

          int b = (int) Math.ceil(pixel.getBlue() * kernel[kr][kc]);
          if (b < 0) b = 0;
          if (b > 255) b = 255;
    
          colors[kr*kernel.length + kc] = new Color(r, g, b); 
        }
      }

      return sumColors(colors);
    }

    private static Color getPixel(int kr, int kc, int r, int c, Picture picture)
    {
      return null;
    }

    private static Color sumColors(Color[] cs)
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

    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture)
    {
      return applyFilter(picture, getGaussianKernel());
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

    // Test client (ungraded).
    public static void main(String[] args)
    {
      Picture pic = new Picture(args[0]);


    }
}
