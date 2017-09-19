/*
ID: paulcru1
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;

class pprime {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    for(long x : genPal(a,b)) {
      if (isPrime(x)) {
        out.println(x);
      }
    }
    out.close();
    System.exit(0);
  }
  public static boolean isPrime(long a) {
    for(long i = 2;i <= Math.sqrt(a);i++) {
      if (a % i == 0) {
        return false;
      }
    }
    return true;
  }
  public static Set<Long> genPal(int a, int b) {
    Set<Long> ret = new TreeSet<>();
    Queue<String> vals = new LinkedList<>();
    vals.add("");
    for(int i = 0; i <= 9;i++) {
      vals.add(i+"");
    }
    while (!vals.isEmpty()) {
      String s = vals.poll();
      if (s.equals("")) {
        for(int i = 0;i <= 9;i++) {
          vals.add(i+s+i);
        }
      }
      else {
        long k = Long.parseLong(s);
        if (a <= k && k <= b) {
          ret.add(k);
        }
        if (s.length() <= 7) {
          for(int i = 0;i <= 9;i++) {
            vals.add(i+s+i);
          }
        }
      }
    }
    return ret;
  }
}