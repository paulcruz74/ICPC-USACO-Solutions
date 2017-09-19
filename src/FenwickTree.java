//note: 0 indexed, set update to false for read, true for write, read is cumulative and inclusive
public class FenwickTree {
	double[] heap;
	public FenwickTree(double[] in){
		heap=new double[Integer.highestOneBit(in.length)<<1];
		for(int i=0;i<in.length;i++)operate(i,in[i],true);
	}
	public double operate(int index, double value,boolean update) {
		int heapIndex=1,root=heap.length>>1,delta=root;
		index++;
		if(index==0||index==heap.length)return 0;
		while(index!=root){
			heapIndex<<=1;
			if(index>root){
				value-=heap[heapIndex>>1];
				heapIndex++;
				root+=delta;
			}
			delta>>=1;
			root-=delta;
		}
		double diff=value-heap[heapIndex];
		if(!update)return -1*diff;
		diff+=operate(index-2,0,false);
		heap[heapIndex]+=diff;
		while(heapIndex!=1){
			if((heapIndex&1)==0)heap[heapIndex>>1]+=diff;
			heapIndex>>=1;
		}
		return 0;
	}
}
