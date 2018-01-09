package ie.gmit.sw;

import java.util.*;

public class SimilarityCalculator {
	private List<Double> jaccardIndices = new ArrayList<Double>();
	private JaccardAlgorithm ja = new JaccardAlgorithm();
	
	public double calculateAllDocs(List<Document> documents, Set<Integer> a){
		System.out.println(documents.size());
		if(documents.size() == 1){
			return 0;
		}else{
			
			for(int i = 0; i < documents.size()-1; i++){
				addJaccardIndex(ja.compareSimilarity(a, documents.get(i).getHashes()));
			}
		}
		return calculateAvg();
	}
	
	public void addJaccardIndex(double ji) {
		System.out.println(ji);
		jaccardIndices.add(ji);
	}

	public double calculateAvg() {
		double sum = 0;
		for (double i : jaccardIndices) {
			sum += i;
		}
		System.out.println(sum + " / " + (jaccardIndices.size()) );
		return (sum / jaccardIndices.size()) * 100;
	}
}
