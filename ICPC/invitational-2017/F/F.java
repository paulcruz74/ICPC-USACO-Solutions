import java.io.*;
import java.util.*;

public class F {
  public static void main(String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(f.readLine());
    String[] orig = new String[n];
    for (int i = 0; i < n; i++) {
      orig[i] = f.readLine();
    }
    solve(x, y, orig);
  }
  public static void solve(int x, int y, String[] orig) {
    String[] copy = new String[orig.length];
    for (int i = 0; i < orig.length; i++) {
      copy[i] = orig[i];
    }
    for (int i = 0; i < copy.length; i++) {
      copy[i] = "Forward";
      if (works(x, y, copy) && !orig[i].equals("Forward")) {
        System.out.printf("%d Forward\n", i+1);
        break;
      }
      copy[i] = "Left";
      if (works(x, y, copy) && !orig[i].equals("Left")) {
        System.out.printf("%d Left\n", i+1);
        break;
      }
      copy[i] = "Right";
      if (works(x, y, copy) && !orig[i].equals("Right")) {
        System.out.printf("%d Right\n", i+1);
        break;
      }
      copy[i] = orig[i];
    }
  }
  public static boolean works(int x, int y, String[] comm) {
    int botx = 0, boty = 0;
    int dir = 0; // north 0 east 1 south 2 west 3
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    for (String s : comm) {
      if (s.equals("Forward")) {
        botx += dx[dir];
        boty += dy[dir];
      }
      else if (s.equals("Right")) {
        dir++;
        dir %= 4;
      }
      else {
        dir += 3;
        dir %= 4;
      }
    }
    return botx == x && boty == y;
  }
}