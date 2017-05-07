/*
ID: paulcru1
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;

class dualpal {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int n = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());
    TreeSet<Integer> nums = new TreeSet<Integer>();
    int curr = s+1;
    while(nums.size() < n){
      if(isValid(curr))
        nums.add(curr);
      curr++;
    }
    for(int k : nums){
      out.println(k);
    }
    out.close();
    System.exit(0);
  }
  public static boolean isValid(int n){
    int count = 0;
    for(int base = 2;base <= 10;base++){
      String format = Integer.toString(n, base);
      if(isPalindrome(format))
        count++;
    }
    return count >= 2;
  }
  public static boolean isPalindrome(String s){
    for(int i = 0;i <= s.length()/2;i++){
      if(s.charAt(i) != s.charAt(s.length()-i-1))
        return false;
    }
    return true;
  }
}