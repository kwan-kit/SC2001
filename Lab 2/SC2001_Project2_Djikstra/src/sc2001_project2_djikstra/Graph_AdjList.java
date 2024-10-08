package sc2001_project2_djikstra;

import java.util.LinkedList;
import java.util.Random;

class AdjListNode {
	private int vertex;
	private int weight;
	
	AdjListNode(int v, int w) {
		vertex = v;
		weight = w;
	}
	
	public int getVertex() {return vertex;}
	public int getWeight() {return weight;}
	
}

public class Graph_AdjList {
	
	private int numVertices;
	private int numEdges;
	private LinkedList<AdjListNode>[] adjList;
	
	public Graph_AdjList(int numVertices) {
		this.numVertices = numVertices;
		adjList = new LinkedList[numVertices];
		
		for (int i=0; i<numVertices; i++) {
			adjList[i] = new LinkedList<AdjListNode>();
		}
	}
	
	public int getNumVertices() {return numVertices;}
	
	public void addEdge(int source, int destination, int weight) {
		AdjListNode edge = new AdjListNode(destination, weight);
		adjList[source].add(edge);
		numEdges++;
	}
	
	public boolean hasEdge(int source, int destination) {
		for (int i=0; i<adjList[source].size(); i++) {
			if (adjList[source].get(i).getVertex() == destination) {return true;}
		}
		return false;
	}
	
	public int getWeight(int source, int destination) {
		if (hasEdge(source, destination)) {
			for (int i=0; i<adjList[source].size(); i++) {
				if (adjList[source].get(i).getVertex() == destination) {return adjList[source].get(i).getWeight();}
			}
		}
		return -1;
	}
	
	public void reconstructGraph(int numEdges, int maxWeight) {
		this.numEdges = 0;
		Random random = new Random();
		for (int i=0; i<numVertices; i++) {
			adjList[i].removeAll(adjList[i]);
		}
		for (int i=0; i<numEdges; i++) {
			int source=0, destination=0, weight=0;
			while (source == destination || hasEdge(source, destination)) {
				source = random.nextInt(numVertices);
				destination = random.nextInt(numVertices);
				weight = random.nextInt(maxWeight)+1;
			}
			addEdge(source, destination, weight);
		}
	}
	
	public int getListSize(int vertex) {return adjList[vertex].size();}
	
	public int getAdjVertex(int vertex, int index) {return adjList[vertex].get(index).getVertex();}
	
	public void printGraph() {
		for (int i=0; i<numVertices; i++) {
			System.out.print("Vertex " + i + ": ");
			for (AdjListNode edge : adjList[i]) {
				System.out.print(edge.getVertex() + ":" + edge.getWeight() + " -> ");
			}
			System.out.println("NULL");
		}
	}
	
	public static Graph_AdjList generateRandomGraph(int numVertices, int numEdges, int maxWeight) {
		Graph_AdjList graph = new Graph_AdjList(numVertices);
		Random random = new Random();
		
		for (int i=0; i<numEdges; i++) {
			int source=0, destination=0, weight=0;
			while (source == destination || graph.hasEdge(source, destination)) {
				source = random.nextInt(numVertices);
				destination = random.nextInt(numVertices);
				weight = random.nextInt(maxWeight)+1;
			}
			graph.addEdge(source, destination, weight);
		}
		
		return graph;
	}
	
	public static Graph_AdjList matrixToList(int[][] matrixGraph) {
		int graphSize = matrixGraph.length;
		Graph_AdjList listGraph = new Graph_AdjList(graphSize);
		for (int i=0; i<graphSize; i++) {
			for (int j=0; j<graphSize; j++) {
				if (i!=j && matrixGraph[i][j]>0) {
					listGraph.addEdge(i, j, matrixGraph[i][j]);
				}
			}
		}
		return listGraph;
	}
	
	//Test code
	/*
	public static void main(String[] args) {
		int vertices = 5;
		Graph_AdjList graph = Graph_AdjList.generateRandomGraph(vertices, 14, 25);
		
		graph.printGraph();
		System.out.println(graph.hasEdge(0, 4) + " " + graph.getWeight(0, 4));
		
	}
	*/
	
}
