import java.io.*;
import java.util.*;

public class F {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    Set<Integer> uws = new TreeSet<>();
    uws.add(0);
    int n = in.nextInt();
    int[][] w = new int[2][n];
    for(int i = 0;i < 2;i++) {
      for(int j = 0;j < n;j++) {
        w[i][j] = in.nextInt();
        uws.add(w[i][j]);
      }
    }
    List<Integer> uw = new ArrayList<>();
    for(int i : uws) {
      uw.add(i);
    }
    Collections.sort(uw);
    System.out.println(binarySearch(uw, w));
  }
  public static int binarySearch(List<Integer> uw, int[][] w) {
    return binarySearch(uw, w, 0, uw.size()-1);  
  }
  public static int binarySearch(List<Integer> uw, int[][] w, int left, int right) {
    int mid = (left + right) / 2;
    // System.out.println(uw);
    // System.out.println(left + " " + right);
    if (works(uw.get(mid), w) && left == right) {
      return uw.get(mid);
    }
    else if (works(uw.get(mid), w)) {
      return binarySearch(uw, w, left, mid);
    }
    else {
      return binarySearch(uw, w, mid+1, right);
    } 

  }
  public static boolean works(int co, int[][] w) {
    List<Integer> row1 = new ArrayList<>();
    List<Integer> row2 = new ArrayList<>();
    for(int i : w[0]) {
      if(i > co) {
        row1.add(i);
      }
    }
    for(int i : w[1]) {
      if(i > co) {
        row2.add(i);
      }
    }
    if (row1.size() % 2 == 1 || row2.size() % 2 == 1) {
      return false;
    }
    for(int i = 0;i < row1.size()/2;i++) {
      if(row1.get(2*i) != row1.get(2*i+1)) {
        return false;
      }
    }
    for(int i = 0;i < row2.size()/2;i++) {
      if(row2.get(2*i) != row2.get(2*i+1)) {
        return false;
      }
    }
    return true;
  }
}