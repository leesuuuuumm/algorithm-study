import java.util.*;

class Node {
    int no;
    
    List<Node> children;
    
    int allowedIndex;
    int dropCount;
    int targetValue;
    
    public Node(int no, int targetValue) {
        this.no = no;
        this.targetValue = targetValue;
        this.children = new ArrayList<>();
    }
    
    public void init() {
        children.sort(Comparator.comparing(o -> o.no));
        for (Node c : children) {
            c.init();
        }
    }
    
    public void add(Node child) {
        children.add(child);
    }
    
    public boolean isEnough() {
        return dropCount * 3 >= targetValue;
    }
    
    public boolean isBurst() {
        return dropCount > targetValue;
    }
    
    public int getEndpoint() {
        if (isLeaf()) {
            return this.no;
        }
        int endpoint = children.get(allowedIndex).getEndpoint();
        allowedIndex = (allowedIndex + 1) % children.size();
        return endpoint;
    }
    
    public int nextValue() {
        int nextValue = Math.max(1, targetValue - (--dropCount) * 3);
        targetValue -= nextValue;
        return nextValue;
    }
    
    private boolean isLeaf() {
        return children.isEmpty();
    }
}

class Solution {
    LinkedList<Integer> result = new LinkedList<>();
    
    public int[] solution(int[][] edges, int[] target) {
        int N = edges.length + 1;
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i, target[i - 1]);
        }
        for (int[] e : edges) {
            makeTree(nodes, e[0], e[1]);
        }
        
        Node root = nodes[1];
        root.init();
        
        List<Node> sequenceOfDrops = new ArrayList<>();
        while (!isEnoughAllNodes(nodes)) {
            Node dropNode = nodes[root.getEndpoint()];
            
            dropNode.dropCount++;
            sequenceOfDrops.add(dropNode);
            
            if (dropNode.isBurst()) 
                return new int[]{-1};
        }
        return sequenceOfDrops.stream()
            .mapToInt(n -> n.nextValue()).toArray();
    }
    
    private boolean isEnoughAllNodes(Node[] nodes) {
        boolean isEnough = true;
        for (Node n : nodes) {
            if (n == null)
                continue;
            isEnough &= n.isEnough();
        }
        return isEnough;
    }
    
    private void makeTree(Node[] nodes, int parent, int child) {
        nodes[parent].add(nodes[child]);
    }
}