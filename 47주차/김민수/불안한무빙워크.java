import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 불안한무빙워크 {
    static int n, k;
    static int[] map;
    static boolean[] person;
    static ArrayList<Integer> topIdx;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[2 * n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        person = new boolean[2 * n];
        topIdx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            topIdx.add(i);
        }
        System.out.println(simulation());
    }

    public static int simulation() {
        boolean isEnd = false;
        int round = 0;

        while (!isEnd) {
            round += 1;
            //무빙워킹 회전
            rotate();
            move();
            // 가장 먼저 올라간 사람부터 한 칸 이동 (오른쪽부터 이동)
            // 만약 앞선 칸에 사람이 이미 있거나 앞선 칸의 안정성이 0인 경우에는 이동하지 않습니다.
            getZero();
            // 0번 칸에 사람이 없고 안전성이 0보다 크면 사람을 올린다.
            // 안정성 체크
            isEnd = check();
        }
        return round;
    }

    public static void rotate() {
        int addIdx;
        int startIdx = topIdx.get(0);
        if (startIdx == 0)
            addIdx = 2 * n - 1;
        else addIdx = startIdx - 1;
        topIdx.add(0, addIdx);
        topIdx.remove(topIdx.size() - 1);
        person[topIdx.get(n-1)]=false;
    }

    public static void move() {
        for (int i = n - 2; i >= 0; i--) {
            int nextIdx = topIdx.get(i + 1);
            int curIdx = topIdx.get(i);
            if (person[curIdx]) {
                if (!person[nextIdx] && map[nextIdx] > 0) {
                    person[nextIdx] = true;
                    person[curIdx] = false;
                    map[nextIdx] -= 1;
                    if (map[nextIdx] == 0)
                        count += 1;
                }
            }
            person[topIdx.get(n - 1)] = false;
        }
    }

    public static void getZero() {
        int zeroIdx = topIdx.get(0);
        if (map[zeroIdx] > 0 && !person[zeroIdx]) {
            person[zeroIdx] = true;
            map[zeroIdx] -= 1;
            if (map[zeroIdx] == 0)
                count += 1;
        }
    }

    public static boolean check() {
        if (count >= k)
            return true;
        return false;
    }
}
