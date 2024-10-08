// [CDT] 돌아가는 팔각의자

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static String[] chairs = new String[4];
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        // 각 의자의 상태 입력
        for (int i = 0; i < 4; i++) {
            chairs[i] = br.readLine();
        }

        // 회전 횟수 입력
        int k = Integer.parseInt(br.readLine());

        // 회전 요청 처리
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1;  // 의자 번호 (0-based)
            int d = Integer.parseInt(st.nextToken());  // 방향 (1: 시계, -1: 반시계)
            rotateChairs(n, d);
        }

        // 결과 계산 (12시 방향에 남쪽지방 사람 착석 여부 계산)
        answer = (chairs[0].charAt(0) - '0') * 1 + 
                 (chairs[1].charAt(0) - '0') * 2 + 
                 (chairs[2].charAt(0) - '0') * 4 + 
                 (chairs[3].charAt(0) - '0') * 8;

        System.out.println(answer);
    }

    static void rotateChairs(int n, int d) {
        boolean[] shouldRotate = new boolean[4];
        int[] directions = new int[4];
        
        // 회전할 의자의 방향 설정
        shouldRotate[n] = true;
        directions[n] = d;

        // 왼쪽으로 전파 (n-1, n-2, ...)
        for (int i = n; i > 0; i--) {
            if (chairs[i].charAt(6) != chairs[i - 1].charAt(2)) {
                shouldRotate[i - 1] = true;
                directions[i - 1] = -directions[i];  // 반대 방향으로 회전
            } else {
                break;
            }
        }

        // 오른쪽으로 전파 (n+1, n+2, ...)
        for (int i = n; i < 3; i++) {
            if (chairs[i].charAt(2) != chairs[i + 1].charAt(6)) {
                shouldRotate[i + 1] = true;
                directions[i + 1] = -directions[i];  // 반대 방향으로 회전
            } else {
                break;
            }
        }

        // 회전 적용
        for (int i = 0; i < 4; i++) {
            if (shouldRotate[i]) {
                if (directions[i] == 1) {
                    chairs[i] = rotateClockwise(chairs[i]);
                } else {
                    chairs[i] = rotateCounterClockwise(chairs[i]);
                }
            }
        }
    }

    // 시계 방향 회전
    static String rotateClockwise(String chair) {
        return chair.charAt(7) + chair.substring(0, 7);
    }

    // 반시계 방향 회전
    static String rotateCounterClockwise(String chair) {
        return chair.substring(1) + chair.charAt(0);
    }
}