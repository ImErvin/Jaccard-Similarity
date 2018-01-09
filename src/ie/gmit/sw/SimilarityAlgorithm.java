package ie.gmit.sw;

import java.util.Set;

/**
 * SimilarityAlgorithm Interface describes a contract for all similarity
 * algorithms. SimilarityAlgorith objects are those used to calculate the
 * similarity of 2 documents. This enhances abstraction and Single
 * Responsibility Rule.
 */
public interface SimilarityAlgorithm {
	/**
	 * Each similarity algorithm will have a compare similarity (assuming that we are always comparing sets)
	 * 
	 * @param a Set a to be compared to b
 	 * @param b Set b to be compared to a
	 * @return a double value of the comparison 
	 */
	public double compareSimilarity(Set<Integer> a, Set<Integer> b);
}
