import java.awt.Point;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 *
 * Bellman Ford
 * Dijkstra w/ adj. list
 * dijkstra w/ adj. mat
 * floyd warshall
 * floyd warshall w/ path recovery
 * convex hull
 * maxflow w/ adj mat
 * maxflow w/ adj list
 * mst w/ tuples
 * mst w/ adj list
 * mst w/ adj mat
 * SCC
 * BCC
 * bipartite match
 * hungarian
 * next permutation
 * num permutations
 * next combination
 * nCr
 * gaussian elimination/linear equation solve
 * gcd
 * extended euclid/bezouts identity
 * polygon area
 * polygon centroid
 * sieve of eratosthenes
 * string search
 * topo sort
 * fenwick tree
 * matrix determinant
 * roman numerals
 * ***************
 * useful math things
 * printf examples
 * java classes/methods
 */
public class notebook{
/**
 * Bellman Ford: finds shortest path in graphs with negative edges and possible minimum weight cycles
 * @param r start node
 * @param in adjacency list
 * @return distance to each node from start node
 */
public int[] bellmanFord(int r, ArrayList<HashMap<Integer,Integer>> in){
	int[] out=new int[in.size()],prev=new int[in.size()];
	Arrays.fill(out, Integer.MAX_VALUE);
	out[r]=0;
	for(int i:in.get(r).keySet()){
		out[i]=in.get(r).get(i);
		prev[i]=r;
	}
	for(int i=0;i<in.size();i++)for(int j=0;j<in.size();j++)for(int k:in.get(j).keySet())if(out[j]+in.get(j).get(k)<out[k]){
		out[k]=out[j]+in.get(j).get(k);
		prev[k]=j;
	}
/*		this loop is only here to detect the negative weight cycles...if you don't have any you can use skip this or just use dijkstra
		for(int j=0;j<in.size();j++)for(int k:in.get(j).keySet())if(out[j]+in.get(j).get(k)<out[k]){
			//TODO Whatever you plan to do with a negative weight cycle
		}*/
	return out;//or you can return prev if you so choose
}
/**
 * Dijkstra:finds the shortest path in weighted graph
 * @param r start node
 * @param in adjacency list
 * @return distance to each node from start node
 */
public int[] dijkstra1(int r,ArrayList<HashMap<Integer,Integer>> in){
	final int[] out=new int[in.size()],prev=new int[in.size()];
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
		for(int i:in.get(t).keySet())if(out[t]+in.get(t).get(i)<out[i]){
			pq.remove(i);
			out[i]=out[t]+in.get(t).get(i);
			prev[i]=t;
			pq.add(i);
		}
	}
	return out;//note you can also return previous here if you like....or some combination of the two
}
/**
 * shortest path from r to all points
 * @param r the start node
 * @param in the adjacency matrix
 * @return the length of the path from r to each node
 */
