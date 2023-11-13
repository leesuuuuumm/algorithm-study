import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    먼저 오름차순으로 정렬한다.
    for문을 돌면서 다른 수 두 개의 합으로 나타낼 수를 고른다.
    해당 for문 내부에서 투 포인터를 수행한다.
    O(N^2)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int good = 0; // 정답
        int sum; // 두 수의 합
        for (int i = 0; i < n; i++) {
            int target = arr[i]; // 다른 두 수의 합으로 나타낼 수
            int l = 0; // 왼쪽 포인터
            int r = n - 1; // 오른쪽 포인터

            while (l < r) {
                if (l == i) {
                    l++;
                } else if (r == i) {
                    r--;
                } else {
                    sum = arr[l] + arr[r];

                    if (sum < target) {
                        l++;
                    } else if (sum > target) {
                        r--;
                    } else {
                        good++;
                        break;
                    }
                }
            }
        }

        System.out.println(good);
    }
}