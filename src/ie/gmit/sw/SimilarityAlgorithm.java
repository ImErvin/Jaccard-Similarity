package ie.gmit.sw;

import java.util.Set;

public interface SimilarityAlgorithm {
	public double compareSimilarity(Set<Integer> a, Set<Integer> b);
}
