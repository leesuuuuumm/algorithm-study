import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    m개의 휴게소를 지었을 때 휴게소가 없는 구간의 최댓값의 최솟값?
    -> 휴게소가 없는 구간의 길이가 x일 때 m개의 휴게소를 지을 수 있는가?

    휴게소가 기존 휴게소와 겹치지 않는지 확인, 세우려는 위치가 고속도로의 끝이 아닌지 확인

    O(NlogL)
     */
    static int n, m, l;
    static int[] pos; //
    static boolean[] isExist; // 해당 위치에 기존 휴게소 존재 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        pos = new int[n + 2]; // 처음과 끝 위치 추가
        isExist = new boolean[l];

        pos[0] = 0;
        pos[n + 1] = l;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pos); // 휴게소 위치 오름차순 정렬

        System.out.println(bSearch());
    }

    public static int bSearch() {
        int left = 1; // 고속도로 양 끝은 안되므로 제외
        int right = l - 1;
        int res = -1; // 정답

        while (left <= right) {
            int mid = (left + right) / 2; // 휴게소 간격

            if (isValid(mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    public static boolean isValid(int mid) { // mid 간격에서 세울 수 있는 휴게소 갯수가 m이하면 return true
        int cnt = 0; // 휴게소 갯수

        for (int i = 0; i < pos.length - 1; i++) {
            cnt += (pos[i + 1] - pos[i] - 1) / mid;
        }

        if (cnt <= m) {
            return true;
        }

        return false;
    }
}
