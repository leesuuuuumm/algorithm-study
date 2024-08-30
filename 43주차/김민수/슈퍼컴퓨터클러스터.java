import java.io.*;
import java.util.*;

public class 슈퍼컴퓨터클러스터 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        long B = Long.parseLong(st[1]);
        st = br.readLine().split(" ");
        long[] arr = new long[N];
        long answer = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st[i]);
        }
        Arrays.sort(arr);
        long left = arr[0];
        long right = arr[N - 1] + (long) Math.sqrt(B);
        while (left <= right) {
            long mid = (left + right) / 2;
            if (calculate(arr, mid, B)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }

        }
        System.out.println(answer);
    }
    public static boolean calculate ( long[] arr, long min, long B){
        long cost = 0;
        for (long i : arr) {
            if (i < min) {
                cost += (min - i) * (min - i);
                if (cost > B) return false;
            }
        }
        return true;
    }
}

