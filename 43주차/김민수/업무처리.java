import java.io.*;
import java.util.*;

public class 업무처리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int H=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        int R=Integer.parseInt(st.nextToken());

        int N=1<<(H+1);
        int minLeaf=1<<H;
        Queue<Integer>[][] que=new ArrayDeque[2][N];
        for(int i=0;i<2;i++){
            for(int j=0;j<N;j++){
                que[i][j]=new ArrayDeque<>();
            }
        }
        for(int i=minLeaf;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<K;j++){
                que[0][i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int result=0;
        for(int day=1;day<=R;day++){
            int bit=day%2; //0: 짝수, 1: 홀수

            //부서장
            if(!que[bit][1].isEmpty()){
                result+=que[bit][1].poll();
            }

            //일반 직원
            for(int j=2;j<minLeaf;j++){
                if(!que[bit][j].isEmpty()){
                    int parent=j/2;
                    que[(j+1)%2][parent].add(que[bit][j].poll());
                }
            }

            //말단 직원
            for(int j=minLeaf;j<N;j++){
                if(!que[0][j].isEmpty()){
                    int parent=j/2;
                    que[(j+1)%2][parent].add(que[0][j].poll());
                }
            }

        }
        System.out.println(result);
    }
}
