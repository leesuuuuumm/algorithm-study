class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(int i=0;i<skill_trees.length;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<skill_trees[i].length();j++){
               if(skill.contains(String.valueOf(skill_trees[i].charAt(j)))){
                    sb.append(String.valueOf(skill_trees[i].charAt(j)));
                 }
               }
                if(skill.substring(0,sb.length()).equals(sb.toString())){
                    answer++;
                }  
            }
    
        return answer;
    }
}
