package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

public class JaccardAlgorithm implements SimilarityAlgorithm{
	
	private Set<Integer> n = new TreeSet<Integer>();
	private int intersectionCardinality;
	
	@Override
	public double compareSimilarity(Set<Integer> a, Set<Integer> b) {
		n = a;
		n.retainAll(b);
		intersectionCardinality = n.size();
		return intersectionCardinality / (a.size() + b.size()) - intersectionCardinality;
	}

}
