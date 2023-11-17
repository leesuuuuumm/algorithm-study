import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        String s1 = str1.toLowerCase();
        String s2 = str2.toLowerCase();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> inter = new ArrayList<>();
        List<String> union = new ArrayList<>();

        for(int i = 0; i < s1.length() - 1; i++) {
            char first = s1.charAt(i);
            char second = s1.charAt(i + 1);

            if(first >= 'a' && first <= 'z' &&
                    second >= 'a' && second <= 'z') {
                list1.add(first + "" + second);
            }
        }

        for(int i = 0; i < s2.length() - 1; i++) {
            char first = s2.charAt(i);
            char second = s2.charAt(i + 1);

            if(first >= 'a' && first <= 'z' &&
                    second >= 'a' && second <= 'z') {
                list2.add(first + "" + second);
            }
        }

        // 중복 원소 처리를 위해 두 리스트를 정렬
        // list1에 있는 원소가 list2에도 존재할 경우 교집합에 추가 후 list2에서 삭제
        // 존재 여부와 상관없이 합집합에 추가
        // list2에 남은 원소를 모두 합집합에 추가
        Collections.sort(list1);
        Collections.sort(list2);

        for (String li : list1) {
            if (list2.remove(li)) {
                inter.add(li);
            }
            union.add(li);
        }

        for (String li : list2) {
            union.add(li);
        }

        double cnt = inter.size();
        double sum = union.size();

        return list1.size() == 0 && list2.size() == 0 ? 65536 : (int) ((cnt / sum) * 65536);
    }
}