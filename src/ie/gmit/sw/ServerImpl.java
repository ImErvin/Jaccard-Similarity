package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class ServerImpl implements Server{
	private List<String> words = new ArrayList<String>();
	private Database db = Database.getInstance();
	private Shingleator shingleator = new ShingleatorImpl();
	private Hasherator hasherator = new HasheratorImpl();
	private Document d;
	//private Set<Integer> hashedShingles = new TreeSet<Integer>();
	
	public ServerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void authenticate() {
		d = new Document();
	}
	
	@Override
	public void readDocument(String title, BufferedReader br) throws IOException {
		String line = "";
		try{
			while ((line = br.readLine()) != null) {
				
				String[] split = line.split(" ");
				for(int i = 0; i < split.length; i++){
					words.add(split[i]);
				}
			}
		}finally{
			br.close();
			d.setTitle(title);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}
	
}
