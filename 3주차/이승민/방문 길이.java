import java.util.*;

class Solution {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public int solution(String dirs) {
        int[] cur = {0, 0};
        Set<String> set = new HashSet<>();
        int ans = 0;

        for (int i = 0; i < dirs.length(); i++) {
            char com = dirs.charAt(i);;

            if (com == 'U') {
                int nx = cur[0] + dx[0];
                int ny = cur[1] + dy[0];
                if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                    continue;
                }

                String path = cur[0] + "" + cur[1] + "" + nx + "" + ny;
                String reverse = nx + "" + ny + "" + cur[0] + "" + cur[1];
                if (!set.contains(path) && !set.contains(reverse)) {
                    set.add(path);
                    set.add(reverse);
                    ans++;
                }

                cur[0] += dx[0];
                cur[1] += dy[0];
            } else if (com == 'D') {
                int nx = cur[0] + dx[1];
                int ny = cur[1] + dy[1];
                if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                    continue;
                }

                String path = cur[0] + "" + cur[1] + "" + nx + "" + ny;
                String reverse = nx + "" + ny + "" + cur[0] + "" + cur[1];
                if (!set.contains(path) && !set.contains(reverse)) {
                    set.add(path);
                    set.add(reverse);
                    ans++;
                }

                cur[0] += dx[1];
                cur[1] += dy[1];
            } else if (com == 'R') {
                int nx = cur[0] + dx[2];
                int ny = cur[1] + dy[2];
                if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                    continue;
                }

                String path = cur[0] + "" + cur[1] + "" + nx + "" + ny;
                String reverse = nx + "" + ny + "" + cur[0] + "" + cur[1];
                if (!set.contains(path) && !set.contains(reverse)) {
                    set.add(path);
                    set.add(reverse);
                    ans++;
                }

                cur[0] += dx[2];
                cur[1] += dy[2];
            } else {
                int nx = cur[0] + dx[3];
                int ny = cur[1] + dy[3];
                if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                    continue;
                }

                String path = cur[0] + "" + cur[1] + "" + nx + "" + ny;
                String reverse = nx + "" + ny + "" + cur[0] + "" + cur[1];
                if (!set.contains(path) && !set.contains(reverse)) {
                    set.add(path);
                    set.add(reverse);
                    ans++;
                }

                cur[0] += dx[3];
                cur[1] += dy[3];
            }
        }

        return ans;
    }
}