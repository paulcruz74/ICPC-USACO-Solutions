import java.io.*;
import java.util.*;
import java.awt.Point;

public class E {
  static class Tuple {
    int val;
    int depth;
    public Tuple(int a, int b) {
      val = a;
      depth = b;
    }
  }
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int R = in.nextInt();
    int N = in.nextInt();
    int A = in.nextInt();
    int B = in.nextInt();
    int X = in.nextInt();
    Set<Integer> bad = new HashSet<>();
    for (int i = 0; i < X; i++) {
      bad.add(in.nextInt());
    }
    Map<Integer, Set<Integer>> adjLists = new HashMap<>();
    for (int i = 1; i <= numCells(R); i++) {
      adjLists.put(i, new HashSet<>());
    }
    int numInRow = R;
    int next = 1;
    for (int row = 1; row < R; row++) {
      for (int cell = 0; cell < numInRow; cell++) {
        int cellNo = next++;
        if (bad.contains(cellNo)) {
          continue;
        }
        List<Integer> adj = new ArrayList<>();
        int bottomLeft = cellNo + numInRow;
        int bottomRight = bottomLeft + 1;
        adj.add(bottomLeft);
        adj.add(bottomRight);
        if (cell != numInRow-1) {
          adj.add(cellNo+1);
        }
        for (int node : adj) {
          if (bad.contains(node)) continue;
          adjLists.get(cellNo).add(node);
          adjLists.get(node).add(cellNo);
        }
      }
      numInRow++;
    }

    for (int row = 1; row < R; row++) {
      for (int cell = 0; cell < numInRow; cell++) {
        int cellNo = next++;
        if (bad.contains(cellNo)) {
          continue;
        }
        List<Integer> adj = new ArrayList<>();
        if (cell != 0) {
          adj.add(cellNo + numInRow - 1);
        }
        if (cell != numInRow-1) {
          adj.add(cellNo + numInRow);
          adj.add(cellNo + 1);
        }
        for (int node : adj) {
          if (bad.contains(node)) continue;
          adjLists.get(cellNo).add(node);
          adjLists.get(node).add(cellNo);
        }
      }
      numInRow--;
    }

    for (int cell = 0; cell < numInRow; cell++) {
      int cellNo = next++;
      if (bad.contains(cellNo)) {
        continue;
      }
      if (cell != numInRow-1) {
        if (bad.contains(cellNo+1)) continue;
        adjLists.get(cellNo).add(cellNo+1);
        adjLists.get(cellNo+1).add(cellNo);
      }
    }
    int ret = bfs(A,B,adjLists, N);
    if (ret == -1) {
      System.out.println("No");
    }
    else {
      System.out.println(ret);
    }

  }
  public static int bfs(int A, int B, Map<Integer, Set<Integer>> adjLists, int depth) {
    Queue<Tuple> q = new LinkedList<>();
    q.add(new Tuple(A, 0));
    Set<Integer> visited = new HashSet<>();
    while (!q.isEmpty()) {
      Tuple t = q.poll();
      visited.add(t.val);
      if (t.depth > depth) {
        break;
      }
      else if (t.val == B) {
        return t.depth;
      }
      for (int neighbor : adjLists.get(t.val)) {
        if (!visited.contains(neighbor)) {
          q.add(new Tuple(neighbor, t.depth+1));
        }
      }
    }
    return -1;
  }
  public static int numCells(int R) {
    return R*R*R-(R-1)*(R-1)*(R-1);
  }
}