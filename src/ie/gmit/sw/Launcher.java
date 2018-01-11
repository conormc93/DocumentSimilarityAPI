/*
 * Author: Conor McGrath
 * 
 */
package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Launcher.
 */
public class Launcher {

	/** The k. */
	int k; // minHashes
	
	/** The j. */
	int j; // shingles
	
	/** The min hashes. */
	private int[] minHashes;

	/**
	 * Instantiates a new launcher.
	 *
	 * @param fileName1 the file name 1
	 * @param fileName2 the file name 2
	 * @param hashes the hashes
	 * @param shingles the shingles
	 * @throws InterruptedException the interrupted exception
	 */
	public Launcher(String fileName1, String fileName2, int hashes, int shingles) throws InterruptedException {
		k = hashes;
		j = shingles;
		Random random = new Random();
		minHashes = new int[k];

		// populating minHashes table with random integers
		for (int i = 0; i < minHashes.length; i++) {
			minHashes[i] = random.nextInt();
		}

		// Queues of Shingles for each file
		BlockingQueue<Shingle> q1 = new LinkedBlockingQueue<>();
		BlockingQueue<Shingle> q2 = new LinkedBlockingQueue<>();

		// Minihash maps
		Map<Integer, List<Integer>> m1 = new HashMap<>();
		Map<Integer, List<Integer>> m2 = new HashMap<>();

		// Document Parser Threads
		Thread t1 = new Thread(new DocumentParser(fileName1, 5, q1, k), "T1");
		Thread t2 = new Thread(new DocumentParser(fileName2, 5, q2, k), "T2");

		t1.start(); // Start Thread
		t2.start(); // Start Thread
		t1.join(); // Thread Join
		t2.join(); // Thread Join

		// Consumer threads- creates mini-hashes for Jaccard indexing
		Thread t3 = new Thread(new Consumer(q1, m1, k, minHashes), "T3");
		Thread t4 = new Thread(new Consumer(q2, m2, k, minHashes), "T4");

		t3.start(); // Start Thread
		t4.start(); // Start Thread
		t3.join(); // Thread Join
		t4.join(); // Thread Join

		// Jaccard calculation
		float result = Jaccard(m1.get(0), m2.get(0));

		// Output Result
		System.out.println("These text files have a similarity of : " + result + "%");

	}// Launcher

	/**
	 * Jaccard.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the float
	 */
	// compute the jaccard index
	float Jaccard(List<Integer> x, List<Integer> y) {

		float result = 0.0f;
		List<Integer> intersection = new ArrayList<Integer>(x);
		intersection.retainAll(y);
		result = ((float) intersection.size() / k) * 100;
		return result;

	}// Jaccard
}// Launcher