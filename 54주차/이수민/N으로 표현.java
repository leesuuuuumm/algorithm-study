import java.util.*;  
class Solution {
    public int solution(int N, int number) {
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        
        for(int i = 0; i <= 8; i++) {
            list.add(new HashSet<>());
        }
        list.get(1).add(N);

        StringBuilder sb = new StringBuilder();

        sb.append(N);

        for(int i = 2; i < list.size(); i++) {
            sb.append(N);
            HashSet<Integer> set = list.get(i);
            set.add(Integer.parseInt(sb.toString()));

            for(int j = 1; j <= i; j++) {
                HashSet<Integer> setA = list.get(j);
                HashSet<Integer> setB = list.get(i - j);

                for (Integer numA : setA) {
                    for (Integer numB : setB) {
                        set.add(numA + numB);
                        set.add(numA - numB);
                        set.add(numA * numB);
                        if(numA != 0 && numB != 0) {
                            set.add(numA / numB);
                        }
                    }
                }
            }
        }
        for (HashSet<Integer> set : list) {
            if(set.contains(number)) return list.indexOf(set);
        }
        return -1;
    }
}
