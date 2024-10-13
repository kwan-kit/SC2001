package sc2001_project2_djikstra;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DijkstraA {
	
	int NumOfVertices;                            // Total number of vertices in graph
	int pQueue[];                                 // Array as priority queue
	int d[];                                      // The output array d[i] will hold the shortest distance from source to vertex i
	int pi[];                                     // Predecessors
	int lenQueue;                                 // Current number of vertices in queue
	Boolean set_S[];                              //Vertices whose shortest paths have been found
	int keyComp;
	
	public int dijkstra(int graph[][], int source) 
	{
		keyComp = 0;
		NumOfVertices = graph.length;
		pQueue = new int[NumOfVertices];  // Array as priority queue
		d = new int[NumOfVertices];       // The output array d[i] will hold the shortest distance from source to i
		pi = new int[NumOfVertices]; 
		lenQueue = 0;
		Boolean set_S[] = new Boolean[NumOfVertices];
		
		
		// Initialize all distances as INFINITE and stpSet[] as false
		for (int i = 0; i < NumOfVertices; i++) {
			d[i] = Integer.MAX_VALUE;
			pi[i] = -1;
			set_S[i] = false;
		}
		
        d[source] = 0;        // Distance of source vertex from itself is always 0
        pi[source] = 0;       // Predecessor of source vertex is itself
        insertSorted(source); // Insert source into pQueue
        
        for (int i = 1; i < NumOfVertices; i++) {   // Insert all other vertices into pQueue
        	insertSorted(i);
        	
        }
        
        while(lenQueue != 0) {
        	int u = pQueue[0];                     // extract and delete vertex with the shortest distance from pQueue                           
        	
        	deleteFirstElement(pQueue, lenQueue); 
        	set_S[u] = true;                       //add u to set S
        	
        	for (int v=0; v<NumOfVertices; v++) {
        		keyComp++;
        		
        		if (!set_S[v] && graph[u][v]!=0      //v is not in set S, and is connected to u
            		&& d[u] != Integer.MAX_VALUE     //and distance of u is found
            		&& d[u] + graph[u][v] < d[v]) {  //and distance of u plus edge from u to v is less than current distance of v
    	
        			deleteElement(pQueue, v);
        			d[v] = d[u]+graph[u][v];         //Update distance of v
        			pi[v] = u;                       //Update the predecessor of u

        			insertSorted(v);                 //Insert v into pQueue in sorted order
        		}
        	}
        }
        
        //printSolution(d);
        return keyComp;
	}
	
//Other Methods ---------------------------------------------------------------------------------------------------------------------------------//
	
	void printSolution(int dist[]) 	//print the results
	{
		System.out.println("Vertex \t\t Distance from Source \t\t Predecessors");
		for (int i=0; i<NumOfVertices; i++) {
			System.out.println(i+" \t\t "+dist[i]+" \t\t\t\t "+pi[i]);
		}
	}
	
	int search(int arr[], int key) {
		
		for (int i=0; i<arr.length; i++) {
			if(arr[i]==key) return i;
			
		} return -1;
	}

	int deleteElement(int arr[], int key)
	{
		int n = pQueue.length;
	
		int pos = search(arr, key); // Find position of element to be deleted
		
		if (pos == -1) {
			//System.out.println("Element " + key + " not found");
			return lenQueue; 
		} //System.out.println("Element " + key + " found");

		// Deleting element
		int i;
		for (i = pos; i < n-1; i++) {
			arr[i] = arr[i + 1];
		}
		lenQueue--;
		
		if (arr[i]==arr[i-1]) arr[i] = -1;
		return n-1;
	}
	
	void deleteFirstElement(int arr[], int n) // Delete first element of queue
    {
        for (int i = 0; i < n - 1; i++) {
            arr[i] = arr[i + 1];
        }
        lenQueue--;
    }
	
	int insertSorted(int vertex) //Insert a vertex into pQueue, in the priority of their distance from the source node
	{
		//System.out.println("Inserting " + vertex);
		if (lenQueue >= pQueue.length) {
			return -1;
		}
		
		int i; //current index of last node in queue
		
		for(i = lenQueue-1; (i>=0 && d[pQueue[i]]>d[vertex]); i--) {
			pQueue[i+1] = pQueue[i];
		} 
		
		pQueue[i+1] = vertex;
		lenQueue++;
		
		//System.out.println(Arrays.toString(pQueue));
		
		return(lenQueue);
	}
	
//---------------------------------------------------------------------------------------------------------------------------------//
	public static void main(String[] args)
    {
    	int sampleSize = 90;
    	int stepSize = 100;
    	
    	int e = 1000;
    	int fixedV = 100;
    	
    	int v = 1000;
		int fixedE = 20000;
		
    	
    	DijkstraA t = new DijkstraA();
    	String[][] data = new String[sampleSize][];
    	
    	
    	for(int fileNum = 1; fileNum<=90; fileNum++) { //fixed v
    		
    		System.out.println("file " + fileNum);
    		int graph[][] = new int[fixedV][fixedV];
    		String fileName = "Fixed V Graphs/output" + Integer.toString(fileNum) + ".txt";
	    	
	    	//read generated graph from output.txt
	    	try {
	    	
		    	File adjMatrices = new File(fileName);
		    	Scanner myReader = new Scanner(adjMatrices);
	    	
		    	int i = 0;
	    	
		    	while (myReader.hasNextLine()) {
		    		
		    		String graphData = myReader.nextLine();
		    		String row[] = graphData.split(",");
		    		
		    		for(int j=0; j<fixedV; j++) 
		    			graph[i][j] = Integer.parseInt(row[j]);
		    		
		    		i++;
	    	}
	    	myReader.close();
    	
	    	} catch (FileNotFoundException err) {
	    	 System.out.println("An error occurred.");
	         err.printStackTrace();
	    	}
	        					     
	        double startTime = System.nanoTime();	
	        int keyComp = t.dijkstra(graph, 0);
	        double endTime = System.nanoTime();
	        double duration = (endTime - startTime);
	   
	        String result[] = {String.valueOf(fixedV), String.valueOf(e), String.valueOf(keyComp), String.valueOf(duration/1000000)};
	        data[(e/100)-10] = result;
	        
	        e += stepSize;
	        System.out.println(fileNum + " done");
	        
    	} //end fixed v 
    	
    	CSVList.writeDataAtOnce("AdjMatrix_FixedV_Results.csv", data);
    	
    	
	for(int fileNum = 1; fileNum<=90; fileNum++) { //fixed e
    		
    		System.out.println("file " + fileNum);
			int graph[][] = new int[v][v];
    		String fileName = "Fixed E Graphs/output" + Integer.toString(fileNum) + ".txt";
	    	
	    	//read generated graph from output.txt
	    	try {
	    	
		    	File adjMatrices = new File(fileName);
		    	Scanner myReader = new Scanner(adjMatrices);
	    	
		    	int i = 0;
	    	
		    	while (myReader.hasNextLine()) {
		    		
		    		String graphData = myReader.nextLine();
		    		String row[] = graphData.split(",");
		    		
		    		for(int j=0; j<fixedV; j++) 
		    			graph[i][j] = Integer.parseInt(row[j]);
		    		
		    		i++;
	    	}
	    	myReader.close();
    	
	    	} catch (FileNotFoundException err) {
	    	 System.out.println("An error occurred.");
	         err.printStackTrace();
	    	}
    	
	        double startTime = System.nanoTime();	
	        int keyComp = t.dijkstra(graph, 0);
	        double endTime = System.nanoTime();
	        double duration = (endTime - startTime);
	   
	        String result[] = {String.valueOf(v), String.valueOf(fixedE), String.valueOf(keyComp), String.valueOf(duration/1000000)};
	        data[(v/100)-10] = result;
	        
	        v += stepSize;
	        System.out.println(fileNum + " done");
    	} //end fixed e
	
    	CSVList.writeDataAtOnce("AdjMatrix_FixedE_Results.csv", data);
    	
    	
    }
}
