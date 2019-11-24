public class Minesweeper {

    public static void main(String[] args) {
      int m = Integer.parseInt(args[0]);
      int n = Integer.parseInt(args[1]);
      int k = Integer.parseInt(args[2]); // assume k <= m*n
      int[][] grid = new int[m][n];
      boolean[] offsets = new boolean[m*n];

      // calc mines
      for (int i = k; i > 0; i--) {
        int offset = 0;
        // find empty field
        while (true) {
          offset = (int) (Math.random() * m * n);
          if (!offsets[offset]) {
            offsets[offset] = true;
            break;
          }
        }
        
        // offset = r * n + c
        grid[offset / n][offset % n] = -1; // a mine
      }

      /*
      // assert minesAcc == k
      int minesAcc = 0;
      for (int r = 0; r < m; r++) 
        for (int c = 0; c < m; c++) if (grid[r][c] == -1) minesAcc++;
      assert minesAcc == k;
      */

      // calc mines surround
      for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
          // skip mines
          if (grid[r][c] == -1) continue;

          int mines = 0;
          if (/*tl*/ r != 0     && c != 0     && grid[r-1][c-1] == -1) mines++;
          if (/*t*/  r != 0                   && grid[r-1][c]   == -1) mines++;
          if (/*tr*/ r != 0     && c != (n-1) && grid[r-1][c+1] == -1) mines++;
          if (/*l*/                c != 0     && grid[r][c-1]   == -1) mines++;
          if (/*r*/                c != (n-1) && grid[r][c+1]   == -1) mines++;
          if (/*bl*/ r != (m-1) && c != 0     && grid[r+1][c-1] == -1) mines++;
          if (/*b*/  r != (m-1)               && grid[r+1][c]   == -1) mines++;
          if (/*br*/ r != (m-1) && c != (n-1) && grid[r+1][c+1] == -1) mines++;
          grid[r][c] = mines;
        }
      }

      // print grid
      for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
          System.out.print((grid[r][c] == -1 ? "*" : grid[r][c]) + "  ");
        }
        System.out.println();
      }
    }
}
