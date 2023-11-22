import java.util.*;
class Solution {
    
    static boolean[] v;
    static int R,C;
    static List<String> minList;
    public int solution(String[][] relation) {
        R = relation.length;
        C = relation[0].length;
        minList = new ArrayList<>(); 
        v = new boolean[C];
        subset(0,relation);
     
        return minList.size();
    }
    
    private static void subset(int cnt,String[][] relation){
        if(cnt == C){ // 행의 개수까지
            
            StringBuilder sb = new StringBuilder();
            
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<C;i++){
                if(!v[i]){ // 뽑음
                    list.add(i);
                    sb.append(i);
                    
                }
            }
            
            // 최소성 
            for(int i=0;i<minList.size();i++){
                String[] s = minList.get(i).split("");
                int minCnt = 0;
                
                for(int j=0;j<s.length;j++){
                    if(sb.toString().contains(s[j])){
                        minCnt++;
                    }
                }
                
                if(minCnt == s.length){
                    return;
                }
            }
            
            
            //중복값 있는지 체크 -- 유일성
            if(!checkUnique(list,relation)){
               return; 
            }
            minList.add(sb.toString());
            return;
        }
        
            v[cnt] = true;  
            subset(cnt+1,relation);
            v[cnt] = false;
            subset(cnt+1,relation);
        
    }
    private static boolean checkUnique(List<Integer> list,String[][] relation){
        HashSet<List<String>> checkSet = new HashSet<>();
        for(int i=0;i<R;i++){
            checkSet.add(new ArrayList<>());
        }
        
    
        for(int i=0;i<R;i++){       
         List<String> valList = new ArrayList<>();   
            for(int j=0;j<list.size();j++){
                valList.add(relation[i][list.get(j)]);
            }
            checkSet.add(valList);
        }
        if(R != checkSet.size()-1){
            return false;
        }
    
        return true;
    }
}
