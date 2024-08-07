import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 불 {
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static Queue<int[]> fire; //불의 위치 {h, w}
    public static char[][] map;
    public static boolean[][] visited;
    public static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            fire=new ArrayDeque<>();
            map = new char[h][w];
            visited=new boolean[h][w];

            int startW = 0;
            int startH = 0;
            for (int j = 0; j < h; j++) {
                String line = br.readLine();
                for (int k = 0; k < w; k++) {
                    map[j][k]=line.charAt(k);
                    if (map[j][k] == '@') {
                        startW = k;
                        startH = j;
                    }else if(map[j][k]=='*'){
                        fire.offer(new int[]{j,k});
                    }
                }
            }
            System.out.println(bfs(startW, startH));
        }

    }

    public static String bfs(int startW, int startH) {
        Queue<Escape> que=new ArrayDeque<>();
        ArrayList<int[]> temp=new ArrayList<>();
        int preTime=0;

        visited[startH][startW]=true;
        que.offer(new Escape(startW, startH, 0));

        while(!que.isEmpty()){
            Escape cur=que.poll();

            if(cur.time!=preTime){
                fire.addAll(temp);
                temp.clear();
            }
            while(!fire.isEmpty()){
                int[] f=fire.poll();
                for(int i=0;i<4;i++){
                    int nH=f[0]+dx[i];
                    int nW=f[1]+dy[i];
                    if(nH>=0&&nW>=0&&nH<h&&nW<w&&map[nH][nW]=='.'){
                        map[nH][nW]='*';
                        temp.add(new int[]{nH,nW});
                    }
                }
            }

            if(cur.w==0||cur.h==0||cur.w==w-1||cur.h==h-1){
                return String.valueOf(cur.time+1);
            }
            for(int i=0;i<4;i++){
                int nW=cur.w+dx[i];
                int nH=cur.h+dy[i];

                if(nW>=0&&nH>=0&&nW<w&&nH<h&&!visited[nH][nW]&&map[nH][nW]=='.'){
                    visited[nH][nW]=true;
                    que.offer(new Escape(nW, nH, cur.time+1));
                }
            }
            preTime=cur.time;
        }
        return "IMPOSSIBLE";
    }

    public static class Escape{
        public int w, h, time;
        public Escape(int w, int h, int time){
            this.w=w;
            this.h=h;
            this.time=time;
        }
    }
}
