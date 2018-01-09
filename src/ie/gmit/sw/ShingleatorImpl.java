package ie.gmit.sw;

import java.util.*;

/**
 * ShingleatorImpl is an implementation of Shingleator ShingleatorImpl serves as
 * a simple shingle function that creates shingles of size n.
 * The intention is to decouple classes and uphold the single responsibility
 * rule. ShingleatorImpl is-a Shingleator (Inheritance).
 */
public class ShingleatorImpl implements Shingleator {
	// Shingled list of words
	private List<String> shingles = new ArrayList<String>();
	// Constant shingle size of fixed 2 - Could add a dynamic variable but decided to keep it
	// simple
	private final int SHINGLE_SIZE = 2;

	/**
	 * Defualt constructor
	 */
	public ShingleatorImpl() {

	}
	
	@Override
	public List<String> shingler(List<String> words) {
		// Set a temp string to null
		String shingle = "";
		// Set the control to 0
		int control = 0;
		// Loop over the array of words fed as a parameter
		for (int i = 0; i < words.size(); i++) {
			// Add each word to the shingle using += operator
			shingle += words.get(i);
			// Increment control
			control++;
			// If the control is the same size as the shingle size
			if (control == SHINGLE_SIZE) {
				// Add the temp string to shingles list
				shingles.add(shingle);
				// Reset temp string
				shingle = "";
				// Reset control
				control = 0;
			}
			// If the control is less than the shingle size and we're at the end of the loop
			// Add the shingle anyway. (This is to catch out any remainders of i % shingle_size
			if (control < SHINGLE_SIZE && i == words.size() - 1) {
				shingles.add(shingle);
			}
		}
		
		// Return the shingles
		return shingles;
	}
}
