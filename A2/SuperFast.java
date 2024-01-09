package comp2402a2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SuperFast implements SuperStack, Collection<Integer> {
  protected ArrayList<Integer> list;
  protected ArrayList<Integer> maxList;
  protected ArrayList<Long> sumList;
  protected int index;
  public SuperFast() {
    list = new ArrayList<>();
    maxList = new ArrayList<>();
    sumList = new ArrayList<>();
  }

  public void push(Integer x) {
    if(!sumList.isEmpty()){
      sumList.add(sumList.get(size() - 1) + x);
    }

    if(list.isEmpty()){
      index = 0;
      maxList.add(x);
      sumList.add((long) x);
    }
    else if (x >= maxList.get(index)){
      index++;
      maxList.add(x);
    }

    list.add(x);

  }

  public Integer pop() {
    if(size() <= 0){
      return null;
    }
    else {
      if(list.get(size() - 1).equals(maxList.get(index))){
        maxList.remove(index);
        index--;
      }
      sumList.remove(size() - 1);

      return list.remove(size() - 1);
    }
  }

  public Integer max() {
    if (list.isEmpty()){
      return null;
    }
    else {
      return maxList.get(index);
    }
  }

  public long ksum(int k) {
    long sum = 0;
    if (k <= 0 || size() == 0){
      return sum;
    }
    else if (k >= size()) {
      sum = sumList.get(size() - 1);
    }
    else{
      sum = sumList.get(size() - 1) - sumList.get(size() - k - 1);
    }
    return sum;

  }

  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  public Iterator<Integer> iterator() {
    return list.iterator();

  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public boolean add(Integer integer) {
    return false;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends Integer> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
  public void clear() {

  }
}