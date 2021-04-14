package assign10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTester {

	@BeforeEach
	void setUp() {
	}

	@Test
	void testCreateHeap() {
		BinaryMaxHeap<Integer> maxInts = new BinaryMaxHeap<>();
		assertTrue(maxInts.isEmpty());
	}
	
	@Test
	void testCreateHeapComparator() {
		BinaryMaxHeap<Integer> minInts = new BinaryMaxHeap<>();
		minInts.add(1);
		minInts.add(0);
		minInts.add(5);
		minInts.add(-2);
		minInts.add(3);
//		minInts.add(5);
//		minInts.add(4);
//		minInts.add(3);
//		minInts.add(2);
//		minInts.add(1);
		
		System.out.println(minInts.toString());
		System.out.println(minInts.extractMax());
		System.out.println(minInts.extractMax());
		System.out.println(minInts.extractMax());
		System.out.println(minInts.extractMax());
		System.out.println(minInts.extractMax());
	}

}
