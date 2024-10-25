import java.util.*;

class 피로도 {
	static int[] result;
	static boolean[] visited;
	static int len;
	static int[][] d;
	static int init;
	static int answer;
	public int solution(int k, int[][] dungeons) {
		answer = 0;
		len=dungeons.length;
		d=dungeons;
		init=k;
		for(int i=0;i<len;i++){
			visited=new boolean[len];
			result=new int[len];
			visited[i]=true;
			result[0]=i;
			backtraking(1);
		}
		return answer;
	}
	public static void backtraking(int depth){
		if(depth==len){
			calculate(result);
			for(int i=0;i<len;i++){
			}

			return;
		}
		for(int i=0;i<len;i++){
			if(!visited[i]){
				visited[i]=true;
				result[depth]=i;
				backtraking(depth+1);
				visited[i]=false;
			}
		}
	}
	public static void calculate(int[] result){
		int left=init;
		int ans=0;
		for(int i=0;i<len;i++){
			int idx=result[i];
			if(left>=d[idx][0]){
				left-=d[idx][1];
				ans+=1;
			}else{
				break;
			}
		}
		answer=Math.max(ans, answer);
	}
}
