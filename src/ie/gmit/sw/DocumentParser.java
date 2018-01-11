/*
 * Author: Conor McGrath
 * 
 */
package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentParser.
 */
public class DocumentParser implements Runnable {
	
	/** The queue. */
	private BlockingQueue<Shingle> queue;
	
	/** The file. */
	private String file;
	
	/** The k. */
	private int shingleSize, k;
	
	/** The Buffer. */
	private Deque<String> Buffer = new LinkedList<>();
	
	/**
	 * Instantiates a new document parser.
	 *
	 * @param f the f
	 * @param ss the ss
	 * @param q the q
	 * @param k the k
	 */
	// Constructor
	public DocumentParser(String f, int ss, BlockingQueue<Shingle> q, int k) {
		this.file = f;
		this.queue = q;
		this.shingleSize = ss;
		this.k = k;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	// Run Method
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;

			// Loop through each line in the file
			while ((line = br.readLine()) != null) {

				String uLine = line.toUpperCase(); // Convert to Upper
				String[] words = uLine.split(" "); // Split line into words
				addWordsToBuffer(words); // Add words to buffer
				Shingle s = getNextShingle();

				// adds the shingle to the queue
				if (s == null) {
					continue;
				}
				queue.put(s);
			}

			queue.put(new Shingle(0, 0));

			flushBuffer();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Flush buffer.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	// Flush Buffer Method
	private void flushBuffer() throws InterruptedException {
		while (Buffer.size() > 0) {
			Shingle s = getNextShingle();
			if (s != null) {
				queue.put(s);
			} else {
				queue.put(new Poison(0, 0));
			} // else
		} // while
	}// flushBuffer

	/**
	 * Gets the next shingle.
	 *
	 * @return the next shingle
	 */
	// Shingle Method
	private Shingle getNextShingle() {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		while (counter < shingleSize) {
			if (Buffer.peek() != null)
				sb.append(Buffer.poll());
			counter++;
		} // while
		if (sb.length() > 0) {
			int docID = 0;
			return (new Shingle(docID, sb.toString().hashCode()));
		} // if
		else {
			return null;
		} // else
	}// getNextShingle

	/**
	 * Adds the words to buffer.
	 *
	 * @param words the words
	 */
	// Add Words To Buffer Method
	private void addWordsToBuffer(String[] words) {
		// Loop through words
		for (String s : words) {
			Buffer.add(s);

		} // for
	}// addWordsToBuffer
}// DocumentParser