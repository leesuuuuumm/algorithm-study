import java.util.*;

public class Solution {
    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return r == location.r && c == location.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    static Set<Location> set = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
    }

    public static int solution(int m, int n, String[] board) {
        int answer = 0;

        char[][] map = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i] = board[i].toCharArray();
            }
        }

        while (true) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 2 x 2 사각형 탐색 범위에서 모든 char가 일치하는지 체크하는 메서드
                    match(i, j, map);
                }
            }

            // set이 비어있으면 루프 탈출
            if (set.isEmpty()) break;

            // set에 있는 위치를 전부 '0'으로 변경
            for (Location currentLocation : set) {
                map[currentLocation.r][currentLocation.c] = '0';
            }

            // 아래로 내리는 작업
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 만약 현재 위치 '0'이면 위에 있는 값을 떨어뜨린다.
                    if (map[i][j] == '0') {
                        fall(i, j, map);
                    }
                }
            }

            set.clear();
        }

        // 비어있는 부분을 체크하면 답임
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '0') answer++;
            }
        }

        return answer;
    }

    private static void match(int m, int n, char[][] map) {
        // 2 x 2 사각형 탐색 범위
        Location[] loc = new Location[4];
        loc[0] = new Location(m, n);
        loc[1] = new Location(m, n + 1);
        loc[2] = new Location(m + 1, n);
        loc[3] = new Location(m + 1, n + 1);

        int height = map.length;
        int width = map[0].length;

        // 가장 오른쪽 아래의 위치가 배열의 범위를 벗어나면 바로 리턴
        if (loc[3].r < 0 || loc[3].r >= height || loc[3].c < 0 || loc[3].c >= width) return;

        char initCharacter = map[loc[0].r][loc[0].c];

        for (int i = 0; i < 4; i++) {
            // 0인 값이 있다면 바로 return
            if (map[loc[i].r][loc[i].c] == '0') return;
            // 만약 모든 값이 다 같지 않다면 return;
            if (initCharacter != map[loc[i].r][loc[i].c]) return;
        }

        Collections.addAll(set, loc);
    }

    private static void fall(int r, int c, char[][] map) {
        int cr = r;
        while (--cr >= 0) {
            map[cr + 1][c] = map[cr][c];
            map[cr][c] = '0';
        }
    }
}