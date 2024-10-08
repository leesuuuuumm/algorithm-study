import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 종전 {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(makeRect());


    }

    public static int makeRect() {
        int dif = Integer.MAX_VALUE;
        for (int bR = 2; bR < N; bR++) {
            for (int bC = 1; bC < N - 1; bC++) {
                int aLen = Math.min(N - 1 - bC, bR - 1);
                for (int a = 1; a <= aLen; a++) {
                    int rR = bR - a;
                    int rC = bC + a;
                    int bLen = Math.min(bR, bC);
                    bLen = Math.min(bLen, bR - a);
                    bLen = Math.min(bLen, bC + a);
                    for (int b = 1; b <= bLen; b++) {
                        int tR = bR - a - b;
                        int tC = bC + a - b;
                        int lR = bR - b;
                        int lC = bC - b;
                        dif = Math.min(dif, calculate(tR, tC, rR, rC, bR, bC, lR, lC));
                    }
                }
            }
        }
        return dif;
    }

    public static int calculate(int tR, int tC, int rR, int rC, int bR, int bC, int lR, int lC) {
        int[][] region = new int[N][N];
        region[bR][bC] = 1;
        region[rR][rC] = 1;
        region[tR][tC] = 1;
        region[lR][lC] = 1;
        int r = bR, c = bC;
        int[] sum = new int[5];
        while (r != rR && c != rC) {
            r -= 1;
            c += 1;
            region[r][c] = 1;
        }
        while (r != tR && c != tC) {
            r -= 1;
            c -= 1;
            region[r][c] = 1;
        }
        while (r != lR && c != lC) {
            r += 1;
            c -= 1;
            region[r][c] = 1;
        }
        while (r != bR && c != bC) {
            r += 1;
            c += 1;
            region[r][c] = 1;
        }

        for(int i=0;i<N;i++){
            int[] spot=new int[2];
            int count=0;
            for(int j=0;j<N;j++){
                if(region[i][j]==1){
                    spot[count++]=j;
                }
            }
            if(count>1){
                for(int j=spot[0]+1;j<spot[1];j++){
                    region[i][j]=1;
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(region[i][j]==1){
                    sum[0]+=map[i][j];
                }
            }
        }

        for (int i = 0; i < lR; i++) {
            for (int j = 0; j <= tC; j++) {
                if (region[i][j] == 1) {
                    break;
                }
                region[i][j] = 2;
                sum[1] += map[i][j];
            }
        }

        for (int i = 0; i <= rR; i++) {
            for (int j = tC + 1; j < N; j++) {
                if (region[i][j] == 1) {
                    continue;
                }
                region[i][j] = 3;
                sum[2] += map[i][j];
            }
        }

        for (int i = lR; i < N; i++) {
            for (int j = 0; j < bC; j++) {
                if (region[i][j] == 1) {
                    break;
                }
                region[i][j] = 4;
                sum[3] += map[i][j];
            }
        }

        for (int i = rR + 1; i < N; i++) {
            for (int j = bC; j < N; j++) {
                if (region[i][j] == 1) {
                    continue;
                }
                region[i][j] = 5;
                sum[4] += map[i][j];
            }
        }

        Arrays.sort(sum);
        if(sum[4]-sum[0]==10){
            System.out.println();
        }
        return sum[4]-sum[0];
    }


}
