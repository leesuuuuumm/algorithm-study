class Solution {
    boolean[] visit;
    int answer;
    
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        visit = new boolean[words.length]; //각 단어의 선택 여부
        
        dfs(begin, target, words, 0); //재귀를 돌며 한 글자만 다른 단어들을 방문
        
        return answer;
    }
    
    public void dfs(String begin, String target, String[] words, int cnt){
        if(begin.equals(target)){
            answer = cnt;
            return;
        }
        
        for(int i = 0; i < words.length; i++){
            if(visit[i]){ //방문한 단어이면 패스
                continue;
            }
            
            int c = 0; //같은 char이 몇개인지 
            
            for(int j = 0; j < begin.length(); j++){
                if(begin.charAt(j) == words[i].charAt(j)){ //같은 위치의 char이 같으면
                    c++;
                }
            }
            
            if(c == begin.length() - 1){ //하나의 char만 빼고 모두 같으면
                visit[i] = true; //방문하기
                dfs(words[i], target, words, cnt+1);
                visit[i] = false; //다음을 위해 해제
            }
        }
    }
}
