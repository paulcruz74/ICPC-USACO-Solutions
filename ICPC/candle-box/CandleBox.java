import java.io.*;
import java.util.*;

public class CandleBox {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int d = in.nextInt();
    int r = in.nextInt();
    int t = in.nextInt();
    int totalCandles = r + t;
    for(int x = 4;x <= 100;x++) {
      int y = x - d;
      int rita = x*(x+1)/2 - 6;
      int theo = y >= 3 ? y*(y+1)/2 - 3 : 0;
      if(rita + theo == r + t) {
        System.out.println(r - rita);
        break;
      }
    }
  }
}