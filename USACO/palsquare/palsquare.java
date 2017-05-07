/*
ID: paulcru1
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.util.*;

class palsquare {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
    int b = Integer.parseInt(f.readLine());
    for(int i = 1;i <= 300;i++){
      String num = Integer.toString(i,b).toUpperCase();
      String format = Integer.toString(i*i, b).toUpperCase();
      if(isPalindrome(format))
        out.println(num + " " +format);
    }
    out.close();
    System.exit(0);
  }
  public static boolean isPalindrome(String s){
    for(int i = 0;i <= s.length()/2;i++){
      if(s.charAt(i) != s.charAt(s.length()-1-i))
        return false;
    }
    return true;
  }
}