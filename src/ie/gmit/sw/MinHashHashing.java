package ie.gmit.sw;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MinHashHashing implements HashingStrategy {
	// Integer TreeSets that hold the hashed value of the shingles
	private Set<Integer> hashedShingles = new TreeSet<Integer>();

	private Set<Integer> hashes = new TreeSet<Integer>(); // Random hashes for
															// minHash
															// Algorithm.

	private final int K = 200; // Number of min hashes K - can be computed but I
								// decided to stick with a fixed 303 for all
								// files.

	private final int SEED = 40; // The seed for random integer generation so
									// that each shingle gets hashed the same
									// way
									// So that if the same document were to get
									// hashed, it will produce the same output.

	public MinHashHashing() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<Integer> hash(List<String> shingles) {
		// Generate a sequence of random numbers
		Random r = new Random(SEED);
		// Generate K number of hashes to be added to the set
		for (int i = 0; i < K; i++) {
			// Each hash is the next integer in the random sequence.
			hashes.add(r.nextInt());
		}
		// For each integer in hashes.
		for (Integer hash : hashes) {
			// Set the minimum to the maximum as this must be the default if we
			// are to decrease it's value.
			int min = Integer.MAX_VALUE;
			// For each string in the arraylist shingles
			for (String word : shingles) {
				// Hash function = word's hash code to the power of random hash
				// number
				// Use this function to compute each strings hash code.
				int minHash = word.hashCode() ^ hash;
				// For each time the function above is executed, if the result
				// is smaller than MAX_VALUE and
				// all values after, set that as the minimum.
				if (minHash < min)
					min = minHash;
			}
			// Add the most minimum hashes to the set.
			hashedShingles.add(min);
		}
		return hashedShingles;
	}
}
