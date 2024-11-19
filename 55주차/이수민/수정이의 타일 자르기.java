import java.util.*;
import java.io.*;
 
public class Solution {
 
    static class Rectangle implements Comparable<Rectangle>{
        int max;
        int min;
        
        public Rectangle(int w,int h) { 
            min=Math.min(w, h);
            max=Math.max(w, h);
        }
        
        @Override
        public int compareTo(Rectangle o) {
            return o.min-this.min;
        }
    }
    static int N,M,size[],cnt;
    static PriorityQueue<Rectangle> pq;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
        
        for (int t = 1; t <=T; t++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            N= Integer.parseInt(st.nextToken());
            M= Integer.parseInt(st.nextToken());
            size=new int[N];
            cnt=0; 
             
           st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                size[i]= Integer.parseInt(st.nextToken());
            }
            cut();
            System.out.println("#"+t+" "+cnt);
        }
         
    }
    private static void cut() {
        Arrays.sort(size);
        pq=new PriorityQueue<>();
        pq.offer(new Rectangle(M,M)); 
        cnt++;
         
        for (int i = N-1; i >=0; i--) { 
            go(1<<size[i]); 
        }
         
    }
    private static void go(int size) {
        Rectangle r=pq.poll();
         
        if(r.min>=size) {
            pq.offer(new Rectangle(r.min-size, size));
            pq.offer(new Rectangle(r.min, r.max-size));
        }else {
        	cnt++;
        	pq.offer(new Rectangle(M-size,size));
        	pq.offer(new Rectangle(M-size,M));
        }
    }
}
