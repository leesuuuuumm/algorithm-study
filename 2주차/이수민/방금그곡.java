import java.util.*;
class Solution {
    
    class Music implements Comparable<Music> {
        String musicName;
        int musicTime;
        int order;
        
        public Music(String musicName, int musicTime, int order){
            this.musicName = musicName;
            this.musicTime = musicTime;
            this.order = order;
        }
        
        @Override
        public int compareTo(Music o){
            if(this.musicTime == o.musicTime){
                return Integer.compare(this.musicTime,o.musicTime);
            }
            return Integer.compare(o.musicTime,this.musicTime);
        }
    } 
    String[] hash = {"C#","D#","F#","G#","A#"};
    String[] chgHash = {"1","2","3","4","5"};
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        PriorityQueue<Music> pq = new PriorityQueue<>();
        
        for(int i=0;i<musicinfos.length;i++){
            String[] str = musicinfos[i].split(",");
            String[] t1 = str[0].split(":");
            String[] t2 = str[1].split(":");
            
            int time = (Integer.parseInt(t2[0]) * 60 + Integer.parseInt(t2[1])) -  
                                (Integer.parseInt(t1[0]) * 60 + Integer.parseInt(t1[1]));
            for(int j = 0; j<hash.length; j++){
                m = m.replaceAll(hash[j],chgHash[j]);
                str[3] = str[3].replaceAll(hash[j],chgHash[j]);
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<time; j++){
                sb.append(str[3].charAt(j%str[3].length()));
            }
            
            if(sb.toString().contains(m)){
               pq.offer(new Music(str[2],time,i));
                
                //System.out.println(str[2]+" "+time+" "+i);
            }
            
        }
        if(pq.isEmpty()){
            return "(None)";
        }
        answer = pq.poll().musicName;
        
        return answer;
    }
}
