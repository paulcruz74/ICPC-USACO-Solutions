import java.io.*;
import java.util.*;

public class Main {
  public static final int INF = Integer.MAX_VALUE/8;
  public static void main(String[] args) throws IOException{
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int caseNum = 1;
    while (true) {
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      if (n == 0 && m == 0 && c == 0) {
        break;
      }
      Set<Integer> cli = new HashSet<>();
      st = new StringTokenizer(f.readLine());
      
      for (int i = 0; i < c; i++) {
        cli.add(Integer.parseInt(st.nextToken()));
      }

      int[][] dist = new int[n][n];
      for (int i = 0;i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (i == j) {
            dist[i][j] = 0;
          }
          else {
            dist[i][j] = INF;
          }
        }
      }
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(f.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        dist[u][v] = d;
      }
      Map<Integer, Map<Integer, Integer>> distMap = new HashMap<>();
      for (int client : cli) {
        distMap.put(client, new HashMap<>());
      }
      distMap.put(0, new HashMap<>());
      for (int client : cli) {
        int[] res = dijkstra2(client, dist);
        for (int node = 0; node < res.length; node++) {
          distMap.get(client).put(node, res[node]);
        }
      }
      int[] temp = dijkstra2(0, dist);
      for (int node = 0; node < temp.length; node++) {
        distMap.get(0).put(node, temp[node]);
      }
      // System.out.println(distMap);


      System.out.printf("Case %d: ", caseNum++);
      System.out.println(search(distMap, cli, 0, new ArrayList<>(), new ArrayList<>()));
      String s = f.readLine();
      while (s.trim().equals("")) {
        s = f.readLine();
      }
      st = new StringTokenizer(s);
    }
  }

  public static int search(Map<Integer, Map<Integer, Integer>> dist, 
                            Set<Integer> cli, int numCars,
                            List<Integer> truckTimes, 
                            List<Integer> truckLocations) {
    if (cli.isEmpty()) {
      return numCars;
    }
    else {
      int minDist = Integer.MAX_VALUE;
      int minCli = 0;
      for (int node : cli) {
        if (dist.get(0).get(node) < minDist) {
          minDist = dist.get(0).get(node);
          minCli = node;
        }
      }
      int bestCars = Integer.MAX_VALUE;
      cli.remove(minCli);
      for (int i = 0; i < truckTimes.size(); i++) {
        int time = truckTimes.get(i);
        int loc = truckLocations.get(i);
        // System.out.println(minCli);
        if (time + dist.get(loc).get(minCli) <= dist.get(0).get(minCli)) {
          truckTimes.set(i, time + dist.get(i).get(minCli));
          truckLocations.set(i, minCli);
          int test = search(dist, cli, numCars, truckTimes, truckLocations);
          bestCars = Math.min(bestCars, test);
          truckLocations.set(i, loc);
          truckTimes.set(i, time);
        }
      }
      truckTimes.add(dist.get(0).get(minCli));
      truckLocations.add(minCli);
      int last = search(dist, cli, numCars+1, truckTimes, truckLocations);
      bestCars = Math.min(bestCars, last);
      return bestCars;
    }
  }
  public static int[] dijkstra2(int r,int[][] in){
    final int[] out=new int[in.length],prev=new int[in.length];
    TreeSet<Integer> pq=new TreeSet<Integer>(new Comparator<Integer>(){
      public int compare(Integer i0, Integer i1) {
        if(out[i0]!=out[i1])return Double.compare(out[i0],out[i1]);
        return i0-i1;
      }
    });
    Arrays.fill(out, Integer.MAX_VALUE/2);
    out[r]=0;
    prev[r]=-1;
    pq.add(r);
    while(!pq.isEmpty()){
      int t=pq.first();
      pq.remove(pq.first());
      for(int i=0;i<in.length;i++)if(out[t]+in[t][i]<out[i]){
        pq.remove(i);
        out[i]=out[t]+in[t][i];
        prev[i]=t;
        pq.add(i);
      }
    }
    return out;//note you can also return previous here if you like....or some combination of the two
  }
}