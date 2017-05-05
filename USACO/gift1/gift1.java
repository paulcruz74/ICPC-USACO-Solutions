/*
ID: paulcru1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    int k = Integer.parseInt(f.readLine());
    Map<String,Integer> delta = new HashMap<String,Integer>();
    List<String> ordered = new ArrayList<>();
    for(int i=0;i<k;i++){
      String name = f.readLine();
      delta.put(name, 0);
      ordered.add(name);
    }
    for(int i=0;i<k;i++){
      String giver = f.readLine();
      StringTokenizer st = new StringTokenizer(f.readLine());
      int amount = Integer.parseInt(st.nextToken());
      int numPeople = Integer.parseInt(st.nextToken());
      int extra = numPeople > 0 ? amount%numPeople : 0;
      delta.put(giver, delta.get(giver)-amount + extra);
      for(int j=0;j<numPeople;j++){
        String gifted = f.readLine();
        delta.put(gifted, delta.get(gifted)+amount/numPeople);
      }
    }
    for(String name : ordered){
      out.printf("%s %d\n", name, delta.get(name));
    }
    out.close();
    System.exit(0);
  }
}