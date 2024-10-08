import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] seq = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            seq[i]=Integer.parseInt(st.nextToken());
        }
        Deque<Integer> que = new ArrayDeque<>();
        for(int i=0;i<N;i++){
            while(!que.isEmpty()&&seq[que.peekLast()]<seq[i]){
                seq[que.pollLast()]=seq[i];
            }
            que.addLast(i);
        }
        while(!que.isEmpty()){
            seq[que.pollLast()]=-1;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<N;i++){
            sb.append(seq[i]).append(" ");
        }
        System.out.println(sb);
    }
}
