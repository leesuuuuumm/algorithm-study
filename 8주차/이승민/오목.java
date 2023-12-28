import java.util.*;

public class Main {
    static int[] dx = {1, 0, -1, 0, -1, 1, 1, -1};
    static int[] dy = {0, -1, 0, 1, 1, 1, -1, -1};
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int[][] map = new int[19][19];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int ans = 0;
        int ansx = -1;
        int ansy = -1;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (map[i][j] == 0)
                    continue;
                for (int k = 0; k < 8; k++) {
                    int cnt = 1;
                    int curx = i;
                    int cury = j;
                    while (true) {
                        cnt++;
                        int nx = curx + dx[k];
                        int ny = cury + dy[k];
                        if (nx < 0 || nx >= 19 || ny < 0 || ny >= 19) {
                            break;
                        }
                        if (map[nx][ny] != map[i][j]) {
                            break;
                        }
                        if (cnt == 3) {
                            ansx = nx;
                            ansy = ny;
                        }
                        if (cnt == 5) {
                            ans = map[i][j];
                            System.out.print(ans + "\n" + (ansx + 1) + " " + (ansy + 1));
                            return;
                        }
                        curx = nx; 
                        cury = ny;
                    }
                }
            }
        }

        System.out.print(ans);
    }
}
