import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 탑 {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int[] input=new int[n+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            input[i]=Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Tuple> que=new PriorityQueue<>();
        int[] result=new int[n+1];
        for(int i=n;i>=1;i--){
            while(true){
                if(!que.isEmpty()) {
                    if (input[i] >= que.peek().num) {
                        Tuple tuple = que.poll();
                        result[tuple.idx] = i;
                    } else {
                        break;
                    }
                }else{
                    break;
                }
            }
            que.add(new Tuple(input[i],i));
        }

        for(int i=1;i<=n;i++){
            System.out.print(result[i]+" ");
        }
    }
    public static class Tuple implements Comparable<Tuple> {
        public int num;
        public int idx;
        public Tuple(int num, int idx){
            this.num=num;
            this.idx=idx;
        }
        @Override
        public int compareTo(Tuple o){
            return this.num-o.num;
        }
    }
}
