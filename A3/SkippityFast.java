package comp2402a3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * An implementation of skiplists for searching
 *
 *
 * @param <T>
 */
public class SkippityFast<T> implements IndexedSSet<T> {
	protected Comparator<T> c;

	@SuppressWarnings("unchecked")
	protected static class Node<T> {
		T x;
		Node<T>[] next;
		protected int[] length;
		public Node(T ix, int h) {
			x = ix;
			next = (Node<T>[])Array.newInstance(Node.class, h+1);
			length = new int[h + 1];
		}
		public int height() {
			return next.length - 1;
		}
	}

	/**
	 * This node<T> sits on the left side of the skiplist
	 */
	protected Node<T> sentinel;

	/**
	 * The maximum height of any element
	 */
	int h;

	/**
	 * The number of elements stored in the skiplist
	 */
	int n;

	/**
	 * A source of random numbers
	 */
	Random rand;

	/**
	 * Used by add(x) method
	 */
	protected Node<T>[] stack;

	@SuppressWarnings("unchecked")
	public SkippityFast(Comparator<T> c) {
		this.c = c;
		n = 0;
		sentinel = new Node<T>(null, 32);
		stack = (Node<T>[])Array.newInstance(Node.class, sentinel.next.length);
		h = 0;
		rand = new Random();
	}

	public SkippityFast() {
		this(new DefaultComparator<T>());
	}

	/**
	 * Simulate repeatedly tossing a coin until it comes up tails.
	 * Note, this code will never generate a height greater than 32
	 * @return the number of coin tosses - 1
	 */
	protected int pickHeight() {
		int z = rand.nextInt();
		int k = 0;
		int m = 1;
		while ((z & m) != 0) {
			k++;
			m <<= 1;
		}
		return k;
	}

	/**
	 * Find the node<T> u that precedes the value x in the skiplist.
	 *
	 * @param x - the value to search for
	 * @return a node<T> u that maximizes u.x subject to
	 * the constraint that u.x < x --- or sentinel if u.x >= x for
	 * all node<T>s x
	 */
	protected Node<T> findPredNode(T x) {
		Node<T> u = sentinel;
		int r = h;
		while (r >= 0) {
			while (u.next[r] != null && c.compare(u.next[r].x,x) < 0)
				u = u.next[r];   // go right in list r
			r--;               // go down into list r-1
		}
		return u;
	}

	public T find(T x) {
		Node<T> u = findPredNode(x);
		return u.next[0] == null ? null : u.next[0].x;
	}

	public T findGE(T x) {
		if (x == null) {   // return first node<T>
			return sentinel.next[0] == null ? null : sentinel.next[0].x;
		}
		return find(x);
	}

	public T findLT(T x) {
		if (x == null) {  // return last node<T>
			Node<T> u = sentinel;
			int r = h;
			while (r >= 0) {
				while (u.next[r] != null)
					u = u.next[r];
				r--;
			}
			return u.x;
		}
		return findPredNode(x).x;
	}

	public boolean add(T x) {
		Node<T> u = sentinel;
		int r = h;
		int comp = 0;
		int j = -1, index = 0;


		while (r >= 0) {
			while (u.next[r] != null && (comp = c.compare(u.next[r].x, x)) < 0) {
				index += u.length[r];
				u = u.next[r];   // go right in list r
			}
			if (u.next[r] != null && comp == 0) return false;
			r--;               // go down into list r-1
		}

		//System.out.println(index);
		Node<T> w = new Node<T>(x, pickHeight());
		int k = w.height();
		if (k > h){
			h = k;
		}
		r = h;
		u = sentinel;
		while (r >= 0) {
			while (u.next[r] != null && index > j + u.length[r]) {
				j += u.length[r];
				u = u.next[r];
			}
			u.length[r]++;
			if (r <= k) {
				w.next[r] = u.next[r];
				u.next[r] = w;
				w.length[r] = u.length[r] - (index - j);
				u.length[r] = index - j;
			}
			r--;
		}

		n++;

//		Node<T> u = sentinel;
//		int r = h;
//		int comp = 0;
//		int index = 0;
//
//		while (r >= 0) {
//			while (u.next[r] != null && (comp = c.compare(u.next[r].x,x)) < 0) {
//				j[r] += u.length[r];
//				index += u.length[r];
//				u = u.next[r];
//			}
//			if (u.next[r] != null && comp == 0) {
//				while(r != h){
//					u.length[r++]--;
//				}
//
//				return false;
//			}
//			u.length[r]++;
//			stack[r--] = u;          // going down, store u
//		}
//
//		Node<T> w = new Node<T>(x, pickHeight());
//		while (w.height() > h)
//			stack[++h] = sentinel;   // height increased
//
//		for (int i = 0; i < w.next.length; i++) {
//			//Prev node
//			stack[i].next[i] = w;
//			if (w.height() > r){
//				stack[i].next[i].length[i] = stack[i].length[i] + (index + 1);
//			}
//			else {
//				stack[i].next[i].length[i] = stack[i].length[i] + (index - j[i]);
//			}
//
//
//			//Next node
//			w.next[i] = stack[i].next[i];
//			if (w.height() > r){
//				stack[i].next[i].length[i] = index + 1;
//			}
//			else {
//				stack[i].next[i].length[i] =index - j[i];
//			}
//
//		}
//		n++;

		return true;
	}

