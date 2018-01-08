package ie.gmit.sw;

import java.util.List;
import java.util.Set;

public interface Hasherator {
	public Set<Integer> hasher(List<String> shingles);
	public Set<Integer> minHasher(List<String> shingles);
}
