import java.util.*;
public class bipartiteMatch {
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
}
