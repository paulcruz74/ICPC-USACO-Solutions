/*
ID: paulcru1
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.*;

class skidesign {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
    int n = Integer.parseInt(f.readLine());
    int[] h = new int[n];
    for(int i = 0;i < n;i++) {
      h[i] = Integer.parseInt(f.readLine());
    }
    int best = Integer.MAX_VALUE;
    for(int min = 0;min <= 100;min++) {
      int score = 0;
      for(int i = 0;i < n;i++) {
        if (h[i] < min) {
          score += (min-h[i])*(min-h[i]);
        }
        else if (h[i] > min + 17) {
          score += (h[i]-min-17)*(h[i]-min-17);
        }
      }
      if (score < best) {
        best = score;
      }
    }
    out.println(best);
    out.close();
    System.exit(0);
  }
}