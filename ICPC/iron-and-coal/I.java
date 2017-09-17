import java.io.*;
import java.util.*;

public class I {
  static int n, m, k;
  static HashSet<Integer> iron;
  static HashSet<Integer> coal;
  static HashMap<Integer, HashSet<Integer>> adj;
  static int[][] dists;
  static final int INF = Integer.MAX_VALUE/4;
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    n = in.nextInt();
    m = in.nextInt();
    k = in.nextInt();
    iron = new HashSet<>();
    for(int i = 0;i < m;i++) {
      iron.add(in.nextInt());
    }
    coal = new HashSet<>();
    for(int i = 0;i < k;i++) {
      coal.add(in.nextInt());
    }
    adj = new HashMap<>();
    for(int i = 1;i <= n;i++) {
      int numAdj = in.nextInt();
      HashSet<Integer> newSet = new HashSet<>();
      for(int j = 0;j < numAdj;j++) {
        newSet.add(in.nextInt());
      }
      adj.put(i, newSet);
    }
    dists = new int[n+1][n+1];
    for(int i = 1;i <= n;i++) {
      for(int j = 1;j <= n;j++) {
        dists[i][j] = INF;
      }
    }
    for(int i = 1;i <= n;i++) {
      // System.out.println(adj.get(i));
      bfs(i);
    }
    boolean worked = false;
    int min = INF;
    for(int i = 1;i <= n;i++) {
      int a = dists[1][i];
      int b = INF;
      for (int j : coal) {
        b = Math.min(b, dists[i][j]);
      }
      int c = INF;
      for(int j : iron) {
        c = Math.min(c, dists[i][j]);
      }
      if (a == INF || b == INF || c == INF) {
        continue;
      }
      worked = true;
      min = Math.min(min, a+b+c);
    }
    if (worked) {
      System.out.println(min);
    }
    else {
      System.out.println("impossible");
    }
    
  }
  public static void bfs(int start) {
    HashSet<Integer> visited = new HashSet<>();
    Queue<Integer> nodes = new LinkedList<>();
    nodes.add(start);
    int dist = 0;
    dists[start][start] = 0;
    while(!nodes.isEmpty()) {
      int curr = nodes.poll();
      // System.out.println(curr);
      visited.add(curr);
      for(int m : adj.get(curr)) {
        if(!visited.contains(m)) {
          nodes.add(m);
          dists[start][m] = dist + 1;
        }
      }
      // System.out.println();
      dist++;
    }
  }
}