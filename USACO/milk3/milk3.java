/*
ID: paulcru1
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;

class milk3 {
  public static boolean[][][] visited;
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    visited = new boolean[a+1][b+1][c+1];
    visit(0, 0, c);
    String s = "";
    for(int x = 0;x <= c;x++) {
      boolean works = false;
      for(int y = 0;y <= b;y++) {
        if (visited[0][y][x]) {
          works = true;
        }
      }
      if (works) {
        s += x + " ";
      }
    }
    out.println(s.trim());
    out.close();
    System.exit(0);
  }
  public static void visit(int a, int b, int c) {
    visited[a][b][c] = true;
    if (a < visited.length - 1) {
      // pour from b to a
      int x = Math.min(b, visited.length - 1 - a);
      if (!visited[a+x][b-x][c]) {
        visit(a+x, b-x, c);
      }
      // pour from c to a
      int y = Math.min(c, visited.length - 1 - a);
      if (!visited[a+y][b][c-y]) {
        visit(a+y, b, c-y);
      }

    }
    if (b < visited[0].length - 1) {
      // pour from a to b
      int x = Math.min(a, visited[0].length - 1 - b);
      if (!visited[a-x][b+x][c]) {
        visit(a-x, b+x, c);
      }
      // pour from c to b
      int y = Math.min(c, visited[0].length - 1 - b);
      if (!visited[a][b+y][c-y]) {
        visit(a, b+y, c-y);
      }
    }
    if (c < visited[0][0].length - 1) {
      // pour from a to c
      int x = Math.min(a, visited[0][0].length - 1 - c);
      if (!visited[a-x][b][c+x]) {
        visit(a-x, b, c+x);
      }
      // pour from b to c
      int y = Math.min(b, visited[0][0].length - 1 - c);
      if (!visited[a][b-y][c+y]) {
        visit(a, b-y, c+y);
      }
    }
  } 
}