import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 흙길보수하기 {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int l=Integer.parseInt(st.nextToken());
        PriorityQueue<Water> pq=new PriorityQueue<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            long start=Long.parseLong(st.nextToken());
            long end=Long.parseLong(st.nextToken());
            pq.add(new Water(start, end));
        }
        long prevLen=0;
        long num=0;
        while(!pq.isEmpty()){
            Water water=pq.poll();
            long start=water.start;
            if(prevLen>water.end)
                continue;
            else if(prevLen>water.start){
                start=prevLen;
            }
            long diff=water.end-start;
            long mod=1;
            if(diff%l!=0){
               mod=(diff/l)+1;
            }else{
                mod=(diff/l);
            }
            prevLen=start+(l*mod);
            num+=(mod);
        }
        System.out.println(num);
    }
    public static class Water implements Comparable<Water>{
        public long start;
        public long end;
        public Water(long start, long end){
            this.start=start;
            this.end=end;
        }

        @Override
        public int compareTo(Water o){
            return (int) (this.start-o.start);
        }
    }
}
