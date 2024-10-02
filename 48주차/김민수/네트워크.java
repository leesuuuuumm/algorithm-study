import java.util.*;
import java.io.*;

class 네트워크 {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int answer;
    public int solution(int n, int[][] computers) {
        graph=new ArrayList[n];
        visited=new boolean[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j)
                    continue;
                if(computers[i][j]==1){
                    graph[i].add(j);
                }
            }
        }
        for(int i=0;i<n;i++){
            bfs(i);
        }


        return answer;
    }

    public static void bfs(int startIdx){
        Queue<Integer> que=new ArrayDeque<>();
        if(!visited[startIdx]){
            answer+=1;
            que.add(startIdx);
            visited[startIdx]=true;
            while(!que.isEmpty()){
                int idx=que.poll();
                for(int next:graph[idx]){
                    if(!visited[next]){
                        que.add(next);
                        visited[next]=true;
                    }
                }
            }
        }
    }

}
