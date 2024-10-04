
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] arr = new int[m][n];
            boolean[][] checked = new boolean[m][n];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 0) continue;
                    else if (arr[i][j] == 1 && !checked[i][j]) {
                        bfs(arr, checked, i, j, m, n);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);

        }

    }

    static void bfs(int[][] arr, boolean[][] checked, int x, int y, int m, int n) {
        Queue<int[]> que = new LinkedList<int[]>();
        que.add(new int[] {x, y});

        int[] gx = {0, 0, 1, -1};
        int[] gy = {1, -1, 0, 0};

        while (!que.isEmpty()) {
            int[] point = que.poll();

            for (int i = 0; i < 4; i++) {
                int dx = gx[i] + point[0];
                int dy = gy[i] + point[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                    if (arr[dx][dy] == 1 && !checked[dx][dy]) {
                        que.add(new int[] {dx, dy});
                        checked[dx][dy] = true;
                    }
                }
            }

        }

    }

}
