package sc2001_project2_djikstra;

public class DijkstraA {
	
	int NumOfVertices;                            // Total number of vertices in graph
	int pQueue[];                                 // Array as priority queue
	int d[];                                      // The output array d[i] will hold the shortest distance from source to vertex i
	int pi[];                                     // Predecessors
	int lenQueue;                                 // Current number of vertices in queue
	Boolean set_S[] = new Boolean[NumOfVertices]; //Vertices whose shortest paths have been found
	
	void dijkstra(int graph[][], int source) 
	{
		
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
        		if (!set_S[v] && graph[u][v]!=0      //v is not in set S, and is connected to u
            		&& d[u] != Integer.MAX_VALUE     //and distance of u is found
            		&& d[u] + graph[u][v] < d[v]) {    //and distance of u plus edge from u to v is less than current distance of v
    	
        			deleteElement(pQueue, v);
        			d[v] = d[u]+graph[u][v];         //Update distance of v
        			pi[v] = u;                       //Update the predecessor of u

        			insertSorted(v);                 //Insert v into pQueue in sorted order
        		}
            		
        	}
        }
        
        printSolution(d);
	}
	
//Other Methods ---------------------------------------------------------------------------------------------------------------------------------//
	
	void printSolution(int dist[]) 	//print the results
	{
		System.out.println("Vertex \t\t Distance from Source \t\t Predecessors");
		for (int i=0; i<NumOfVertices; i++) {
			System.out.println(i+" \t\t "+dist[i]+" \t\t\t\t "+pi[i]);
		}
	}
	
	int binarySearch(int arr[], int low, int high, int key)  //To search for the element to be deleted
	{
		if (high < low)
			return -1;
		int mid = (low + high) / 2;
		if (key == arr[mid])
			return mid;
		if (key > arr[mid])
			return binarySearch(arr, (mid + 1), high, key);
		return binarySearch(arr, low, (mid - 1), key);
	}

	int deleteElement(int arr[], int key)
	{
		int n = pQueue.length;
		
		int pos = binarySearch(arr, 0, n-1, key);    // Find position of element to be deleted
		
		if (pos == -1) {
			System.out.println("Element not found");
			return lenQueue; 
		}

		// Deleting element
		int i;
		for (i = pos; i < n-1; i++)
		arr[i] = arr[i + 1];
		lenQueue--;
		
		return n-1;
	}
	
	void deleteFirstElement(int arr[], int n) // Delete first element of queue
    {
        for (int i = 0; i < n - 1; i++)
            arr[i] = arr[i + 1];
        lenQueue--;
    }
	
	int insertSorted(int vertex) //Insert a vertex into pQueue, in the priority of their distance from the source node
	{
		
		if (lenQueue >= pQueue.length) {
			return -1;
		}
		
		int i; //current index of last node in queue
		
		for(i = lenQueue-1; (i>=0 && d[pQueue[i]]>d[vertex]); i--) {
			pQueue[i+1] = pQueue[i];
		} 
		
		pQueue[i+1] = vertex;
		lenQueue++;
		return(lenQueue);
	}
	
// TEST ---------------------------------------------------------------------------------------------------------------------------------//
    public static void main(String[] args)
    {

        /*int graph[][]
            = new int[][] { {0,2,7,0,0},
        					{0,0,4,0,0},
        					{0,0,0,1,4},
        					{0,0,0,0,2},
        					{0,0,0,0,0}};*/
        					
       int graph[][]
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
       
        DijkstraA t = new DijkstraA();
        
        t.dijkstra(graph, 0);
	
    }
}
