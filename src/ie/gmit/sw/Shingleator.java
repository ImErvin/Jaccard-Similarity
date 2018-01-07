package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class Shingleator {
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> shingles = new ArrayList<String>();
	private Set<Integer> shinglesHashCodes = new TreeSet<Integer>();
	private Set<Integer> shinglesHashCodes1 = new TreeSet<Integer>();
	private Set<Integer> n = new TreeSet<Integer>();
	private int shingleSize = 3;
	private int k = 200;
	private Set<Integer> hashes = new TreeSet<Integer>();
	
	
	public Shingleator() {
		
	}
	
	public void parseFile(BufferedReader br) throws IOException{
		String line = null;
		while ((line = br.readLine()) != null) {
			//Break each line up into shingles and do something. The servlet really should act as a
			//contoller and dispatch this task to something else... Divide and conquer...! I've been
			//telling you all this since 2nd year...!
			String[] split = line.split(" ");
			for(int i = 0; i < split.length; i++){
				words.add(split[i]);
			}
		}
	}
	
	public void createHashes(){
		Random r = new Random();
		for(int i = 0; i < 200; i++)
		{
			hashes.add(r.nextInt());
		}
		for(Integer hash : hashes)
		{
			int min = Integer.MAX_VALUE;
			for(String word : shingles){
				int minHash = word.hashCode() ^ hash;
				if(minHash < min) min = minHash;
			}
			shinglesHashCodes.add(min);
		}
		
		for(Integer h : shinglesHashCodes){
			System.out.println(h);
		}
		
		createHashes2();
		
//		for(Integer i : shinglesHashCodes)
//		{
//			System.out.println(i);
//		}
	}
	
	public void createHashes2(){
		Random r = new Random();
		for(int i = 0; i < 200; i++)
		{
			hashes.add(r.nextInt());
		}
		for(Integer hash : hashes)
		{
			int min = Integer.MAX_VALUE;
			for(String word : shingles){
				int minHash = word.hashCode() ^ hash;
				if(minHash < min) min = minHash;
			}
			shinglesHashCodes1.add(min);
		}
		shinglesHashCodes1.retainAll(shinglesHashCodes);
		
		System.out.println("\n\n SECOND FELLAS!! \n\n");
		for(Integer h : shinglesHashCodes1){
			System.out.println(h);
		}
		
//		for(Integer i : shinglesHashCodes)
//		{
//			System.out.println(i);
//		}
	}
	
	public void createShingles(){
		String shingle = "";
		int control = 0;
		for(int i = 0; i < words.size(); i++){
			shingle += words.get(i);
			control++;
			if(control == shingleSize){
				shingles.add(shingle);
				shinglesHashCodes.add(shingle.hashCode());
				shingle = "";
				control = 0;
			}
			if(control < shingleSize && i == words.size()-1)
			{
				shingles.add(shingle);
//				shinglesHashCodes.add(shingle.hashCode());
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
