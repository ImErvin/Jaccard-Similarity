package ie.gmit.sw;

import java.util.*;

public class SimilarityCalculator {
	private List<Double> jaccardIndices = new ArrayList<Double>();
	private JaccardAlgorithm ja = new JaccardAlgorithm();
	
	public double calculateJaccard(Set<Integer> a, Set<Integer> b){
		return ja.compareSimilarity(a, b);
	}
	
	public void calculateAllDocs(List<Document> documents, Set<Integer> a){
		for(Document d:documents){
			addJaccardIndex(calculateJaccard(a, d.getHashes()));
		}
	}
	
	public void addJaccardIndex(double ji) {
		jaccardIndices.add(ji);
	}

	public double calculateAvg() {
		double sum = 0;
		for (double i : jaccardIndices) {
			sum += i;
		}
		return sum / jaccardIndices.size();
	}
}
