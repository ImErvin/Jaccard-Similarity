package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class Shingleator {
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> shingles = new ArrayList<String>();
	private Set<Integer> shinglesHashCodes = new TreeSet<Integer>();
	private int shingleSize = 3;
	private int k = 200;
	private Set<Integer> hashes = new TreeSet<Integer>();
	private String title;
	private Database db = Database.getInstance();
		
	public Shingleator() {

	}

	public void parseFile(String title, BufferedReader br) throws IOException {
		this.title = title;
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] split = line.split(" ");
			for (int i = 0; i < split.length; i++) {
				words.add(split[i]);
			}
		}
	}

	public void createHashes() {
//		Random r = new Random(40);
//		System.out.println(r);
//		for (int i = 0; i < k; i++) {
//			hashes.add(r.nextInt());
//		}
//		for (Integer hash : hashes) {
//			int min = Integer.MAX_VALUE;
//			for (String word : shingles) {
//				int minHash = word.hashCode() ^ hash;
//				if (minHash < min)
//					min = minHash;
//			}
//			shinglesHashCodes.add(min);
//		}
		
		for(String shingle:shingles){
			shinglesHashCodes.add(shingle.hashCode());
		}
		Document d = new Document(title, shinglesHashCodes);

		//db.addDocument(d);
		System.out.println(shinglesHashCodes);
		Set<Integer> comparor = db.getFirstDocument().getHashes();
		System.out.println(comparor);
		shinglesHashCodes.retainAll(comparor);
		System.out.println(shinglesHashCodes.size());
	}

	public void createShingles() {
		String shingle = "";
		int control = 0;
		for (int i = 0; i < words.size(); i++) {
			shingle += words.get(i);
			control++;
			if (control == shingleSize) {
				shingles.add(shingle);
				shingle = "";
				control = 0;
			}
			if (control < shingleSize && i == words.size() - 1) {
				shingles.add(shingle);
			}
		}
		createHashes();
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public ArrayList<String> getShingles() {
		return shingles;
	}
}
