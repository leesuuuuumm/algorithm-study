// [PRG] 충돌위험찾기

import java.util.Queue;
import java.util.LinkedList;
import java.lang.Math;

class Solution {
    
    class Robot {
        int[] now;
        int[] des;
        int distance;
        
        Robot(int[] now, int[] des, int distance) {
            this.now = now;
            this.des = des;
            this.distance = distance;
        }
    }
    
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        int[][] visit = new int[100][100];
        Queue<Robot> q = new LinkedList<>();
        for(int[] route : routes) {
            int[] now = points[route[0] - 1];
            int[] des = points[route[1] - 1];
            int distance = Math.abs(des[0] - now[0]) + Math.abs(des[1] - now[1]);
            q.add(new Robot(now, des, distance));
            visit[now[0]][now[1]] += 1;
            if(visit[now[0]][now[1]] == 2) answer += 1;
        }
        
        int robotNum = routes.length;
        int roundRobot = 0;
        int count = 0;
        visit = new int[100][100];
        while(!q.isEmpty()) {
            Robot robot = q.poll();
            count += 1;
            
            for(int i = 0; i < 4; i++) {
                int nx = robot.now[0] + move[i][0];
                int ny = robot.now[1] + move[i][1];
                
                if(nx >= 100 || ny >= 100 || nx < 0 || ny < 0) continue;
                
                int nextDistance = Math.abs(robot.des[0] - nx) + Math.abs(robot.des[1] - ny);
                if(nextDistance >= robot.distance) continue;
                
                visit[nx][ny] += 1;
                if(visit[nx][ny] == 2) answer += 1;
                
                if(robot.des[0] == nx && robot.des[1] == ny) {
                    roundRobot += 1;
                    break;
                }
                
                q.add(new Robot(new int[]{nx, ny}, robot.des, nextDistance));
                break;
            }
            
            
            if(count == robotNum) {
                visit = new int[100][100];
                robotNum -= roundRobot;
                count = 0;
            }
        }
        
        return answer;
    }
}