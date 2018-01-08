package ie.gmit.sw;

import java.util.*;

public class SimilarityCalculator {
	private List<Double> jaccardIndices = new ArrayList<Double>();
	private JaccardAlgorithm ja = new JaccardAlgorithm();
	
	public void calculateJaccard(Set<Integer> a, Set<Integer> b){
		addJaccardIndex(ja.compareSimilarity(a, b));
	}
	
	public void addJaccardIndex(double ji) {
		jaccardIndices.add(ji);
	}

	public double calculateAvg() {
		double sum = 0;
		for (double index : jaccardIndices) {
			sum += index;
		}
		return sum / jaccardIndices.size();
	}
}
