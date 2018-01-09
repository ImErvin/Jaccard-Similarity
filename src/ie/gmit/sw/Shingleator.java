package ie.gmit.sw;

import java.util.List;

/**
 * Shingleator Interface describes a contract for all shingling operators.
 * Shingleator objects are those used to work with word arraylists to create
 * shingles. This enhances abstraction and Single Responsibility Rule.
 */
public interface Shingleator {
	/**
	 * All shinglerators have a shingler method that takes a list of words and
	 * creates shingles from them.
	 * 
	 * @param words the list of words to be shingled
	 * @return return the list of words post shingling
	 */
	public List<String> shingler(List<String> words);
}
