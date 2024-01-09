package comp2402a4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UltraFast implements UltraStack {
  int n;
  int h;
  protected int[] max;
  protected long[] sum;
  protected ArrayList<Integer> intList;
  public UltraFast() {
    h = 0;
    n = 0;
    max = new int[1];
    sum = new long[1];
  }

  public int left(int i){
    return 2 * i + 1;
  }
  public int right(int i){
    return 2 * i + 2;
  }
  public int parent(int i){
    return (i - 1) / 2;
  }
  public void push(int x) {
    if (n == (1 << h)){
      resize();
    }

    max[(1 << h) - 1 + n] = x;
    sum[(1 << h) - 1 + n] = x;
    int node = parent((1 << h) - 1 + n);
    n++;

    for (int i = 0; i < h; i++) {
      if (max[left(node)] > max[right(node)]) {
        max[node] = max[left(node)];
      }
      else max[node] = Math.max(max[left(node)], max[right(node)]);

      sum[node] = sum[left(node)] + sum[right(node)];

      node = parent(node);

    }
  }
  public Integer pop() {
    if (n == 0){
      return null;
    }

    n--;
    int num = max[(1 << h) - 1 + n];
    max[(1 << h) - 1 + n] = 0;
    sum[(1 << h) - 1 + n] = 0;
    int node = parent((1 << h) - 1 + n);

    for (int i = 0; i < h; i++){
      if (max[left(node)] > max[right(node)]) {
          max[node] = max[left(node)];
      }
      else max[node] = Math.max(max[left(node)], max[right(node)]);

      sum[node] = sum[left(node)] + sum[right(node)];

      node = parent(node);
    }


    return num;
  }

  public void resize(){

    int[] newMax = new int[(1 << (h + 2)) - 1];
    long[] newSum = new long[(1 << (h + 2)) - 1];
    for (int i = 0; i < h + 1; i++){
      System.arraycopy(max, (1 << i) - 1, newMax, (1 << (i + 1)) - 1, 1 << i);//0, 1, 3 | 1, 3, 7,
      System.arraycopy(sum, (1 << i) - 1, newSum, (1 << (i + 1)) - 1, 1 << i);
    }
    newMax[0] = max[0];
    newSum[0] = sum[0];
    h++;

    max = newMax;
    sum = newSum;

  }

  public Integer get(int i) {
    if(i < 0 || i >= n){
      return null;
    }
    return max[(1 << h) -1 + i];
  }

  public Integer set(int i, int x) {
    if(i < 0 || i >= n){
      return null;
    }
    int num = max[(1 << h) - 1 + i];
    max[(1 << h) - 1 + i] = x;
    sum[(1 << h) - 1 + i] = x;
    int node = parent((1 << h) - 1 + i);

    for (int j = 0; j < h; j++) {
      if (max[left(node)] > max[right(node)]) {
        max[node] = max[left(node)];
      }
      else max[node] = Math.max(max[left(node)], max[right(node)]);

      sum[node] = sum[left(node)] + sum[right(node)];

      node = parent(node);

    }

    return num;
  }

  public Integer max() {
    if(n == 0){
      return null;
    }
    return max[0];
  }

  public long ksum(int k) {
    long total = 0;
    int numNodes;
    int count = 0;
    int curHeight = 0;
    int node = 0;
    if(k <= 0 || size() == 0){
      return total;
    }
    else if (k >= size()) {
      total = sum[0];
      return total;
    }
    else {
      k += (1 << h) - n;
      int k_copy = k;

      while(count != k){
        numNodes = (1 << (h - curHeight));
        if (numNodes > k_copy){
          node = right(node);
          curHeight++;
        }
        else {
          count += numNodes;
          total += sum[node];
          node = left(parent(node));
          k_copy -= numNodes;

        }
      }
    }
    return total;
  }

  public int size() {
    return n;
  }

  public Iterator<Integer> iterator() {
      intList = new ArrayList<>();

      for (int i = 0;  i < n; i++) {
          intList.add(max[(1 << h) - 1 + i]);
      }
      return intList.iterator();
  }
}
