package java.datastructures;

public final class QuickUnionUF {
  public QuickUnionUF(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  public void union(int p, int q) {
    int i = root(p);
    int j = root(q);
    id[i] = j;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  private int root(int i) {
    while (id[i] != i) {
      i = id[i];
    }
    return i;
  }

  private int[] id;
}
