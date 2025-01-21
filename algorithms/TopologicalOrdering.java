import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public final class TopologicalOrdering {
  public static void main(String[] args) {
    Graph g = new Graph(6);
    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);
    System.out.println(g.getTopologicalOrdering());
  }
}

class Graph {
  public Graph(int V) {
    this.V = V;
    adj = new LinkedList[V];
    for (int i = 0; i < V; i++) {
      adj[i] = new LinkedList<Integer>();
    }
  }

  public void addEdge(int v, int w) {
    adj[v].add(w);
  }

  public List<Integer> getTopologicalOrdering() {
    Stack<Integer> stack = new Stack<Integer>();
    boolean[] visited = new boolean[V];
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        topologicalOrderingUtil(i, visited, stack);
      }
    }
    List<Integer> topologicalOrdering = new LinkedList<Integer>();
    while (!stack.isEmpty()) {
      topologicalOrdering.add(stack.pop());
    }
    return topologicalOrdering;
  }

  private void topologicalOrderingUtil(int v, boolean[] visited, Stack<Integer> stack) {
    visited[v] = true;
    for (int i : adj[v]) {
      if (!visited[i]) {
        topologicalOrderingUtil(i, visited, stack);
      }
    }
    stack.push(v);
  }

  private int V;
  private List<Integer>[] adj;
}
