package ie.gmit.sw;

import java.util.*;

public class Hashing{
	List<String> shingles = new ArrayList<String>();
	
	public Hashing() {

	}
	
	public Hashing(List<String> shingles) {
		this.shingles = shingles;
	}
	
	public Set<Integer> hash(HashingStrategy hs){
		return hs.hash(shingles);
	}
}
