package ie.gmit.sw;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HashCodeHashing implements HashingStrategy {
	// Integer TreeSets that hold the hashed value of the shingles
	private Set<Integer> hashedShingles = new TreeSet<Integer>();

	@Override
	public Set<Integer> hash(List<String> shingles) {
		for (String shingle : shingles) {
			hashedShingles.add(shingle.hashCode());
		}
		return hashedShingles;
	}
	
	
}
