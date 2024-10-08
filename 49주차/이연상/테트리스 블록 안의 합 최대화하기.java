// [CDT] 테트리스 블록 안의 합 최대화하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.Math;

public class Main {

    static int n;
    static int m;
    static int[][] map;

    static int[][][] blocks = {
        {{0,0}, {0, 1}, {0,2}, {0, 3}}, // 1
        {{0,0}, {1, 0}, {2, 0}, {3, 0}}, // 1 상하
        {{0, 0}, {0, 1}, {1, 0}, {1, 1}}, // 2 
        {{0, 0}, {1, 0}, {1, 1}, {2, 1}}, // 3
        {{0, 1}, {1, 0}, {1, 1}, {2, 0}}, // 3 상하
        {{0, 1}, {0, 2}, {1, 0}, {1, 1}}, // 3 90도
        {{0 ,0}, {0, 1}, {1, 1}, {1, 2}}, // 3 90도 좌우
        {{0, 0}, {1, 0}, {2, 0}, {1, 1}}, // 4
        {{0, 1}, {1, 1}, {2, 1}, {1, 0}}, // 4 좌우
        {{0, 0}, {0, 1}, {0, 2}, {1, 1}}, // 4 90도
        {{0, 1}, {1, 0}, {1, 1}, {1, 2}}, // 4 90도 상하
        {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, // 5
        {{0, 0}, {0, 1}, {1, 0}, {2, 0}}, // 5 상하
        {{0, 1}, {1, 1}, {2, 0}, {2, 1}}, // 5 좌우
        {{0, 0}, {0, 1}, {1, 1}, {2, 1}}, // 5 좌우 상하
        {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, // 5 90도
        {{0, 0}, {0, 1}, {0, 2}, {1, 2}}, // 5 90도 좌우
        {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, // 5 90도 상하
        {{0, 2}, {1, 0}, {1, 1}, {1, 2}}, // 5 90도 좌우 상하
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for(int i = 0; i < blocks.length; i++) {

            for(int x = 0; x < n; x++) { // x값 늘려보기
                boolean isOk = true;
                for(int j = 0; j < 4; j++) {
                    if(blocks[i][j][0] + x >= n) {
                        isOk = false;
                        break;                        
                    }
                }   
                  
                if(!isOk) continue;

                for(int y = 0; y < m; y++) { // y값 늘려보기
                    int temp = 0;
                    for(int j = 0; j < 4; j++) {
                        if(blocks[i][j][1] + y >= m) {
                            isOk = false;
                            break;
                        }
                        temp += map[blocks[i][j][0] + x][blocks[i][j][1] + y];
                    }
                    if(isOk) answer = Math.max(answer, temp);
                }
            }
        }

        System.out.println(answer);
    }
}