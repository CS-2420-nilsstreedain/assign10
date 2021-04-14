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
	

	public BinaryMaxHeap(List<? extends E> list) {
		buildHeap(list);
	}


	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		this.cmp = cmp;
		buildHeap(list);
	}
	
	@SuppressWarnings("unchecked")
	private void buildHeap(List<? extends E> list) {
		tree = (E[])new Object[list.size() + 1];
		for (int i = 0; i < list.size(); i++)
			tree[i + 1] = list.get(i);
		size = list.size();
		
		for (int i = size / 2; i > 0; i--)
			percolateDown(i);
	}
	
	private void percolateUp(int index) {
		while (index > 1 && innerCompare(tree[index], tree[parent(index)]) > 0) {
			swap(index, parent(index));
			index = parent(index);
		}
		
//		E temp = tree[index];
//		while (index > 1 && innerCompare(temp, tree[parent(index)]) > 0) {
//			tree[index] = tree[parent(index)];
//			index = parent(index);
//		}
//		tree[index] = temp;
	}
	
	private void percolateDown(int index) {
		int biggestChildIndex = getBiggestChild(index);
		while(leftChild(index) <= size && innerCompare(tree[index], tree[biggestChildIndex]) < 0 ) {
			biggestChildIndex = getBiggestChild(index);
			swap(index, biggestChildIndex);
			index = biggestChildIndex;
		}

//		while(leftChild(index) <= size) {
//			int biggestChildIndex = getBiggestChild(index);
//			
//			if (innerCompare(tree[index], tree[biggestChildIndex]) < 0)
//				swap(index, biggestChildIndex);
//			else
//				break;
//			
//			index = biggestChildIndex;
//		}
	}
	
	@SuppressWarnings("unchecked")
	private void doubleBacking() {
		E[] treeCopy = (E[])new Object[tree.length * 2];
		for (int i = 1; i < tree.length; i++)
			treeCopy[i] = tree[i];
		
		tree = treeCopy;
	}
	
	private void swap(int index1, int index2) {
		E tmp = tree[index1];
		tree[index1] = tree[index2];
		tree[index2] = tmp;
	}
	
	private int parent(int index) {
		return index / 2;
	}
	
	private int leftChild(int index) {
		return index * 2;
	}
	
	private int rightChild(int index) {
		return (index * 2) + 1;
	}
	
	private int getBiggestChild(int index) {
		if (leftChild(index) == size || innerCompare(tree[leftChild(index)], tree[rightChild(index)]) > 0)
			return leftChild(index);
		return rightChild(index);
	}
	
	@SuppressWarnings("unchecked")
	private int innerCompare(E element1, E element2) {
		if (cmp == null) {
		
			return ((Comparable<? super E>)element1).compareTo(element2);
		}
		
		return cmp.compare(element1, element2);
	}
	
	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	@Override
	public void add(E item) {
		if (size == tree.length - 1) 
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
		if (this.isEmpty())
			throw new NoSuchElementException();
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
		if (this.isEmpty())
			throw new NoSuchElementException();
		
		E max = tree[1];
		tree[1] = tree[size--];
		percolateDown(1);
		
		return max;
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
		E[] treeCopy = (E[])new Object[size];
		for (int i = 1; i <= size; i++)
			treeCopy[i - 1] = tree[i];
		return treeCopy;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		int end = 1;
		for (int i = 1; i <= size; i++) {
			result.append(tree[i]);
			result.append(" ");
			if (i == end) {
				result.append("\n");
				end = end * 2 + 1;
			}
		}
		return result.toString();
	}
}	
