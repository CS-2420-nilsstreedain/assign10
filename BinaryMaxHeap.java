package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Paul Nuffer and Nils Streedain
 *
 * @param <E> - the generic type of element stored in the BinaryMaxHeap
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
	
	private E[] tree;
	
	private int size;
	
	private Comparator<? super E> cmp = null;
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		tree = (E[])new Object[100];
		size = 0;
	}
	
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this();
		this.cmp = cmp;
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list) {
		tree = (E[])new Object[list.size() + 1];
		for (int i = 0; i < list.size(); i++)
			tree[i + 1] = list.get(i);
		size = list.size();
		//FIGURE OUT HOW TO RUN PERCOLATE DOWN
	}

	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		tree = (E[])new Object[list.size() + 1];
		this.cmp = cmp;
		for (int i = 0; i < list.size(); i++)
			tree[i + 1] = list.get(i);
		size = list.size();
		//FIGURE OUT HOW TO RUN PERCOLATE DOWN
	}
	
	private void buildHeap() {
		
	}
	
	private void percolateUp(int index) {
		
	}
	
	private void percolateDown(int index) {
		
	}
	
	@SuppressWarnings("unchecked")
	private void doubleBacking() {
		E[] treeCopy = (E[])new Object[tree.length * 2];
		for (int i = 1; i < tree.length; i++)
			treeCopy[i] = tree[i];
		
		tree = treeCopy;
	}

	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	@Override
	public void add(E item) {
		if(size == tree.length - 1) 
			doubleBacking();
		
		tree[++size] = item;
		percolateUp(size);
	}
	
	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		return tree[1];
	}
	
	/**
	 * Returns and removes the maximum item this priority queue.
	 * O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the number of items in this priority queue.
	 * O(1)
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Returns true if this priority queue is empty, false otherwise.
	 * O(1)
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Empties this priority queue of items.
	 * O(1)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		tree = (E[])new Object[100];
		size = 0;
	}
	
	/** 
	 * Creates and returns an array of the items in this priority queue,
	 * in the same order they appear in the backing array.
	 * O(N)
	 * 
	 * (NOTE: This method is needed for grading purposes. The root item 
	 * must be stored at index 0 in the returned array, regardless of 
	 * whether it is in stored there in the backing array.) 
	 */
	@Override
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		E[] treeCopy = (E[])new Object[tree.length - 1];
		for (int i = 1; i < tree.length; i++)
			treeCopy[i - 1] = tree[i];
		return treeCopy;
	}
}	
