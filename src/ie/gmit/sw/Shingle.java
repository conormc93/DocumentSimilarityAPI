/*
 * Author: Conor McGrath
 * 
 */
package ie.gmit.sw;

// TODO: Auto-generated Javadoc
/**
 * The Class Shingle.
 */
public class Shingle {
	
	/** The doc ID. */
	private int docID;
	
	/** The hash code. */
	private int hashCode;
		
	/**
	 * Instantiates a new shingle.
	 *
	 * @param docID the doc ID
	 * @param hashCode the hash code
	 */
	//Constructors
	public Shingle(int docID, int hashCode) {
		super();
		this.docID = docID;
		this.hashCode = hashCode;
	}
	
	/**
	 * Gets the doc ID.
	 *
	 * @return the doc ID
	 */
	// Getters and Setters
	public int getDocID() {
		return docID;
	}

	/**
	 * Sets the doc ID.
	 *
	 * @param docID the new doc ID
	 */
	public void setDocID(int docID) {
		this.docID = docID;
	}

	/**
	 * Gets the hash code.
	 *
	 * @return the hash code
	 */
	public int getHashCode() {
		return hashCode;
	}

	/**
	 * Sets the hash code.
	 *
	 * @param hashCode the new hash code
	 */
	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}
	
}//Shingle