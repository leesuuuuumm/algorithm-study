import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        // record 순회하면서 각 문자열 확인 후 hashmap에 아이디, 닉네임 저장
        for (String re : record) {
            String[] r = re.split(" ");
            if (r[0].equals("Enter")) {
                map.put(r[1], r[2]);
            } else if (r[0].equals("Change")) {
                map.put(r[1], r[2]);
            }
        }
        // 닉네임 업데이트가 끝났으면 record 순회하면서 change는 무시, enter과 leave에 유저아이디를 통해 닉네임 조회 후 문장 생성, 정답 배열에 추가
        List<String> list = new ArrayList<>();
        for (String re : record) {
            String[] r = re.split(" ");
            if (r[0].equals("Enter")) {
                list.add(map.get(r[1]) + "님이 들어왔습니다.");
            } else if (r[0].equals("Leave")) {
                list.add(map.get(r[1]) + "님이 나갔습니다.");
            }
        }

        return list.toArray(new String[0]);
    }
}