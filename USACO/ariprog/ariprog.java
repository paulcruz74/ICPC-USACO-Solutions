/*
ID: paulcru1
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

class ariprog {
  public static int MAX;
  public static int MAXB;
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
    int n = Integer.parseInt(f.readLine());
    int m = Integer.parseInt(f.readLine());
    MAX = m*m*2;
    MAXB = m*m*2/(n-1);
    boolean[] bisquare = new boolean[MAX+1];
    for(int i = 0;i <= m;i++) {
      for(int j = 0;j <= m;j++) {
        bisquare[i*i+j*j] = true;
      }
    }
    boolean printed = false;
    for(int b = 1;b <= MAXB;b++) {
      for(int a = 0;a <= MAX;a++) {
        boolean works = true;
        for(int i = 0;i < n;i++) {
          if (a+b*i > MAX || !bisquare[a+b*i]) {
            works = false;
            break;
          }
        }
        if (works) {
          printed = true;
          out.println(a + " " + b);
        }
      }
    }
    if (!printed) {
      out.println("NONE");
    }
    out.close();
    System.exit(0);
  }
}