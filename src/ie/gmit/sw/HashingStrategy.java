package ie.gmit.sw;

import java.util.List;
import java.util.Set;

/**
 * Hasherator Interface describes a contract for all documentor objects.
 * Documentor objects are those used to work with Files. This enhances
 * abstraction and Single Responsibility Rule.
 */
public interface HashingStrategy {
	public Set<Integer> hash(List<String> shingles);
}
