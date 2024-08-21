package 김민우;

import java.io.*;
import java.util.*;

public class 효율적인해킹 {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] res;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 컴퓨터의 수 (1 ~ n)
        int m = Integer.parseInt(st.nextToken()); // m: 신뢰하는 관계의 수
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        res = new int[n + 1];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
        }


        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            visited[i] = true;
            dfs(i);
        }

        int max = Integer.MIN_VALUE;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            max = Math.max(res[i], max);
        }

        for (int i = 1; i <= n; i++) {
            if (max == res[i]) {
                result.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int num : result) {
            sb.append(num + " ");
        }

        System.out.println(sb);

    }

    static void dfs(int start) {

        for (int n : graph[start]) {
            if (!visited[n]) {
                visited[n] = true;
                res[n]++; // 해당 원수 카운트 증가
                dfs(n);
            }
        }

    }
}
