import java.io.*;
import java.util.*;

public class E {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    HashMap<Integer, Integer> map = new HashMap<>();
    int n = in.nextInt();
    for(int i = 1;i <= n;i++) {
      map.put(i, in.nextInt());
    }
    int maxIndex = 1;
    for(int i = 1;i <= n;i++) {
      if(map.get(i) > map.get(maxIndex)) {
        maxIndex = i;
      }
    }
    String s = maxIndex + " ";
    int sum = 0;
    for(int i = 1;i <= n;i++) {
      sum += map.get(i); 
      if (i != maxIndex) {
        s += i + " ";
      } 
    }
    if (map.get(maxIndex)*2 <= sum) {
      System.out.println(s.trim());  
    }
    else{
      System.out.println("impossible");
    }
    
  }
}