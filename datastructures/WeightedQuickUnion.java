package java.datastructures;

public final class WeightedQuickUnion {
  public WeightedQuickUnion(int n) {
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
    while (id[i] != i) {
      i = id[i];
    }
    return i;
  }

  private int[] id, size;
}
