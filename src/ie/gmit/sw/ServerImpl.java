package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class ServerImpl implements Server {
	private List<String> words;
	private Documentor documentor;
	private Database db;
	private Shingleator shingleator;
	private Hashing hashing;
	private SimilarityCalculator sc;
	private Document d;
	// private Set<Integer> hashedShingles = new TreeSet<Integer>();

	public ServerImpl() {
		documentor = new DocumentorImpl();
		db = Database.getInstance();
		shingleator = new ShingleatorImpl();
		sc = new SimilarityCalculator();
	}

	@Override
	public boolean authenticate(String title, String firstLine) {
		d = new Document();
		return true;
	}

	@Override
	public void readDocument(String title, BufferedReader br) throws IOException {
		try {
			words = documentor.readDocument(br);
			d.setTitle(title);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	@Override
	public void processDocument(HashingMethod hashingMethod) {
		hashing = new Hashing(shingleator.shingler(words));
		d.setHashes(
				hashing.hash(hashingMethod == hashingMethod.MINHASH ? new MinHashHashing() : new HashCodeHashing()));
	}

	@Override
	public List<String> displayDocument() {
		return words;
	}

	@Override
	public boolean addDocument() {
		return db.addDocument(d);
	}

	@Override
	public double compareSim() {
		return sc.calculateAllDocs(new ArrayList<Document>(db.getAllDocuments()), d.getHashes());
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	@Override
	public double process(String title, BufferedReader br, HashingMethod hashingMethod) throws IOException {
		readDocument(title, br);
		processDocument(hashingMethod);
		addDocument();
		return compareSim();
	}

}
