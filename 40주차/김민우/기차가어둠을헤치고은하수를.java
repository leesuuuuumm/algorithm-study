package 김민우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 기차가어둠을헤치고은하수를 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 기차의 수
        int m = Integer.parseInt(st.nextToken()); // 명령의 수
        boolean[][] train = new boolean[n + 1][21]; // 1based

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine()); // 명령이 1과 2일 땐 x도 입력
            int cmd = Integer.parseInt(st.nextToken());
            int trainNum = Integer.parseInt(st.nextToken());
            int x = 0;
            if (cmd == 1) {
                x = Integer.parseInt(st.nextToken());
                // 1 : i 번째 좌석에 사람을 태운다. 사람이 있다면 동작하지 않는다.
                train[trainNum][x] = true;

            }

            // 2 명령어 수행
            else if (cmd == 2) {
                x = Integer.parseInt(st.nextToken());
                // 2 : i 번째 기차 x의 좌석에서 내린다
                train[trainNum][x] = false;
            }

            else if (cmd == 3) {
                // i 번째 기차의 승객들을 모두 한 칸 뒤로 이동시킨다.
                for (int j = 19; j >= 1; j--) {
                    train[trainNum][j + 1] = train[trainNum][j];
                }
                train[trainNum][1] = false; // 앞자리는 무조건 false
            }
            else {
                for (int j = 2; j <= 20; j++) {
                    train[trainNum][j - 1] = train[trainNum][j];
                }
                train[trainNum][20] = false;
            }
        }
        Set<String> uniqueStates = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder state = new StringBuilder();
            for (int j = 1; j <= 20; j++) {
                state.append(train[i][j] ? '1' : '0');
            }
            uniqueStates.add(state.toString());
        }

        System.out.println(uniqueStates.size());
    }
}