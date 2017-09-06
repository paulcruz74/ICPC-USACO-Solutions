import java.io.*;
import java.util.*;

public class BallsAndNeedles {
  class Node {
    private int x;
    private int y;
    private int z;
    private Map<Node> adj3D;
    private Map<Node> adj2D;
    public Node(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.adj3D = new HashMap<>();
      this.adj2D = new HashMap<>();
    }
    public int hashCode() {
      String s = x+"-"+y+"-"+z;
      return s.hashCode();
    }
  }
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int k = in.nextInt();
    Set<Node> allNodes = new HashSet<>();
    for(int i = 0;i < k;i++) {
      int[] n1 = new int[3];
      int[] n2 = new int[3];
      for(int j = 0;j < 3;j++) {
        n1[j] = in.nextInt();
      }
      for(int j = 0;j < 3;j++) {
        n2[j] = in.nextInt();
      }
      
    }
  }
}