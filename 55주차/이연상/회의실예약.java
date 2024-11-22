// [SOFT] 회의실 예약

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static String[] rooms;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());

        rooms = new String[n];
        for(int i = 0; i < n; i++) {
            rooms[i] = br.readLine();
        }

        HashMap<String, boolean[]> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(rooms[i], new boolean[19]);
        }
        
        for(int i = 0; i < m; i++) {
            String[] use = br.readLine().split(" ");
            String room = use[0];
            int enter = Integer.valueOf(use[1]);
            int exit = Integer.valueOf(use[2]);

            boolean[] roomInfo = map.get(room);
            for(int j = enter; j <= exit; j++) {
                roomInfo[j] = true;
            }
            map.put(room, roomInfo);
        }

        String[] keySet = map.keySet().toArray(new String[0]);
        Arrays.sort(keySet, (o1, o2) -> o1.charAt(0) - o2.charAt(0));
        for(int i = 0; i < keySet.length; i++) {
            String room = keySet[i];
            if(i != 0) {
                System.out.println("-----");
            }
            
            boolean[] roomInfo = map.get(room);
            System.out.println("Room " + room + ":");

            ArrayList<String> times = new ArrayList<>();
            int idx = 9;
            while(idx < 19) {
                if(!roomInfo[idx]) {
                    String time;
                    if(idx == 9) time = "09-";
                    else time = String.valueOf(idx - 1) + "-";

                    while(!roomInfo[idx]) {
                        idx += 1;
                        if(idx == 18 || roomInfo[idx] == true) {
                            break;
                        }
                    }
                    time += String.valueOf(idx);
                    times.add(time);
                }
                idx++;
            }
            if(times.isEmpty()) {
                System.out.println("Not_available");
                continue;
            }
            System.out.println(times.size() + " available:");
            for(int j = 0; j < times.size(); j++) {
                System.out.println(times.get(j));
            }
        }
    }
}ㄴ