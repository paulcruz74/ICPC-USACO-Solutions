import java.awt.*;
import java.util.*;
public class Hull {
	public ArrayList<Point> hull(ArrayList<Point> in){//default is exclusive, see 2 comments for inclusivity
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
}
