package comp2402a2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DuperFast implements DuperDeque {
  protected comp2402a2.SuperFast front;
  protected comp2402a2.SuperFast back;
  protected comp2402a2.SuperFast frontCopy;
  protected comp2402a2.SuperFast backCopy;
  protected ArrayList<Integer> intList;
  public DuperFast() {
    front = new comp2402a2.SuperFast();
    back = new comp2402a2.SuperFast();

  }

  public void addFirst(Integer x) {
    front.push(x);
    balance();
  }

  public void addLast(Integer x) {
    back.push(x);
    balance();

  }

  public Integer removeFirst() {
    if(front.list.isEmpty() && back.list.isEmpty()) {
      return null;
    }
    else if(front.list.isEmpty()){
      int x = back.pop();
      balance();
      return x;
    }
    else {
      int x = front.pop();
      balance();
      return x;
    }


  }

  public Integer removeLast() {
    if(front.list.isEmpty() && back.list.isEmpty()) {
      return null;
    }
    else if(back.list.isEmpty()){
      int x = front.pop();
      balance();
      return x;
    }
    else {
      int x = back.pop();
      balance();
      return x;
    }


  }

  public Integer max() {//get max of front and back then compare
    if (front.list.isEmpty() && back.list.isEmpty()){// when balance happens max doesn't change so it's disconnected
      return null;
    }
    else if (front.list.isEmpty()){
      return back.max();
    }
    else if (back.list.isEmpty()) {
      return front.max();
    }
    else if (back.max() > front.max()) {
      return back.max();
    }
    else if (front.max() > back.max()) {
      return front.max();
    }
    else {
      return front.max();
    }
  }

  public long ksumFirst(int k) {//get sum of front then back if k > front.size
    if (k > front.size() ){//&& (k - front.size() >= back.size())
      return front.ksum(k) + back.sumList.get(k - front.size() - 1);
    }
    else {
      return front.ksum(k);
    }
  }

  public long ksumLast(int k){
    if (k > back.size()){//&& (k - back.size() >= front.size())
      return back.ksum(k) + front.sumList.get(k - back.size() - 1);
    }
    else {
      return back.ksum(k);
    }
  }
  public void balance(){
    int n = size();

    if (front.size() > 3 * back.size()){
      int s = front.size() - (n / 2);
      frontCopy = new comp2402a2.SuperFast();
      backCopy = new comp2402a2.SuperFast();

      for (int i = s; i < front.size(); i++){
        frontCopy.push(front.list.get(i));
      }

      for (int i = s - 1; i >= 0; i--){
        backCopy.push(front.list.get(i));
      }

      for (Integer i: back.list) {
        backCopy.push(i);
      }

      front = frontCopy;
      back = backCopy;
    }
    else if (back.size() > 3 * front.size()) {
      int s = (n / 2) - front.size();
      frontCopy = new comp2402a2.SuperFast();
      backCopy = new comp2402a2.SuperFast();

      for (int i = s - 1; i >= 0; i--){
        frontCopy.push(back.list.get(i));
      }

      for (Integer i: front.list) {
        frontCopy.push(i);
      }

      for (int i = s; i < back.size(); i++){
        backCopy.push(back.list.get(i));
      }

      front = frontCopy;
      back = backCopy;
    }

  }

  /*
  public void balance(){
    int n = size();
    if(n >= 4){
      if (front.size() > 3 * back.size()){
        int s = front.size() - (n / 2) ;
        frontCopy.list.addAll(front.list.subList(s,front.size()));
        backCopy.list.addAll(front.list.subList(0, s));
        Collections.reverse(backCopy.list);
        backCopy.list.addAll(back);
        front = frontCopy;
        back = backCopy;
      }
      else if (back.size() > 3 * front.size()) {
        int s = (n / 2) - front.size();
        frontCopy.list.addAll(back.list.subList(0, s));
        Collections.reverse(frontCopy.list);
        frontCopy.list.addAll(front);
        backCopy.list.addAll(back.list.subList(s, back.size()));
        front = frontCopy;
        back = backCopy;
      }
    }
  }

   */

  public int size() {
    return front.size() + back.size();
  }

  public Iterator<Integer> iterator() {
    intList = new ArrayList<>();

    /*System.out.println(front.list);
    System.out.println(back.list);

    System.out.println("\n\n");

     */

    intList.addAll(front.list);
    Collections.reverse(intList);
    intList.addAll(back.list);

    return intList.iterator();
  }
}