package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class ServerImpl implements Server{
	private List<String> words = new ArrayList<String>();
	private Documentor documentor = new DocumentorImpl();
	private Database db = Database.getInstance();
	private Shingleator shingleator = new ShingleatorImpl();
	private Hasherator hasherator = new HasheratorImpl();
	private SimilarityCalculator sc = new SimilarityCalculator();
	private Document d;
	//private Set<Integer> hashedShingles = new TreeSet<Integer>();
	
	public ServerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start() {
		d = new Document();
	}
	
	@Override
	public void readDocument(String title, BufferedReader br) throws IOException {
		try{
			words = documentor.readDocument(br);
			d.setTitle(title);
		}catch(IOException ioe){
			System.out.println(ioe);
		}
	}
	
	@Override
	public void processDocument(HashingMethod hashingMethod) {
		if(hashingMethod == HashingMethod.HASHCODE){
			d.setHashes(hasherator.hasher(shingleator.shingler(words)));
		}else{
			d.setHashes(hasherator.minHasher(shingleator.shingler(words)));
		}
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
	
}
