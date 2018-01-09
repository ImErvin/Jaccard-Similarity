package ie.gmit.sw;

import java.util.*;

/**
 * Hashing is a gateway method to be able to invoke an algorithm. The intention
 * of this class is to use the strategy pattern in other classes by passing an
 * instance of the interface into the hash function.
 */
public class Hashing {
	List<String> shingles = new ArrayList<String>();

	/**
	 * Default Constructor
	 */
	public Hashing() {

	}

	/**
	 * Constructor with Fields
	 * 
	 * @param shingles
	 *            A list of words to hash
	 */
	public Hashing(List<String> shingles) {
		// Set the shingles
		this.shingles = shingles;
	}

	/**
	 * Strategy Pattern implementation, hash will hash the list above depending
	 * on which instance of HashingStrategy is provided.
	 * 
	 * @param hs
	 *            an instance of HashingStrategy
	 * @return a set of hashes
	 */
	public Set<Integer> hash(HashingStrategy hs) {
		// return the set by delegating the work to the hashing algorithm and
		// returning what it returns
		return hs.hash(shingles);
	}
}
