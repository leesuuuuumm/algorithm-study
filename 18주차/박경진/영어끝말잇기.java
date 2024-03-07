import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Queue<String> q = new LinkedList<>(); //큐에 나온 단어 저장
        int w = words.length; //단어의 개수
        
        q.add(words[0]);
        for(int i = 1; i < w; i++){
            //첫번째 케이스, 끝말잊기가 아닌 경우
            String before = words[i-1]; //이전 단어
            String now = words[i]; //현재 단어
            System.out.println("words are " + before + " " + now);
            char end = before.charAt(before.length()-1); //이전 단어의 마지막 알파벳
            char start = now.charAt(0); //현재 단어의 첫번째 알파벳
            System.out.println("chars are " + end + " " +  start);
            if(end != start){ //끝말잊기가 아닌 경우
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            
            //두번째 케이스, 이미 말한 단어인 경우
            if(q.contains(now)){ //이미 말한 단어
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            
            q.add(now);
        }

        return answer;
    }
}
