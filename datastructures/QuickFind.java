public final class QuickFind {
  private final int n;
  private final int[] parent;

  public QuickFind(int n) {
    this.n = n;
    parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }

  /**
   * Merges the components containing p and q. Changes all entries with the same value as parent[p]
   * to parent[q].
   */
  public void union(int p, int q) {
    int pid = parent[p];
    int qid = parent[q];
    if (pid == qid) {
      return;
    }
    for (int i = 0; i < n; i++) {
      if (parent[i] == pid) {
        parent[i] = qid;
      }
    }
  }

  /**
   * Checks if p and q are connected. This is simply done by checking if they have the same parent.
   */
  public boolean connected(int p, int q) {
    return parent[p] == parent[q];
  }

  public static void main(String[] args) {
    QuickFind qf = new QuickFind(10);
    qf.union(4, 3);
    qf.union(3, 8);
    qf.union(6, 5);
    qf.union(9, 4);
    qf.union(2, 1);
    System.out.println(qf.connected(8, 9));
    System.out.println(qf.connected(5, 0));
    qf.union(5, 0);
    qf.union(7, 2);
    qf.union(6, 1);
    qf.union(7, 3);
    System.out.println(qf.connected(5, 4));
  }
}
