import java.util.*;

public class Solution {
    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int[] solution(String[][] places) {

        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            List<Location> locs = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (places[i][j].charAt(k) == 'P') locs.add(new Location(j, k));
                }
            }
            answer[i] = bfs(places[i], locs);
        }

        return answer;
    }

    private int bfs(String[] place, List<Location> locs) {
        int answer = 1;

        for (Location loc : locs) {
            Queue<Location> queue = new LinkedList<>();
            Queue<Location> subQueue = new LinkedList<>();
            boolean[][] visit = new boolean[5][5];

            queue.offer(loc);
            visit[loc.r][loc.c] = true;

            int manhattaan = 0;

            while (!queue.isEmpty() && manhattaan <= 2) {
                Location currentLocation = queue.poll();

                if (manhattaan != 0 && place[currentLocation.r].charAt(currentLocation.c) == 'P') return answer = 0;

                for (int i = 0; i < 4; i++) {
                    int nr = currentLocation.r + dr[i];
                    int nc = currentLocation.c + dc[i];

                    if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                    if (visit[nr][nc]) continue;
                    if (place[currentLocation.r].charAt(currentLocation.c) == 'X') continue;

                    subQueue.offer(new Location(nr, nc));
                    visit[nr][nc] = true;
                }

                if (queue.isEmpty()) {
                    queue.addAll(subQueue);
                    subQueue.clear();
                    manhattaan++;
                }
            }
        }
        return answer;
    }
}