/*
ID: paulcru1
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.util.*;

class sprime {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
    int n = Integer.parseInt(f.readLine());
    Queue<Integer> sprimes = new LinkedList<>();
    for(int i = 2;i <= 9;i++) {
      if (isPrime(i)) {
        sprimes.add(i);
      }
    }
    for (int i = 2;i <= n;i++) {
      int size = sprimes.size();
      for (int j = 0;j < size;j++) {
        int t = sprimes.poll();
        for(int digit = 1;digit <= 9;digit += 2) {
          int newT = t * 10 + digit;
          if (isPrime(newT)) {
            sprimes.add(newT);
          }
        }
      }
    }

    while (!sprimes.isEmpty()) {
      out.println(sprimes.poll());
    }
    out.close();
    System.exit(0);
  }
  public static boolean isPrime(int x) {
    for(int i = 2;i <= Math.sqrt(x);i++) {
      if (x % i == 0) {
        return false;
      }
    }
    return true;
  }
}