public int[] dijkstra2(int r,int[][] in){
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
/**
 * FLoyd Warshall for shortest distance from every node to every other
 * @param in adjacency matrix
 */
public void floydWarshall(int[][] in){
	for(int k=0;k<in.length;k++)for(int i=0;i<in.length;i++)for(int j=0;j<in.length;j++)in[i][j]=Math.min(in[i][j], in[i][k]+in[k][j]);
}
/**
 * Floyd Warshall with path recovery
 * @param in adjacency matrix
 * @return the next node on the path from i to j...just iterate over it until [i][j]=j
 */
public int[][] floydWarshallwPath(int[][] in){
	int[][] next=new int[in.length][in.length];
	for(int i=0;i<in.length;i++)for(int j=0;j<in.length;j++)if(in[i][j]<Integer.MAX_VALUE)next[i][j]=j;
	for(int k=0;k<in.length;k++)for(int i=0;i<in.length;i++)for(int j=0;j<in.length;j++)if(in[i][k]+in[k][j]<in[i][j]){
		in[i][j]=in[i][k]+in[k][j];
		next[i][j]=next[i][k];		
	}
	return next;
}
/**
 * Get the convex of a graph
 * default is exclusive, see comments for inclusivity
 * @param in input
 * @return output
 */
public ArrayList<Point> convexHull(ArrayList<Point> in){//default is exclusive, see 2 comments for inclusivity
	LinkedList<Point> out=new LinkedList<Point>();
	Point temp=new Point(0,Integer.MAX_VALUE);
	for(Point p:in)if(p.y<temp.y||(p.y==temp.y&&p.x<temp.x))temp=p;
	final Point min=new Point(temp);
	TreeSet<Point> pq=new TreeSet<Point>(new Comparator<Point>(){
		public int compare(Point p0, Point p1) {
			double temp=Math.atan2(p0.y-min.y,p0.x-min.x)-Math.atan2(p1.y-min.y,p1.x-min.x);
			if(temp<0||(temp==0&&min.distance(p0)<min.distance(p1)))return -1;
			return 1;
		}
	});
	out.add(min);
	for(Point p:in)if(!p.equals(min))pq.add(p);
	while(!pq.isEmpty()){
		if(out.size()<2)out.add(pq.pollFirst());
		if(pq.isEmpty())break;//this line only necessary for degenerate case
		Point m=out.getLast(),n=out.get(out.size()-2),o=pq.first();
		if((m.x-n.x)*(o.y-m.y)-(o.x-m.x)*(m.y-n.y)>0)out.add(pq.pollFirst());//take left hand turn only, change to >= for inclusivity
		else out.remove(out.size()-1);
	}
/*		ArrayList<Point> tail=new ArrayList<Point>();//this block is only needed for inclusivity
	for(Point p:in)if(!p.equals(min)&&(p.x-min.x)*(out.getLast().y-p.y)-(out.getLast().x-p.x)*(p.y-min.y)==0)tail.add(p);
	Collections.sort(tail, new Comparator<Point>(){
		public int compare(Point p0, Point p1) {
			return Double.compare(min.distance(p1),min.distance(p0));
		}
	});
	out.removeLast();
	out.addAll(tail);*/
	return new ArrayList<Point>(out);
}
/**
 * Maxflow: calculates the maximum flow through the graph
 * @param u the source
 * @param v the sink
 * @param in the adjacency matrix
 * @return the maximum flow
 */
public int mf1(int u,int v,int[][] in){
	int[][] flow=new int[in.length][in.length];
	int tot=0;
	while(true){
		Queue<Integer>q=new LinkedList<Integer>();
		int[] prev=new int[in.length],fill=new int[in.length];
		q.offer(u);
		Arrays.fill(prev, -1);
		prev[u]=-2;
		fill[u]=Integer.MAX_VALUE;
		while(prev[v]==-1){
			if(q.isEmpty())return tot;
			int p=q.poll();
			for(int i=0;i<in.length;i++)if(p!=i&&prev[i]==-1&&(flow[p][i]<in[p][i]||flow[i][p]>0)){
				prev[i]=p;
				fill[i]=Math.min(fill[p], in[p][i]-flow[p][i]+flow[i][p]);
				q.offer(i);
			}
		}
		tot+=fill[v];
		int t=v;
		while(t!=u){
			int s=prev[t];
			flow[t][s]-=Math.max(0, flow[s][t]+fill[v]-in[s][t]);
			flow[s][t]=Math.min(flow[s][t]+fill[v], in[s][t]);
			t=s;
		}
	}
}
/**
 * calculates the maxflow from source to sink
 * @param u the source
 * @param v the sink
 * @param in adjacency list of the 
 * @return the max flow through the graph
 */
public int mf2(int u, int v, ArrayList<HashMap<Integer, Integer>> in) {
	ArrayList<HashMap<Integer,Integer>> flow=new ArrayList<HashMap<Integer,Integer>>();
	for(int i=0;i<in.size();i++){
		flow.add(new HashMap<Integer,Integer>());
		for(int j:in.get(i).keySet())flow.get(i).put(j, 0);
	}
	int tot=0;
	while(true){
		Queue<Integer> q=new LinkedList<Integer>();
		int[] prev=new int[in.size()],fill=new int[in.size()];
		q.offer(u);
		Arrays.fill(prev, -1);
		prev[u]=-2;
		fill[u]=Integer.MAX_VALUE;
		while(prev[v]==-1){
			if(q.isEmpty())return tot;
			int p=q.poll();
			for(int i=0;i<in.size();i++)if(p!=i&&prev[i]==-1&&((in.get(p).containsKey(i)&&flow.get(p).get(i)<in.get(p).get(i))||(in.get(i).containsKey(p)&&flow.get(i).get(p)>0))){
				prev[i]=p;
				fill[i]=0;
				if(in.get(p).containsKey(i))fill[i]+=in.get(p).get(i)-flow.get(p).get(i);
				if(in.get(i).containsKey(p))fill[i]+=flow.get(i).get(p);
				fill[i]=Math.min(fill[p],fill[i]);//???
				q.offer(i);
			}
		}
		tot+=fill[v];
		int t=v;
		while(t!=u){
			int s=prev[t];
			int excess=fill[v];
			if(in.get(s).containsKey(t)){
				excess=Math.max(flow.get(s).get(t)+fill[v]-in.get(s).get(t), 0);
				flow.get(s).put(t,flow.get(s).get(t)+fill[v]-excess);
			}
			if(excess>0)flow.get(t).put(s, flow.get(t).get(s)-excess);
			t=s;
		}
	}
}
/**
 * Minimum Spanning Tree for tuples on the xy plane
 * @param in the tuples
 * @return points representing the edges in the MST (indexed to the input list)
 */
public  ArrayList<Point> MinimumSpanningTree1(ArrayList<Point> in){
	ArrayList<Point> out=new ArrayList<Point>();
	HashSet<Integer> visited=new HashSet<Integer>();
	final ArrayList<Point> al=new ArrayList<Point>(in);
	PriorityQueue<Point> pq=new PriorityQueue<Point>(al.size(), new Comparator<Point>(){
		public int compare(Point o1, Point o2) {
			if(al.get(o1.x).distance(al.get(o1.y))-al.get(o2.x).distance(al.get(o2.y))>0)return 1;
			return -1;
		}
	});
	visited.add(0);
	for(int i=1;i<in.size();i++)pq.offer(new Point(0,i));
	while(visited.size()<al.size()){
		Point t=pq.poll();
		if(visited.contains(t.y))continue;
		visited.add(t.y);
		out.add(t);
		for(int i=1;i<in.size();i++)if(!visited.contains(i))pq.offer(new Point(t.y,i));
	}
	return out;
}
/**
 * MST for arbitrary graph
 * @param in adjacency list
 * @return same as the other MST
 */
public ArrayList<Point> MinimumSpanningTree2(ArrayList<HashMap<Integer,Double>> in){
	ArrayList<Point> out=new ArrayList<Point>();
	HashSet<Integer> visited=new HashSet<Integer>();
	final ArrayList<HashMap<Integer,Double>> al=new ArrayList<HashMap<Integer,Double>>(in);
	PriorityQueue<Point> pq=new PriorityQueue<Point>(al.size(), new Comparator<Point>(){
		public int compare(Point o1, Point o2) {
			if(al.get(o1.x).get(o1.y)-al.get(o2.x).get(o2.y)>0)return 1;
			return -1;
		}
	});
	visited.add(0);
	for(int i:in.get(0).keySet())pq.offer(new Point(0,i));
	while(visited.size()<al.size()){
		Point t=pq.poll();
		if(visited.contains(t.y))continue;
		visited.add(t.y);
		out.add(t);
		for(int i:in.get(t.y).keySet())	if(!visited.contains(i))pq.offer((new Point(t.y,i)));
	}
	return out;
}
/**
 * MST for an arbitrary graph specified by an adjacency matrix
 * @param in adjacency matrix
 * @return the edges of the minimum spanning tree
 */
public ArrayList<Point> minimumSpanningTree3(double[][] in){
	ArrayList<Point> out=new ArrayList<Point>();
	HashSet<Integer> visited=new HashSet<Integer>();
	final double[][] al=new double[in.length][in.length];
	for(int i=0;i<in.length;i++)for(int j=0;j<in.length;j++)al[i][j]=in[i][j];
	PriorityQueue<Point> pq=new PriorityQueue<Point>(al.length, new Comparator<Point>(){
		public int compare(Point o1, Point o2) {
			if(al[o1.x][o1.y]-al[o2.x][o2.y]>0)return 1;
			return -1;
		}
	});
	visited.add(0);
	for(int i=0;i<in.length;i++)if(in[0][i]<Integer.MAX_VALUE/2)pq.offer(new Point(0,i));
	while(visited.size()<in.length){
		Point t=pq.poll();
		if(visited.contains(t.y))continue;
		visited.add(t.y);
		out.add(t);
		for(int i=0;i<in.length;i++)if(in[t.y][i]<Integer.MAX_VALUE/2&&!visited.contains(i))pq.offer((new Point(t.y,i)));
	}
	return out;
}

/**
 * Strongly Connected Components
 * @param in adjacency list
 * @return list of nodes by strongly connected component
 */
public int[] stronglyConnectedComponents(ArrayList<ArrayList<Integer>> in){
	int[][] t=new int[5][in.size()];//order,link,ins,out,various indices
	Stack<Integer> s=new Stack<Integer>();
	for(int i=0;i<in.size();i++)if(t[0][i]==0)dfs(i,in,s,t);
	return t[3];
}
private void dfs(int i,ArrayList<ArrayList<Integer>> in,Stack<Integer> s ,int[][] t) {
	t[2][i]=t[1][i]=t[0][i]=++t[4][0];
	s.push(i);
	for(int j:in.get(i)){
		if(t[0][j]==0)dfs(j,in,s,t);
		if(t[2][j]>0)t[1][i]=Math.min(t[1][i],t[1][j]);;			
	}
	if(t[0][i]==t[1][i]){
		while(true){
			t[2][s.peek()]=0;
			t[3][s.peek()]=t[4][1];
			if(s.pop()==i)break;
		}
		t[4][1]++;
	}
}
/**
 * BiConnected Components/Doubly Connected Components
 * @param in the adjacency list
 * @return list of edges by biconnected component
 */
public ArrayList<ArrayList<Point>> biConnectedComponents(ArrayList<ArrayList<Integer>> in){
	int[][] t=new int[3][in.size()];//order,link,various indices
	Stack<Point> s=new Stack<Point>();
	ArrayList<ArrayList<Point>> out=new ArrayList<ArrayList<Point>>();
	HashSet<Point>u=new HashSet<Point>();
	for(int i=0;i<in.size();i++)if(t[0][i]==0)dfs(i,in,out,s,t,u);//this only necessary for unconnected components....
	if(!s.isEmpty())out.add(new ArrayList<Point>(s));
	return out;
}
private void dfs(int i,ArrayList<ArrayList<Integer>> in,ArrayList<ArrayList<Point>> out,Stack<Point> s ,int[][] t, HashSet<Point> u) {
	t[1][i]=t[0][i]=++t[2][0];
	int min=t[1][i];//need this because we can't update the min until after we've finished all recursion
	for(int j:in.get(i)){
		if(u.contains(new Point(j,i)))continue;//if already traversed this edge in the other direction
		u.add(new Point(i,j));
		s.push(new Point(i,j));
		if(t[0][j]==0)dfs(j,in,out,s,t,u);//don't  recurse on previously visited nodes
		min=Math.min(min,t[1][j]);
		if(t[0][i]<=t[1][j]){
			ArrayList<Point> al=new ArrayList<Point>();
			while(al.size()==0||al.get(al.size()-1).x!=i)al.add(s.pop());//pop through the edge that had i as the outgoing node
			out.add(al);
		}
	}
	t[1][i]=min;
}
/**
 * calculate the bipartite match of a graph, augmenting path algorithm
 * @param al the adjacency list
 * @param l the number of nodes in the first side of the graph
 * @return the size of the bipartite matching
 */
public int maxMatch(ArrayList<ArrayList<Integer>> al,int l){
	int[] match=new int[al.size()],prev=new int[al.size()];
	Arrays.fill(match, -1);
	for(int i=0;i<l;i++){
		Queue<Integer> q=new LinkedList<Integer>();
		Arrays.fill(prev, -1);
		q.offer(i);
		OUT:
		while(!q.isEmpty()){
			int p=q.poll();
			for(int j:al.get(p))if(prev[j]==-1){//must be unvisited
				prev[j]=p;
				if(match[j]==-1){//if unmatched break
					prev[i]=j;
					break OUT;
				}
				for(int k:al.get(j))if(match[k]==j&&prev[k]==-1){
					prev[k]=j;
					q.offer(k);//any used and unvisited edge goes on the queue
				}
			}
		}
		int s=prev[i];
		prev[i]=-1;
		while(s!=-1){
			match[s]=prev[s];
			match[prev[s]]=s;
			s=prev[prev[s]];
		}
	}
	int ans=0;
	for(int i=0;i<l;i++)if(match[i]!=-1)ans++;//can actually extract the matches if need be
	return ans;
}
/**
 * Hungarian Algorithm for calculating the maximum weight matching of a bipartite graph
 * @param cost - cost of matching person i to task j
 * @return [i]= the j matched with i
 */
public int[] maxBipartiteMatching(int[][] cost){
	int n=cost.length;
	int x = -1,y=-1;
	int[][] match=new int[2][n],label=new int[2][n],prev=new int[2][n], slack=new int[2][n];
	for(int i=0;i<n;i++)for(int j=0;j<n;j++)label[0][i]=Math.max(label[0][i], cost[i][j]);
	for(int[] i:match)Arrays.fill(i, -1);
	for(int rnd=0;rnd<n;rnd++){
		HashSet<Integer> s=new HashSet<Integer>(),t=new HashSet<Integer>();
		Queue<Integer> q=new LinkedList<Integer>();
		for(int[] i:prev)Arrays.fill(i, -1);
		for(int i=0;i<n;i++)if(match[0][i]==-1){//find an unmatched x
			q.offer(i);
			x=i;
			s.add(i);
			prev[0][x]=-2;
			break;
		}
		for(int i=0;i<n;i++){
			slack[0][i]=label[0][x]+label[1][i]-cost[x][i];
			slack[1][i]=x;
		}
		OUT:
		while(true){
			while(!q.isEmpty()){
				int cur=q.poll();
				for(int i=0;i<n;i++)if(!t.contains(i)&&cost[cur][i]==label[0][cur]+label[1][i]){
					int m=match[1][i];
					prev[1][i]=cur;
					if(m==-1){
						y=i;
						break OUT;
					}
					t.add(i);
					q.offer(m);
					s.add(m);
					prev[0][m]=i;
					for(int j=0;j<n;j++)if(slack[0][j]> label[0][m]+label[1][j]-cost[m][j]){
						slack[0][j]=label[0][m]+label[1][j]-cost[m][j];
						slack[1][j]=m;
					}
				}
			}
			int min=Integer.MAX_VALUE;
			int mini = 0;
			for(int i=0;i<n;i++)if(!t.contains(i)&&slack[0][i]<min){
				min=slack[0][i];
				mini=i;
			}
			for(int i=0;i<n;i++){
				if(s.contains(i))label[0][i]-=min;
				if(t.contains(i))label[1][i]+=min;
				else slack[0][i]-=min;
			}
			t.remove(mini);
			q.offer(slack[1][mini]);
		}
		while(y!=-2){
			match[1][y]=prev[1][y];
			match[0][match[1][y]]=y;
			y=prev[0][match[1][y]];
		}
	}
	return match[0];
}
/**
 * Next Permutation of the given input array
 * @param in the array
 * @return whether there is a next permutation or not
 */
public boolean nextPermutation(int[] in){
	for(int i=in.length-2;i>=0;i--)if(in[i]<in[i+1]){
		for(int j=in.length-1;j>i;j--)if(in[j]>in[i]){
			int t=in[i];
			in[i]=in[j];
			in[j]=t;
			break;
		}
		for(int j=i+1;j<in.length-(j-i);j++){
			int t=in[j];
			in[j]=in[in.length-(j-i)];
			in[in.length-(j-i)]=t;
		}
		return true;
	}
	return false;
}
/**
 * calculate the number of permutations of a given list
 * @param in the count of each unique element in the list
 * @return number of permutations of the list specified by the input
 */
public long numPermutation(int[] in){
	int total=0;
	for(int i:in)total+=i;
	long[][] dp=new long[total+1][total+1];
	Arrays.fill(dp[0], 1);
	for(int i=1;i<=total;i++)for(int j=0;j<=total;j++)for(int k=0;k<=j;k++)dp[i][j]+=dp[i-1][j-k];
	long ans=1;
	total=0;
	for(int i:in){
		ans*=dp[total][i];
		total+=i;
	}
	return ans;
}
/**
 * Next Combination of the givin input array (left to right), set max to 2 to iterate over all combinations
 * @param in the array
 * @param max the maximum value you are permuting to
 * @return whether there is a next combination or not
 */
public boolean nextCombination(int[] in,int max){
	for(int i=0;i<in.length;i++){
		if(in[i]<max){
			in[i]++;
			return true;
		}
		in[i]=0;
	}
	return false;
}
/**
 * calculates n-choose-r or ans[n][r]
 * @param max maximum value you need for n or r
 * @param mod the mod value, if not needed, use 1
 * @return the values of n choose r for all n,r <max
 */
public long[][]  numCombinations(int max, int mod){
	long[][] ans=new long[max][max];
	for(int i=0;i<max;i++)ans[i][0]=1;
	for(int i=1;i<max;i++)for(int j=1;j<max;j++)ans[i][j]=(ans[i-1][j]+ans[i-1][j-1])%mod;
	return ans;
}
/**
 * solves A*X=b
 * @param A coefficients
 * @param b constants
 * @return values of the variables
 */
public double[] linearEquationSolve( double[][] A, double[] b ){
	double EPS=.000001;//or whatever you need it to be
	int n = A.length;
	double a[][] = new double[n][n+1], temp[], scale;
	for( int i = 0; i < n; i++ ) for( int j = 0; j < n; j++ ) a[i][j] = A[i][j];
	for( int i = 0; i < n; i++ ) a[i][n] = b[i];
	for( int i = 0; i < n; i++ ){
		for( int j = i; j < n; j++ )if( Math.abs(a[j][i])>EPS ){
				temp = a[j];
				a[j] = a[i];
				a[i] = temp;
				break;
		}
		scale = 1/a[i][i];
		for( int j = i; j <= n; j++ ) a[i][j] *= scale;
		for( int j = 0; j < n; j++ )if( i != j && Math.abs(a[j][i])>EPS ){
			scale = -a[j][i];
			for( int k = i; k <= n; k++ ) a[j][k] += scale*a[i][k];
		}
	}
	double[] x = new double[n];
	for( int i = 0; i < n; i++ ) x[i] = a[i][n];
	return x;
}
/**
 * greatest common demonimator
 * @param a
 * @param b
 * @return the gcd
 */
public int greatestCommonDenominator( int a, int b ){
	while( b != 0 ){
		int t = a%b;
		a = b;
		b = t;
	}
	return a;
}
/**
 * solves for (x,y) in Bezout's Identity: ax+by = gcd(a,b)
 * @param a
 * @param b
 * @return int[2] containing x,y
 */
public int[] bezoutIdentitySolve( int a, int b ){
	int x = 0, lx = 1, y = 1, ly = 0;
	while( b != 0 ){
		int t = a%b, q = a/b;
		a = b; b = t;
		t = x; x = lx-q*x; lx = t;
		t = y; y = ly-q*y; ly = t;
	}
	return new int[]{ lx, ly };
}
/**
 * polygon area
 * @param x array of x values
 * @param y array of y values
 * @return the area
 */
public double polygonArea( double[] x, double[] y ){
	double a = 0;
	for( int i = 1; i < x.length; i++ ) a += x[i-1]*y[i] - x[i]*y[i-1];
	a += x[x.length-1]*y[0] - x[0]*y[x.length-1];
	a /= 2;
	return Math.abs(a);
}
/**
 * calculates polygon area AND centroid
 * @param x array of x values
 * @param y array of y values
 * @return {area,x coordinate,y coordinate}
 */
public double[] polygonAreaCentroid( double[] x, double[] y ){
	double[] a = new double[3];
	int n = x.length;
	double cross;
	for( int i = 1; i < x.length; i++ ){
		cross = x[i-1]*y[i] - x[i]*y[i-1];
		a[0] += cross;
		a[1] += cross*(x[i-1]+x[i]); a[2] += cross*(y[i-1]+y[i]);
	}
	cross = x[n-1]*y[0] - x[0]*y[n-1];
	a[0] += cross;
	a[0] /= 2;
	if( a[0] < 0 ) a[0] *= -1;
	a[1] += cross*(x[n]+x[0]); a[2] += cross*(y[n]+y[0]);
	a[1] /= 6*a[0]; a[2] /= 6*a[0];
	return a;
}
/**
 * Sieve of Eratosthenes, returns all primes<=n
 * @param n the maximum prime you want
 * @return int[] of primes
 */
public ArrayList<Integer> findPrimes( int n ){
	boolean[] a = new boolean[n+1];
	ArrayList<Integer> temp=new ArrayList<Integer>();
	int i;
	for( i = 2; i <= n; i++ ) a[i] = true;
	for( i = 2; i < Math.ceil(Math.sqrt(n)); i++ )if( a[i] ){
		temp.add(i);
		for( int j = 2*i; j <= n; j += i )a[j] = false;
	}
	for(;i<=n;i++)if(a[i])temp.add(i);
	return temp;
}
/**
 * String matching: finds first index where token appears in string
 * @param s string to be searched
 * @param t token you're searching for
 * @return index where token is found, -1 if not found
 */
public int stringFind( String s, String t ){
	int f[] = new int[t.length()+1];
	for( int i = 2; i <= t.length(); i++ ){
		int j = f[i-1];
		do{
			if( t.charAt(j) == t.charAt(i-1) ){
				f[i] = j+1;
				break;
			}
			j = f[j];
		}while( j > 0 );
	}
	int i = 0, j = 0;
	while( i+j < s.length() ){
		if( j == t.length() ) return i;
		if( s.charAt(i+j) == t.charAt(j) ) j++;
		else if( j > 0 ){
			i += j - f[j];
			j = f[j];
		}
		else i++;
	}
	return -1;
}
/**
 * binary search over some function in which the point you're looking for is at the only global extreme
 * @param max upper bound on the search domain
 * @return value you're looking for
 */
public int binarySearch(int max){
	int ans=0;
	while(max!=0){
		if(true/*replace with condition you're testing*/)ans+=max;
		max>>=1;
	}
	return ans;
}
/**
 * topological sort a directed graph
 * @param in the adjacency list (adj. matrix version is slower)
 * @return the nodes in topologically sorted order if one exists, or new int[0] othewise
 */
public int[] topologicalSort(ArrayList<ArrayList<Integer>> in){
	int[] rev=new int[in.size()],out=new int[in.size()];
	for(ArrayList<Integer> i:in)for(int j:i)rev[j]++;
	Queue<Integer> q=new LinkedList<Integer>();
	for(int i=0;i<in.size();i++)if(rev[i]==0)q.add(i);
	if(q.isEmpty())return new int[0];
	int count=0;
	while(!q.isEmpty()){
		int t=q.poll();
		for(int i:in.get(t)){
			rev[i]--;
			if(rev[i]==0)q.add(i);
		}
		out[count++]=t;
	}
	if(count<out.length)return new int[0];
	return out;
}
/**
 * fenwick tree rather implemented with a heap rather than strict binary ops
 * @author Kevin
 *
 */
public class FenwickTree {
	double[] heap;
	/**
	 * fenwick tree constructor
	 * @param in the array of values
	 */
	public FenwickTree(double[] in){
		heap=new double[Integer.highestOneBit(in.length)<<1];
		for(int i=0;i<in.length;i++)operate(i,in[i],true);
	}
	/**
	 * query or update the tree
	 * @param index the index to query or update
	 * @param value the value to change it to
	 * @param update whether you want to update the tree or not
	 * @return the value of the query or 0 if an update
	 */
	public double operate(int index, double value,boolean update) {
		int heapIndex=1,root=heap.length>>1,delta=root;
		index++;
		if(index==0||index==heap.length)return 0;
		while(index!=root){
			heapIndex<<=1;
			if(index>root){
				value-=heap[heapIndex>>1];
				heapIndex++;
				root+=delta;
			}
			delta>>=1;
			root-=delta;
		}
		double diff=value-heap[heapIndex];
		if(!update)return -1*diff;
		diff+=operate(index-2,0,false);
		heap[heapIndex]+=diff;
		while(heapIndex!=1){
			if((heapIndex&1)==0)heap[heapIndex>>1]+=diff;
			heapIndex>>=1;
		}
		return 0;
	}
}
/**
 * finds the determinant of the input matrix
 * @param d the input matrix
 * @param row 0 to start with
 * @param mask 0 to start with
 * @param dp -1 filled array of the same length as ((1<<d.length)-1)
 * @return the value of the determinant
 */
public long det(int[][] d, int row, int mask, long[] dp) {
	if(row == d.length)return 1;
	if(dp[mask]>0)return dp[mask];
	int sign=1;
	long ans=0;
	for(int i=0;i<d.length;i++){
		if(((mask>>i)&1)==0){
			ans+=sign*d[row][i]*det(d,row+1,mask|(1<<i),dp);
			sign*=-1;
		}
	}
	dp[mask]=ans;
	return ans;
}
/**
 * converts an integer to a roman numeral
 * @param n the number
 * @return a string of the roman numeral
 */
public String i2rn(int n) {
	String[] rn={"I","V","X","L","C","D","M"};
	String out="";
	while(n>1000){
		n-=1000;
		out+=rn[6];
	}
	for(int i=2;i>=0;i--){
		int temp=n/(int)Math.pow(10, i);
		n%=(int)Math.pow(10, i);
		if(temp%5==4){
			out+=rn[2*i];
			temp++;
		}
		if(temp>9){
			out+=rn[2*i+2];
			temp-=10;
		}
		if(temp>4)out+=rn[2*i+1];
		for(int j=0;j<temp%5;j++)out+=rn[2*i];
	}
	return out;
}
/**
 * converts a roman numeral string to an integer
 * @param n the input string
 * @return the integer
 */
public int rn2i(String n) {
	//2x0 100 500 4x0 1 2x0 50 1000 8x0 5 0 10
	int[] nr={0,0,100,500,0,0,0,0,1,0,0,50,1000,0,0,0,0,0,0,0,0,5,0,10};
	int ans=0;
	int[] t=new int[n.length()+1];
	for(int i=0;i<n.length();i++)t[i]=nr[n.charAt(i)-'A'];
	for(int i=0;i<n.length();i++){
		if(t[i+1]>t[i])ans-=t[i];
		else ans+=t[i];
	}
	return ans;
}
/**
 * conputes the intersection of two lines given in standard form
 * @param a1
 * @param b1
 * @param c1
 * @param a2
 * @param b2
 * @param c2
 * @return null if parallel, else {x,y}
 * 
 * two points: a=y2-y1, b=x1-x2, c=a*x1+b*y1
 * slope intercept: a=-1*m b=1 c=b
 * point slope: a=-1*m b=1 c=y'-m*x'+b
 */
public double[] lineIntersect(double a1,double b1,double c1,double a2,double b2,double c2){
	double den=a1*b2-a2*b1;
	if(Math.abs(den)<.000001)return null;//parallel
	double x=(b2*c1-b1*c2)/den;
	double y=(a1*c2-a2*c1)/den;
	double[] ans={x,y};
	return ans;
}
/* other stuff
 * *******************************************************
 * MATH
 perp  has form -Bx+Ay=D, find d by plugging in any point
 
 to reflect, find perp through one point, double the vector two the intersection
 to rotate x' = xcos(0)-ysin(0) y'=xsin(0)+ycos(0)
 dot product A.x*B.x+A.y*B.y+A.z*B.z = |A|*|B|*cos(theta)
 
 cross product={A.y*B.z-A.z*B.y, A.z*B.x-A.x*B.z, A.x*B.y-A.y*B.x}
 |A x B|=|A|*|B|*sin(theta)
 can be used to determine what side of a line a point is on, or what direction a line turns (whether the z coordinate is positive or not)
 
 euclidean distance =((x1-x2)^2+(y1-y2)^2+(z1-z)^2+....)^.5
 
 law of sines sin(A)/a=sin(B)/b=sin(C)/c
 law of consines c^2=a^2+b^2-s*a*b*cos(C)
 
 area of triangle =1/2*b*h = 1/2*a*b*sin(C) = 1/2*c^2*sin(A)*sin(B)/sin(C)
 area of a triangle based on 3 points A=1/2*(A.x*B.y+B.x*C.y+C.x*A.y-A.x*C.y-B.x*A.y-C.x-B.y)
 area of triangle based on 3 side lengths A=sqrt(s*(s-a)*(s-b)*(s-c)) where s=1/2*(a+b+c)
 triangle incircle is centered at intersection of angle bisectors
 triangle outcircle is centered at intersection of perpendicular bisectors
 
 least common denominator= a*b/gcd(a,b)
 sum of first n numbers= n/2*(n+1)
 sum of first n squares= n*(n+1)*(2n+1)/6
 sum of first n cubes= (n/2*(n+1))^2
 lcm(a,b)*gcd(a,b)=a*b
 
 cramer's rule A=array of coefficients, x=det(A_x)/det(A) y=det(A_y)/det(A), 
 where A_? means replace the column representing that variable with the column of constants
 ************************************************************
 PRINTF EXAMPLES
   printf ("Characters: %c %c \n", 'a', 65);
   printf ("Decimals: %d %ld\n", 1977, 650000L);
   printf ("Preceding with blanks: %10d \n", 1977);
   printf ("Preceding with zeros: %010d \n", 1977);
   printf ("Some different radixes: %d %x %o %#x %#o \n", 100, 100, 100, 100, 100);
   printf ("floats: %4.2f %+.0e %E \n", 3.1416, 3.1416, 3.1416);
   printf ("Width trick: %*d \n", 5, 10);
   printf ("%s \n", "A string");
   
    Characters: a A
	Decimals: 1977 650000
	Preceding with blanks:       1977
	Preceding with zeros: 0000001977
	Some different radixes: 100 64 144 0x64 0144
	floats: 3.14 +3e+000 3.141600E+000
	Width trick:    10
	A string
	
	new DecimalFormat("#,###,##0.00").format(Math.min(1000000, b))
************************************************************
regex stuff:
Matcher m=Pattern.compile(regex).matcher(string);

JAVA OBJECTS AND METHODS

Native types:


int: 32 bytes from -2 billion to +2 billion 
string->int Integer.parseInt(string,base)
int->string Integer.toString(int) Integer.toHexString,toBinaryString...etc
other methods
Integer	.bitCount(i) number of 1's in the binary representation
	.highest/lowestOneBit(i)
	.numberOfLeading/TrailingZeros(i)

long: 64 bytes from -2^63 to 2^63
double: decimal number up to 2^128
char: character, useful to know you can subtract 'A' from chars to get index in the alphabet
String: just convert it to soemthing else


Containers:


Array:
must be created with a length int[] j=new int[25];
length can't change
methods:
Arrays 	.sort
	.binarySearch --array must be sorted before hand...negative result indicates object not found
	.copyOfRange --copies part of an array to a new array
	.fill --fill an array with a value
	.length

Collections:

Collection<T> j=new COllection<T>(oldCollection); creates j with all the elements of oldCollection
methods
COllections	.binarySearch --list must be sorted
		.copy --copies a list to a new list
		.fill
		.max
		.min
		.replaceAll --replaces all occurances of one thing with another
		.reverse
		.swap
		.sort --use custome comparator or Colletions.reverseOrder() to sort backwards

List:

just a list of stuff

List	.add/addAll
	.contains/containsAll
	.isEmpty
	.indexOf
	.remove/removeAll
	.toArray
	.size

ArrayList<T> j=new ArrayList<T>();
length can change, o(1) lookup/insert from back, o(n) insert/delete (from front)

LinkedList<T> j=new LinkedList<T>();
o(n) lookup, o(1) insert/delete at front/back

Set:

a container which only contains 1 of a given element

Set	.add/addAll
	.contains/containsAll
	.isEmpty
	.remove/RemoveAll
	.size
	.toArray

HashSet: o(1) for all operations
TreeSet: o(log(n)) for all operations, is sorted inherently
Treeset 	.ceiling --returns first object higher than the given object
		.first
		.floor --opposite of ceiling
		.headset --returns a treeset of items lower than the given one
		.last
		.subSet --set between two given objects
		.tailset --opposite of headset

Map:

maps a key to a value

Map	.clear
	.containsKey
	.keySet
	.put
	.get
	.remove

HashMap: only one you'll ever use, commonly HashMap<ArrayList<Integer>>

OTHER COllections:

Stack: can only get back the things you added in reverse order
stack	.push --add something to the stack
	.pop --get and remove the item at the top of the stack
	.peek --check the item on top of the stack without removing

Queue: can only get back the oldest item in the list, must be a linked list Queue j=new LinkedList();
Queue	.offer --add something to the queue
	.poll --get and remove the front of the queue
	.peek --check the front but dont remove

Priority Queue: sorted list that returns the lowest value thing, must instantiate with a size and a comparator
o(log(n)) add, o(log(n)) remove from front o(n) arbitrary remove


AWT objects:

Point: an x,y integer pair, often used to represent edges

Point	.x/y the two values of the point
	.translate move the point by the offsets given
	.distance distance between two points

Point2D.Double: same as a point, but uses doubles instead of ints...useful sometimes

Line2D.Double: contains 2 points that define the line
	.getBounds --returns a Rectangle defined by the box
	.intersectsLine
	.ptLineDist
	.ptSegDist

Shape/Rectangle, I've never used, but useful for testing if a point is inside a given shape, taking intersections/etc


****************************************
dumb things to not do

corner cases
divide by zero error
xproduct=0
copying lines...double check EVERY change
exit condition
changes you made for simplicity breaking corner cases
correct mini-maxes
what's inside nested loops
initial doesn't work check
accidental overflow (including temporary from multiply before division)
double/integer division
multiple solutions to problem when you might not get right one(aka for dijkstras)
explicitly calculating number of times through loop instead of while()
lost bits off the front converting ints to strings (especially binary)
extra bits off the front converting strings to ints (negative numbers)
substrings off the end
number of integer partitions is SMALL
recursion base cases
mods with doubles
structs that get saved across test cases
looping over changing data (missing something)
treesets with common elements
rounding doubles without adding/subtracting an epison
comparing doubles without checking delta from one another
long+=double
1L before left shifting >32
extra teadline when switching what you're reading
don't compare arrays for equality without a custom comparator
make comparators consistent
print out your damn code
== instead of .equals
	*/
 
}
