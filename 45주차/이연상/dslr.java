// [BOJ] DSLR

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String args[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tC = Integer.parseInt(br.readLine());
        int a, b;
        
        while(tC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());   
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(bfs(a, b)+"\n");
        }
        bw.close();
    }

    static String bfs(int a, int b) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        String command[] = new String[10000];
        command[a] = "";
        int dslr, d, s, l, r;
        while(!queue.isEmpty() && command[b] == null) {
            dslr = queue.remove();
            d = (dslr * 2) % 10000;
            if(command[d] == null) {
                command[d] = command[dslr] + "D";
                queue.add(d);
            }
            s = dslr == 0 ? 9999 : dslr-1;
            if(command[s] == null) {
                command[s] = command[dslr] + "S";
                queue.add(s);
            }
            l = dslr % 1000 * 10 + dslr / 1000;
            if(command[l] == null) {
                command[l] = command[dslr] + "L";
                queue.add(l);
            }
            r = dslr % 10 * 1000 + dslr / 10;
            if(command[r] == null) {
                command[r] = command[dslr] + "R";
                queue.add(r);
            }
        }
        return command[b];
    }
}