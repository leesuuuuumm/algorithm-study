import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    O(N)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] vis = new int[100001]; // 방문 체크 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int r = -1;
        long cnt = 0;
        for (int l = 0; l < n; l++) {
            // 다음에 r이 이동할 위치의 숫자가 이미 등장했는지 확인하고 r을 옮긴다.
            while (r + 1 < n && vis[arr[r + 1]] == 0) {
                r++;
                vis[arr[r]]++;
            }

            cnt += r - l + 1;

            vis[arr[l]]--;
        }

        System.out.println(cnt);
    }
}