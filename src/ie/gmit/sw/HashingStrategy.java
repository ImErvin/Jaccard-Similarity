package ie.gmit.sw;

import java.util.List;
import java.util.Set;

/**
 * HashingStrategy is an interface for all hashing methods (algorithms).
 * HashingStrategy's children provide a method of hashing a document for
 * algorithm calculations and saving to db. This improves abstraction and also
 * follows the SRR.
 */
public interface HashingStrategy {
	/**
	 * Method to take a list of words and apply a hash function to them.
	 * 
	 * @param shingles the list of words to be hashed.
	 * @return The set of hashes after words have been hashed.
	 */
	public Set<Integer> hash(List<String> shingles);
}
