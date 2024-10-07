import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이상한다트게임 {
    static int r, m;//r: 원판의 개수, m: 숫자 개수, k 회전 횟수
    static int[][] info;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        info = new int[r + 1][m];
        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            simulation(x, d, k);
        }

        int sum = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < m; j++) {
                if (info[i][j] != -1) {
                    sum += info[i][j];
                }
            }
        }

        System.out.println(sum);

    }

    public static void simulation(int x, int d, int k) {
        flag = false;
        rotate(x, d, k);
        inject();
        normalize();
    }

    public static void rotate(int x, int d, int k) {
        for (int i = x; i <= r; i += x) {//x의 배수만큼
            int[] afterRotate = new int[m];
            if (d == 0) {//시계방향
                for (int j = 0; j < m; j++) {
                    int nextIdx = (j + k) % m;
                    afterRotate[nextIdx] = info[i][j];

                }
            } else {
                for (int j = 0; j < m; j++) {
                    int nextIdx = (j - k + m) % m;
                    afterRotate[nextIdx] = info[i][j];

                }
            }
            for (int j = 0; j < m; j++) {
                info[i][j] = afterRotate[j];
            }
        }
    }

    public static void inject() {//인접한거 계산하고 삭제하기
        int[][] newInfo = new int[r + 1][m];
        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < m; j++) {
                newInfo[i][j] = info[i][j];
            }
        }
        for (int j = 0; j < m; j++) {
            if (info[1][j] != -1 && info[1][j] == info[2][j]) {
                newInfo[1][j] = newInfo[2][j] = -1;
                flag = true;
            }
        }

        for (int j = 0; j < m; j++) {
            if (info[r][j] != -1 && info[r][j] == info[r - 1][j]) {
                newInfo[r - 1][j] = newInfo[r][j] = -1;
                flag = true;
            }
        }
        for (int i = 2; i < r; i++) {
            int nextIdx = i + 1;
            int beforeIdx = i - 1;
            for (int j = 0; j < m; j++) {
                if (info[i][j] != -1 && info[nextIdx][j] == info[i][j]) {
                    newInfo[nextIdx][j] = newInfo[i][j] = -1;
                    flag = true;
                }
                if (info[i][j] != -1 && info[i][j] == info[beforeIdx][j]) {
                    newInfo[i][j] = newInfo[beforeIdx][j] = -1;
                    flag = true;

                }
            }
        }
        for (int j = 0; j < m; j++) {
            int nextIdx = (j + 1) % m;
            int beforeIdx = (j - 1 + m) % m;
            for (int i = 1; i <= r; i++) {
                int nextValue = info[i][nextIdx];
                int curValue = info[i][j];
                int beforeValue = info[i][beforeIdx];
                if (curValue != -1 && nextValue == curValue) {
                    newInfo[i][nextIdx] = newInfo[i][j] = -1;
                    flag = true;

                }
                if (curValue != -1 && curValue == beforeValue) {
                    newInfo[i][j] = newInfo[i][beforeIdx] = -1;
                    flag = true;

                }
            }
        }

        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < m; j++) {
                info[i][j] = newInfo[i][j];
            }
        }
    }

    public static void normalize() {
        int sum = 0;
        int count = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < m; j++) {
                if (info[i][j] != -1) {
                    sum += info[i][j];
                    count += 1;
                }
            }
        }
        if (!flag && count > 0) {
            int mid = sum / count;
            for (int i = 1; i <= r; i++) {
                for (int j = 0; j < m; j++) {
                    if (info[i][j] > mid) {
                        info[i][j] -= 1;
                    } else if (info[i][j] != -1 && info[i][j] < mid) {
                        info[i][j] += 1;
                    }
                }
            }
        }
    }
}
