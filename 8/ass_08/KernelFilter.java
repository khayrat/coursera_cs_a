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
    
      return pic;
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
