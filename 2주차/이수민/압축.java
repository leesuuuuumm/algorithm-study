import java.util.*;
class Solution {
    public ArrayList<Integer> solution(String msg) {
        String[] alph = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
				"T", "U", "V", "W", "X", "Y", "Z" };
        ArrayList<Integer> answer = new ArrayList<>();
		ArrayList<String> list = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();

		int j;
		for (j = 0; j < 26; j++) {
           map.put(alph[j], j+1);
		}
		String[] s = msg.split("");
		for (int i = 0; i < msg.length(); i++) {
			list.add(s[i]);
		}

		StringBuilder now = new StringBuilder();
		int idx = -1;
		now.append(list.get(++idx));
		String prev = "";
		while (true) {
			if (map.containsKey(now.toString())) {
				prev = now.toString();
				if (idx == list.size() - 1) {
					if(map.containsKey(now.toString())) {
					answer.add(map.get(now.toString()));
					}
					break;
				}
				now.append(list.get(++idx));
				// System.out.println(now.toString());
			} else {

				map.put(now.toString(), ++j);
				answer.add(map.get(prev));
				now.setLength(0);
				now.append(list.get(idx));
			}
		}

		return answer;
    }
}
