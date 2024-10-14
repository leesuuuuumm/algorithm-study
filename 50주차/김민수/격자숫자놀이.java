import java.util.*;
import java.io.*;

public class 격자숫자놀이 {
    static int r, c, k;
    static int[][] map;
    static int row, col;
    static int t;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        map=new int[100][100];
        row=3;
        col=3;
        for(int i=0;i<3;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        t=0;
        flag=false;
        if(map[r-1][c-1]==k) {
            System.out.println(0);
            return;
        }
        while(t<100) {
            t+=1;
            simulation(map);
            if(flag) {
                flag=false;
                t+=1;
            }
            if(map[r-1][c-1]==k) {
                System.out.println(t);
                return;
            }

        }

        System.out.println(-1);


    }

    public static void simulation(int[][] map) {
        int[][] newMap=new int[100][100];
        int nextR=0;
        int nextC=0;

        if(row>=col) {
            nextR=row;
            for(int i=0;i<row;i++) {
                int[][] count=new int[100][2];
                for(int j=0;j<col;j++) {
                    int val=map[i][j];
                    count[val][0]=val;
                    count[val][1]+=1;
                }
                Arrays.sort(count, (o1,o2)->{
                    if(o1[1]==o2[1]) {
                        return o1[0]-o2[0];
                    }return o1[1]-o2[1];
                });
                int idx=0;
                for(int k=0;k<100;k++) {
                    if(count[k][0]==0||count[k][1]==0)
                        continue;
                    if(idx==100) {
                        flag=true;
                        break;
                    }
                    else {
                        newMap[i][idx++]=count[k][0];
                        newMap[i][idx++]=count[k][1];
                    }
                }
                nextC=Math.max(nextC, idx);
            }
            row=nextR;
            col=nextC;
        }else {
            nextC=col;
            for(int i=0;i<col;i++) {
                int[][] count=new int[100][2];
                for(int j=0;j<row;j++) {
                    int val=map[j][i];
                    count[val][0]=val;
                    count[val][1]+=1;
                }
                Arrays.sort(count, (o1,o2)->{
                    if(o1[1]==o2[1]) {
                        return o1[0]-o2[0];
                    }return o1[1]-o2[1];
                });
                int idx=0;
                for(int k=0;k<100;k++) {
                    if(count[k][1]==0||count[k][0]==0)
                        continue;
                    if(idx==100) {
                        flag=true;
                        break;
                    }
                    else {
                        newMap[idx++][i]=count[k][0];
                        newMap[idx++][i]=count[k][1];
                    }
                }
                nextR=Math.max(nextR, idx);
            }
            row=nextR;
            col=nextC;

        }

        for(int i=0;i<100;i++) {
            for(int j=0;j<100;j++) {
                map[i][j]=newMap[i][j];
            }
        }
    }



}
