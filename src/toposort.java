import java.util.*;

public class toposort {
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
}
