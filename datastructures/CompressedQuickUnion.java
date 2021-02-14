package java.datastructures;

public final class CompressedQuickUnion {
  public CompressedQuickUnion(int n) {
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
    if (id[i] == i) {
      return i;
    }
    return id[i] = root(i);
  }

  private int[] id;
}
