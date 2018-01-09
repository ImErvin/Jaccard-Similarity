package ie.gmit.sw;

import java.util.*;
import com.db4o.*;

/**
 * Database class - The aim of this class was to provide a way of accessing the
 * database for all the other classes. I made the database a singleton because a
 * database should only have one instance and a global point of access. Also
 * this is a way of respecting the single responsibility rule - the database
 * does what a database should do and that's it.
 */
public class Database {
	// ArrayList of Document Objects
	private ArrayList<Document> documents;
	// The instance of the database
	private static Database instance = null;

	/**
	 * Private constructor to ensure it cannot be instantiated publicly.
	 */
	private Database() {

	}

	/**
	 * Creates an instance of database if one does not already exist. Upholds
	 * the singleton pattern.
	 * 
	 * @return the instance of Database to be used by other classes.
	 */
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
			System.out.println("Created an instance");
		}
		return instance;
	}

	/**
	 * Adds a Document object to the db4o database.
	 * 
	 * @param d
	 *            the Document object to be added to the database
	 * @return boolean to that returns true if successful and false if
	 *         unsuccessful
	 */
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

	/**
	 * Returns all the Document objects in the db4o database.
	 * 
	 * @return an ArrayList of Document objects to be used by other classes.
	 */
	public List<Document> getAllDocuments() {
		System.out.println("Open DB Get All Documento");
		ObjectContainer db = Db4oEmbedded.openFile("database.db4o");
		try {
			documents = new ArrayList<Document>(db.queryByExample(Document.class));
			db.commit();
			return documents;
		} catch (Exception e) {
			db.rollback();
			return null;
		} finally {
			try {
				db.close();
				System.out.println("Closing DB Get All Documento");
			} catch (Exception e) {

			}
		}
	}

}
