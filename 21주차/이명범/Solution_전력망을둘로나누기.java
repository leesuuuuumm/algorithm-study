import java.util.*;

class Edge {
    int no;
    List<Edge> connected;
    
    public Edge (int no) {
        this.no = no;
        this.connected = new ArrayList<>();
    }
    
    public void add(Edge o) {
        connected.add(o);
    }
    
    public int getCount(int cutNo, boolean[] flag) {
        flag[no] = true;
        
        int result = 1;
        for (Edge next : connected) {
            if (cutNo == next.no)
                continue;
            if (flag[next.no])
                continue;

            result += next.getCount(next.no, flag);
        }
        return result;
    }
}

class Solution {
    static Edge[] edges;
    
    public int solution(int n, int[][] wires) {
        init(n, wires);
        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            int c1 = edges[v1].getCount(v2, new boolean[n + 1]);
            int c2 = edges[v2].getCount(v1, new boolean[n + 1]);
            answer = Math.min(answer, (int) Math.abs(c1 - c2));
        }
        return answer;
    }
    
    private void init(int n, int[][] wires) {
        edges = new Edge[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new Edge(i);
        }
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            edges[v1].add(edges[v2]);
            edges[v2].add(edges[v1]);
        }
    }
}