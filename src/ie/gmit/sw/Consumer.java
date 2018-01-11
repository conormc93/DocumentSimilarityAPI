/*
 * Author: Conor McGrath
 * 
 */
package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
 */
public class Consumer implements Runnable {

	/** The queue. */
	private BlockingQueue<Shingle> queue;
	
	/** The k. */
	private int k;
	
	/** The min hashes. */
	private int[] minHashes;

	/** The map. */
	Map<Integer, List<Integer>> map;

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	// Run Method
	public void run() {
		int docCount = 1;
		int max = Integer.MAX_VALUE;
		int value = 0;

		while (docCount > 0) {
			try {
				Shingle s = queue.take();

				// If the EOF has been reached in the queue
				if (s.getHashCode() == 0) {
					docCount--;
					continue;
				}

				List<Integer> list = map.get(s.getDocID());

				if (list == null) {
					list = new ArrayList<Integer>(k);

					for (int j = 0; j < k; j++) {
						list.add(j, max);
					}

					map.put(s.getDocID(), list);
				}

				for (int i = 0; i < minHashes.length; i++) {

					value = s.getHashCode() ^ minHashes[i];
					if (list.get(i) > value) {
						list.set(i, value);
					}
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // while
	}// run
	
	/**
	 * Instantiates a new consumer.
	 *
	 * @param q the q
	 * @param map the map
	 * @param k the k
	 * @param hashes the hashes
	 */
	/// Constructor
	public Consumer(BlockingQueue<Shingle> q, Map<Integer, List<Integer>> map, int k, int[] hashes) {
		super();
		this.queue = q;
		this.k = k;
		this.map = map;
		this.minHashes = hashes;
	}
}