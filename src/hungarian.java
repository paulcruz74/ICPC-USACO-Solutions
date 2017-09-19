import java.util.*;
public class hungarian{
	public int[] match(int[][] cost){
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
}
