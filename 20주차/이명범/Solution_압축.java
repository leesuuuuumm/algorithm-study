import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int[] solution(String msg) {

        // 사전 등록 작업
        Map<String, Integer> dictionary = new HashMap<>();

        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            dictionary.put(temp.append((char) ('A' + i)).toString(), i + 1);
            temp.setLength(0);
        }

        // 27번부터 사전 인덱스 시작
        int dictionaryIndex = 27;
        // 문자열을 검색할 포인터
        int pointer = 0;

        List<Integer> answerList = new ArrayList<>();

        // 포인터가 참조 범위를 벗어나면 loop 탈출
        while (true) {
            if (pointer == msg.length()) {
                answerList.add(dictionary.get(temp.toString()));
                break;
            }

            // 현재 방문한 위치의 char를 StringBuilder에 넣는다.
            temp.append(msg.charAt(pointer));

            // 그 문자열이 사전에 있는지 체크한다.
            if (dictionary.containsKey(temp.toString())) {
                // 사전에 있으면 다음 인덱스를 탐색한다.
                pointer++;
            } else {
                // 그렇지 않으면 현재 StringBuilder에 있는 문자열을 딕셔너리에 저장
                dictionary.put(temp.toString(), dictionaryIndex++);
                // 저장 후 StringBuilder를 이전 포인터에 있던 상태로 길이 조정
                temp.setLength(temp.length() - 1);
                // 그 후 딕셔너리에 있는 value로 저장.
                answerList.add(dictionary.get(temp.toString()));
                // 마지막으로 StringBuilder 초기화
                temp.setLength(0);
            }
        }

        int size = answerList.size();

        int[] answer = new int[size];

        for (int i = 0; i < size; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}