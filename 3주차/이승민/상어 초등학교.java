import java.util.*;

public class Main {
    /*
    비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
    1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
    2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
     */
    static class Node implements Comparable<Node> {
        int x, y, like, empty;

        Node(int x, int y, int like, int empty) {
            this.x = x;
            this.y = y;
            this.like = like;
            this.empty = empty;
        }

        @Override
        public int compareTo(Node n) {
            if (like < n.like) {
                return n.like - like;
            } else if (like == n.like) {
                if (empty < n.empty) {
                    return n.empty - empty;
                } else if (empty == n.empty) {
                    if (x < n.x) {
                        return x - n.x;
                    } else if (x == n.x) {
                        return y - n.y;
                    }
                }
            }
            return -1;
        }
    }

    static int[][] seat;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n;
    static Map<Integer, Set<Integer>> like;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        seat = new int[n + 1][n + 1];
        like = new HashMap<>();
        for (int i = 0; i < n * n; i++) {
            pq = new PriorityQueue<>();
            int student = sc.nextInt();
            like.put(student, new HashSet<>());
            for (int j = 0; j < 4; j++) {
                int tmp = sc.nextInt();
                like.get(student).add(tmp);
            }

            check(student); // 규칙 체크하면서 자리 배정
        }

        // 만족도 계산 및 출력
        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int cnt = calcLike(i, j, seat[i][j]);

                switch (cnt) {
                    case 0:
                        break;
                    case 1:
                        ans += 1;
                        break;
                    case 2:
                        ans += 10;
                        break;
                    case 3:
                        ans += 100;
                        break;
                    case 4:
                        ans += 1000;
                        break;
                }
            }
        }

        System.out.println(ans);
    }

    public static void check(int student) {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 이미 자리에 사람이 있으면 skip
                if (seat[i][j] != 0) {
                    continue;
                }

                // 자리 정보 계산
                Node node = new Node(i, j, calcLike(i, j, student), calcEmpty(i, j));

                pq.offer(node);
            }
        }

        Node deter = pq.poll();
        seat[deter.x][deter.y] = student;
    }

    public static int calcLike(int x, int y, int student) {
        int cnt = 0;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (1 <= nx && nx <= n && 1 <= ny && ny <= n) {
                if (like.get(student).contains(seat[nx][ny])) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static int calcEmpty(int x, int y) {
        int cnt = 0;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (1 <= nx && nx <= n && 1 <= ny && ny <= n) {
                if (seat[nx][ny] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}