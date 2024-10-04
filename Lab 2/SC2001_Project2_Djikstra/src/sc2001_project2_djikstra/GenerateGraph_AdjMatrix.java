package sc2001_project2_djikstra;
import java.util.Random;
import java.util.Scanner;

public class GenerateGraph_AdjMatrix {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		
      	System.out.println("Enter Number Of Vertices:");
      	int numVerts = sc.nextInt();
      	
      	System.out.println("Enter Number Of Edges:");
      	int numEdges = sc.nextInt();
      	
      	Graph_AdjMatrix graph = new Graph_AdjMatrix(numVerts); //initialize graph
      	
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
      			System.out.print("Adding Edge" + i);
      			System.out.println("Connecting: " + source + " to " + randomNodeDest + " with weight: " + randomWeight);
          		graph.addEdge(source,randomNodeDest,randomWeight);  //connect the vertices with an edge.
          		i++;
      		} else {System.out.println("REPEATED"); continue;}
      		
      	}
      	
      	graph.printGraph(); //display the graph in an adjacency matrix.
  }
        
}

