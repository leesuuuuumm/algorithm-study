import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>();
        
        Arrays.sort(phone_book, new Comparator<String>(){
           
            public int compare(String o1, String o2){
                return Integer.compare(o1.length(), o2.length());
            }
        });
        
        for(int i=0;i<phone_book.length;i++){
            if(set.size() == 0)
                set.add(phone_book[i]);
            else{
                StringBuilder sb = new StringBuilder();
                String[] str = phone_book[i].split("");
                for(int j=0;j<str.length;j++){
                    sb.append(str[j]);
                    if(set.contains(sb.toString())) return false;
                }
                set.add(phone_book[i]);
            }
        }
        
        return true;
    }
}
