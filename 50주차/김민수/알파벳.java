import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파벳 {
    static int R, C;
    static int max;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static boolean[] alphabet;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabet = new boolean[26];
        visited = new boolean[R][C];
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = ch[j] - 'A';
            }
        }
        max = 1;
        alphabet[map[0][0]]=true;
        visited[0][0] = true;
        dfs(0, 0, 1);
        System.out.println(max);
    }

    public static void dfs(int r, int c, int num) {
        max=Math.max(num, max);
        for(int i=0;i<4;i++){
            int nx=r+dx[i];
            int ny=c+dy[i];
            if(isInRange(nx,ny)&&!visited[nx][ny]){
                int value=map[nx][ny];
                if(!alphabet[value]){
                    visited[nx][ny]=true;
                    alphabet[value]=true;
                    dfs(nx,ny,num+1);
                    visited[nx][ny]=false;
                    alphabet[value]=false;
                }
            }
        }
    }

    public static boolean isInRange(int nx, int ny) {
        return (nx >= 0 && ny >= 0 && nx < R && ny < C);
    }

}
