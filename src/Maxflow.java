import java.util.*;
public class Maxflow{
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
}