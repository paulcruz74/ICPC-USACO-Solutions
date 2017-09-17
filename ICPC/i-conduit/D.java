import java.io.*;
import java.util.*;
import java.awt.geom.*;

public class D {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    while(n != 0) {
      double[][][] pts = new double[n][2][2];
      for(int i = 0;i < n;i++) {
        for(int j = 0;j < 4;j++) {
          pts[i][j/2][j%2] = in.nextDouble();
        }
      }
      double[][] slope = new double[n][2];
      for(int i = 0;i < n;i++) {
        slope[i][0] = (pts[i][1][1] - pts[i][0][1]);
        slope[i][1] = (pts[i][1][0] - pts[i][0][0]);
      }
      // System.out.println("n: "+n);
      HashMap<Integer, HashSet<Integer>> overlap = new HashMap<>();
      for(int i = 0;i < n;i++){
        overlap.put(i, new HashSet<>());
      }
      for(int i = 0;i < n;i++){
        for(int j = 0;j < i;j++) {
          if (i == j) continue;
          // System.out.println("val: " + Math.abs(slope[i][0]*slope[j][1] - slope[j][0]*slope[i][1]));
          if (Math.abs(slope[i][0]*slope[j][1] - slope[j][0]*slope[i][1]) < 0.0001) {
            boolean intersects = Line2D.linesIntersect(pts[i][0][0], pts[i][0][1],
                                                       pts[i][1][0], pts[i][1][1],
                                                       pts[j][0][0], pts[j][0][1],
                                                       pts[j][1][0], pts[j][1][1]);
            if (intersects) {
              overlap.get(i).add(j);
              overlap.get(j).add(i);
            }
          }
        }
      }
      HashSet<Integer> visited = new HashSet<>();
      int total = 0;
      for(int i = 0; i < n;i++) {
        total += dfs(overlap, i, visited);
      }
      System.out.println(total);
      n = in.nextInt();
    }
  }
  public static long dfs(HashMap<Integer, HashSet<Integer>> adj, int node, HashSet<Integer> visited) {
    if(visited.contains(node)) {
      return 0;
    }
    else {
      visited.add(node);
      for(int i : adj.get(node)) {
        dfs(adj, i, visited);
      }
      return 1;
    }
  }
}