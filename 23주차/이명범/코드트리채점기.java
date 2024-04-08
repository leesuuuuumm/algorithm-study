import java.io.*;
import java.util.*;

public class Main {

    /**
     * 채점 대기 큐에 들어갈 자료구조
     */
    static class Request implements Comparable<Request> {
        int t;
        int p;
        String url;

        public Request(int t, int p, String url) {
            this.t = t;
            this.p = p;
            this.url = url;
        }

        public String getDomain() {
            return url.split("/")[0];
        }

        @Override
        public int compareTo(Request o) {
            return p == o.p ? t - o.t : p - o.p;
        }
    }

    static class Judge {
        int s;
        int e;
        String url;
        int id;

        public Judge(int s, String url, int id) {
            this.s = s;
            this.url = url;
            this.id = id;
        }

        public String getDomain() {
            return url.split("/")[0];
        }
    }

    static int Q, N;
    static Judge[] judges;
    static Map<String, Judge> history = new HashMap<>();
    static StringBuilder result = new StringBuilder();
    static Set<String> sameUrl = new HashSet<>();
    static Set<String> sameDomain = new HashSet<>();
    static PriorityQueue<Integer> rest = new PriorityQueue<>();
    static Map<String, PriorityQueue<Request>> waitingQueue = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new FileReader("src/codetree/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int com = Integer.parseInt(st.nextToken());

            if (com == 100) {
                N = Integer.parseInt(st.nextToken());
                String url = st.nextToken();
                judges = new Judge[N + 1];

                Request request = new Request(0, 1, url);
                PriorityQueue<Request> value = waitingQueue.getOrDefault(request.getDomain(), new PriorityQueue<>());
                value.add(request);
                waitingQueue.put(request.getDomain(), value);
                sameUrl.add(url);
                for (int j = 1; j <= N; j++) {
                    rest.add(j);
                }
            }
            /**
             * 채점 요청
             *
             * 1. waitingQueue에 url이 정확히 일치하는 경우 큐에 넣지 않는다.
             * 2. Request 객체를 생성한 후 waitingQueue에 넣는다.
             *
             * 최적화 1. url을 set으로 관리한다.
             */
            else if (com == 200) {
                int t = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                String url = st.nextToken();

                if (!sameUrl.contains(url)) {
                    Request request = new Request(t, p, url);
                    PriorityQueue<Request> value = waitingQueue.getOrDefault(request.getDomain(), new PriorityQueue<>());
                    value.add(request);
                    waitingQueue.put(request.getDomain(), value);
                    sameUrl.add(url);
                }
            }
            /**
             * 채점 시도
             * 1. 쉬고 있는 채점기가 없다면 해당 "명령"을 무시한다.
             * 2. 우선 순위가 높은 채점을 찾는다.
             * 3. 현재 작업 중인 채점 중 똑같은 도메인이 있다면 해당 "채점"을 무시한다.
             * 4. 채점 시도 시작 시간이 가장 최근에 작업한 도메인의 s + 3 * (e - s)보다 작다면 해당 "채점"을 무시한다.
             * 5. judges에 Judge 객체를 생성한 후 넣어준다.
             *
             * 최적화 1 : 똑같은 도메인 체크를 Set으로 관리하자.
             * 최적화 2 : 쉬고있는 채점기를 pq로 관리하자.
             * 최적화 3 : Map<String, PriorityQueue<Request>>로 대기 큐 관리,,?
             */
            else if (com == 300) {
                int t = Integer.parseInt(st.nextToken());
                if (rest.isEmpty()) continue;

                int mt = Integer.MAX_VALUE;
                int mp = Integer.MAX_VALUE;
                Request request = null;
                for (String domain : waitingQueue.keySet()) {
                    if (sameDomain.contains(domain)) continue;
                    if (waitingQueue.get(domain).isEmpty()) continue;

                    // 4.
                    Judge recentJudge = history.get(domain);
                    if (recentJudge != null && t < recentJudge.s + 3 * (recentJudge.e - recentJudge.s)) continue;

                    Request cur = waitingQueue.get(domain).peek();

                    if (mp > cur.p) {
                        mt = cur.t;
                        mp = cur.p;
                        request = cur;
                    } else if (mp == cur.p && mt > cur.t) {
                        mt = cur.t;
                        request = cur;
                    }
                }
                if (request == null) continue;
                judges[rest.peek()] = new Judge(t, request.url, rest.peek());
                rest.poll();
                sameDomain.add(request.getDomain());
                sameUrl.remove(request.url);
                waitingQueue.get(request.getDomain()).poll();
            }
            /**
             * 채점 종료
             * 1. 해당 id가 쉬고 있는 채점기라면 해당 "명령"을 무시한다.
             * 2. 해당 작업의 종료 시간을 체크하고 history에 넣어준 후 judges를 null 처리한다.
             */
            else if (com == 400) {
                int t = Integer.parseInt(st.nextToken());
                int id = Integer.parseInt(st.nextToken());

                // 1.
                if (judges[id] == null) continue;

                // 2.
                judges[id].e = t;
                history.put(judges[id].getDomain(), judges[id]);
                sameDomain.remove(judges[id].getDomain());
                rest.add(id);
                judges[id] = null;
            } else if (com == 500) {
                int t = Integer.parseInt(st.nextToken());
                int size = 0;
                for (String domain : waitingQueue.keySet()) {
                    size += waitingQueue.get(domain).size();
                }
                result.append(size).append("\n");
            }
        }
        System.out.println(result);
    }
}
