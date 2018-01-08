package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

public class JaccardAlgorithm implements SimilarityAlgorithm{
	
	private Set<Integer> n;
	private int intersectionCardinality;
	private int unionCardinatlity;
	
	public JaccardAlgorithm() {
		
	}
	
	@Override
	public double compareSimilarity(Set<Integer> a, Set<Integer> b) {
		unionCardinatlity = a.size() + b.size();
		n = new TreeSet<Integer>(a);
		n.retainAll(b);
		intersectionCardinality = n.size();
		return Double.valueOf(intersectionCardinality) / (Double.valueOf(unionCardinatlity) - Double.valueOf((intersectionCardinality)));
	}

}
