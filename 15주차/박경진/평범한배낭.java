import java.util.Scanner;

public class Main {
    static int[] weight;
    static int[] value;
    static int[][] dp;
    static int N;
    static int K;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //물품의 수
        K = sc.nextInt(); //배낭의 최대 무게
        weight = new int[N]; //물픔의 무게
        value = new int[N]; //물품의 가치
        dp = new int[N+1][K+1]; //열: 담을 무게, 행: 물품의 인덱스

        for(int i = 0; i < N; i++){
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        System.out.println(whatsinmybag());
    }

    //남은 무게와 현재 인덱스를 넘겨받아 얼마의 가치를 담을 수 있는지 계산하는 함수
    public static int whatsinmybag(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= K; j++){
                dp[i][j] = dp[i-1][j]; //이전 물품만 담겼을 때 가치를 가져옴

                if(j >= weight[i-1]){ //지금 물품의 무게와 담을 무게 비교
                    //이전 물품의 해당 무게에서의 가치와 남은 무게에서의 물품 가치 + 현재 물품의 가치를 비교
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i-1]] + value[i-1]);
                }
            }
        }

        return dp[N][K];
    }
}
