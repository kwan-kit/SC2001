package sc2001_project2_djikstra;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.PrintStream; 

public class GenerateGraph_AdjMatrix {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Graph_AdjMatrix graph = null; //initialize graph
		
      	int numVerts = 100;
      	int numEdges = 1000;
      	PrintStream out = null;
      	
      	int fileNum = 1;
      	while (numEdges <= 9900) { //fixed v
      		
      		graph = new Graph_AdjMatrix(numVerts); //initialize graph
      		
          	int i = 0;
          	while ( i < numEdges)
          	{
          		int source = i%numVerts;
          		int randomNodeDest = -1;
          		
          		do {
          			randomNodeDest = rand.nextInt(numVerts);            //find a random destination vertex to connect source vertex to. 
          		} while (randomNodeDest == source); 		            //try again if destination vertex is the same as the source vertex.
          			
          		int randomWeight = rand.nextInt(9)+1;                   //generate random weight for the edge.
          		
          		if (graph.hasEdge(source,randomNodeDest) == false) {    //if the nodes are not already connected,
          			//System.out.print("Adding Edge" + i);
          			//System.out.println("Connecting: " + source + " to " + randomNodeDest + " with weight: " + randomWeight);
              		graph.addEdge(source,randomNodeDest,randomWeight);  //connect the vertices with an edge.
              		i++;
          		} else {
          			//System.out.println("REPEATED"); 
          			continue;
          		}
 
          	}
          	
          	String fileName = "output" + Integer.toString(fileNum) + ".txt";
          	
          	try {
          		out = new PrintStream(new FileOutputStream(fileName));
          		graph.printGraphToFile(out);
          	
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
          	System.setOut(out);
      		
          	fileNum++;
      		numEdges += 100;
      		
          	}//fixed v end 
      	
      		numVerts = 1000;
      		numEdges = 20000;
      		
      		while (numVerts <= 9900) { //fixed e
      		
	      		graph = new Graph_AdjMatrix(numVerts); //initialize graph
	      		
	          	int i = 0;
	          	while ( i < numEdges)
	          	{
	          		int source = i%numVerts;
	          		int randomNodeDest = -1;
	          		
	          		do {
	          			randomNodeDest = rand.nextInt(numVerts);            //find a random destination vertex to connect source vertex to. 
	          		} while (randomNodeDest == source); 		            //try again if destination vertex is the same as the source vertex.
	          			
	          		int randomWeight = rand.nextInt(9)+1;                   //generate random weight for the edge.
	          		
	          		if (graph.hasEdge(source,randomNodeDest) == false) {    //if the nodes are not already connected,
	          			//System.out.print("Adding Edge" + i);
	          			//System.out.println("Connecting: " + source + " to " + randomNodeDest + " with weight: " + randomWeight);
	              		graph.addEdge(source,randomNodeDest,randomWeight);  //connect the vertices with an edge.
	              		i++;
	          		} else {
	          			//System.out.println("REPEATED"); 
	          			continue;
	          		}
	 
	          	}
	          	
	          	String fileName = "output" + Integer.toString(fileNum) + ".txt";
	          	
	          	try {
	          		out = new PrintStream(new FileOutputStream(fileName));
	          		graph.printGraphToFile(out);
	          	
	    		} catch (FileNotFoundException e) {
	    			e.printStackTrace();
	    		}
	          	System.setOut(out);
	      		
	          	fileNum++;
	      		numVerts += 100;
	      		
          	}
      	
      	}
	}
        


