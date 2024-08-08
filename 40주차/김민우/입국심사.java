import java.io.*;
import java.util.*;

// 심사 시간을 구하는 법
// 특정 시간 T가 주어졌을 때 그 시간 동안 처리할 수 있는 사람의 수를 계산할 수 있다.
// 각 심사대에서 T 시간동안 처리할 수 있는 사람의 수 : T
// 모든 심사대에서 처리할 수 있는 사람의 수를 합하면 총 처리 가능한 사람의 수가 된다.
// ex ) n = 3 , m = 10, 7분, 10분, 5분
// (20분 가정)
// 1번 심사대 = 20 / 7 = 2.85 => 2명 가능
// 2번 심사대 = 20 / 10 = 2 -> 2명 가능
// 3번 심사대 = 20 / 5 -> 4명 가능
// 20분 동안 총 8명 처리가 가능하다.
public class 입국심사 {

    static int n;
    static long m;
    static int[] arr;
    static long MAX_VALUE;
    static long result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 입국 심사 수
        m = Long.parseLong(st.nextToken()); // 사람 수

        arr = new int[n];
        int max = 0; // 심사가 가장 오래걸리는 사람의 소요 시간
        for (int i = 0; i < n; i ++) {
            arr[i] = Integer.parseInt(br.readLine()); // 배열에 소요 시간 삽입 ex) {3, 8, 3, 6, 9, 2, 4}
            max = Math.max(max, arr[i]);
        }

        MAX_VALUE = m * max; // 사람 수 * 최대 시간 = 가장 오래걸리는 시간
        result = MAX_VALUE;
        Arrays.sort(arr); // 이분 탐색을 위해 정렬

        searchMinTime();
        System.out.println(result);

    }

    // 시간 기준 이분탐색
    static void searchMinTime() {
        long lo = 0;
        long hi = MAX_VALUE;

        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            long totalPerson = 0; // 해당 시간에 각 심사대에서 맡는 사람 수의 합
            for (int i : arr) {
                long cntPerson = mid / i;

                // 정렬이기에, 현재 심사대의 시간이 검사중인 시간보다 크거나, 현재까지 사람 수의 합이 총 사람 수보다 크면 탈출
                if (totalPerson >= m || i > mid) {
                    break;
                }

                totalPerson += cntPerson;
            }
            // totalPerson이 총 사람 수보다 크거나 같다면 더 짧은 시간에 완료될 수 있음
            if (totalPerson >= m) {
                hi = mid - 1;
                result = Math.min(result, mid);
            } else {
                lo = mid + 1;
            }
        }

    }
}