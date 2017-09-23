import java.io.*;
import java.util.*;

public class Main {
  public static final double ERR = 0.00000001;
  public static void main(String[] args) throws IOException{
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    while (f.ready()) {
      StringTokenizer st = new StringTokenizer(f.readLine());
      int n = Integer.parseInt(st.nextToken());
      int T = Integer.parseInt(st.nextToken());
      double[][] c = new double[n][3];
      for (int i = 0; i < n; i++) {
        st = new StringTokenizer(f.readLine());
        c[i][0] = Double.parseDouble(st.nextToken());
        c[i][1] = Double.parseDouble(st.nextToken());
        c[i][2] = Double.parseDouble(st.nextToken());
      }
      double[] t = new double[n];
      for (int i = 0; i < n; i++) {
        t[i] = (double)T/n;
      }
      while (true) {
        int minIdx = -1;
        int maxIdx = -1;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;        
        for (int i = 0; i < n; i++) {
          double deriv = 2*c[i][0]*t[i] + c[i][1];
          if (deriv < min && t[i] >= ERR) {
            min = deriv;
            minIdx = i;
          }
          if (deriv > max && T - t[i] >= ERR) {
            max = deriv;
            maxIdx = i;
          }
        }
        if (max - min < ERR) {
          break;
        }
        else if (minIdx == -1 || maxIdx == -1) {
          break;
        }
        double totalTime = t[minIdx] + t[maxIdx];
        double newT1 = (2*c[minIdx][0]*totalTime + c[minIdx][1] - c[maxIdx][1])/(2*c[minIdx][0] + 2*c[maxIdx][0]);
        newT1 = Math.max(Math.min(totalTime, newT1), 0);
        double newT2 = totalTime - newT1;
        t[maxIdx] = newT1;
        t[minIdx] = newT2;
      }
      double avg = 0;
      for (int i = 0; i < n; i++) {
        // System.out.println(Arrays.toString(c[i]));
        // System.out.println(t[i]);
        avg += c[i][0]*t[i]*t[i] + c[i][1]*t[i] + c[i][2];
        // System.out.println(avg);
      }
      System.out.println(avg/n);
    }
  }
}