package tp1;

// Java Program to implement
// Bellman For Algorithm
import java.util.Arrays;

// Bellman For Algorothm
public class BellmanFord {
      // Graph is Created Using Edge Class
    static class Edge {
        int source, destination, weight;

        Edge() {
            source = destination = weight = 0;
        }
    }

    int V, E;
    Edge edge[];

    // Constructor to initialize the graph
    BellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // Bellman-Ford Algorithm to find shortest paths from source to all vertices
    public static String BellmanFordAlgo(BellmanFord graph, int source) {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];

        // Step 1: Initialize distances from source to all other vertices as INFINITE
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Step 2: Relax all edges |V| - 1 times.
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].source;
                int v = graph.edge[j].destination;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        // Step 3: Check for negative-weight cycles
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].source;
            int v = graph.edge[j].destination;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                //System.out.println("Graph contains negative weight cycle");
                return "CYCLE_NEGATIF";
                
            }
        }

        // Print distances from source to all vertices
        //printDistances(dist, V);
        
        // Return distances from source to all vertices
        //return writeDistances(dist, V);
        
        return Arrays.toString(dist).replaceAll("[\\[\\],]", "");
    }

    // Print distances from source to all vertices
    public static void printDistances(int dist[], int V) {
        System.out.println("Vertex Distance from Source:");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }
    
    public static String writeDistances(int dist[], int V) {
    	StringBuilder sb = new StringBuilder();
    	//sb.append("Vertex Distance from Source:");
    	for (int i = 0; i < V; ++i)
            sb.append("\n" + i + "\t\t" + dist[i]);
    	return sb.toString();
    }
    
    
    public void exple_main() {
    	
        //graph question 2
        int V = 6;
        int E = 9;
        BellmanFord graph = new BellmanFord(V, E);
        
        //edges
        //Edge A-B
        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = 6;
        
        //Edge A-C
        graph.edge[1].source = 0;
        graph.edge[1].destination = 2;
        graph.edge[1].weight = 4;
        
        //Edge A-D
        graph.edge[2].source = 0;
        graph.edge[2].destination = 3;
        graph.edge[2].weight = 5;
        
        //Edge B-E
        graph.edge[3].source = 1;
        graph.edge[3].destination = 4;
        graph.edge[3].weight = -1;
        
        //Edge C-B
        graph.edge[4].source = 2;
        graph.edge[4].destination = 1;
        graph.edge[4].weight = -2;
        
        //Edge C-E
        graph.edge[5].source = 2;
        graph.edge[5].destination = 4;
        graph.edge[5].weight = 3;
        
        //Edge D-C
        graph.edge[6].source = 3;
        graph.edge[6].destination = 2;
        graph.edge[6].weight = -2;

        //Edge D-F
        graph.edge[7].source = 3;
        graph.edge[7].destination = 5;
        graph.edge[7].weight = -1;
        
        //Edge E-F
        graph.edge[8].source = 4;
        graph.edge[8].destination = 5;
        graph.edge[8].weight = 3;
        
        
        /*
         * 
        int V = 5;
        int E = 8;
        BellmanFord graph = new BellmanFord(V, E);
        
        // Define edges
        // Edge 0-1
        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = -1;
      
        // Edge 0-2
        graph.edge[1].source = 0;
        graph.edge[1].destination = 2;
        graph.edge[1].weight = 4;
      
        // Edge 1-2
        graph.edge[2].source = 1;
        graph.edge[2].destination = 2;
        graph.edge[2].weight = 3;
      
        // Edge 1-3
        graph.edge[3].source = 1;
        graph.edge[3].destination = 3;
        graph.edge[3].weight = 2;
      
        // Edge 1-4
        graph.edge[4].source = 1;
        graph.edge[4].destination = 4;
        graph.edge[4].weight = 2;
      
        // Edge 3-2
        graph.edge[5].source = 3;
        graph.edge[5].destination = 2;
        graph.edge[5].weight = 5;
      
        // Edge 3-1
        graph.edge[6].source = 3;
        graph.edge[6].destination = 1;
        graph.edge[6].weight = 1;
      
        // Edge 4-3
        graph.edge[7].source = 4;
        graph.edge[7].destination = 3;
        graph.edge[7].weight = -3;
		*/
        
        
        // Execute Bellman-Ford algorithm
        System.out.println(BellmanFordAlgo(graph, 0));
    }

    // Main method to test the Bellman-Ford algorithm
    public static void main(String[] args) {
        
    	//graph question 2
        int V = 6;
        int E = 9;
        BellmanFord graph = new BellmanFord(V, E);
        
        //edges
        //Edge A-B
        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = 6;
        
        //Edge A-C
        graph.edge[1].source = 0;
        graph.edge[1].destination = 2;
        graph.edge[1].weight = 4;
        
        //Edge A-D
        graph.edge[2].source = 0;
        graph.edge[2].destination = 3;
        graph.edge[2].weight = 5;
        
        //Edge B-E
        graph.edge[3].source = 1;
        graph.edge[3].destination = 4;
        graph.edge[3].weight = -1;
        
        //Edge C-B
        graph.edge[4].source = 2;
        graph.edge[4].destination = 1;
        graph.edge[4].weight = -2;
        
        //Edge C-E
        graph.edge[5].source = 2;
        graph.edge[5].destination = 4;
        graph.edge[5].weight = 3;
        
        //Edge D-C
        graph.edge[6].source = 3;
        graph.edge[6].destination = 2;
        graph.edge[6].weight = -2;

        //Edge D-F
        graph.edge[7].source = 3;
        graph.edge[7].destination = 5;
        graph.edge[7].weight = -1;
        
        //Edge E-F
        graph.edge[8].source = 4;
        graph.edge[8].destination = 5;
        graph.edge[8].weight = 3;
        
    	
    	System.out.println(BellmanFordAlgo(graph, 0));
    }
}
