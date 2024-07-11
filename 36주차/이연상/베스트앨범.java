// [PRG] 베스트앨범

import java.util.*;

class Solution {
    
    class Music {
        public int idx;
        public int play;
        
        public Music(int play, int idx) {
            this.play = play;
            this.idx = idx;
        }
    }
    
    public ArrayList<Integer> solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
        }
        
        ArrayList<String> mapToGenre = new ArrayList<>();
        for(String item : map.keySet()) {
            mapToGenre.add(item);
        }
        Collections.sort(mapToGenre, (a, b) -> map.get(b) - map.get(a));
        
        
        for(String genre : mapToGenre) {
            ArrayList<Music> music = new ArrayList<>();
            for(int i = 0; i < genres.length; i++) {
                if(genre.equals(genres[i]))
                    music.add(new Music(plays[i], i));
            }
            
            Collections.sort(music, (a, b) -> b.play - a.play);
            for(int i = 0; i < music.size(); i++) {
                if(i > 1) break;
                answer.add(music.get(i).idx);
            }
        }

        
        return answer;
    }
}