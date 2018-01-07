package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class Shingleator {
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> shingles = new ArrayList<String>();
	private int shingleSize = 3;
	
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
	
	public void createShingles(){
		String shingle = "";
		int control = 0;
		for(int i = 0; i < words.size(); i++){
			shingle += words.get(i);
			control++;
			if(control == shingleSize){
				shingles.add(shingle);
				shingle = "";
				control = 0;
			}
			if(control < shingleSize && i == words.size()-1)
			{
				shingles.add(shingle);
			}
		}
	}
	
	public ArrayList<String> getWords() {
		return words;
	}

	public ArrayList<String> getShingles() {
		return shingles;
	}
}
