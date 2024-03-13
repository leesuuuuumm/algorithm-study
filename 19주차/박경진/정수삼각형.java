import java.util.*;

public class Main {
    static int n;
    static int[][] pyramid;
    static int[][] memo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        pyramid = new int[n][n];
        memo = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                pyramid[i][j] = sc.nextInt();
            }
        }

        int answer = dp(0,0);
        System.out.println(answer);
    }

    public static int dp(int x, int y){
        if(x == n-1){
            memo[x][y] = pyramid[x][y];
        }
        else {
            int one = memo[x+1][y], two = memo[x+1][y+1];
            if(one == 0) one = dp(x+1,y);
            if(two == 0) two = dp(x+1,y+1);
            int max = Math.max(one, two);
            memo[x][y] = Math.max(pyramid[x][y] + max, memo[x][y]);
        }

        return memo[x][y];
    }
}
