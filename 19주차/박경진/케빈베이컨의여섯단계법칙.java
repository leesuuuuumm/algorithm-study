import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] relation = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == j) relation[i][j] = 0;
                else relation[i][j] = 987654321;
            }
        }

        for(int i = 0; i < M; i++){
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            relation[x][y] = relation[y][x] = 1;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    //기존의 단계보다 적은 단계로 이어질 수 있다면
                    if(relation[j][k] > relation[j][i] + relation[i][k]){
                        relation[j][k] = relation[j][i] + relation[i][k];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int mini = -1;

        for(int i = 0; i < N; i++){
            int sum = 0;
            for(int j = 0; j < N; j++){
                sum += relation[i][j];
            }

            if(min > sum){
                min = sum;
                mini = i + 1;
            }
        }

        System.out.println(mini);
    }
}
