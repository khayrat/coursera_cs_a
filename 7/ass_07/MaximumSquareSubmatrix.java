public class MaximumSquareSubmatrix 
{
  // Returns the size of the largest contiguous square submatrix
  // of a[][] containing only 1s.
  public static int size(int[][] a)
  {
    int n = a.length;
    int max = 0;
    int[][] sm = new int[n][n];

    // initialize first row
    for (int i = 0; i < n; i++)
    {
      sm[0][i] = a[0][i];
    }

    // initialize first colums
    for (int i = 0; i < n; i++)
    {
      sm[i][0] = a[i][0];
    }

    // traverse matrix a starting from second row/colums
    for (int i = 1; i < n; i++)
    {
      for (int j = 1; j < n; j++)
      {
        if (a[i][j] != 0)
        {
          int sub = 1 + minAdjacent(sm, i, j);
          sm[i][j] = sub;
          if (sub > max) max = sub;
        }
      }
    }

    return max;
  }

  private static int minAdjacent(int[][] a, int i, int j)
  {
    return Math.min(Math.min(a[i-1][j-1], a[i-1][j]), a[i][j-1]);
  }
    
  // Reads an n-by-n matrix of 0s and 1s from standard input
  // and prints the size of the largest contiguous square submatrix
  // containing only 1s.
  public static void main(String[] args)
  {
    int n = StdIn.readInt();
    int[][] matrix = new int[n][n];

    for(int i = 0; i < n; i++)
      for(int j = 0; j < n; j++)
        matrix[i][j] = StdIn.readInt();

    StdOut.printf("%d\n", size(matrix));

    /*
    for(int i = 0; i < n; i++)
    {
      for(int j = 0; j < n; j++)
        StdOut.printf(" %d ", matrix[i][j]);
      StdOut.printf("\n");
    }
    */
  }
}
