import java.util.*;
public class BellmanFord {
	public int[] BF(int r, ArrayList<HashMap<Integer,Integer>> in){
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
}
