import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public final class TopologicalOrdering {
  private static class Graph {
    private int numVertices;
    private List<Integer>[] adj;
    private int[] indegree;

    public Graph(int numVertices) {
      this.numVertices = numVertices;
      adj = new LinkedList[numVertices];
      for (int i = 0; i < numVertices; i++) {
        adj[i] = new LinkedList<>();
      }
      indegree = new int[numVertices];
    }

    public void addEdge(int from, int to) {
      adj[from].add(to);
      indegree[to]++;
    }

    /**
     * Returns a topological ordering of the graph.
     *
     * <p>Executes the following operations:
     *
     * <ol>
     *   <li>Start a DFS from every vertex, if not yet visited. This is done to ensure we cover all
     *       disconnected subgraphs.
     *   <li>For each vertex, visit all its neighbors.
     *   <li>Once done, add the vertex to a stack. This ensures all the dependencies are completed
     *       before the current vertex.
     *   <li>Return the stack in reverse order.
     * </ol>
     */
    public List<Integer> getTopologicalOrdering() {
      Stack<Integer> stack = new Stack<>();
      boolean[] visited = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++) {
        if (!visited[i]) {
          getTopologicalOrderingUtil(i, visited, stack);
        }
      }
      List<Integer> topologicalOrdering = new LinkedList<>();
      while (!stack.isEmpty()) {
        topologicalOrdering.add(stack.pop());
      }
      return topologicalOrdering;
    }

    private void getTopologicalOrderingUtil(int v, boolean[] visited, Stack<Integer> stack) {
      visited[v] = true;
      for (int i : adj[v]) {
        if (!visited[i]) {
          getTopologicalOrderingUtil(i, visited, stack);
        }
      }
      stack.push(v);
    }

    /**
     * Returns all topological orderings of the graph.
     *
     * <p>Executes the following operations:
     *
     * <ol>
     *   <li>For each vertex with indegree 0, remove it from the graph and update the indegrees of
     *       its neighbors. Also, mark the vertex as visited to avoid adding it to the ordering
     *       again.
     *   <li>Recursively call the function with the updated graph. Once all vertices are added to
     *       the ordering, add the ordering to the list of all orderings.
     *   <li>Restore the graph to its original state.
     * </ol>
     */
    public List<List<Integer>> getAllTopologicalOrderings() {
      List<List<Integer>> allTopologicalOrderings = new LinkedList<>();
      boolean[] visited = new boolean[numVertices];
      getAllTopologicalOrderingsUtil(new LinkedList<>(), allTopologicalOrderings, visited);
      return allTopologicalOrderings;
    }

    private void getAllTopologicalOrderingsUtil(
        List<Integer> topologicalOrdering,
        List<List<Integer>> allTopologicalOrderings,
        boolean[] visited) {
      if (topologicalOrdering.size() == numVertices) {
        allTopologicalOrderings.add(new LinkedList<>(topologicalOrdering));
        return;
      }
      for (int i = 0; i < numVertices; i++) {
        if (indegree[i] == 0 && !visited[i]) {
          topologicalOrdering.add(i);
          visited[i] = true;
          for (int j : adj[i]) {
            indegree[j]--;
          }
          getAllTopologicalOrderingsUtil(topologicalOrdering, allTopologicalOrderings, visited);
          topologicalOrdering.remove(topologicalOrdering.size() - 1);
          visited[i] = false;
          for (int j : adj[i]) {
            indegree[j]++;
          }
        }
      }
    }

    public void print() {
      for (int i = 0; i < numVertices; i++) {
        System.out.println(i + ": " + adj[i]);
      }
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph(6);
    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);
    g.getAllTopologicalOrderings().forEach(System.out::println);
  }
}