	public boolean remove(T x) {//needs adjusting so it works correctly
		boolean removed = false;
		Node<T> u = sentinel;
		int r = h;
		int comp = 0;
		while (r >= 0) {
			while (u.next[r] != null && (comp = c.compare(u.next[r].x, x)) < 0) {
				u = u.next[r];
			}
			if (u.next[r] != null && comp == 0) {
				removed = true;
				u.length[r] += u.next[r].length[r];
				u.next[r] = u.next[r].next[r];
				if(u.next[r] == null){
					u.length[r] = 0;
				}
				if (u == sentinel && u.next[r] == null){
					//System.out.println("HELLO");
					h--;  // height has gone down
				}
			}
			stack[r] = u;
			r--;
		}
		if (removed) {
			n--;
			for (int i = 0; i <= h; i++){
				//System.out.println(h);

				if(stack[i].next[i] != null){
					stack[i].length[i]--;
				}
			}

		}

		return removed;

//		T x = null;
//		Node u = sentinel;
//		int r = h;
//		int j = -1; // index of node u
//		while (r >= 0) {
//			while (u.next[r] != null && j+u.length[r] < i) {
//				j += u.length[r];
//				u = u.next[r];
//			}
//			u.length[r]--;  // for the node we are removing
//			if (j + u.length[r] + 1 == i && u.next[r] != null) {
//				x = u.next[r].x;
//				u.length[r] += u.next[r].length[r];
//				u.next[r] = u.next[r].next[r];
//				if (u == sentinel && u.next[r] == null)
//					h--;
//			}
//			r--;
//		}
//		n--;
		//return x;
	}
	protected Node<T> findPred(int i) {
		Node<T> u = sentinel;
		int r = h;
		int j = -1;
		while (r >= 0) {
			while (u.next[r] != null && j + u.length[r] < i){
				j += u.length[r];
				u = u.next[r];   // go right in list r
			}

			r--;               // go down into list r-1
		}
//		System.out.println(j);
		return u;
	}
	public T get(int i) {
		// TODO: You need to rewrite this method so that it is faster

		if(i < 0 || i > n-1) {
			return null;
			//throw new IndexOutOfBoundsException();
		}
		if (sentinel == null){
			return null;
		}

//		Node<T> x = sentinel;
//		int r = h;
//		while (r>=0){
//			while(x.next[r] != null){
//				System.out.print(x.x + " (len:"+ x.length[r] + ")----");
//				x = x.next[r];
//			}
//			if(x.next[r]== null && x != sentinel){
//				System.out.print(x.x);
//			}
//			System.out.println();
//			x = sentinel;
//			r--;
//		}

		return findPred(i).next[0].x;
	}

	public int rangecount(T x, T y){
		// TODO: You need to rewrite this method so that it is faster
		if(c.compare(x, y) > 0){
			T temp = x;
			x = y;
			y = temp;
		}
		int count = 0;

		Node<T> u = sentinel;
		int r = h;
		int j = 0;
		while (r >= 0) {
			while (u.next[r] != null && c.compare(u.next[r].x,x) < 0){
				j += u.length[r];
				u = u.next[r];   // go right in list r
			}
			r--;               // go down into list r-1
		}



		u = sentinel;
		r = h;
		int k = 0;
		while (r >= 0) {
			while (u.next[r] != null && c.compare(u.next[r].x,y) <= 0){
				k += u.length[r];
				u = u.next[r];   // go right in list r
			}
			r--;               // go down into list r-1
		}

		count = k - j;




//		Iterator<T> it = this.iterator();
//		for (int i = 0; i < n; i++) {
//			T item = it.next();
//			if(c.compare(item, x) >= 0 && c.compare(item, y) <= 0)
//				count++;
//		}
		return count;
	}

	public void clear() {
		n = 0;
		h = 0;
		Arrays.fill(sentinel.next, null);
	}

	public int size() {
		return n;
	}

	public Comparator<T> comparator() {
		return c;
	}

	/**
	 * Create a new iterator in which the next value in the iteration is u.next.x
	 * @param u
	 * @return
	 */
	protected Iterator<T> iterator(Node<T> u) {
		class SkiplistIterator implements Iterator<T> {
			Node<T> u, prev;
			public SkiplistIterator(Node<T> u) {
				this.u = u;
				prev = null;
			}
			public boolean hasNext() {
				return u.next[0] != null;
			}
			public T next() {
				prev = u;
				u = u.next[0];
				return u.x;
			}
			public void remove() {
				// Not constant time
				SkippityFast.this.remove(prev.x);
			}
		}
		return new SkiplistIterator(u);
	}

	public Iterator<T> iterator() {
		return iterator(sentinel);
	}

	public Iterator<T> iterator(T x) {
		return iterator(findPredNode(x));
	}
}
