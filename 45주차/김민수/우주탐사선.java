import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 우주탐사선 {
    static boolean[] visited;
    static int N;
    static int result;
    static int temp;
    static int[][] graph;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        int startIdx=Integer.parseInt(st.nextToken());
        graph=new int[N][N];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                graph[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        visited=new boolean[N];

        //플로이드-워셜
        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    graph[i][j]=Math.min(graph[i][j], graph[i][k]+graph[k][j]);
                }
            }
        }
        temp=0;
        visited[startIdx]=true;
        result=Integer.MAX_VALUE;
        dfs(1, startIdx);

        System.out.println(result);
    }
    public static void dfs(int step, int idx){
        if(step==N){
            result=Math.min(result, temp);
            return;
        }
        for(int i=0;i<N;i++){
            if(i==idx)
                continue;
            if(!visited[i]){
                temp+=graph[idx][i];
                visited[i]=true;
                dfs(step+1, i);
                visited[i]=false;
                temp-=graph[idx][i];
            }
        }
    }
}
