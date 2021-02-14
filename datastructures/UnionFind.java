package java.datastructures;

public final class UnionFind {
  public UnionFind(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
      size[i] = 1;
    }
  }

  public void union(int p, int q) {
    int i = root(p);
    int j = root(q);
    if (size[i] < size[j]) {
      id[i] = j;
      size[j] += size[i];
    } else {
      id[j] = i;
      size[i] += size[j];
    }
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

  private int[] id, size;
}
