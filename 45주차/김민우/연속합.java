import java.util.Scanner;

public class Main {

    static int[] arr;		// 배열
    static Integer[] dp;		// 메모이제이션 할 dp
    static int max;			// 최댓값 변수

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        arr = new int[N];
        dp = new Integer[N];

        for(int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }

        dp[0] = arr[0];
        max = arr[0];

        recur(N - 1);

        System.out.println(max);
    }

    static int recur(int N) {
        
        if(dp[N] == null) {
            dp[N] = Math.max(recur(N - 1) + arr[N], arr[N]);


            max = Math.max(dp[N], max);
        }

        return dp[N];
    }
}