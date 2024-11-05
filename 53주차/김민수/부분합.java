import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int S=Integer.parseInt(st.nextToken());
		int[] input=new int[N+2];
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++){
			input[i]=Integer.parseInt(st.nextToken());
		}

		int left=1;
		int right=1;
		int answer=N+1;
		int sum=0;
		while(left<=right&&right<=N+1){
			if(sum<S){
				sum+=input[right++];
			}else{
				answer=Math.min(answer,right-left);
				sum-=input[left++];
			}
		}

		if(answer>N){
			System.out.println(0);
		}else{
			System.out.println(answer);
		}

	}
}
