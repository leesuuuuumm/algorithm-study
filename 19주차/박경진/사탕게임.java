import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[][] map = new char[N][N];

        sc.nextLine();
        for(int i = 0; i < N; i++){
            String s = sc.nextLine();
            for(int j = 0; j < N; j++){
                map[i][j] = s.charAt(j);
            }
        }
        int answer = 0;
        for(int i = 0; i < N; i++){
            int cnt = 0;
            for(int j = 0; j < N; j++){
                if(j+1 < N && map[i][j] != map[i][j+1]){
                    char temp = map[i][j];
                    map[i][j] = map[i][j+1];
                    map[i][j+1] = temp;
                    cnt = countCandy(map);
                    map[i][j+1] = map[i][j];
                    map[i][j] = temp;
                }
                if(cnt > answer) answer = cnt;

                if( i+1 < N &&map[i][j] != map[i+1][j]){
                    char temp = map[i][j];
                    map[i][j] = map[i+1][j];
                    map[i+1][j] = temp;
                    cnt = countCandy(map);
                    map[i+1][j] = map[i][j];
                    map[i][j] = temp;
                }
                if(cnt > answer) answer = cnt;
            }
        }
        System.out.println(answer);
    }

    public static int countCandy(char[][] map){
        int max = 0;
        for(int i = 0; i < map.length; i++){
            int cnt = 1;
            for(int j = 0; j < map.length-1; j++){
                if(map[i][j] == map[i][j+1]){
                    cnt++;
                    if(j == map.length-2) {
                        if(max < cnt) max = cnt;
                        cnt = 1;
                    }
                }
                else if(map[i][j] != map[i][j+1]){
                    if(max < cnt) max = cnt;
                    cnt = 1;
                }
            }
        }
        for(int i = 0; i < map.length; i++){
            int cnt = 1;
            for(int j = 0; j < map.length-1; j++){
                if(map[j][i] == map[j+1][i]){
                    cnt++;
                    if(j == map.length-2) {
                        if(max < cnt) max = cnt;
                        cnt = 1;
                    }
                }
                else{
                    if(max < cnt) max = cnt;
                    cnt = 1;
                }
            }
        }
        return max;
    }
}
