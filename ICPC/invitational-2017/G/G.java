import java.io.*;
import java.util.*;

class Point {
  int x;
  int y;
  public Point(int a, int b) {
    this.x = a;
    this.y = b;
  }
  public int hashCode() {
    int hash = 53;
    hash = ((hash + x) << 7) - (hash + x);
    hash = ((hash + y) << 7) - (hash + y);
    return hash;
  }
  public boolean equals(Object obj) {
    if (!(obj instanceof Point)) {
      return false;
    }
    Point p = (Point)obj;
    return this.x == p.x && this.y == p.y;
  }
}
public class G {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    HashSet<Point> visited = new HashSet<>();
    long total = 0;
    for (int i = 0; i < n; i++) {
      int x = in.nextInt();
      int y = in.nextInt();
      int[] dx = {2018, -2018, 0, 0, 1118, -1118, 1118, -1118, 1680, 1680, -1680, -1680};
      int[] dy = {0, 0, 2018, -2018, 1680, 1680, -1680, -1680, 1118, -1118, 1118, -1118};
      for (int j = 0; j < dx.length; j++) {
        int newx = x + dx[j];
        int newy = y + dy[j];
        if (visited.contains(new Point(newx, newy))) {
          total++;
        }
      }
      visited.add(new Point(x,y));
    } 
    System.out.println(total);
    
  }
}