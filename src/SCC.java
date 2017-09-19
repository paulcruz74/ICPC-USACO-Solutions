import java.util.*;
public class SCC {
	public int[] tarjan(ArrayList<ArrayList<Integer>> in){
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
}
