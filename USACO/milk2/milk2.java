/*
ID: paulcru1
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class milk2 {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
    int n = Integer.parseInt(f.readLine());
    HashMap<Integer, Integer> starts = new HashMap<>();
    HashMap<Integer, Integer> ends = new HashMap<>();
    ArrayList<Integer> times = new ArrayList<>();
    TreeSet<Integer> removeDups = new TreeSet<Integer>();
    for(int i = 0;i < n;i++){
      StringTokenizer st = new StringTokenizer(f.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      removeDups.add(s);
      removeDups.add(e);
      starts.put(s, starts.getOrDefault(s,0)+1);
      ends.put(e, ends.getOrDefault(e,0)+1);
    }
    for(int i : removeDups){
      times.add(i);
    }
    Collections.sort(times);
    int scan = 0;
    int accumMilk = 0;
    int bestMilk = 0;
    int bestIdle = 0;
    int index = 0;
    for(int time : times){
      if(starts.keySet().contains(time))
        scan+=starts.get(time);
      if(ends.keySet().contains(time))
        scan-=ends.get(time);
      if(scan > 0){
        accumMilk += times.get(index+1)-time;
      }
      else if(scan == 0){
        if(accumMilk > bestMilk)
          bestMilk = accumMilk;
        accumMilk = 0;
        if((index < (times.size()-1)) && times.get(index+1)-times.get(index) > bestIdle){
          bestIdle = times.get(index+1)-times.get(index);
        }
      }
      index++;
    }
    out.println(bestMilk + " " +bestIdle);
    out.close();
    System.exit(0);
  }
}