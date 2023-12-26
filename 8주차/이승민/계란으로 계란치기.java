import java.io.*;
import java.util.*;

public class Main {
    /*
    2초, 512MB
    상대 계란의 무게만큼 내구도 감소
     */
    static int n;
    static int[] weight;
    static int[] shield;
    static int cnt;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        weight = new int[n];
        shield = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            shield[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        func(0);
        System.out.println(max);
    }

    public static void func(int num) throws Exception { // num: 현재 들고 있는 계란의 번호
        if (num == n) { // 마지막 계란을 들었을 때
            max = Math.max(max, cnt); // 최댓값 갱신
            return;
        }
        if (shield[num] <= 0 || cnt == n - 1) { // 들고 있는 계란이 깨지거나, 다른 계란이 모두 깨진 상태면 넘어가고 다음 계란을 든다.
            func(num + 1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (num == i || shield[i] <= 0) { // 자기 자신을 때릴 수 없음, 때릴 계란이 이미 부서졌을 때 넘어가기
                continue;
            }

            shield[i] -= weight[num];
            shield[num] -= weight[i];
            if (shield[num] <= 0) {
                cnt++;
            }
            if (shield[i] <= 0) {
                cnt++;
            }
            func(num + 1); // 오른쪽 계란 들기
            if (shield[num] <= 0) {
                cnt--;
            }
            if (shield[i] <= 0) {
                cnt--;
            }
            shield[i] += weight[num];
            shield[num] += weight[i];
        }
    }
}
