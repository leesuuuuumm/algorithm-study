import java.util.*;

class Solution {
    static final int N = 50;
    
    int[] groups = new int[N * N];
    String[] values = new String[N * N];
    List<String> result = new ArrayList<>();
    
    public String[] solution(String[] commands) {
        init();
        Arrays.stream(commands).forEach(this::process);
        return result.toArray(new String[0]);
    }
    
    private void process(String command) {
        String[] com = command.split(" ");
        
        if (com[0].equals("UPDATE")) {
            if (com.length == 3) {
                String value1 = com[1];
                String value2 = com[2];
                
                update(value1, value2);
            }
            
            if (com.length == 4) {
                int r = Integer.parseInt(com[1]) - 1;
                int c = Integer.parseInt(com[2]) - 1;
                String value = com[3];
                
                update(r, c, value);
            }
            return;
        }
        
        if (com[0].equals("MERGE")) {
            int r1 = Integer.parseInt(com[1]) - 1;
            int c1 = Integer.parseInt(com[2]) - 1;
            int r2 = Integer.parseInt(com[3]) - 1;
            int c2 = Integer.parseInt(com[4]) - 1;
            
            merge(r1, c1, r2, c2);
            return;
        }
        
        if (com[0].equals("UNMERGE")) {
            int r = Integer.parseInt(com[1]) - 1;
            int c = Integer.parseInt(com[2]) - 1;
            
            unmerge(r, c);
            return;
        }
        
        if (com[0].equals("PRINT")) {
            int r = Integer.parseInt(com[1]) - 1;
            int c = Integer.parseInt(com[2]) - 1;
            
            result.add(print(r, c));
            return;
        }
    }
    
    private void init() {
        for (int i = 0; i < N * N; i++) {
            groups[i] = i;
            values[i] = "";
        }
    }
    
    private void update(int r, int c, String value) {
        int idx = convert(r, c);
        int groupIdx = find(idx);
        values[groupIdx] = value;
    }
    
    private void update(String value1, String value2) {
        if (value1.equals(value2))
            return;
        
        for (int i = 0; i < N * N; i++) {
            if (values[i].equals(value1))
                values[i] = value2;
        }
    }
    
    private void merge(int r1, int c1, int r2, int c2) {
        int idx1 = find(convert(r1, c1));
        int idx2 = find(convert(r2, c2));
        
        if (idx1 == idx2)
            return;
        
        union(idx1, idx2);
        values[idx1] = values[idx1].isEmpty() ? values[idx2] : values[idx1];
        values[idx2] = "";
    }
    
    private void unmerge(int r, int c) {
        for (int i = 0; i < N * N; i++) 
            find(i);
        
        int idx = convert(r, c);
        int groupIdx = find(idx);
        for (int i = 0; i < N * N; i++) {
            if (find(i) != groupIdx)
                continue;
            
            groups[i] = i;
        }
        
        if (idx == groupIdx)
            return;
        
        values[idx] = values[groupIdx];
        values[groupIdx] = "";
    }
    
    private String print(int r, int c) {
        int idx = convert(r, c);
        int groupIdx = find(idx);
        return values[groupIdx].isEmpty() ? "EMPTY" : values[groupIdx];
    }
    
    private int find(int a) {
        if (a == groups[a])
            return a;
        
        return groups[a] = find(groups[a]);
    }
    
    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if (pa == pb)
            return;
        
        groups[pb] = pa;
    }
    
    private int convert(int r, int c) {
        return r * N + c;
    }
    
    private int[] up(int v) {
        return new int[]{v / N, v % N};
    }
}