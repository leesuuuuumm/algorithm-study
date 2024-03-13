import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Solution {

    public static class Music {
        // 시작 시간
        String start;
        // 종료 시간
        String end;
        // 음악 제목
        String title;
        // 악보 정보
        String score;
        // 재생 시간
        int playtime;
        // 재생 시간 동안 진행된 악보 나열
        String scoreForPlaytime;

        public Music(String musicInfo) {
            String[] split = musicInfo.split(",");

            start = split[0];
            end = split[1];
            title = split[2];
            score = replaceString(split[3]);

            init();
        }

        private void init() {
            // 재생 시간 설정
            setPlaytime();
            // 재생 시간 동안의 악보 나열
            setScoreForPlaytime();
        }

        // 시간 포맷을 초 단위 정수형으로 변경
        private int toIntFormat(String time) {
            String[] s = time.split(":");

            int hour = Integer.parseInt(s[0]);
            int min = Integer.parseInt(s[1]);

            return hour * 60 + min;
        }

        // 재생 시간 설정
        private void setPlaytime() {
            playtime = toIntFormat(end) - toIntFormat(start);
        }

        private void setScoreForPlaytime() {
            StringBuilder temp = new StringBuilder();

            for (int i = 0; i < playtime; i++) {
                temp.append(score.charAt(i % score.length()));
            }

            scoreForPlaytime = temp.toString();
        }

        public boolean contains(String m) {
            return scoreForPlaytime.contains(m);
        }

    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        m = replaceString(m);

        List<Music> musics = new ArrayList<>();
        List<Music> sameMusics = new ArrayList<>();

        for (String music : musicinfos) {
            musics.add(new Music(music));
        }

        for (Music music : musics) {
            if (music.contains(m)) sameMusics.add(music);
        }

        Optional<Music> max = sameMusics.stream().max(Comparator.comparingInt(m2 -> m2.playtime));

        if (max.isPresent()) {
            answer = max.get().title;
        } else {
            answer = "(None)";
        }

        return answer;
    }

    private static String replaceString(String s) {
        s = s.replaceAll("A#", "a");
        s = s.replaceAll("C#", "c");
        s = s.replaceAll("D#", "d");
        s = s.replaceAll("F#", "f");
        s = s.replaceAll("G#", "g");

        return s;
    }

    public static void main(String[] args) {
        solution("CC#BCC#BCC#", new String[]{"03:00,03:08,FOO,CC#B"});
    }
}