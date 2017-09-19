/*
 * Alright, say you need to find the shortest path between all pairs of points in a graph? this is floyd warshall and it will do it for you
 * in n^3 time. Basically you start by initializing every path to infinity, and then say for a given path from i to j, can I get from i to j
 * by using k? if you can then you update the known distance; ensure that all disconnected vertices are set to Integer.MAX_VALUE in the adjacency matrix
 */
public class FloydWarshall {


	public void FW(int[][] in){
		for(int k=0;k<in.length;k++)for(int i=0;i<in.length;i++)for(int j=0;j<in.length;j++)in[i][j]=Math.min(in[i][j], in[i][k]+in[k][j]);
	}

	public int[][] FWwPath(int[][] in){
		int[][] next=new int[in.length][in.length];
		for(int i=0;i<in.length;i++)for(int j=0;j<in.length;j++)if(in[i][j]<Integer.MAX_VALUE)next[i][j]=j;

		for(int k=0;k<in.length;k++)for(int i=0;i<in.length;i++)for(int j=0;j<in.length;j++)if(in[i][k]+in[k][j]<in[i][j]){
			in[i][j]=in[i][k]+in[k][j];
			next[i][j]=next[i][k];		
		}
		return next;
	}
}
