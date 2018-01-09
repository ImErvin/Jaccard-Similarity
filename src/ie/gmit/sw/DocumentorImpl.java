package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentorImpl implements Documentor{
	private List<String> words = new ArrayList<String>();
	
	public DocumentorImpl() {
		
	}
	
	@Override
	public List<String> readDocument(BufferedReader br) throws IOException {
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
		}
		
		return words;
	}
}
