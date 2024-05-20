// [BOJ] 트리

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    ArrayList<Integer>[] graph = new ArrayList[n];
    int[] parent = new int[n];
    for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
    }

    int rootNode = 0;
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
        int myParent = Integer.parseInt(st.nextToken());
        if(myParent == -1){
            rootNode = i;
            continue;
        }
        graph[myParent].add(i);
        parent[i] = myParent;
    }

    int removeNode = Integer.parseInt(br.readLine());
    graph[parent[removeNode]].remove(Integer.valueOf(removeNode));

    int result = countLeafNode(graph, n, rootNode ,removeNode);

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    bw.write(Integer.toString(result));
    bw.flush();
    br.close();
    bw.close();
}

static int countLeafNode(ArrayList<Integer>[] graph, int n, int rootNode, int removeNode){
    Queue<Integer> q = new LinkedList<>();
    q.offer(rootNode);

    int cnt = 0;

    while (!q.isEmpty()) {
        int curr = q.poll();
        if(curr == removeNode)
            continue;

        if(graph[curr].isEmpty()){
            cnt++;
            continue;
        }
        for(int next : graph[curr]){
            q.offer(next);
        }
    }

    return cnt;
}