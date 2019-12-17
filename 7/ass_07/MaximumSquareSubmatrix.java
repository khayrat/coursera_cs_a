public class MaximumSquareSubmatrix 
{
  // Returns the size of the largest contiguous square submatrix
  // of a[][] containing only 1s.
  public static int size(int[][] a)
  {
    return 0;
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
