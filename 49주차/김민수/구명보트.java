import java.util.Arrays;

class 구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0; // 가장 가벼운 사람의 인덱스
        int right = people.length - 1; // 가장 무거운 사람의 인덱스
        int answer = 0; // 필요한 보트 개수

        while (left <= right) {
            // 가장 가벼운 사람과 가장 무거운 사람을 함께 태울 수 있으면
            if (people[left] + people[right] <= limit) {
                left++; // 가벼운 사람도 태웠으니 왼쪽 포인터 증가
            }
            // 무거운 사람은 항상 태움
            right--; // 무거운 사람 태우고 오른쪽 포인터 감소
            answer++; // 보트 한 대 사용
        }

        return answer;
    }
}
