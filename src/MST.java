import java.util.*;
import java.awt.*;


public class MST{
	/**
	 * implementation for use with tuples on an xy plane
	 */
	public  ArrayList<Point> MST1(ArrayList<Point> in){
		ArrayList<Point> out=new ArrayList<Point>();
		HashSet<Integer> visited=new HashSet<Integer>();
		final ArrayList<Point> al=new ArrayList<Point>(in);
		PriorityQueue<Point> pq=new PriorityQueue<Point>(al.size(), new Comparator<Point>(){
			public int compare(Point o1, Point o2) {
				double d=al.get(o1.x).distance(al.get(o1.y))-al.get(o2.x).distance(al.get(o2.y));
				if(d!=0)return (int) Math.signum(d);
				if(o1.x!=o2.x)return o1.x-o2.x;
				return o1.y-o2.y;
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
	 *implementation for use with arbitrary point distance pairs: takes as input an adjacency list where the distance between p and q is in.get(p).get(q)
	 */
	public ArrayList<Point> MST2(ArrayList<HashMap<Integer,Double>> in){
		ArrayList<Point> out=new ArrayList<Point>();
		HashSet<Integer> visited=new HashSet<Integer>();
		final ArrayList<HashMap<Integer,Double>> al=new ArrayList<HashMap<Integer,Double>>(in);
		PriorityQueue<Point> pq=new PriorityQueue<Point>(al.size(), new Comparator<Point>(){
			public int compare(Point o1, Point o2) {
				double d=al.get(o1.x).get(o1.y)-al.get(o2.x).get(o2.y);
				if(d!=0)return (int) Math.signum(d);
				if(o1.x!=o2.x)return o1.x-o2.x;
				return o1.y-o2.y;
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
	//implementation for an arbitrary graph with an AM
	public ArrayList<Point> MST3(double[][] in){
		ArrayList<Point> out=new ArrayList<Point>();
		HashSet<Integer> visited=new HashSet<Integer>();
		final double[][] al=new double[in.length][in.length];
		for(int i=0;i<in.length;i++)for(int j=0;j<in.length;j++)al[i][j]=in[i][j];
		PriorityQueue<Point> pq=new PriorityQueue<Point>(al.length, new Comparator<Point>(){
			public int compare(Point o1, Point o2) {
				double d=al[o1.x][o1.y]-al[o2.x][o2.y];
				if(d!=0)return (int) Math.signum(d);
				if(o1.x!=o2.x)return o1.x-o2.x;
				return o1.y-o2.y;
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


}