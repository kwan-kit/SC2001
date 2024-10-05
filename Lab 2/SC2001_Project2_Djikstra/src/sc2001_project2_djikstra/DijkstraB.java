package sc2001_project2_djikstra;

import java.util.ArrayList;

public class DijkstraB {
	static int INF = Integer.MAX_VALUE;
	int numVertices;
	int d[];
	int pi[];

	public void dijkstra(Graph_AdjList graph, int source) {
		this.numVertices = graph.getNumVertices();
		int d[] = new int[numVertices];
		int pi[] = new int[numVertices];
		boolean set_S[] = new boolean[numVertices];
		ArrayList<Integer> pQueue = new ArrayList<Integer>();
		int u;
		
		for (int i = 0; i < numVertices; i++) {
			d[i] = INF;
			pi[i] = -1;
			set_S[i] = false;
		}
		
		d[source] = 0;
		pi[source] = source;
		
		pQueue.add(source);
		for (int i=0; i<numVertices; i++) {
			if (i!=source) {
				pQueue.add(i);
			}
		}
		
		minHeap H = new minHeap();
		while (pQueue.isEmpty() == false) {
			u = H.getMin(d, pQueue);
			pQueue.remove(pQueue.indexOf(u));
			set_S[u] = true;
			
			for (int i = 0; i<graph.getListSize(u); i++) {
				int v = graph.getAdjVertex(u, i);
				if (set_S[v] == false && d[v] > (d[u] + graph.getWeight(u, v))) {
					//pQueue.remove(pQueue.indexOf(v));
					d[v] = d[u] + graph.getWeight(u, v);
					pi[v] = u;
					//pQueue.add(v);
				}
			}
		}
		
		this.d = d;
		this.pi = pi;
	}
	
	public void printSolution( ) {
		System.out.println("Vertex \t\t Distance from Source \t\t Predecessors");
		for (int i=0; i<numVertices; i++) {
			System.out.println(i+" \t\t "+ d[i]+" \t\t\t\t "+pi[i]);
		}
	}
	
	
	
	public static void main(String[] args) {
		Graph_AdjList graph = Graph_AdjList.generateRandomGraph(10, 50, 10);
		/* Test case from DijkstraA
		Graph_AdjList graph = new Graph_AdjList(10);
		graph.addEdge(0, 2, 1);
		graph.addEdge(0, 5, 7);
		graph.addEdge(0, 6, 8);
		graph.addEdge(0, 7, 9);
		graph.addEdge(0, 8, 8);
		graph.addEdge(1, 0, 4);
		graph.addEdge(1, 4, 4);
		graph.addEdge(1, 5, 5);
		graph.addEdge(1, 8, 5);
		graph.addEdge(1, 9, 9);
		graph.addEdge(2, 3, 3);
		graph.addEdge(2, 4, 3);
		graph.addEdge(2, 6, 7);
		graph.addEdge(2, 7, 6);
		graph.addEdge(2, 8, 3);
		graph.addEdge(3, 2, 5);
		graph.addEdge(3, 5, 7);
		graph.addEdge(3, 6, 1);
		graph.addEdge(3, 7, 4);
		graph.addEdge(3, 9, 4);
		graph.addEdge(4, 3, 1);
		graph.addEdge(4, 6, 8);
		graph.addEdge(4, 7, 2);
		graph.addEdge(4, 8, 9);
		graph.addEdge(4, 9, 2);
		graph.addEdge(5, 0, 5);
		graph.addEdge(5, 2, 1);
		graph.addEdge(5, 6, 9);
		graph.addEdge(5, 7, 3);
		graph.addEdge(5, 8, 2);
		graph.addEdge(6, 0, 8);
		graph.addEdge(6, 1, 5);
		graph.addEdge(6, 2, 1);
		graph.addEdge(6, 8, 5);
		graph.addEdge(6, 9, 6);
		graph.addEdge(7, 1, 8);
		graph.addEdge(7, 2, 7);
		graph.addEdge(7, 3, 6);
		graph.addEdge(7, 6, 6);
		graph.addEdge(7, 9, 1);
		graph.addEdge(8, 0, 1);
		graph.addEdge(8, 1, 2);
		graph.addEdge(8, 4, 8);
		graph.addEdge(8, 5, 7);
		graph.addEdge(8, 9, 2);
		graph.addEdge(9, 0, 6);
		graph.addEdge(9, 2, 9);
		graph.addEdge(9, 4, 6);
		graph.addEdge(9, 6, 5);
		graph.addEdge(9, 7, 1);
		*/
		graph.printGraph();
		DijkstraB dijB = new DijkstraB();
		dijB.dijkstra(graph, 0);
		dijB.printSolution();
		
	}

}
