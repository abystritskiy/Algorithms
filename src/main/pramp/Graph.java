import java.util.LinkedList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;


public class Graph {
    private LinkedList<Integer>[] adj;
    private int V;

    public Graph(int V) {
        this.V = V;
        this.adj = new LinkedList[V];
        for (int i=0; i<V; i++) {
            this.adj[i] = new LinkedList();
        }

        PriorityQueue<Integer> pQ = new PriorityQueue<>();


    }

    public void addEdge(int v, int s) {
        adj[v].add(s);
    }

    public Iterable<Integer> getAdjacent(int v) {
        return adj[v];
    }

    void BFS(int s) {

        boolean[] visited = new boolean[this.V];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(s);

        Iterator<Integer> i = adj[s].listIterator();
        while (i.hasNext()) {
            int vertex = i.next();
            if (!visited[vertex]) {
                queue.add(vertex);
                visited[vertex] = true;
            }
        }
    }


    void DFS(int s) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (!stack.empty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                visited[vertex] = true;
                for (int node: getAdjacent(vertex)) {
                    stack.push(node);
                }
            }
        }
    }


    public static void main(String[] args) {

        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        pQ.add(10);
        pQ.add(7);
        pQ.add(4);
        System.out.println(pQ.poll());
    }
}