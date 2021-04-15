package assign10;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTester {
	Random rng = new Random();
	
	BinaryMaxHeap<Integer> emptyInts;
	BinaryMaxHeap<Integer> negativeInts;

	@BeforeEach
	void setUp() {
		emptyInts = new BinaryMaxHeap<>();
		
		negativeInts = new BinaryMaxHeap<>();
		for (int i = 0; i < 1000; i++)
			negativeInts.add(rng.nextInt(100000) * -1);
	}

	@Test
	void testCreateHeap() {
		BinaryMaxHeap<Integer> maxInts = new BinaryMaxHeap<>();
		assertTrue(maxInts.isEmpty());
	}
	
	@Test
	void testCreateHeapComparator() {
		BinaryMaxHeap<Integer> minInts = new BinaryMaxHeap<>((o1, o2) -> (o2 - o1));
		minInts.add(1);
		minInts.add(0);
		minInts.add(5);
		minInts.add(-2);
		minInts.add(3);

		assertEquals(-2, minInts.peek());
	}
	
	@Test
	void testCreateHeapListBuildHeap() {
		ArrayList<Integer> listToAdd = new ArrayList<>();
		listToAdd.add(1);
		listToAdd.add(0);
		listToAdd.add(5);
		listToAdd.add(-2);
		listToAdd.add(3);
		BinaryMaxHeap<Integer> maxInts = new BinaryMaxHeap<>(listToAdd);
		assertEquals(5, maxInts.extractMax());
	}
	
	@Test
	void testCreateHeapListComparator() {
		ArrayList<Integer> listToAdd = new ArrayList<>();
		listToAdd.add(1);
		listToAdd.add(0);
		listToAdd.add(5);
		listToAdd.add(-2);
		listToAdd.add(3);
		BinaryMaxHeap<Integer> minInts = new BinaryMaxHeap<>(listToAdd, (o1, o2) -> (o2 - o1));
		assertEquals(-2, minInts.extractMax());
	}
	
	@Test
	void testEmptyAdd() {
		emptyInts.add(1);
		assertEquals(1, emptyInts.peek());
	}
	
	@Test
	void testAddMaxToLarge() {
		negativeInts.add(1);
		assertEquals(1, negativeInts.peek());
	}
	
	@Test
	void testPeekException () {
		assertThrows(NoSuchElementException.class, () -> {
			emptyInts.peek();
		});
	}
	
	@Test
	void testPeek() {
		negativeInts.add(1);
		negativeInts.add(2);
		negativeInts.add(3);
		negativeInts.add(4);
		negativeInts.add(2);
		assertEquals(4, negativeInts.peek());
	}
	
	@Test
	void testExtractMaxClear() {
		for (int i = 0; i < 1000; i++)
			negativeInts.extractMax();
		assertTrue(negativeInts.isEmpty());
	}
	
	@Test
	void testExtractMax() {
		for (int i = 0; i < 1000; i++)
			negativeInts.extractMax();
		negativeInts.add(1);
		assertEquals(1, negativeInts.extractMax());
	}
	
	@Test
	void testSizeEmpty() {
		assertEquals(0, emptyInts.size());
	}
	
	@Test
	void testSize() {
		assertEquals(1000, negativeInts.size());
	}
	
	@Test
	void testIsEmptyEmpty() {
		assertTrue(emptyInts.isEmpty());
	}
	
	@Test
	void testIsEmptyRemove() {
		emptyInts.add(1);
		emptyInts.extractMax();
		assertTrue(emptyInts.isEmpty());
	}
	
	@Test
	void testIsNotEmpty() {
		assertFalse(negativeInts.isEmpty());
	}
	
	@Test
	void testClearEmpty() {
		emptyInts.clear();
		assertTrue(emptyInts.isEmpty());
	}
	
	@Test
	void testClear() {
		negativeInts.clear();
		assertTrue(negativeInts.isEmpty());
	}

	@Test
	void bigAddAndExtractMax() {
		emptyInts.add(5);
		emptyInts.add(1);
		emptyInts.add(3);
		emptyInts.add(4);
		emptyInts.add(2);
		
		assertEquals(emptyInts.extractMax(), 5);
		assertArrayEquals(new Object[] {4, 2, 3, 1}, emptyInts.toArray());
	}
	
	@Test
	void testFullKthLargestHeap() {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(5);
		ints.add(1);
		ints.add(3);
		ints.add(4);
		ints.add(2);
		
		ArrayList<Integer> sorted = (ArrayList<Integer>) FindKLargest.findKLargestHeap(ints, 5);
		
		assertEquals(5, sorted.remove(0));
		assertArrayEquals(new Object[] {4, 3, 2, 1}, sorted.toArray());
	}
	
	@Test
	void testFullKthLargestHeapReverseCmp() {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(5);
		ints.add(1);
		ints.add(3);
		ints.add(4);
		ints.add(2);
		
		ArrayList<Integer> sorted = (ArrayList<Integer>) FindKLargest.findKLargestHeap(ints, 5, (o1, o2) -> (o2 - o1));
		
		assertEquals(1, sorted.remove(0));
		assertArrayEquals(new Object[] {2, 3, 4, 5}, sorted.toArray());
	}
	
	@Test
	void testFullKthLargestSort() {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(5);
		ints.add(1);
		ints.add(3);
		ints.add(4);
		ints.add(2);
		
		ArrayList<Integer> sorted = (ArrayList<Integer>) FindKLargest.findKLargestSort(ints, 5);
		
		assertEquals(5, sorted.remove(0));
		assertArrayEquals(new Object[] {4, 3, 2, 1}, sorted.toArray());
	}
	
	@Test
	void testFullKthLargestSortReverseCmp() {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(5);
		ints.add(1);
		ints.add(3);
		ints.add(4);
		ints.add(2);
		
		ArrayList<Integer> sorted = (ArrayList<Integer>) FindKLargest.findKLargestSort(ints, 5, (o1, o2) -> (o2 - o1));
		
		assertEquals(1, sorted.remove(0));
		assertArrayEquals(new Object[] {2, 3, 4, 5}, sorted.toArray());
	}
}
