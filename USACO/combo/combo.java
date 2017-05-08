/*
ID: paulcru1
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

class combo {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("combo.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
    int n = Integer.parseInt(f.readLine());
    int[] farmer = new int[3];
    int[] master = new int[3];
    StringTokenizer st = new StringTokenizer(f.readLine());
    for(int i = 0;i < 3;i++){
      farmer[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(f.readLine());
    for(int i = 0;i < 3;i++){
      master[i] = Integer.parseInt(st.nextToken());
    }
    boolean[][][] working = new boolean[n+1][n+1][n+1];
    fill(farmer, working, n);
    fill(master, working, n);
    int count = 0;
    for(int i = 1;i <= n;i++){
      for(int j = 1;j <= n;j++){
        for(int k = 1;k <=n;k++){
          if(working[i][j][k]){
            count++;
          }
        }
      }
    }
    out.println(count);
    out.close();
    System.exit(0);
  }
  public static void fill(int[] combo, boolean[][][] working, int max){
    for(int i : createSet(combo[0], max)){
      for(int j : createSet(combo[1], max)){
        for(int k : createSet(combo[2], max)){
          working[i][j][k] = true;
        }
      }
    }
  }
  public static HashSet<Integer> createSet(int n, int max){
    HashSet<Integer> vals = new HashSet<Integer>();
    for(int i = 0;i <= 2;i++){
      int nmi = (n-i == 0) ? max : (n-i+max)%max;
      int npi = (n+i == max) ? max : (n+i)%max;
      vals.add(nmi);
      vals.add(npi);
    }
    return vals;
  }
}



