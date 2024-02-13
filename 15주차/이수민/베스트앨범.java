import java.util.*;
class Solution {
    static class Info implements Comparable<Info>{
        int num;
        int playCnt;
        
        public Info(int num, int playCnt){
            this.num = num;
            this.playCnt = playCnt;
        }
        
        public int compareTo(Info o){
            
            if(this.playCnt == o.playCnt){
                return Integer.compare(this.num, o.num);
            }
            return Integer.compare(o.playCnt,this.playCnt);
        }
        
    }
    public ArrayList<Integer> solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        
        HashMap<String, Integer> mp = new HashMap<>();
        for(int i=0;i<genres.length;i++){
            mp.put(genres[i],mp.getOrDefault(genres[i],0)+plays[i]);
        }
        
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(mp.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>(){
           
            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String, Integer> o2){
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });
        
        LinkedHashMap<String,PriorityQueue<Info>> map = new LinkedHashMap<>();
        for(Map.Entry<String,Integer> i: list){
            map.put(i.getKey(),new PriorityQueue<Info>());
        }
        
        for(int i=0;i<genres.length;i++){
            map.get(genres[i]).offer(new Info(i,plays[i]));
        }
        
        
        for(String i: map.keySet()){
            answer.add(map.get(i).poll().num);
            if(map.get(i).size()>0){   
                answer.add(map.get(i).poll().num);
            }
        }
        
        

        return answer;
    }
}
