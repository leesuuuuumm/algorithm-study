import java.util.*;

class Solution {
    private  ArrayList<int[]> list;
    public int[][] solution(int n) {

        list = new ArrayList<>();
        dfs(n,1,3,2);


        int[][] answer = new int[list.size()][];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }

    private void dfs(int n, int start, int to, int mid) {
        if(n == 1){
            list.add(new int[]{start, to});
            return;
        }
        dfs(n-1, start, mid, to);
        list.add(new int[]{start, to});
        dfs(n-1, mid, to, start);
    }
}
