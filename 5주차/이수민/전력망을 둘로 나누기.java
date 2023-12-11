import java.util.*;
class Solution {
    
    Queue<Integer> que;
    boolean[] v ;
    int[][] edge;
    int cnt;
    public int solution(int n, int[][] wires) {

        edge = new int[n+1][n+1];
        for(int i=0;i<wires.length;i++){
            edge[wires[i][0]][wires[i][1]] = 1;
            edge[wires[i][1]][wires[i][0]] = 1;
        }
        // for(int r=1;r<edge.length;r++){
        //     for(int c = 1; c<edge.length;c++){
        //         System.out.print(edge[r][c]+" ");
        //     }
        //     System.out.println();
        // }
        int min = Integer.MAX_VALUE;
        que = new LinkedList<>();
        for(int i=0;i<wires.length;i++){
            v = new boolean[n+1];
            cnt = 1;
            edge[wires[i][0]][wires[i][1]] = 0;
            edge[wires[i][1]][wires[i][0]] = 0;
            que.offer(wires[i][0]);
            v[wires[i][0]] = true;
            bfs();
            min = Math.min(min,Math.abs((n-cnt)-cnt));
            edge[wires[i][0]][wires[i][1]] = 1;
            edge[wires[i][1]][wires[i][0]] = 1; 
        }
        
        return min;
    }
    private void bfs(){
        while(!que.isEmpty()){
            int cur = que.poll();
            for(int c=1;c<edge.length;c++){
                if(edge[cur][c]==1&&!v[c]){
                    que.offer(c);
                    v[c] = true;
                    cnt++;
                }
            }
        }
    }
}
