import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    왼쪽 포인터를 이동시킨다.(제외된 값은 합에서 뺀다.)
    오른쪽 포인터를 라이언 인형이 K개보다 적게 있는 동안 이동시킨다.(추가된 값은 합에 더한다.)
    ans 최솟값 갱신

    O(N)
     */
    static int n, k;
    static int[] dolls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dolls = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
        }

        calcSet();
    }

    static void calcSet() {
        int ans = Integer.MAX_VALUE; // 정답

        int right = 0; // 오른쪽 포인터
        int length = 1; // 현재 집합 크기
        int cnt = 0; // 현재 라이언 인형 갯수
        boolean isExist = false; // 조건을 만족하는 집합 여부
        if (dolls[0] == 1) {
            cnt++;
        }

        for (int left = 0; left < n; left++) {
            while (right < n - 1 && cnt < k) {
                right++;
                if (dolls[right] == 1) {
                    cnt++;
                }
                length++;
            }

            if (cnt >= k) {
                isExist = true;
                ans = Math.min(ans, length);
            }

            length--;
            if (dolls[left] == 1) {
                cnt--;
            }
        }

        if (!isExist) {
            System.out.println(-1);
            return;
        }

        System.out.println(ans);
    }