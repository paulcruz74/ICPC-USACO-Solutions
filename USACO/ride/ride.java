/*
ID: paulcru1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    String s1 = f.readLine();
    String s2 = f.readLine();
    Map<Character,Integer> pts = getPointMappings();
    if(numPoints(s1, pts) == numPoints(s2, pts)){
      out.println("GO");
    }
    else{
      out.println("STAY");
    }
    out.close();
    System.exit(0);
  }
  public static int numPoints(String s, Map<Character,Integer> points){
    int product = 1;
    for(int i=0;i<s.length();i++){
      product *= points.get(s.charAt(i));
      product %= 47;
    }
    return product;
  }
  public static Map<Character,Integer> getPointMappings(){
    Map<Character,Integer> points = new HashMap<Character,Integer>();
    for(int i=1;i<=26;i++){
      points.put((char)(((int)'A')-1+i), i);
    }
    return points;
  }
}