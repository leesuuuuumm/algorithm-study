import java.util.*;

class Solution {
    static class Point {
        int r;
        int c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        Point[] oppositePoint = getOppositePoint(new Point(startY, startX), n, m);
        
        int[] answer = new int[balls.length];
        Arrays.fill(answer, Integer.MAX_VALUE);
        
        for (int i = 0; i < balls.length; i++) {
            for (int dir = 0; dir < 4; dir++) {
                if (impossibleOneCushion(dir, startY, startX, balls[i])) continue;
                answer[i] = Math.min(answer[i], calculateDistance(oppositePoint[dir], new Point(balls[i][1], balls[i][0])));
            }
        }
        return answer;
    }
    
    private Point[] getOppositePoint(Point a, int n, int m) {
        Point[] result = new Point[4];
        result[0] = new Point(a.r, 2 * m - a.c);
        result[1] = new Point(a.r, 0 - a.c);
        result[2] = new Point(2 * n - a.r, a.c);
        result[3] = new Point(0 - a.r, a.c);
        
        return result;
    }
    
    private boolean impossibleOneCushion(int dir, int r, int c, int[] ball) {
        Point target = new Point(ball[1], ball[0]);
        
        if (dir == 0) return r == target.r && c < target.c;
        else if (dir == 1) return r == target.r && c > target.c;
        else if (dir == 2) return r < target.r && c == target.c;
        else return r > target.r && c == target.c;
    }
    
    private int calculateDistance(Point a, Point b) {
        return (int) Math.pow(a.r - b.r, 2) + (int) Math.pow(a.c - b.c, 2);
    }
}