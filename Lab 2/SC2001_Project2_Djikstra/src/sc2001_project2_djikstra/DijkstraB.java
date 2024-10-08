package sc2001_project2_djikstra;

import java.util.ArrayList;

public class DijkstraB {
	static int INF = Integer.MAX_VALUE;
	int numVertices;
	int d[];
	int pi[];
	int keyComp = 0;

	public void dijkstra(Graph_AdjList graph, int source) {
		this.numVertices = graph.getNumVertices();
		int d[] = new int[numVertices];
		int pi[] = new int[numVertices];
		this.keyComp=0;
		boolean set_S[] = new boolean[numVertices];
		ArrayList<Integer> pQueue = new ArrayList<Integer>();
		int u;
		
		for (int i = 0; i < numVertices; i++) { //V times
			d[i] = INF;
			pi[i] = -1;
			set_S[i] = false;
			keyComp++;
		}
		
		d[source] = 0;
		pi[source] = source;
		
		pQueue.add(source);
		for (int i=0; i<numVertices; i++) { //V times
			if (i!=source) {
				pQueue.add(i);
			}
			keyComp++;
		}
		
		minHeap H = new minHeap(d, pQueue); // O(n)
		while (pQueue.isEmpty() == false) { 
			u = H.extractMin(); // O(VlgV) since extractMin is done V times
			set_S[u] = true;
			
			for (int i = 0; i<graph.getListSize(u); i++) { // O(ElgV) since DecreaseKey is done E times
				int v = graph.getAdjVertex(u, i);
				keyComp++;
				if (set_S[v] == false && d[v] > (d[u] + graph.getWeight(u, v))) { 
					pQueue.remove(pQueue.indexOf(v));
					d[v] = d[u] + graph.getWeight(u, v);
					pi[v] = u;
					H.insertKey(v);
				}
			}
		}
		
		this.d = d;
		this.pi = pi;
		keyComp += H.extractKeyComp();
	}
	
	public void printSolution( ) {
		System.out.println("Vertex \t\t Distance from Source \t\t Predecessors");
		for (int i=0; i<numVertices; i++) {
			System.out.println(i+" \t\t "+ d[i]+" \t\t\t\t "+pi[i]);
		}
		
		System.out.println("Number of Key Comparisons: " + keyComp);
	}
	
	public int getKeyComp() {
		return keyComp;
	}
	
	
	public static void main(String[] args) {
		// generateRandomGraph(Vertices, Edges, MaxWeight)
		Graph_AdjList graph = Graph_AdjList.generateRandomGraph(100, 9900, 9900);
		
		// Test case from DijkstraA
		/*
		int matrixGraph[][]
              = new int[][]	{{0,0,1,0,0,7,8,9,8,0},
        					{4,0,0,0,4,0,5,0,5,9}, 
        					{0,0,0,3,3,0,7,6,3,0}, 
        					{0,5,0,0,0,7,1,4,0,4}, 
        					{0,0,0,1,0,0,8,2,9,2}, 
        					{5,0,1,0,0,0,9,3,2,0}, 
        					{8,5,1,0,0,0,0,0,5,6},
        					{0,8,7,6,0,0,6,0,0,1}, 
        					{1,2,0,0,8,7,0,0,0,2}, 
        					{6,0,9,0,6,0,5,1,0,0}};
        					
        Graph_AdjList graph = Graph_AdjList.matrixToList(graph);
        */
		
		/*
		Graph_AdjList graph = new Graph_AdjList(10);
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (j==i+1)
					graph.addEdge(i, j, 1);
				else if (i!=j)
					graph.addEdge(i, j, 50-(2*i));
			}
		}
		*/
		//Graph_AdjList graph = new Graph_AdjList(100); //V = 100;
		
		// Variables
		int sampleSize = 90;
		int stepSize = 100;
		
		int startE = 1000; 	//fixed V
		int fixedV = 100;
		
		int startV = 1000; 	//fixed E
		int fixedE = 20000;	//Also used as maxWeight
		
		DijkstraB dijB = new DijkstraB();
		String[][] data = new String[sampleSize][];
		for (int i=startE; i<=(sampleSize-1)*stepSize; i+=stepSize) {
			graph.reconstructGraph(i, i);
			double startTime = System.nanoTime();
			dijB.dijkstra(graph, 0);
			double endTime = System.nanoTime();
			double elapsedTime = (endTime - startTime) / 1000000; // Time in milliseconds
			String[] result = {String.valueOf(fixedV), String.valueOf(i), String.valueOf(dijB.getKeyComp()), String.valueOf(elapsedTime)};
			data[(i/100)-10] = result;
		}
		CSVList.writeDataAtOnce("AdjList_FixedV_Results.csv", data);
		
		data = new String[sampleSize][];
		for (int i=startV; i<=(sampleSize-1)*stepSize; i+=stepSize) {
			graph = Graph_AdjList.generateRandomGraph(i, fixedE, fixedE);
			double startTime = System.nanoTime();
			dijB.dijkstra(graph, 0);
			double endTime = System.nanoTime();
			double elapsedTime = (endTime - startTime) / 1000000; // Time in milliseconds
			String[] result = {String.valueOf(i), String.valueOf(fixedE), String.valueOf(dijB.getKeyComp()), String.valueOf(elapsedTime)};
			data[(i/100)-10] = result;
		}
		CSVList.writeDataAtOnce("AdjList_FixedE_Results.csv", data);
		
		/*
		dijB.printSolution();
		*/
		
		/*
		String[][] data = new String[2][3];
		String[] result = {"1", "2", String.valueOf(dijB.getKeyComp())};
		data[0] = result;
		data[1] = result;
		CSV.writeDataAtOnce("test.csv", data);
		*/
		
	}

}

