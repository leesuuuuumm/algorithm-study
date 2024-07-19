import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 현재 인덱스 위치
    static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 검증용 스택
        Stack<Character> stack = new Stack<>();
        // 저장된 스트링
        String bracketBox = br.readLine();
        // 청크의 개수
        int chunk = 0;

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');

        for (int i = 0; i < bracketBox.length(); i++) {
            char curBracket = bracketBox.charAt(i);
            if (curBracket == '(' || curBracket == '[') {
                stack.push(curBracket);
            } else {
                // 스택이 비어있으면
                if(stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }

                if(stack.pop() != map.get(curBracket)){
                    System.out.println(0);
                    return;
                }
            }
            if(stack.isEmpty()) {
                chunk++;
            }
        }
        // 청크 단위 결과값 저장
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < chunk; i++) {
            list.add(calculateChunk(bracketBox));
        }

        System.out.println(list.stream().mapToInt(i -> i).sum());
    }

    private static int calculateChunk(String bracketBox) {
        char curBracket = bracketBox.charAt(index++);
        if (curBracket == '(' || curBracket == '[') {
            return calc(bracketBox);
        } else {
            return 0;
        }
    }

    private static int calc(String bracketBox) {
        // 괄호 안의 값을 저장할 리스트
        List<Integer> list = new ArrayList<>();

        while (true) {
            // 현재 인덱스의 값 저장 (초기값 0부터 1씩 증가)
            char curBracket = bracketBox.charAt(index++);

            if (curBracket == ')' || curBracket == ']') {
                if (!list.isEmpty()) {
                    return list.stream().mapToInt(i -> i).sum() * getNumberByBracket(curBracket);
                } else {
                    return getNumberByBracket(curBracket);
                }
            }
            list.add(calc(bracketBox));
        }
    }

    private static int getNumberByBracket(char c) {
        if (c == ')') return 2;
        if (c == ']') return 3;
        else return 0;
    }
}
