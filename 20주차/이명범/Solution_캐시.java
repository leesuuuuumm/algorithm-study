import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        List<String> citiesInCache = new ArrayList<>();

        for (String city : cities) {
            // 대소문자를 비교하지 않으므로 모든 문자를 소문자로 변환하여 사용한다.
            String lowerCaseCity = city.toLowerCase();
            
            // 현재 검색중인 도시가 캐시에 있으면
            if(citiesInCache.contains(lowerCaseCity)) {
                // 실행시간 1 추가
                answer++;
                // 리스트에서 지움
                citiesInCache.remove(lowerCaseCity);
            }
            // 만약 없다면
            else {
                // 실행시간 5 추가
                answer += 5;
            }
            // LRU 알고리즘을 사용하므로 제일 최근에 검색한 도시는 캐시의 첫번째에 넣어준다. 소문자와 비교할 것이므로 캐시에는 소문자 문자열로 넣어둔다.
            citiesInCache.add(lowerCaseCity);
            // 만약 캐시 크기가 cacheSize를 초과한다면 size를 유지시키기 위해 마지막 요소를 삭제한다.
            if (citiesInCache.size() > cacheSize) {
                citiesInCache.remove(0);
            }
        }

        return answer;
    }
}