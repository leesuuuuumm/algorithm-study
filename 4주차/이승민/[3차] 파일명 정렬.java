import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        String s, head;
        int number;

        Node() {

        }

        Node(String s, String head, int number) {
            this.s = s;
            this.head = head;
            this.number = number;
        }

        @Override
        public int compareTo(Node other) {
            if (this.head.compareTo(other.head) == 0) {
                return this.number - other.number;
            } else {
                return this.head.compareTo(other.head);
            }
        }
    }
    public String[] solution(String[] files) {
        List<Node> list = new ArrayList<>();
        // files를 순회하면서 각 문자열을 head(소문자로 변환)와 number(숫자로 변환)로 구분한다.(새로운 객체에 문자열과 함께 담는다.)
        for (String f : files) {
            Node n = new Node();
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            // head
            while (idx <= f.length() - 1 && !Character.isDigit(f.charAt(idx))) {
                sb.append(f.charAt(idx));
                idx++;
            }
            System.out.println(sb);
            n.head = sb.toString().toLowerCase();
            sb.setLength(0);

            // number
            while(idx <= f.length() - 1 && Character.isDigit(f.charAt(idx))) {
                if (sb.length() >= 5) {
                    break;
                }
                sb.append(f.charAt(idx));
                idx++;
            }
            System.out.println(sb);
            n.number = Integer.parseInt(sb.toString());
            n.s = f;
            list.add(n);
            continue;
        }
        // 새로운 객체의 compareTo를 overriding한다.(주어진 기준에 따라 정렬 기준 변경)
        // 새로운 객체로 이루어진 배열을 정렬한다.
        Collections.sort(list);
        // 정렬된 배열을 순회하면서 안에 담긴 문자열만 정답 배열에 담아서 반환한다.
        List<String> ans = new ArrayList<>();
        for (Node n : list) {
            ans.add(n.s);
        }
        return ans.toArray(new String[0]);
    }
}