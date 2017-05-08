/*
ID: paulcru1
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;

class barn1 {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int m = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int[] stalls = new int[c];
    for(int i = 0;i < c;i++){
      stalls[i] = Integer.parseInt(f.readLine());
    }
    Arrays.sort(stalls);
    int numStalls = stalls[c-1] - stalls[0] + 1;
    List<Integer> spaces = new ArrayList<Integer>();
    for(int i = 1;i < c;i++){
      spaces.add(stalls[i]-stalls[i-1]-1);
    }
    Collections.sort(spaces);
    int totalRemoved = 0;
    for(int i = 0;i < m-1 && i < spaces.size();i++){
      totalRemoved += spaces.get(spaces.size()-i-1);
    }
    out.println(numStalls-totalRemoved);
    out.close();
    System.exit(0);
  }
}