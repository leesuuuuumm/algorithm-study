import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Character> vowels;
    static StringBuilder sb = new StringBuilder();
    static Set<String> set = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[] input = new char[C];

        st = new StringTokenizer(br.readLine(), " ");

        int flag = 0;

        for (int i = 0; i < C; i++) {
            input[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(input);

        for (int i = 0; i < C; i++) {
            for (Character vow : vowels) {
                if (input[i] == vow) flag = flag | 1 << i;
            }
        }

        // 입력 끝
        combination(0, 0, input, new char[L]);

        System.out.println(sb);
    }

    private static void combination(int cnt, int start, char[] input, char[] result) {
        int L = result.length;

        if (cnt == L) {
            // 모음이 0개거나 자음의 개수가 2개보다 적다면 리턴
            int vowsNum = 0;

            for (int i = 0; i < L; i++) {
                for (Character vow : vowels) {
                    if (result[i] == vow) vowsNum++;
                }
            }

            if(vowsNum == 0 || vowsNum > L - 2) return;

            // 스트링 빌더로 괄호와 콤마 제거
            for (int i = 0; i < L; i++) {
                sb.append(result[i]);
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < input.length; i++) {
            result[cnt] = input[i];
            combination(cnt + 1, i + 1, input, result);
        }
    }
}
