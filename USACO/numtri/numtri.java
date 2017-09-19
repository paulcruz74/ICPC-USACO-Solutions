/*
ID: paulcru1
LANG: JAVA
TASK: numtri
*/
import java.io.*;
import java.util.*;

class numtri {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
    int n = Integer.parseInt(f.readLine());
    int[][] tri = new int[n][n];
    tri[0][0] = Integer.parseInt(f.readLine());
    for(int i = 1;i < n;i++) {
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int j = 0;j <= i;j++) {
        int val = Integer.parseInt(st.nextToken());
        if (j == 0) {
          tri[i][j] = val + tri[i-1][j];
        }
        else if (j == i) {
          tri[i][j] = val + tri[i-1][j-1];
        }
        else {
          tri[i][j] = val + Math.max(tri[i-1][j-1], tri[i-1][j]);
        }
      }
    }
    int max = Integer.MIN_VALUE;
    for(int i = 0;i < n;i++) {
      max = Math.max(max, tri[n-1][i]);
    }
    out.println(max);
    out.close();
    System.exit(0);
  }
}