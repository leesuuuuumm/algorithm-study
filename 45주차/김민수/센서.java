import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 센서 {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        int K=Integer.parseInt(br.readLine());
        String[] str=br.readLine().split(" ");
        int[] dist=new int[N];
        for(int i=0;i<N;i++){
            dist[i]=Integer.parseInt(str[i]);
        }
        Arrays.sort(dist);
        if(N<=K){
            System.out.println(0);
            return;
        }
        PriorityQueue<Diff> diff=new PriorityQueue<>();
        ArrayList<Integer> cutIdx=new ArrayList<>();
        for(int i=1;i<N;i++){
            diff.add(new Diff(i, dist[i]-dist[i-1]));
        }
        for(int i=0;i<K-1;i++){
            cutIdx.add(diff.poll().idx);
        }
        Collections.sort(cutIdx);
        int curIdx=0;
        int result=0;
        for(int i=0;i<cutIdx.size();i++){
            int nextIdx=cutIdx.get(i);
            result+=(dist[nextIdx-1]-dist[curIdx]);
            curIdx=nextIdx;
        }
        result+=dist[N-1]-dist[curIdx];
        System.out.println(result);
    }
    public static class Diff implements Comparable<Diff>{
        public int idx;
        public int dif;
        public Diff(int idx, int dif){
            this.idx=idx;
            this.dif=dif;
        }

        @Override
        public int compareTo(Diff o){
            return o.dif-this.dif;
        }
    }
}
