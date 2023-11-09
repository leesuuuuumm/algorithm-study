import java.util.*;

class Solution {
    /*
    7 9 1 1 4 7 9 1 1 4

    두 개를 이어붙인 후 set을 사용해 중복을 제거하면서 부분수열 갯수 구하기
    */
    public int solution(int[] elements) {
        int[] newEle = new int[elements.length * 2];
        int s = 0;
        for (int i = 0; i < newEle.length; i++) {
            if (s >= elements.length) {
                s -= elements.length;
            }
            newEle[i] = elements[s++];
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= elements.length; i++) { // 부분수열 길이 1, 2, 3, ...
            for (int j = 0; j < elements.length; j++) {
                int total = 0;
                for (int k = j; k < j + i; k++) {
                    total += newEle[k];
                }
                set.add(total);
            }
        }

        return set.size();
    }
}