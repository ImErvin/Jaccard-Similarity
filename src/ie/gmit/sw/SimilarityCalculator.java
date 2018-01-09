package ie.gmit.sw;

import java.util.*;

/**
 * SimilarityCalculator is a gateway method to be able to invoke an algorithm.
 * The intention of this class is to use the strategy pattern in other classes
 * by passing an instance of the interface into the similarity function.
 */
public class SimilarityCalculator {
	// A list of doubles that represents the similarity of all objects
	private List<Double> allCalculations = new ArrayList<Double>();
	// A composition of a similarityalgorithm
	private SimilarityAlgorithm similarityAlgorithm;

	/**
	 * This method is used to calculate the similarity of set A against an array
	 * of size N of sets B.
	 * 
	 * @param documents
	 *            Array of size n of sets B (These are the documents previously
	 *            uploaded)
	 * @param a
	 *            Set a (this is the document being uploaded from client)
	 * @param sa
	 *            This is an instance of similarityalgorithm
	 * @return
	 */
	public double calculateAllDocs(List<Document> documents, Set<Integer> a, SimilarityAlgorithm sa) {
		// StrategyPattern-esque behavior dynamically setting the algorithm to
		// be used.
		similarityAlgorithm = sa;

		// If it's the first document in the documents, return 0 as there is
		// nothing to compare against
		if (documents.size() == 1) {
			return 0;
		} else {
			// Loop over the size of documents - 1 (avoid comparing against
			// itself)
			for (int i = 0; i < documents.size() - 1; i++) {
				// Add the result of the delegated similarity function to
				// allCalculations
				addJaccardIndex(similarityAlgorithm.compareSimilarity(a, documents.get(i).getHashes()));
			}
		}
		// Delegate the avg calculation and return it's response.
		return calculateAvg();
	}

	/**
	 * Method to add to the allCalculations list.
	 * 
	 * @param ji
	 *            a double to be added to the list of doubles.
	 */
	public void addJaccardIndex(double ji) {
		allCalculations.add(ji);
	}

	/**
	 * Method to loop over the list of doubles and calculate the average. This
	 * is used to measure the similarity of all documents.
	 * 
	 * @return the average of the list.
	 */
	public double calculateAvg() {
		// Set a temp double to 0
		double sum = 0;
		// Enhanced loop to loop over each double and add it onto sum.
		for (double i : allCalculations) {
			sum += i;
		}
		// Console log the similarity level.
		System.out.println("Similarity % = " + (sum / allCalculations.size()) * 100);
		// Return the average (sum/size) and multiplied by 100 (100/1) to present a precentile.
		return (sum / allCalculations.size()) * 100;
	}
}
