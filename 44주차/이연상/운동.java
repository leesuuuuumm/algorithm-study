// [BOJ] 운동

import java.io.*;
import java.util.StringTokenizer;
 
public class Main {

    public static int V, N, a, b, c, answer, max;
    public static int[][] arr;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        V = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[V + 1][V + 1];
        max = 1000000000;
 
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                arr[i][j] = max;
            }
        }
 
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); 
            b = Integer.parseInt(st.nextToken()); 
            c = Integer.parseInt(st.nextToken()); 
            arr[a][b] = c;
        }
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                        arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
                }
            }
        }
        answer = max;
        for (int i = 1; i <= V; i++) { 
            for (int j = 1; j <= V; j++) { 
                if (arr[i][j] != max && arr[j][i] != max) {
                    answer = Math.min(answer, arr[i][j] + arr[j][i]);
                }
            }
        }
        answer = max == answer ? -1 : answer;
        System.out.println(answer);
    }
}