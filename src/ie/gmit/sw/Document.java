package ie.gmit.sw;

import java.util.*;

public class Document {
	private String title;
	private Set<Integer> hashes = new TreeSet<Integer>();
	
	public Document() {

	}
	
	public Document(String title, Set<Integer> hashes) {
		super();
		this.title = title;
		this.hashes = hashes;
	}

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
