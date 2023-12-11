import java.util.*;

class Solution {
    public String solution(String number, int k) {
        // 그리디로 접근
        // number.length - k 만큼의 길이를 가진 정답 반환
        // 뒤에서 k - 1만큼 뺀 위치까지의 문자 중 가장 큰 값 선택
        // 그 다음부턴 선택한 숫자의 바로 다음 인덱스부터 이전 최대 위치 + 1까지의 문자 중 가장 큰 값을 선택해나간다.
        int start = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length() - k; i++) {
            char max = '0';
            for (int j = start; j <= i + k; j++){
                if (number.charAt(j) > max) {
                    max = number.charAt(j);
                    start = j + 1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }
}