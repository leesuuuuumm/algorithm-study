import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로{
    static ArrayList<Node>[] adjacent;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int E=Integer.parseInt(st.nextToken());
        adjacent=new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            adjacent[i]=new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());
            adjacent[start].add(new Node(end, value));
            adjacent[end].add(new Node(start, value));
        }
        st=new StringTokenizer(br.readLine());
        int v1=Integer.parseInt(st.nextToken());
        int v2=Integer.parseInt(st.nextToken());
        int res1_1=dijkstra(1,v1,N);
        int res1_2=dijkstra(v1,v2,N);
        int res1_3=dijkstra(v2,N,N);
        if(res1_1==-1||res1_2==-1||res1_3==-1){
            System.out.println(-1);
            System.exit(0);
        }
        int res1=res1_1+res1_2+res1_3;

        int res2_1=dijkstra(1,v2,N);
        int res2_2=dijkstra(v2,v1,N);
        int res2_3=dijkstra(v1,N,N);
        if(res2_1==-1||res2_2==-1||res2_3==-1){
            System.out.println(-1);
            System.exit(0);
        }
        int res2=res2_1+res2_2+res2_3;
        int result=Math.min(res1,res2);
        System.out.println(result);
    }

    static int dijkstra(int start, int end, int n){
        int[] dist=new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start]=0;
        while(!pq.isEmpty()){
            Node cur=pq.poll();
            if(cur.value>dist[cur.toIdx])
                continue;
            for(Node next:adjacent[cur.toIdx]){
                if(dist[next.toIdx]>dist[cur.toIdx]+next.value){
                    dist[next.toIdx]=dist[cur.toIdx]+next.value;
                    pq.add(new Node(next.toIdx, dist[cur.toIdx]+next.value));
                }
            }
        }
        if(dist[end]==Integer.MAX_VALUE){
            return -1;
        }
        return dist[end];
    }
    public static class Node implements Comparable<Node>{
        public int toIdx, value;
        public Node(int toIdx, int value){
            this.toIdx=toIdx;
            this.value=value;
        }

        @Override
        public int compareTo(Node o){
            return this.value-o.value;
        }
    }
}
