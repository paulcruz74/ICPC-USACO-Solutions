import java.io.*;
import java.util.*;

public class E {
  public static int possible;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int testCase = 1;
    while (n != 0 || m != 0) {
      int[][] e = new int[m][3];
      for (int i = 0; i < m; i++) {
        e[i][0] = in.nextInt();
        e[i][1] = in.nextInt();
        e[i][2] = in.nextInt();
      }
      int min = checkAll(n, e);
      if (min == Integer.MAX_VALUE) {
        System.out.printf("There is no reliable net possible for test case %d.\n", testCase++);
      }
      else {
        System.out.printf("The minimal cost for test case %d is %d.\n", testCase++, min);
      }
      n = in.nextInt();
      m = in.nextInt();
    }
  }

  public static int checkAll(int n, int[][] e) {
    possible = Integer.MAX_VALUE;
    boolean[] used = new boolean[e.length];
    dfs(n, e, used, 0);
    return possible;
  }

  public static void dfs(int n, int[][] e, boolean[] used, int next) {
    if (next == e.length && works(n, e, used)) {
      possible = Math.min(possible, cost(e, used));
    }
    else if (next < e.length) {
      used[next] = false;
      dfs(n, e, used, next+1);
      used[next] = true;
      dfs(n, e, used, next+1);
    }
  }

  public static boolean works(int n, int[][] e, boolean[] used) {
    int[] deg = new int[n+1];
    for (int i = 0; i < used.length; i++) {
      if (used[i]) {
        deg[e[i][0]]++;
        deg[e[i][1]]++;
      }
    }
    for (int i = 1; i <= n; i++) {
      if (deg[i] != 2) {
        return false;
      }
    }

    boolean[] visited = new boolean[n+1];
    visit(1, e, visited, used);
    visited[0] = true;
    for (boolean b : visited) {
      if (!b) {
        return false;
      }
    }
    return true;
  }

  public static void visit(int node, int[][] e, boolean[] visited, boolean[] used) {
    visited[node] = true;
    for (int i = 0; i < e.length; i++) {
      if (used[i] && e[i][0] == node && !visited[e[i][1]]) {
        visit(e[i][1], e, visited, used);
      }
      else if (used[i] && e[i][1] == node && !visited[e[i][0]]) {
        visit(e[i][0], e, visited, used);
      }
    }
  }
  public static int cost(int[][] e, boolean[] used) {
    int total = 0;
    for (int i = 0; i < used.length; i++) {
      if (used[i]) {
        total += e[i][2];
      }
    } 
    return total;
  }
}