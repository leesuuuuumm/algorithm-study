import java.io.*;
import java.util.*;

public class 숫자고르기 {

    static int n;
    static boolean[] visited;
    static ArrayList<Integer> result = new ArrayList<>(); // 답을 담을 리스트
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 주요 로직
        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(result);

        System.out.println(result.size());
        for (int n : result) {
            System.out.println(n);
        }

    }

    public static void dfs(int start, int target) {
        if (visited[arr[start]] == false) {
            visited[arr[start]] = true;
            dfs(arr[start], target);
            visited[arr[start]] = false;

        }
        if (arr[start] == target) result.add(target);

    }

}
