import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Character> essentials = List.of('a', 'n', 't', 'i', 'c');

    static int N, K, result;
    static String[] words;

    static int[] letters;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        // a, n, t, i, c는 반드시 포함해야 함
        K = Integer.parseInt(st.nextToken()) - 5;
        if (K < 0) {
            System.out.println(0);
            return;
        }
        words = new String[N];
        letters = new int[N];
        for (int i = 0; i < N; i++) {
            words[i] = init(br.readLine());
            for (int j = 0; j < words[i].length(); j++) {
                letters[i] = letters[i] | 1 << (words[i].charAt(j) - 'a');
            }
        }

        combination(0, 0, 0);

        System.out.println(result);
    }

    private static void combination(int cnt, int start, int key) {
        if (cnt == K) {
            int count = 0;

            for (int letter : letters) {
                if ((key & letter) == letter) count++;
            }

            result = Math.max(result, count);

            return;
        }

        for (int i = start; i < 26; i++) {
            if (isEssential((char) (i + 'a'))) continue;

            combination(cnt + 1, i + 1, key | 1 << i);
        }
    }

    private static String init(String origin) {
        return extract(origin.substring(4, origin.length() - 4));
    }

    private static String extract(String origin) {
        StringBuilder extracted = new StringBuilder();

        for (int i = 0; i < origin.length(); i++) {
            char ch = origin.charAt(i);
            if (!isEssential(ch)) extracted.append(ch);
        }

        return extracted.toString();
    }

    private static boolean isEssential(char ch) {
        for (char essential : essentials) {
            if (essential == ch) return true;
        }
        return false;
    }
}