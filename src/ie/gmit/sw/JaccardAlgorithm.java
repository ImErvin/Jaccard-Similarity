package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

/**
 * JaccardAlgorithm is an implementation of Similarity Algorithm
 * JaccardAlgorithm serves as a SimilarityAlgorithm, this is based on the
 * Jaccard Index algorithm |A n B| / |A u B|.The intention is to decouple
 * classes and uphold the single responsibility rule. JaccardAlgorithm is-a
 * SimilarityAlgorithm (Inheritance).
 */
public class JaccardAlgorithm implements SimilarityAlgorithm {
	// Temp set N
	private Set<Integer> n;
	// | A n B |
	private int intersectionCardinality;
	// | A u B |
	private int unionCardinatlity;

	/**
	 * Default Constructor
	 */
	public JaccardAlgorithm() {

	}

	@Override
	public double compareSimilarity(Set<Integer> a, Set<Integer> b) {
		// Unioncardinatlity is the size of set a + the size of set b
		unionCardinatlity = a.size() + b.size();
		// Create a new treeset of integers composed of set a
		n = new TreeSet<Integer>(a);
		// Delete all except common integers of n and b
		n.retainAll(b);
		// The cardinality of intersection is the size of n (whatever is left
		// over after deleting non-common integers
		intersectionCardinality = n.size();
		// Return calculation (| A n B | / | A | + | B | - | A n B |)
		return Double.valueOf(intersectionCardinality)
				/ (Double.valueOf(unionCardinatlity) - Double.valueOf((intersectionCardinality)));
	}

}
