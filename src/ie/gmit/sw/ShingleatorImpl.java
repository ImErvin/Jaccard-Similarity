package ie.gmit.sw;

import java.util.*;

public class ShingleatorImpl implements Shingleator{
	private List<String> shingles = new ArrayList<String>();
	private final int SHINGLE_SIZE = 3;
	
	public ShingleatorImpl() {

	}

	@Override
	public List<String> shingler(List<String> words) {
		String shingle = "";
		int control = 0;
		for (int i = 0; i < words.size(); i++) {
			shingle += words.get(i);
			control++;
			if (control == SHINGLE_SIZE) {
				shingles.add(shingle);
				shingle = "";
				control = 0;
			}
			if (control < SHINGLE_SIZE && i == words.size() - 1) {
				shingles.add(shingle);
			}
		}
		
		return shingles;
	}
}
