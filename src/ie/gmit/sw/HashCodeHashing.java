package ie.gmit.sw;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * HashCodeHashing is an implementation of HashingStrategy
 * HashCode serves as a hashing function, this is the simple .hashCode()
 * representation of shingles.The intention is to decouple classes and uphold
 * the single responsibility rule. HashCodeHashing is-a HashingStrategy (Inheritance).
 */
public class HashCodeHashing implements HashingStrategy {
	// Integer TreeSets that hold the hashed value of the shingles
	private Set<Integer> hashedShingles = new TreeSet<Integer>();

	@Override
	public Set<Integer> hash(List<String> shingles) {
		// For each shingle in shingles
		for (String shingle : shingles) {
			// Simply just get the hashCode of the shingle and add it to the hashedShingles list.
			hashedShingles.add(shingle.hashCode());
		}
		return hashedShingles;
	}
	
	
}
