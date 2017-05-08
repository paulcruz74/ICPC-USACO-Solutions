/*
ID: paulcru1
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.*;

class wormhole {
  public static int[][] portals;
  public static boolean[] visited;
  public static int count;
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
    int n = Integer.parseInt(f.readLine());
    portals = new int[n][2];
    count = 0;
    for(int i = 0;i < n;i++){
      StringTokenizer st = new StringTokenizer(f.readLine());
      portals[i][0] = Integer.parseInt(st.nextToken());
      portals[i][1] = Integer.parseInt(st.nextToken());
    }
    search(new ArrayList<Integer>(), n);
    out.println(count);
    out.close();
    System.exit(0);
  }
  public static void search(List<Integer> used, int max){
    if(used.size() == max){
      Map<Integer,Integer> pp = makePairs(used, max);
      if(doesLoop(pp, max))
        count++;
    }
    else{
      for(int i = 0;i < max;i++){
        if(!used.contains(i)){
          used.add(i);
          search(used, max);
          used.remove(new Integer(i));
        }
      }
    }
  }
  public static boolean doesLoop(Map<Integer,Integer> map, int max){
    for(int i = 0;i < max;i++){
      visited = new boolean[max];
      int curr = i;
      while(true){
        if(visited[i])
          return true;
        visited[i] = true;
        int next = getNextRight(i);
        if(next == -1){        
          break;
        }
        if(visited[next])
          return true;
        visited[next] = true;
        curr = map.get(next);
      }
    }
    return false;
  }
  public static int getNextRight(int portalNo){
    int next = -1;
    int mostLeft = Integer.MAX_VALUE;
    for(int i = 0;i < portals.length;i++){
      if(portalNo == i)
        continue;
      if(portals[i][1] == portals[portalNo][1] && portals[i][0] < mostLeft){
        mostLeft = portals[i][0];
        next = i;
      }
    }
    return next;
  }
  public static Map<Integer,Integer> makePairs(List<Integer> used, int max){
    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    for(int i = 0;i < max/2;i++){
      int a = used.get(2*i);
      int b = used.get(2*i+1);
      map.put(a,b);
      map.put(b,a);
    }
    return map;
  }
}