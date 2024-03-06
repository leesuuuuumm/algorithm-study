import java.util.*;

class Solution {
    static int[][][] map;
    static int answer;
    
    public int solution(String dirs) {
        answer = 0;
        map = new int[11][11][5]; //주인공의 위치는 (5,5) 
        //1: 위에서 옴, 2: 오른쪽에서 옴, 3: 아래에서 옴, 4: 왼쪽에서 옴
        int com = dirs.length(); //명령의 개수
        int x = 5, y = 5; //주인공의 현재 위치
        
        for(int i = 0; i < com; i++){
            if(dirs.charAt(i) == 'U'){ //위로
                //맵을 벗어나면
                if(x - 1 < 0) continue;
                //이미 지나간 길이면
                System.out.println((x-1)+""+""+y+""+map[x-1][y][3]);
                if(map[x-1][y][3] > 0 || map[x][y][1] > 0) {
                    x = x-1;
                    continue;
                }
                
                map[x-1][y][3]++;
                x = x-1;
                System.out.println("U is save in " + x + " " + y +" "+ map[x][y][3]);
            }
            else if(dirs.charAt(i) == 'D'){ //아래로
                //맵을 벗어나지 않으면
                if(x + 1 > 10) continue;
                //이미 지나간 길이면
                if(map[x+1][y][1] > 0 || map[x][y][3] > 0) {
                    x = x+1;
                    continue;
                }
                
                map[x+1][y][1]++;
                x = x+1;
                System.out.println("D is save in " + x + " " + y +" "+ map[x][y][1]);
            }
            else if(dirs.charAt(i) == 'R'){ //오른쪽으로
                if(y + 1 > 10) continue;
                if(map[x][y+1][4] > 0 || map[x][y][2] > 0) {
                    y = y+1;
                    continue;
                }
                
                map[x][y+1][4]++;
                y = y+1;
                System.out.println("R is save in " + x + " " + y +" "+ map[x][y][4]);
            }
            else if(dirs.charAt(i) == 'L'){ //왼쪽으로
                if(y - 1 < 0) continue;
                if(map[x][y-1][2] > 0 || map[x][y][4] > 0) {
                    y = y-1;
                    continue;
                }
                
                map[x][y-1][2]++;
                y = y-1;
                System.out.println("L is save in " + x + " " + y +" "+ map[x][y][2]);
            }
            answer++;
        }
        
        return answer;
    }
}
