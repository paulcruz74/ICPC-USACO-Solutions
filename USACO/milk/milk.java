/*
ID: paulcru1
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

class milk {
  static class MilkPair implements Comparable<MilkPair>{
    int p;
    int q;
    public MilkPair(int price, int amount){
      this.p = price;
      this.q = amount;
    }
    public int compareTo(MilkPair m){
      if(this.p < m.p)
        return -1;
      else if (this.p==m.p)
        return 0;
      else 
        return 1;
    }
    public int getPrice(){
      return p;
    }
    public int getQuantity(){
      return q;
    }
  }
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("milk.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    List<MilkPair> list = new ArrayList<MilkPair>();
    for(int i = 0;i < m;i++){
      st = new StringTokenizer(f.readLine());
      MilkPair mp = new MilkPair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      list.add(mp);
    }
    Collections.sort(list);
    int total = 0;
    int cost = 0;
    while(total < n){
      MilkPair mp = list.remove(0);
      if(mp.getQuantity() + total >= n){
        cost += (n - total) * mp.getPrice();
        break;
      }
      else{
        cost += mp.getPrice()*mp.getQuantity();
        total += mp.getQuantity();
      }
    }
    out.println(cost);
    out.close();
    System.exit(0);
  }
}