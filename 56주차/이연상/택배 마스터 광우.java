// [SOFT] 택배 마스터 광우

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K;
    static int[] rail;
    static boolean[] visited;
    static int answer = 987654321;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        rail = new int[N];
        visited = new boolean[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            rail[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, new int[N]);
        System.out.println(answer);
     }

    static void dfs(int count, int[] arr) {
        if(count == N) {
            calMin(arr);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            arr[count] = rail[i];
            dfs(count+1, arr);
            visited[i] = false;
        }
    }

    static void calMin(int[] arr) {
        int temp = 0;
        int idx = 0;
        int weight = 0;
        int sum = 0;
        while(temp < K) {
            int next = sum + arr[idx % N];
            if(next > M) {
                temp += 1;
                weight += sum;
                sum = 0;
            } else {
                sum = next;
                idx += 1;
            }
        }
        answer = Math.min(answer, weight);
    }
}
