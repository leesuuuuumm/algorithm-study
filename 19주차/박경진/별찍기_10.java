import java.util.*;

public class Main {
    static char[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++){
                map[i][j] = ' ';
            }
        }

        //r과 c를 각각 3,9,27,81...으로 나눠 4칸은 별 그다음은 공백, 그후 4칸은 별
        makeStar(0,0,n, false);

        String[] answer = new String[n];
        for(int i = 0; i < n; i++){
            answer[i] = "";
            for(int j = 0; j < n; j++){
                answer[i] += map[i][j];
            }
        }

        for(int i = 0; i < n; i++){
            System.out.println(answer[i]);
        }
    }

    public static void makeStar(int x, int y, int n, boolean blank){
        if(blank){ //빈칸일 경우
            return;
        }

        if(n == 1){ //가장 작은 크기의 조각
            map[x][y] = '*';
            return;
        }

        int temp = n / 3;
        int cnt = 0;

        for(int i = x; i < x+n; i=i+temp){
            for(int j = y; j < y+n; j=j+temp){
                cnt++;
                if(cnt == 5){ //다섯번째이면 공백
                    makeStar(i,j,temp,true);
                }
                else{
                    makeStar(i,j,temp,false);
                }
            }
        }
    }
}
