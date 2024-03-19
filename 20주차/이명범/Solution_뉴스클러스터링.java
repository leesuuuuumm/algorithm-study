import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(solution("handshake", "shake hands"));
    }

    public static int solution(String str1, String str2) {
        int answer = 0;

        // 대소문자 구분을 하지 않으므로 소문자로 통일한다.
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 2글자씩 나눈 문자열을 저장할 List Collection
        List<String> stringSet1 = new ArrayList<>();
        List<String> stringSet2 = new ArrayList<>();
        // 문자열을 2글자씩 나누는 메서드
        addSet(str1, stringSet1);
        addSet(str2, stringSet2);

        // 합집합
        List<String> union = new ArrayList<>();
        // 리스트에 있는 문자열 전부 저장
        union.addAll(stringSet1);
        union.addAll(stringSet2);

        // 교집합
        List<String> intersect = new ArrayList<>();

        // stringSet2의 문자열을 stringSet1과 비교
        for (int i = 0; i < stringSet2.size(); i++) {
            String cur = stringSet2.get(i);
            // 만약 cur 문자열이 stringSet1에 있으면
            if (stringSet1.contains(cur)) {
                stringSet1.remove(cur);
                intersect.add(cur);
            }
        }


        // 전체 집합과 교집합을 빼면 합집합.
        for (String s : intersect) {
            union.remove(s);
        }

        if (union.size() == 0) answer = 65536;
        else answer = (int) Math.floor(((double) intersect.size() / union.size() * 65536));

        return answer;
    }

    private static void addSet(String str, List<String> set) {
        int length = str.length() - 1;

        for (int i = 0; i < length; i++) {
            // 둘 다 알파벳인 경우에만 실행
            if (isAlphabet(str.charAt(i)) && isAlphabet(str.charAt(i + 1))) {
                // 길이 2의 문자열로 나누어서 집합에 저장
                String s = "" + str.charAt(i) + str.charAt(i + 1);
                set.add(s);
            }
        }
    }

    private static boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}