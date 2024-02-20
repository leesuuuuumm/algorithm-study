import java.util.*;

public class Main {
    static int N;
    static Integer[] dp;
    static int[] wines;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); //포도주의 개수
        wines = new int[N+1]; //포도주의 양

        for(int i = 1; i < N+1; i++){
            wines[i] = sc.nextInt(); //포도주의 양 저장
        }

        dp = new Integer[N+1]; //각 포도주를 마실 때 최대 양

        dp[0] = 0;
        if(N >= 1) dp[1] = wines[1];
        if(N > 1) dp[2] = wines[2] + wines[1];

        System.out.println(drink(N));
    }

    static int drink(int now){
        if(dp[now] == null){
            dp[now] = Math.max(Math.max(drink(now-2),drink(now-3)+wines[now-1])+wines[now], drink(now-1));
        }

        return dp[now];
    }
}
