import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    static class User {
        String id;
        String name;

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Log {
        String action;
        User user;

        public Log(String action, User user) {
            this.action = action;
            this.user = user;
        }

        @Override
        public String toString() {
            if (action.equals("Enter")) {
                return user.name + "님이 들어왔습니다.";
            } else if (action.equals("Leave")) {
                return user.name + "님이 나갔습니다.";
            }

            return "error";
        }
    }

    static Map<String, User> userdb = new HashMap<>();

    public String[] solution(String[] record) {
        List<Log> logList = new ArrayList<>();

        for (String s : record) {
            // 0 : action, 1 : userID, 2: username
            String[] r = s.split(" ");

            // 만약 user DB에 아이디가 없다면 db에 넣는다.
            if (!userdb.containsKey(r[1])) userdb.put(r[1], new User(r[1], r[2]));

            // action이 Leave일 때를 제외하고는 이름을 바꿔줌
            if (!r[0].equals("Leave")) userdb.get(r[1]).name = r[2];

            if (!r[0].equals("Change")) {
                logList.add(new Log(r[0], userdb.get(r[1])));
            }
        }

        // logList를 스트림을 이용해 Log의 toString 리스트로 변환
        List<String> answerlist = logList.stream().map(Log::toString).collect(Collectors.toList());

        return answerlist.toArray(new String[answerlist.size()]);
    }
}