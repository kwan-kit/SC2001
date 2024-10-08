package sc2001_project2_djikstra;

import java.io.*;
import java.util.*;

import com.opencsv.CSVWriter;

public class CSVList {

	public static void writeDataLineByLine(String filePath, String[][] stringsToPrint) 
	{ 
	    // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath); 
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding header to csv 
	        writer.writeNext(new String[] {"Vertices", "Edges", "KeyComp", "ElapsedTime"});
	        for (String[] strings : stringsToPrint) {
	        	writer.writeNext(strings);
	        }
	        /*
	        String[] header = { "Name", "Class", "Marks" }; 
	        writer.writeNext(header); 
	  
	        // add data to csv 
	        String[] data1 = { "Aman", "10", "620" }; 
	        writer.writeNext(data1); 
	        String[] data2 = { "Suraj", "10", "630" }; 
	        writer.writeNext(data2); 
	        */
	  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	} 
	
	public static void writeDataAtOnce(String filePath, String[][] stringsToPrint) 
	{ 
	  
	    // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath); 
	  
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // create a List which contains String array 
	        List<String[]> data = new ArrayList<String[]>(); 
	        data.add(new String[] {"Vertices", "Edges", "KeyComp", "ElapsedTime"});
	        for (String[] strings : stringsToPrint) {
	        	data.add(strings);
	        }
	        /*
	        data.add(new String[] { "Name", "Class", "Marks" }); 
	        data.add(new String[] { "Aman", "10", "620" }); 
	        data.add(new String[] { "Suraj", "10", "630" }); 
	        */
	        writer.writeAll(data); 
	  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	} 

}
