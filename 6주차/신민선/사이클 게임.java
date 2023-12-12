import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for(int i = 0; i<N; i++)
            parent[i] = i;

        int count = 0;
        boolean flag = true;
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            if(flag) {
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                boolean unioned = union(A, B);

                if (!unioned) {
                    count = i + 1;
                    flag = false;
                }
            }
        }

        System.out.println(count);
    }
    static int find(int idx){
        if(parent[idx] == idx)
            return idx;
        else
            return find(parent[idx]);
    }
    static boolean union(int idx1, int idx2){
        int p1 = find(idx1);
        int p2 = find(idx2);

        if(p1 == p2)
            return false;
        else{
            parent[p2] = p1;
            return true;
        }
    }
}
