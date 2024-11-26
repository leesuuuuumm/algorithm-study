import java.io.*;
 
public class Solution {
 
    static int[][] dp;
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            char[] ch = br.readLine().toCharArray();
            dp = new int[ch.length][16];
 
            searchfirstDay(ch[0]);
 
            for (int i = 1; i < ch.length; i++) {
                searchOtherDays(ch[i], i);
 
            }
 
            int sum = 0;
            for (int i = 1; i <= 15; i++) {
                sum += dp[dp.length - 1][i];
                sum %= 1000000007;
 
            }
            System.out.println("#" + t + " " + sum);
 
        }
 
    }
 
    private static void searchfirstDay(char c) {
        int key = 1 << (c - 'A');
 
        for (int i = 1; i < 16; i++) {
            if ((i & key) != 0 && ((i & 1) != 0)) {
                dp[0][i] = 1;
            }
 
        }
 
    }
 
    private static void searchOtherDays(char c, int day) {
        int key = 1 << (c - 'A');
 
        for (int i = 1; i < 16; i++) {
            if (dp[day - 1][i] != 0) {
                for (int j = 1; j < 16; j++) {
                    if ((j & key) != 0 && (j & i) != 0) { // 책임자가 있고 전날에 참석한 경우
                        dp[day][j]  += (dp[day - 1][i] );
                        dp[day][j] %= 1000000007;
                        //System.out.println(i + " " + day + " " + j);
 
                    }
 
                }
 
            }
 
        }
 
    }
}
