package assign10;

import java.util.Random;

public class BinaryMaxHeapEfficiency {
	public static void main(String[] args) {
		Random rng = new Random();
		System.out.println("N\tnanoTime");
		
		int incr = 100000;
		for (int probSize = 100000; probSize <= 10000000; probSize += incr) {

			int timesToLoop = 10000;

			BinaryMaxHeap<Integer> bmh = new BinaryMaxHeap<>();
			for (int j = 0; j < probSize; j++)
				bmh.add(rng.nextInt());

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			long stopTime, midpointTime, startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}

			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				bmh.add(rng.nextInt());
//				bmh.peek();
//				bmh.extractMax();
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {
				rng.nextInt();
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(probSize + "\t" + String.format("%.5f", averageTime));
		}
	}
}
