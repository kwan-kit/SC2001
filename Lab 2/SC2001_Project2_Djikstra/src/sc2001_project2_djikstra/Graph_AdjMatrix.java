package sc2001_project2_djikstra;

public class Graph_AdjMatrix {

	private int[][] adjMatrix;  //2d array
	private int numVertices;    //number of vertices in graph
	
	//constructor initializes a directed graph with number of vertices
	public Graph_AdjMatrix(int numVertices) {
		this.numVertices = numVertices;
		adjMatrix = new int[numVertices][numVertices];
	}
	
	//connect 2 vertices with an edge
	public void addEdge(int source, int destination, int weight) {
		adjMatrix[source][destination] = weight;
	}
	
	//disconnect 2 vertices by removing edge
	public void removeEdge(int source, int destination) {
		adjMatrix[source][destination] = 0;
	}
	
	//check if 2 vertices are connected by an edge
	public boolean hasEdge(int source, int destination) {
		if (adjMatrix[source][destination] != 0) {
				return true;
		} else { return false;}
	}
	
	public void printGraph() {
		System.out.println("Graph: Adjacency Matrix");
		for (int i=0; i<numVertices; i++) {
			for (int j=0; j<numVertices; j++) {
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
