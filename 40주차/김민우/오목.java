package 김민우;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class 오목 {
    // 4방향 탐색
    static int dr[] = {1, 0, 1, -1}, dc[] = {0, 1, 1, 1}, map[][], N = 19, D = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];

        for (int i = 0; i < N; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 배열 선언

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < D; k++) {
                    int color = map[i][j]; // 현재 색

                    // 오목 && 육목 검사
                    if (color != 0 && search(i, j, 1, k, color) && checkReverse(i - dr[k], j - dc[k], color)) {
                        System.out.println(color);
                        System.out.println((1 + i) + " " + (1 + j));
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }

    public static boolean search(int r, int c, int dep, int flag, int color) {

        int tr = r + dr[flag];
        int tc = c + dc[flag];
        // is valid
        boolean b = tr < 0 || tr >= N || tc < 0 || tc >= N || map[tr][tc] != color;

        // 5목이면
        if (dep == 5) {
            // 6번째 돌 위치가 바둑판 밖이거나, 다른 색이면 오목 확정
            if (b) return true;
            return false; // 같은 색이면 6목이라는 것이니 false
        }
        // 5목이 아닐 때
        if (b) return false;

        return search(tr, tc, dep + 1, flag, color);
    }

    // search()에서 정방향은 확인했으니 역방향만 확인하면 된다.
    public static boolean checkReverse(int tr, int tc, int color) {
        // 탐색 시작 방향 반대 체크
        return tr < 0 || tr >= N || tc < 0 || tc >= N || map[tr][tc] != color; // 색이 다르면 오목 확정
    }
}
