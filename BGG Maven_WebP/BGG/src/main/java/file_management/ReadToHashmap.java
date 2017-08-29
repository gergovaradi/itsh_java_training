package file_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ReadToHashmap {
	
	 public  Map<String, String> readToMap() throws Exception {
		 
	        Map<String, String> map = new HashMap<String, String>();
	        BufferedReader in = null;
	        try{
	        in = new BufferedReader(new FileReader("example.txt"));}
	        catch(Exception e){
	        	System.out.println("Read file error: " + e.getMessage());
	        }
	        String line = "";
	        while ((line = in.readLine()) != null) {
	            String parts[] = line.split("-");
	            map.put(parts[0], parts[1]);
	        }
	        in.close();
	        System.out.println(map.toString());
	        return map;
	    }

}
