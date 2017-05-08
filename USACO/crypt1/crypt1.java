/*
ID: paulcru1
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

class crypt1 {
  static int numSolutions;
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
    int n = Integer.parseInt(f.readLine());
    StringTokenizer st = new StringTokenizer(f.readLine());
    HashSet<Integer> digits = new HashSet<Integer>();
    for(int i = 0;i < n;i++){
      digits.add(Integer.parseInt(st.nextToken()));
    }
    numSolutions = 0;
    search("", digits);
    out.println(numSolutions);
    out.close();
    System.exit(0);
  }
  public static void search(String accum, HashSet<Integer> digits){
    if(accum.length() == 5){
      int abc = Integer.parseInt(accum.substring(0,3));
      int de  = Integer.parseInt(accum.substring(3));
      int p1 = abc*(de%10);
      int p2 = abc*(de/10);
      int prod = abc*de;
      if(isValid(p1,digits,3) && isValid(p2,digits,3) && isValid(prod, digits, 4))
        numSolutions++;
    }
    else{
      for(int k : digits){
        search(accum+k, digits);
      }
    }
  }
  public static boolean isValid(int num, HashSet<Integer> digits, int numDigits){
    String s = ""+num;
    if(s.length() != numDigits)
      return false;
    int k = num;
    for(int i = 0;i < numDigits;i++){
      int digit = k % 10;
      if(!digits.contains(digit))
        return false;
      k /= 10;
    }
    return true;
  }
}