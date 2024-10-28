import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0인네정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] input = new int[4][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				input[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		long[] ABSum = new long[N * N];
		long[] CDSum = new long[N * N];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				long abSum = (long)input[0][i] + input[1][j];
				long cdSum = (long)input[2][i] + input[3][j];
				ABSum[idx] = abSum;
				CDSum[idx++] = cdSum;
			}
		}

		Arrays.sort(ABSum);
		Arrays.sort(CDSum);

		int left = 0;
		int size = N * N;
		int right = size - 1;
		long result=0;
		while (left < N * N && right >= 0) {
			long sum=ABSum[left]+CDSum[right];
			if(sum==0){
				int leftTemp=left;
				int countAB=0;
				while(leftTemp<size&&ABSum[leftTemp]==ABSum[left]){
					countAB+=1;
					leftTemp++;
				}

				int rightTemp=right;
				int countCd=0;
				while(rightTemp>=0&&CDSum[rightTemp]==CDSum[right]){
					countCd+=1;
					rightTemp--;
				}
				result+=(long)countAB*countCd;
				left=leftTemp;
				right=rightTemp;
			}else if(sum>0){
				right--;
			}else{
				left++;
			}
		}
		System.out.println(result);
	}

}
