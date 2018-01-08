package ie.gmit.sw;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class HasheratorImpl implements Hasherator {
	// Member Variables

	// Integer TreeSets that hold the hashed value of the shingles
	private Set<Integer> hashedShingles = new TreeSet<Integer>();
	private Set<Integer> minHashedShingles = new TreeSet<Integer>();

	private Set<Integer> hashes = new TreeSet<Integer>(); // Random hashes for
														  // minHash
														  // Algorithm.
	
	private final int K = 200; // Number of min hashes K - can be computed but I
							   // decided to stick with a fixed 303 for all files.
	
	private final int SEED = 40; // The seed for random integer generation so
								  // that each shingle gets hashed the same
								  // way
								  // So that if the same document were to get
								  // hashed, it will produce the same output.

	// Default Constructor
	public HasheratorImpl() {
		// TODO Auto-generated constructor stub
	}

	// Produce a set of hashcodes from an arraylist of strings.
	@Override
	public Set<Integer> hasher(List<String> shingles) {

		for (String shingle : shingles) {
			hashedShingles.add(shingle.hashCode());
		}
		return hashedShingles;
	}

	// Produce a fixed set of minimum hashcodes from an arraylist of strings
	@Override
	public Set<Integer> minHasher(List<String> shingles) {
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
			minHashedShingles.add(min);
		}
		return minHashedShingles;
	}

}
