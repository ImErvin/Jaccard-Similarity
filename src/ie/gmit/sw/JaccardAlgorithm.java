package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

public class JaccardAlgorithm implements SimilarityAlgorithm{
	
	private Set<Integer> n = new TreeSet<Integer>();
	private int intersectionCardinality;
	
	public JaccardAlgorithm() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double compareSimilarity(Set<Integer> a, Set<Integer> b) {
		n = a;
		n.retainAll(b);
		intersectionCardinality = n.size();
		System.out.println("Algorithm = \n " + intersectionCardinality + " \n --------------------- \n" + 
		a.size() + " + " + b.size() + " - " + intersectionCardinality);
		return intersectionCardinality / ((a.size() + b.size()) - intersectionCardinality);
	}

}
