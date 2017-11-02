import java.io.*;
import java.util.*;

public class G {
  static class Score implements Comparable<Score> {
    private int team;
    private int np;
    private int pen;
    public Score(int a, int b, int c) {
      np = a;
      pen = b;
      team = c;
    }
    public int compareTo(Score s) {
      if (this.np < s.np) {
        return 1;
      }
      else if (this.np > s.np) {
        return -1;
      }
      else {
        if (this.pen == s.pen) {
          return this.team - s.team;
        }
        else if (this.pen < s.pen) {
          return -1;
        }
        else {
          return 1;
        }
      }
    }
    public void update(int p) {
      this.np++;
      this.pen += p;
    }
    public String toString() {
      return np + " " + pen;
    }
    public boolean equals(Object o) {
      if (o instanceof Score) {
        Score s = (Score)o;
        return s.team == this.team;
      }
      else {
        return false;
      }
    }
  }
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    HashMap<Integer, Score> map = new HashMap<>();
    TreeSet<Score> allScores = new TreeSet<Score>();
    int n = in.nextInt();
    int m = in.nextInt();
    for (int i = 1; i <= n; i++) {
      map.put(i, new Score(0,0, i));
      allScores.add(map.get(i));
    }
    for (int i = 0; i < m; i++) {
      // for(Score s : allScores) {
      //   System.out.println (s);
      // }
      // System.out.println();
      int team = in.nextInt();
      int pen = in.nextInt();
      allScores.remove(map.get(team));
      map.get(team).update(pen);
      allScores.add(map.get(team));
      System.out.println(allScores.headSet(map.get(1)).size()+1);
    }
  }
}