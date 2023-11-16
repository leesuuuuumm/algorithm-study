import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    /*
    처음 주어진 상태가 0초, 폭탄 3 표시
    아무것도 안하는 상태 1초, 폭탄 1씩 감소
    나머지 칸에 모두 폭탄 설치 2초, 기존 폭탄 1씩 감소
    3초가 지난 폭탄들 폭발 3초, 남아있는 폭탄도 1씩 감소
    나머지 칸에 모두 폭탄 설치 4초, 기존 폭탄 1씩 감소
    3초가 지난 폭탄들 폭발 5초, 남아있는 폭탄도 1씩 감소

    폭발시킬 폭탄이 있을 때 해당 폭탄들을 큐나 리스트에 넣어두고 차례대로 터뜨리는 것이 핵심
    그렇지 않고 그냥 터뜨리면 인접한 폭탄이 사라지기 때문

    O(RCN)
     */
    static int r, c, n;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        q = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < c; j++) {
                if (tmp.charAt(j) == '.') {
                    map[i][j] = -1; // 폭탄이 없는 칸은 -1로 표시
                } else {
                    map[i][j] = 3;
                }
            }
        }

        int second = 0; // 경과한 시간

        second++;
        timeDesc(); // 1초간 아무것도 안함
        if (second == n) {
            printMap();
            return;
        }

        while (true) {
            second++;
            timeDesc(); // 폭탄 설치 전에 시간 감소 적용
            makeBomb(); // 이후 폭탄 설치
            if (second == n) {
                printMap();
                break;
            }

            second++;
            timeDesc();
            if (second == n) {
                printMap();
                break;
            }
        }
    }

    static void timeDesc() { // 시간 감소시키기 및 0초가 된 폭탄은 폭발시키기
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    map[i][j]--;
                }
            }
        }

        // 남은 시간이 0초가 된 폭탄이 있는지 확인 후 폭발시키기
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 0) {
                    q.offer(new Node(i, j));
                }
            }
        }

        burst();
    }

    static void burst() { // 0초가 된 폭탄들을 모두 폭발시키기
        while (!q.isEmpty()) {
            Node cur = q.poll();

            map[cur.x][cur.y] = -1;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    continue;
                }

                map[nx][ny] = -1;
            }
        }
    }

    static void makeBomb() { // 빈 칸에 폭탄 설치
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = 3;
                }
            }
        }
    }

    static void printMap() { // 현재 격자판 상태 출력
        char[][] ans = new char[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != -1) {
                    ans[i][j] = 'O';
                } else {
                    ans[i][j] = '.';
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}