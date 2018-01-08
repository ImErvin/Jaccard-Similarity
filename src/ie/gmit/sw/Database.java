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
		}
		return instance;
	}

	public boolean addDocument(Document d) {
		ObjectContainer db = Db4oEmbedded.openFile("database.db4o");
		try {
			db.store(d);
			db.commit();
		} catch (Exception e) {
			db.rollback();
			return false;
		} finally {
			db.close();
			return true;
		}
	}

	public Document getFirstDocument() {
		ObjectContainer db = Db4oEmbedded.openFile("database.db4o");
		System.out.println("WO");
		try {
			documents = db.queryByExample(Document.class);
//			for(Document doc:documents){
//				db.delete(doc);
//			}
			db.commit();
		} catch (Exception e) {
			db.rollback();
		} finally {
			//db.close();
		}

		return documents.get(documents.size()-1);
	}

}
