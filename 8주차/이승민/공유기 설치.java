import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    c개의 공유기를 설치해서 인접한 두 공유기 사이의 최대 거리?
    -> 인접한 두 공유기 사이의 거리가 x일 때, c개의 공유기를 설치할 수 있는가?

    O(NlogN + Nlogx)
     */
    static int n, c;
    static int[] coordinate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        coordinate = new int[n];
        for (int i = 0; i < n; i++) {
            coordinate[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coordinate);

        System.out.println(bSearch());
    }

    public static int bSearch() {
        int left = 1; // 두 공유기 사이 최소 1
        int right = 1000000000; // 최대 10억
        int res = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (calcCnt(mid) >= c) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    public static int calcCnt(int mid) {
        int cnt = 1;
        int last = coordinate[0];

        for (int i = 1; i < n; i++) {
            if (coordinate[i] - last >= mid) {
                last = coordinate[i];
                cnt++;
            }
        }

        return cnt;
    }
}
