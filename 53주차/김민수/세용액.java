import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 세용액 {

	//투포인터 & 이분탐색
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		long[] input=new long[N];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			input[i]=Long.parseLong(st.nextToken());
		}
		Arrays.sort(input);

		long[] answer=binarySearch(input, N);
		Arrays.sort(answer);
		for(int i=0;i<3;i++){
			System.out.print(answer[i]+" ");
		}
	}
	public static long[] binarySearch(long[] arr, int N){
		long[] answer=new long[3];
		long sum=Long.MAX_VALUE;
		for(int i=0;i<N;i++){
			long fix=arr[i];

			int left=i+1;
			int right=N-1;
			while(left<right){
				long temp=arr[left]+arr[right]+fix;
				if(Math.abs(sum)>Math.abs(temp)){
					answer[0]=fix;
					answer[1]=arr[left];
					answer[2]=arr[right];
					sum=temp;
				};
				if(temp==0){
					return answer;
				}else if(temp>0){
					right-=1;
				}else{
					left+=1;
				}
			}
		}
		return answer;
	}
}
