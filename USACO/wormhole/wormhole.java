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
  public static HashMap<Integer, Integer> rightMap;
  public static int[] pairs;
  public static boolean[] used;
  public static void main(String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
    n = Integer.parseInt(f.readLine());
    portals = new int[n][2];
    for(int i = 0;i < n;i++) {
      StringTokenizer st = new StringTokenizer(f.readLine());
      portals[i][0] = Integer.parseInt(st.nextToken());
      portals[i][1] = Integer.parseInt(st.nextToken());
    }
    rightMap = new HashMap<>();
    for(int i = 0;i < n;i++) {
      rightMap.put(i, rightPortal(i));
    }
    // try all pairs
    pairs = new int[n];
    used = new boolean[n];
    out.println(solve(0));
    out.close();
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
  public static int solve(int filled) {
    if (filled == n) {
      for(int start = 0;start < n;start++) {
        int step = 0;
        int next = start;
        while(step <= 2*n) {
          if (next == -1) {
            break;
          }
          if (step % 2 == 0) {
            next = rightMap.get(next);
          }
          else {
            next = pairs[next];
          }
          step++;
        }
        if (next != -1) {
          // System.out.println(Arrays.toString(pairs));
          return 1;
        }
      }
      return 0;
    }
    else {
      int total = 0;
      int mustMatch = -1;
      for(int i = 0;i < n;i++) {
        if (!used[i]) {
          mustMatch = i;
          break;
        }
      }
      for(int j = mustMatch+1;j < n;j++) {
            if (!used[j]) {
              used[mustMatch] = true;
              used[j] = true;
              pairs[mustMatch] = j;
              pairs[j] = mustMatch;
              total += solve(filled+2);
              used[mustMatch] = false;
              used[j] = false;
              pairs[mustMatch] = 0;
              pairs[j] = 0;
            }
          }
      return total;
    }
  }
}