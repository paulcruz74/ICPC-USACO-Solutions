import java.io.*;
import java.util.*;

public class F {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[][] weights = new int[2][n];
    Set<Integer> uniqueWeights = new HashSet<>();
    Map<Integer, Integer> weightToRow = new HashMap<>();
    for (int i = 0;i < 2;i++) {
      for (int j = 0;j < n;j++) {
        weights[i][j] = in.nextInt();
        uniqueWeights.add(weights[i][j]);
        weightToRow.put(weights[i][j], weightToRow.getOrDefault(weights[i][j], 0) | (i + 1));
      }
    }
    Set<Integer> diffRows = new HashSet<>();
    int max = 0;
    for (int i : uniqueWeights) {
      int count = 0;
      for (int j = 0;j < n;j++) {
        if (weights[0][j] == i) {
          count++;
        }
      }
      if (count % 2 == 1) {
        max = Math.max(max, i);
        diffRows.add(i);
      }
    }
    int[][] w = new int[2][n - diffRows.size()];
    for(int i = 0;i < 2;i++) {
      int next = 0;
      for(int j = 0;j < n;j++) {
        if (!diffRows.contains(weights[i][j])) {
          w[i][next++] = weights[i][j];
        }
      }
    }
    // System.out.println(Arrays.toString(w[0]));
    // System.out.println(Arrays.toString(w[1]));
    // System.out.println(weightToRow);
    for(int i : uniqueWeights) {
      if (diffRows.contains(i)) {
        continue;
      }
      else {
        int row = weightToRow.get(i);
        int inst = 0;
        int[] idx = new int[2];
        int[] rowWeights = w[row-1];
        for(int j = 0;j < rowWeights.length;j++) {
          if(rowWeights[j] == i) {
            idx[inst++] = j;
          }
        }

        for(int j = idx[0];j <= idx[1];j++) {
          if (rowWeights[j] != -1 && rowWeights[j] != i) {
            max = Math.max(max, i);
          }
        }
        rowWeights[idx[0]] = -1;
        rowWeights[idx[1]] = -1;
      }
    } 
    System.out.println(max);
  }
}