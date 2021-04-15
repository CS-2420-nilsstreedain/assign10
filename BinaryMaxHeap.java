package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Creates a Binary Max Heap implementing a PriorityQueue interface
 * 
 * @author Paul Nuffer and Nils Streedain
 *
 * @param <E> - the generic type of element stored in the BinaryMaxHeap
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

	private E[] tree;
	private int size;
	private Comparator<? super E> cmp = null;

	/**
	 * BinaryMaxHeap constructor with no parameters
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		tree = (E[]) new Object[100];
		size = 0;
	}

	/**
	 * BinaryMaxHeap constructor that allows for a specified comparator
	 * 
	 * @param cmp - comparator to use for BinaryMaxHeap
	 */
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this();
		this.cmp = cmp;
	}

	/**
	 * BinaryMaxHeap constructor that is built off a parameter list
	 * 
	 * @param list - list to build BinaryMaxHeap from
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		buildHeap(list);
	}

	/**
	 * BinaryMaxHeap constructor that allows for a specified comparator and is built
	 * off a parameter list
	 * 
	 * @param cmp  - comparator to use for BinaryMaxHeap
	 * @param list - list to build BinaryMaxHeap from
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		this.cmp = cmp;
		buildHeap(list);
	}

	/**
	 * Private helper method for BinaryMaxHeap constructors that turns a parameter
	 * list into a BinaryMaxHeap
	 * 
	 * @param list - list to build BinaryMaxHeap from
	 */
	@SuppressWarnings("unchecked")
	private void buildHeap(List<? extends E> list) {
		// Creates a tree to move list elements into
		tree = (E[]) new Object[list.size() + 1];

		// Adds each element from list to tree
		for (int i = 0; i < list.size(); i++)
			tree[i + 1] = list.get(i);
		size = list.size();

		// PercolatesDown every element that is not leaf
		for (int i = size / 2; i > 0; i--)
			percolateDown(i);
	}

	/**
	 * Private helper method for percolating up an element in the tree
	 * 
	 * @param index - index of element to be percolated up
	 */
	private void percolateUp(int index) {
		// Checks that the element at index is greater than the element at the parent
		// index, meaning a swap is valid
		while (index > 1 && innerCompare(tree[index], tree[parent(index)]) > 0) {
			swap(index, parent(index));
			index = parent(index);
		}
	}

	/**
	 * Private helper method for percolating down an element in the tree
	 * 
	 * @param index - index of the element to be percolated down
	 */
	private void percolateDown(int index) {
		// While index has a left child
		while (leftChild(index) <= size) {
			int biggestChildIndex = getBiggestChild(index);

			// If the element at index is less than the larger of it's children
			if (innerCompare(tree[index], tree[biggestChildIndex]) < 0)
				// Index is swapped with the largest of it's children
				swap(index, biggestChildIndex);
			else
				break;

			// Index's biggest child is then used for the next iteration
			index = biggestChildIndex;
		}
	}

	/**
	 * Private helper method to double the size of the backing array
	 */
	@SuppressWarnings("unchecked")
	private void doubleBacking() {
		// Creates a new tree double the size of the previous
		E[] newTree = (E[]) new Object[tree.length * 2];

		// Adds each element from the previous tree to the new tree
		for (int i = 1; i < tree.length; i++)
			newTree[i] = tree[i];

		// Sets tree to the new, larger, tree
		tree = newTree;
	}

	/**
	 * Private helper method that swaps the element at one index with the element at
	 * another index
	 * 
	 * @param index1 - Element to be swapped with the seconds element
	 * @param index2 - Element to be swapped with the first element
	 */
	private void swap(int index1, int index2) {
		// Creates a temp element to store the value of element one
		E tmp = tree[index1];

		// Adds the respective values to elements being swapped
		tree[index1] = tree[index2];
		tree[index2] = tmp;
	}

	/**
	 * Private helper method that returns the parent index of an index in the tree
	 * 
	 * @param index - index to find the parent of
	 * 
	 * @return - parent index of "index"
	 */
	private int parent(int index) {
		return index / 2;
	}

	/**
	 * Private helper method that returns the left child index of an index in the
	 * tree
	 * 
	 * @param index - index to find the left child
	 * 
	 * @return - the left child index of "index"
	 */
	private int leftChild(int index) {
		return index * 2;
	}

	/**
	 * Private helper method that returns the right child index of an index in the
	 * tree
	 * 
	 * @param index - index to find the right child
	 * 
	 * @return - the right child index of "index"
	 */
	private int rightChild(int index) {
		return (index * 2) + 1;
	}

	/**
	 * Private helper method to find the larger of two children from a given index
	 * in the tree
	 * 
	 * @param index - to find the largest child of
	 * 
	 * @return - the largest child of "index"
	 */
	private int getBiggestChild(int index) {
		// First checks if the left is the last element in the tree to make sure
		// rightChild() is not called. Then compares leftChild() and rightChild()
		if (leftChild(index) == size || innerCompare(tree[leftChild(index)], tree[rightChild(index)]) > 0)
			return leftChild(index);
		return rightChild(index);
	}

	/**
	 * Private helper method that compares two elements
	 * 
	 * @param element1 - First element to be compared
	 * @param element2 - Second element to be compared
	 * 
	 * @return - Returns a value representing the comparison of the two elements
	 *         either through natural ordering or a comparator if provided
	 */
	@SuppressWarnings("unchecked")
	private int innerCompare(E element1, E element2) {
		// If no comparator is provided, natural ordering is used
		if (cmp == null)
			return ((Comparable<? super E>) element1).compareTo(element2);

		// Otherwise, the provided comparator is used
		return cmp.compare(element1, element2);
	}

	/**
	 * Adds the given item to this BinaryMaxHeap. O(1) in the average case, O(log N)
	 * in the worst case
	 * 
	 * @param item - Item to be added
	 */
	@Override
	public void add(E item) {
		if (size == tree.length - 1)
			doubleBacking();

		tree[++size] = item;
		percolateUp(size);
	}

	/**
	 * Returns, but does not remove, the maximum item this BinaryMaxHeap. O(1)
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
	 * Returns and removes the maximum item this BinaryMaxHeap. O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		// Throws an exception if the BinaryMaxHeap is empty
		if (this.isEmpty())
			throw new NoSuchElementException();

		// Keeps track of the element being removed from the tree
		E max = tree[1];

		// Replaces the element being removed with the last element in the backing array
		tree[1] = tree[size--];

		// Percolates that last element to a correct location
		percolateDown(1);

		return max;
	}

	/**
	 * Returns the number of items in this BinaryMaxHeap. O(1)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this BinaryMaxHeap is empty, false otherwise. O(1)
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Empties this BinaryMaxHeap of items. O(1)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		tree = (E[]) new Object[100];
		size = 0;
	}

	/**
	 * Creates and returns an array of the items in this BinaryMaxHeap, in the same
	 * order they appear in the backing array. O(N)
	 * 
	 * (NOTE: This method is needed for grading purposes. The root item must be
	 * stored at index 0 in the returned array, regardless of whether it is in
	 * stored there in the backing array.)
	 */
	@Override
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		E[] treeCopy = (E[]) new Object[size];
		for (int i = 1; i <= size; i++)
			treeCopy[i - 1] = tree[i];
		return treeCopy;
	}

	/**
	 * Private helper method for representing the BinaryMaxHeap while debugging
	 * 
	 * @return - A basic multi-line representation of the BinaryMaxHeap
	 */
	@SuppressWarnings("unused")
	private String stringRepresentation() {
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
