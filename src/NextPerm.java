import java.util.Arrays;

public class NextPerm {
	public boolean NextPermutation(int[] in){
		for(int i=in.length-2;i>=0;i--)if(in[i]<in[i+1]){
			for(int j=in.length-1;j>i;j--)if(in[j]>in[i]){
				int t=in[i];
				in[i]=in[j];
				in[j]=t;
				break;
			}
			for(int j=i+1;j<in.length-(j-i);j++){
				int t=in[j];
				in[j]=in[in.length-(j-i)];
				in[in.length-(j-i)]=t;
			}
			return true;
		}
		return false;
	}
	public long numPermutation(int[] in){
		int total=0;
		for(int i:in)total+=i;
		long[][] dp=new long[total+1][total+1];
		Arrays.fill(dp[0], 1);
		for(int i=1;i<=total;i++)for(int j=0;j<=total;j++)for(int k=0;k<=j;k++)dp[i][j]+=dp[i-1][j-k];
		long ans=1;
		total=0;
		for(int i:in){
			ans*=dp[total][i];
			total+=i;
		}
		return ans;
	}
}
