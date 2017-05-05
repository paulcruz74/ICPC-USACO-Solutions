/*
ID: paulcru1
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;

class beads {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("beads.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    int length = Integer.parseInt(f.readLine());
    String necklace = f.readLine();
    int best = -1;
    for(int s = 0;s<length;s++){
      int l = s;
      int r = right(l,length);
      char leftChar = necklace.charAt(l);
      char rightChar = necklace.charAt(r);
      int total = 2;
      while(match(leftChar, necklace.charAt(left(l,length))) && total < length){
        total++;
        leftChar = replace(leftChar, necklace.charAt(left(l,length)));
        l = left(l,length);
      }
      while(match(rightChar, necklace.charAt(right(r,length))) && total < length){
        total++;
        rightChar = replace(rightChar, necklace.charAt(right(r,length)));
        r = right(r,length);
      }
      best = total > best ? total : best;
    }
    out.println(best);
    out.close();
    System.exit(0);
  }
  public static char replace(char a, char b){
    return (a == 'r' || a == 'b') ? a : b;
  }
  public static boolean match(char a, char b){
    return (a == 'w' || b == 'w' || a == b);
  }
  public static int left(int i, int length){
    return (i-1+length)%length;
  }
  public static int right(int i, int length){
    return (i+1)%length;
  }
}