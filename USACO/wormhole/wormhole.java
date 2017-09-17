/*
ID: paulcru1
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.*;

class wormhole {
  public static int n;
  public static int[][] portals;
  public static HashMap<Integer, HashSet<Integer>> adj;
  public static void main(String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
    n = Integer.parseInt(f.readLine());
    portals = new int[n][2];
    for(int i = 0;i < n;i++) {
      StringTokenizer st = new StringTokenizer(f.readLine());
      portals[i][0] = Integer.parseInt(st.nextToken());
      portals[i][1] = Integer.parseInt(st.nextToken());
    }
    HashMap<Integer, Integer> rightMap = new HashMap<>();
    for(int i = 0;i < n;i++) {
      rightMap.put(i, rightPortal(i));
    }
    
    // try all pairs
    int chosen = 0;
    boolean[] used = new boolean[n];
    int[][] pairs = new int[n/2][2];
    while (chosen < n) {
      
    }
  }
  public static int rightPortal(int p) {
    int rightIdx = -1;
    int minX = Integer.MAX_VALUE;
    for(int i = 0;i < n;i++) {
      if (i == p) {
        continue;
      }
      else {
        boolean sameY = portals[i][1] == portals[p][1];
        boolean right = portals[i][0] > portals[p][0];
        if (sameY && right && portals[i][0] < minX) {
          rightIdx = i;
          minX = portals[i][0];
        }
      }
    }
    return rightIdx;
  }
}