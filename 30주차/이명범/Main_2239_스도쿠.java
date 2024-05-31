import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] row, col, block;
    static int count;
    static List<Location> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[9][9];

        row = new int[9];
        col = new int[9];
        block = new int[9];
        // 채워지지 않은 공간의 개수
        count = 0;
        // 0의 위치 체크
        list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                int value = str.charAt(j) - '0';
                map[i][j] = value;

                // 만약 값이 0이 아니면
                if (value != 0) {
                    flag(i, j, value);
                    continue;
                }

                count++;
                list.add(new Location(i, j));
            }
        }
        solution(map, 0);
    }

    private static boolean solution(int[][] map, int cnt) {
        if (cnt == count) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

            sb.setLength(sb.length() - 1);

            System.out.println(sb);

            return true;
        }

        Location currentLocation = list.get(cnt);

        // 1부터 9까지 경우의 수
        for (int k = 1; k <= 9; k++) {
            // 행에 같은 값이 있으면 continue
            if ((row[currentLocation.r] & 1 << k) != 0) continue;
            // 열에 같은 값이 있으면 continue;
            if ((col[currentLocation.c] & 1 << k) != 0) continue;
            // 블럭에 같은 값이 있으면 continue;
            if ((block[currentLocation.r / 3 * 3 + currentLocation.c / 3] & 1 << k) != 0) continue;

            map[currentLocation.r][currentLocation.c] = k;
            flag(currentLocation.r, currentLocation.c, k);

            if (solution(map, cnt + 1)) return true;

            map[currentLocation.r][currentLocation.c] = 0;
            uncheckedFlag(currentLocation.r, currentLocation.c, k);
        }
        return false;
    }

    private static void uncheckedFlag(int i, int j, int k) {
        row[i] ^= 1 << k;
        col[j] ^= 1 << k;
        block[i / 3 * 3 + j / 3] ^= 1 << k;
    }

    private static void flag(int i, int j, int k) {
        row[i] |= 1 << k;
        col[j] |= 1 << k;
        block[i / 3 * 3 + j / 3] |= 1 << k;
    }
}