package ie.gmit.sw;

import java.util.*;

/**
 * Document Object - Used as an abstract template of what each document should
 * look like. The aim is to provide general OOP by templating objects.
 */
public class Document {
	// Title of the document
	private String title;
	// Contents of the document post hashing
	private Set<Integer> hashes = new TreeSet<Integer>();

	/**
	 * Default Constructor
	 * 
	 */
	public Document() {

	}

	/**
	 * Default Constructor using Fields
	 * 
	 * @param title
	 *            set's the string
	 * @param hashes
	 *            set's the treeset
	 */
	public Document(String title, Set<Integer> hashes) {
		super();
		this.title = title;
		this.hashes = hashes;
	}

	// Getters and Setters for stronger encapsulation and to be able to use
	// private variables
	// publicly.
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Integer> getHashes() {
		return hashes;
	}

	public void setHashes(Set<Integer> hashes) {
		this.hashes = hashes;
	}

}
