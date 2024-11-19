// [BOJ] 로봇 시뮬레이션

import java.util.*;

public class RobotSimulation {
    static int[][] board;
    static int[][] NESW_list = {
        {-1, 0}, // N
        {0, 1},  // E
        {1, 0},  // S
        {0, -1}  // W
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        scanner.nextLine();

        board = new int[B][A];
        Map<Character, Integer> NESW_dic = Map.of(
            'N', 0,
            'E', 1,
            'S', 2,
            'W', 3
        );

        // 로봇 정보 저장
        List<int[]> robots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] input = scanner.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            char d = input[2].charAt(0);

            int r = B - y;
            int c = x - 1;
            robots.add(new int[]{r, c, NESW_dic.get(d)});
            board[r][c] = i + 1;
        }

        // 명령 실행
        boolean flag = false;
        for (int i = 0; i < M; i++) {
            String[] command = scanner.nextLine().split(" ");
            int robot = Integer.parseInt(command[0]);
            char instruction = command[1].charAt(0);
            int loop = Integer.parseInt(command[2]);

            int[] currentRobot = robots.get(robot - 1);
            int r = currentRobot[0];
            int c = currentRobot[1];
            int d = currentRobot[2];

            if (instruction == 'L' || instruction == 'R') {
                int new_d = (d + loop) % 4;
                if (loop % 2 != 0 && instruction == 'L') {
                    new_d = (new_d + 2) % 4;
                }
                robots.set(robot - 1, new int[]{r, c, new_d});
            } else {
                int dr = NESW_list[d][0];
                int dc = NESW_list[d][1];

                for (int j = 1; j <= loop; j++) {
                    if (0 <= r + dr && r + dr < B && 0 <= c + dc && c + dc < A) {
                        if (board[r + dr][c + dc] != 0) {
                            flag = true;
                            System.out.println("Robot " + board[r][c] + " crashes into robot " + board[r + dr][c + dc]);
                            break;
                        } else {
                            board[r][c] = 0;
                            r += dr;
                            c += dc;
                            board[r][c] = robot;
                            robots.set(robot - 1, new int[]{r, c, d});
                        }
                    } else {
                        flag = true;
                        System.out.println("Robot " + board[r][c] + " crashes into the wall");
                        break;
                    }
                }
            }
            if (flag) break;
        }

        if (!flag) {
            System.out.println("OK");
        }
    }
}