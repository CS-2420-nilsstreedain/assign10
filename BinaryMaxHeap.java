package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E> {
	
	private E[] tree;
	
	private int size;
	
	private Comparator<? super E> cmp = null;
	
	public BinaryMaxHeap() {
		tree = (E[])new Object[100];
		size = 0;
	}
	
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this();
		this.cmp = cmp;
	}
	
	public BinaryMaxHeap(List<? extends E> list) {
		this();
		list.sort();
		for (int i = 1; i < tree.length; i++)
			tree[i] = list.get(i);
		size = tree.length;
	}

	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		
	}
	
	private void buildHeap() {

	}
	
	private void percolateUp() {
		
	}
	
	private void percolateDown() {
		
	}

	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	@Override
	public void add(E item) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Returns true if this priority queue is empty, false otherwise.
	 * O(1)
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Empties this priority queue of items.
	 * O(1)
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}}
