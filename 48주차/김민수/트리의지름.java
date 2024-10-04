import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리의지름 {
    static ArrayList<int[]>[] graph;
    static boolean[] visited;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        graph=new ArrayList[n+1];
        max=Integer.MIN_VALUE;
        for(int i=0;i<=n;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent=Integer.parseInt(st.nextToken());
            int child=Integer.parseInt(st.nextToken());
            int dist=Integer.parseInt(st.nextToken());
            graph[parent].add(new int[]{child,dist});
            graph[child].add(new int[]{parent,dist});
        }

        for(int i=1;i<=n;i++){
            visited=new boolean[n+1];
            visited[i]=true;
            dfs(i,0);
        }
        System.out.println(max);

    }
    public static void dfs(int idx, int sum){
        visited[idx]=true;
        max=Math.max(max, sum);
        for(int[] info:graph[idx]){
            int next=info[0];
            int dist=info[1];
            if(!visited[next]){
                dfs(next, sum+dist);
            }
        }
    }

}
