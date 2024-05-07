// [BOJ] 호텔

import java.io.*;
import java.util.*;
 
class Main { // Boj_1106_호텔
    public static int[] dp;
    public static int N, C, answer;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); 
        C = Integer.parseInt(st.nextToken()); 
        dp = new int[N+101];
        Arrays.fill(dp, Integer.MAX_VALUE); 
        dp[0] = 0;
    
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
 
            for (int j = people; j < dp.length; j++) {
                if (dp[j - people] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - people] + price);
                }
            }
        }
        answer = dp[N];
        for (int i = N; i < N + 101; i++) {
            answer = Math.min(dp[i], answer);
        }
 
        System.out.println(answer);
    }
}
