import java.io.*;
import java.util.*;

public class Main {
    /*
    1초, 256MB
    n=3*2^k

    i = 3의 문자열은 [k = 1일때의 i = 0 의 문자열] + [공백 1] + [k = 1일때의 i = 0 의 문자열]
    i = 4의 문자열은 [k = 1일때의 i = 1 의 문자열] + [공백 1] + [k = 1일때의 i = 1 의 문자열]
    i = 5의 문자열은 [k = 1일때의 i = 2 의 문자열] + [공백 1] + [k = 1일때의 i = 2 의 문자열]
    i = 0~2 문자열은 양 옆에 list의 크기만큼 공백 추가
    */
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] map = new String[n];
        map[0] = ("  *  ");
        map[1] = (" * * ");
        map[2] = ("*****");

        for (int k = 1; 3 * (int) Math.pow(2, k) <= n; k++) {
            func(map, k);
        }

        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(map[i] + "\n");
        }
        System.out.println(sb);
    }

    public static void func(String[] map, int k) {
        int bottom = 3 * (int) Math.pow(2, k);
        int middle = bottom / 2;

        // i = middle ~ bottom 이전 문자열 복사
        for (int i = middle; i < bottom; i++) {
            sb = new StringBuilder();
            sb.append(map[i - middle]);
            sb.append(" ");
            sb.append(map[i - middle]);

            map[i] = sb.toString();
        }

        // i = 0 ~ middle 앞뒤 공백 추가
        sb = new StringBuilder();
        for (int i = 0; i < middle; i++) {
            sb.append(" ");
        }
        for (int i = 0; i < middle; i++) {
            map[i] = sb.toString() + map[i] + sb.toString();
        }
    }
}
