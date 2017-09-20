import java.io.*;
import java.util.*;

public class J {
  public static void main(String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    // read input
    char[][] board = new char[r][c];
    for (int i = 0; i < r; i++) {
      String s = f.readLine();
      for (int j = 0; j < c; j++) {
        board[i][j] = s.charAt(j);
      }
    }

    // determine location and direction
    // U = 0, R = 1, D = 2, L = 3
    int rpos = -1;
    int cpos = -1;
    int direction = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (board[i][j] != '.' && board[i][j] != '*') {
          rpos = i;
          cpos = j;
          if (board[i][j] == 'U') {
            direction = 0;
          }
          else if (board[i][j] == 'R') {
            direction = 1;
          }
          else if (board[i][j] == 'D') {
            direction = 2;
          }
          else {
            direction = 3;
          }
        }
      }
    }
    int count = 0;
    boolean[][][] v1 = new boolean[r][c][4];
    boolean[][] v2 = new boolean[r][c];
    while (true) {
      if (v1[rpos][cpos][direction]) {
        break;
      }
      else if (!v2[rpos][cpos]) {
        count++;
        v2[rpos][cpos] = true;
      }
      v1[rpos][cpos][direction] = true;

      // find next position/direction and change to it
      int nextr, nextc;
      if (direction == 0) {
        nextr = rpos - 1;
        nextc = cpos;
      }
      else if (direction == 1) {
        nextr = rpos;
        nextc = cpos + 1;
      }
      else if (direction == 2) {
        nextr = rpos + 1;
        nextc = cpos;
      }
      else {
        nextr = rpos;
        nextc = cpos - 1;
      }
      if (inBounds(nextr, nextc, r, c) && board[nextr][nextc] != '*') {
        rpos = nextr;
        cpos = nextc;
      }
      else {
        direction++;
        direction %= 4;
      }
    }
    System.out.println(count);

  }
  public static boolean inBounds(int nextr, int nextc, int r, int c) {
    return (nextr >= 0 && nextr < r && nextc >= 0 && nextc < c);
  }
}