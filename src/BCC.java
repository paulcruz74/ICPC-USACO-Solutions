import java.awt.Point;
import java.util.*;


public class BCC {
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
}
