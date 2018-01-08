package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

public class JaccardAlgorithm implements SimilarityAlgorithm{
	
	private Set<Integer> n = new TreeSet<Integer>();
	private int intersectionCardinality;
	private int unionCardinatlity;
	
	public JaccardAlgorithm() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double compareSimilarity(Set<Integer> a, Set<Integer> b) {
		n = a;
		unionCardinatlity = a.size() + b.size();
		n.retainAll(b);
		intersectionCardinality = n.size();
		
		System.out.println("POWER " + intersectionCardinality + " " + unionCardinatlity);
		return (Double.valueOf(intersectionCardinality) / (Double.valueOf(unionCardinatlity) - Double.valueOf((intersectionCardinality))) * 100);
	}

}
