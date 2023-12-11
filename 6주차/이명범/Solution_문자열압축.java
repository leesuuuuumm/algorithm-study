import java.util.ArrayList;
import java.util.List;

public class Solution_문자열압축 {
    public static void main(String[] args) {
        System.out.println(solution("a"));
    }

    public static int solution(String s) {
        int min = Integer.MAX_VALUE;
        // i : 버퍼의 길이
        for (int i = 1; i < s.length() / 2 + 1; i++) {
            List<String> list = new ArrayList<>();

            int index = 0;
            // index + i가 s.length()보다 크면 더이상 문자열을 자를 수 없음
            while (index + i <= s.length()) {
                list.add(s.substring(index, index + i));
                index += i;
            }
            list.add(s.substring(index));

            String temp = pressure(list);

            min = Math.min(min, temp.length());
        }

        if (s.length() == 1) return 1;
        return min;
    }

    private static String pressure(List<String> list) {
        StringBuilder sb = new StringBuilder();

        int count = 0;
        String prev = "";
        for (String cur : list) {
            // 현재 스트링이 이전 스트링과 같다면
            if (cur.equals(prev)) {
                count++;
            } else {
                if (count == 0 || count == 1) sb.append(prev);
                else sb.append(count + prev);

                count = 1;
            }
            prev = cur;
        }
        if (count == 0 || count == 1) sb.append(prev);
        else sb.append(count + prev);


        String temp = sb.toString();
        return temp;
    }
}