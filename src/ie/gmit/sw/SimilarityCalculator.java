package ie.gmit.sw;

import java.util.*;

public class SimilarityCalculator {
	private List<Double> allCalculations = new ArrayList<Double>();
	private SimilarityAlgorithm sa;
	
	public double calculateAllDocs(List<Document> documents, Set<Integer> a, SimilarityAlgorithm sa){
		this.sa = sa;
		if(documents.size() == 1){
			return 0;
		}else{
			
			for(int i = 0; i < documents.size()-1; i++){
				addJaccardIndex(sa.compareSimilarity(a, documents.get(i).getHashes()));
			}
		}
		return calculateAvg();
	}
	
	public void addJaccardIndex(double ji) {
		System.out.println(ji);
		allCalculations.add(ji);
	}

	public double calculateAvg() {
		double sum = 0;
		for (double i : allCalculations) {
			sum += i;
		}
		System.out.println(sum + " / " + (allCalculations.size()) );
		return (sum / allCalculations.size()) * 100;
	}
}
