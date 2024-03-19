import java.util.*;

public class Main {
    static int[] nums;
    static Integer[] memo;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int answer = 0;
        nums = new int[N];
        memo = new Integer[N];
        for(int i = 0; i < N; i++){
            nums[i] = sc.nextInt();
        }

        answer = Integer.MIN_VALUE;
        memo[0] = nums[0];
        if(N-1 >= 0) dp(N-1);

        for(int i = 0; i < N; i++){
            answer = Math.max(answer, memo[i]);
        }
        System.out.println(answer);
    }

    public static int dp(int N){
        if(memo[N] == null && N-1 >= 0){
            memo[N] = Math.max(nums[N], dp(N-1)+nums[N]);
        }

        return memo[N];
    }
}
