import java.io.*;
import java.util.*;

public class H {
  public static final long MOD = 1000000007;
  public static final int MAX = 1000000;
  public static Map<Integer, Long> fMemo;
  public static void main(String[] args) {
    fMemo = new HashMap<>();
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    int X = in.nextInt();
    int Y = in.nextInt();
    long total = 0;
    fMemo.put(0, (long)1);
    fMemo.put(1, (long)1);
    for (int i = 2; i <= MAX; i++) {
      fMemo.put(i, (fMemo.get(i-1)*i)%MOD);
    }
    for (int steps = 1; steps <= Math.min(N/X, N/Y); steps++) {
      long temp = (calc(N,X,steps)%MOD)*(calc(N,Y,steps)%MOD);
      temp %= MOD;
      total += temp;
      total %= MOD;
    }
    System.out.println(total%MOD);
  }
  public static long calc(int N, int k, int steps) {
    // long t1 = c((N/k)-1, steps-1)%MOD;
    // long t2 = c((N%k)+ + steps-1, steps-1)%MOD;
    long t3 = c(N-k*steps+steps-1, steps-1);
    return (t3)%MOD;
  }
  public static long f(int i) {
    return fMemo.get(i)%MOD;
  }
  public static long c(int a, int b) {
    long top = f(a)%MOD;
    long bot = (f(b)%MOD)*(f(a-b)%MOD);
    top %= MOD;
    bot %= MOD;
    return (top*pow(bot, MOD-2))%MOD;
  }
  public static long pow(long a, long b) {
    if (b == 1) {
      return a % MOD;
    }
    else if (b == 0) {
      return 1;
    }
    else {
      long temp = (a*a)%MOD;
      if (b%2==1) {
        return (pow(temp, b/2)*a)%MOD;
      }
      else {
        return pow(temp, b/2)%MOD;
      }
    }
  }
}