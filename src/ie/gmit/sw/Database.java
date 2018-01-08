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

	public boolean addDocument(Document d) {
		System.out.println("Open DB Add Documento");
		ObjectContainer db = Db4oEmbedded.openFile("database.db4o");
		try {
			db.store(d);
			db.commit();
			return true;
		} catch (Exception e) {
			db.rollback();
			return false;
		} finally {
			db.close();
			System.out.println("Closing DB Add Documento");
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
	
	public List<Document> getAllDocuments(){
		System.out.println("Open DB Get All Documento");
		ObjectContainer db = Db4oEmbedded.openFile("database.db4o");
		try {
			documents = db.queryByExample(Document.class);
			db.commit();
			return documents;
		} catch (Exception e) {
			db.rollback();
			return null;
		} finally {
			try{
				db.close();
				System.out.println("Closing DB Get All Documento");
			}catch(Exception e)
			{
				
			}
		}
	}

}
