package ie.gmit.sw;

import java.util.*;
import com.db4o.*;

public class Database {
	private List<Document> documents = new ArrayList<Document>();
	private static Database instance = null;
	//private ObjectContainer db = Db4oEmbedded.openFile("database.db4o");
	
	private Database() {

	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
			System.out.println("Created an instance");
		}
		return instance;
	}

	@SuppressWarnings("finally")
	public boolean addDocument(Document d) {
		System.out.println("Open DB Add Documento");
		ObjectContainer db = Db4oEmbedded.openFile("database.db4o");
		try {
			db.store(d);
			db.commit();
		} catch (Exception e) {
			db.rollback();
			return false;
		} finally {
			db.close();
			System.out.println("Closing DB Add Documento");
			return true;
		}
	}

	public Document getFirstDocument() {
		System.out.println("Open DB Get Documento");
		ObjectContainer db = Db4oEmbedded.openFile("database.db4o");
		System.out.println("WO");
		try {
			documents = db.queryByExample(Document.class);
			db.commit();
			return documents.get(0);
		} catch (Exception e) {
			db.rollback();
			return null;
		} finally {
			db.close();
			System.out.println("Closing DB Get Documento");
		}
	}

}
