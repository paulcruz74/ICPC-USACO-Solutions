import java.io.*;
import java.util.*;

public class D {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int count = 1;
    while (n != 0 || m != 0) {
      int[][] q, k, p;
      int nq = in.nextInt();
      int[][] board = new int[n+1][m+1];
      q = new int[nq][2];
      for (int i = 0; i < nq; i++) {
        q[i][0] = in.nextInt();
        q[i][1] = in.nextInt();
        board[q[i][0]][q[i][1]] = 2;
      }
      int nk = in.nextInt();
      k = new int[nk][2];
      for (int i = 0; i < nk; i++) {
        k[i][0] = in.nextInt();
        k[i][1] = in.nextInt();
        board[k[i][0]][k[i][1]] = 2;
      }
      int np = in.nextInt();
      p = new int[np][2];
      for (int i = 0; i < np; i++) {
        p[i][0] = in.nextInt();
        p[i][1] = in.nextInt();
        board[p[i][0]][p[i][1]] = 2;
      }
      for (int i = 0; i < nq; i++) {
        int[] dr = {0, 0, 1, 1, 1, -1, -1, -1};
        int[] dc = {1, -1, 1, 0, -1, 1, 0, -1};
        int r = q[i][0];
        int c = q[i][1];
        boolean[] stop = new boolean[8];
        for (int x = 1; x <= Math.max(m, n); x++) {
          for (int dir = 0; dir < 8; dir++) {
            if (!stop[dir]) {
              int newr = r + dr[dir] * x;
              int newc = c + dc[dir] * x;
              if (inBounds(newr, newc, n, m) && board[newr][newc] != 2) {
                board[newr][newc] = 1;
              }
              else {
                stop[dir] = true;
              }
            }
          }
        }
      }
      for(int i = 0; i < nk; i++) {
        int[] dr = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] dc = {2, 1, -1, -2, -2, -1, 1, 2};
        int r = k[i][0];
        int c = k[i][1];
        for (int j = 0; j < 8; j++) {
          int newr = r + dr[j];
          int newc = c + dc[j];
          if (inBounds(newr, newc, n, m) && board[newr][newc] != 2) {
            board[newr][newc] = 1;
          }
        }
      }
      int total = 0;
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
          if (board[i][j] == 0) {
            total++;
          }
        }
      }
      // for (int[] t : board) {
      //   System.out.println(Arrays.toString(t));
      // }
      System.out.printf("Board %d has %d safe squares.\n", count++, total);
      n = in.nextInt();
      m = in.nextInt();
    }
  }
  public static boolean inBounds(int r, int c, int n, int m) {
    return (r >= 1 && r <= n && c >= 1 && c <= m);
  }
}