import java.util.*;
class Solution {
    public ArrayList<Integer> solution(String s) {
        
        String[] str = s.substring(1,s.length()-1).split("},");
                
   
        for(int i=0;i<str.length;i++){
            str[i] = str[i].replaceAll("\\{","");
            
            str[i] = str[i].replaceAll("\\}","");
        }
        
        Arrays.sort(str, new Comparator<String>(){
           
            public int compare(String o1, String o2){
                
                return Integer.compare(o1.length(),o2.length());
            }
        });
        
        ArrayList<Integer> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for(int i=0;i<str.length;i++){
            String[] tmp = str[i].split(",");
            for(int j=0;j<tmp.length;j++){
                if(set.size()==0 || !set.contains(tmp[j])){
                    set.add(tmp[j]);
                    list.add(Integer.parseInt(tmp[j]));
                }
            }
            
        }
        return list;

    }
        
}
