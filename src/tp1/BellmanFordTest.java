package tp1;

import java.io.*;
import java.util.*;
import tp1.BellmanFord;

public class BellmanFordTest {
    static class TestCase {
        int V, E, source;
        List<int[]> edges = new ArrayList<>();
        String expectedOutput;

        TestCase(int V, int E, int source, List<int[]> edges, String expectedOutput) {
            this.V = V;
            this.E = E;
            this.source = source;
            this.edges = edges;
            this.expectedOutput = expectedOutput;
        }
    }



    // Lire les tests à partir d'un fichier
    public static List<TestCase> loadTestCases(String filename) {
    	
        List<TestCase> testCases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { //FileName lit char par char, donc on utilise BufferedReader pour avoir des lignes
        	
            String line;
            int V = 0, E = 0, source = 0;
            List<int[]> edges = new ArrayList<>();
            String expectedOutput = "";
            //boolean readingEdges = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                if (line.startsWith("V=")) V = Integer.parseInt(line.split("=")[1]);
                else if (line.startsWith("E=")) E = Integer.parseInt(line.split("=")[1]);
                else if (line.startsWith("SOURCE=")) source = Integer.parseInt(line.split("=")[1]);
                else if (line.startsWith("EXPECTED=")) { 											//EXPECTED doit être la dernière ligne du test
                    expectedOutput = line.split("=")[1];
                    testCases.add(new TestCase(V, E, source, new ArrayList<>(edges), expectedOutput));
                    edges.clear();
                } else {
                    String[] parts = line.split(" ");
                    edges.add(new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testCases;
    }

    // Exécute l'algorithme Bellman-Ford et retourne le résultat sous forme de chaîne
/*    public static String executeBellmanFord(BellmanFord graph, int source) {
        int V = graph.V, E = graph.E;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph.edge[j].source;
                int v = graph.edge[j].destination;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Vérification des cycles négatifs
        for (int j = 0; j < E; j++) {
            int u = graph.edge[j].source;
            int v = graph.edge[j].destination;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                return "CYCLE_NEGATIF";
            }
        }

        return Arrays.toString(dist).replaceAll("[\\[\\],]", "");
    }*/
    
    public static String executeBellmanFord(BellmanFord graph, int source) {
    	return BellmanFord.BellmanFordAlgo(graph, source);
    }
    
    
    public static void main(String[] args) {
        List<TestCase> testCases = loadTestCases("tests.txt");
        int passed = 0;
        int failed = 0;
        int testNb = 1;

        for (TestCase test : testCases) {
        	
            System.out.println("Exécution du test n° " + testNb + " avec source " + test.source);
            BellmanFord graph = new BellmanFord(test.V, test.E);
            
            for (int i = 0; i < test.edges.size(); i++) {
                graph.edge[i].source = test.edges.get(i)[0];
                graph.edge[i].destination = test.edges.get(i)[1];
                graph.edge[i].weight = test.edges.get(i)[2];
            }
            
            String result = executeBellmanFord(graph, test.source);
            
            if (result.equals(test.expectedOutput)) {
                System.out.println("Test Réussi");
                passed++;
            } else {
                System.out.println("Test Échoué (Attendu: " + test.expectedOutput + ", Obtenu: " + result + ")");
                failed++;
            }
            testNb++;
        }

        System.out.println("\n=== Rapport de Test ===");
        System.out.println("Total de tests exécutés : " + (passed + failed));
        System.out.println("Tests réussis : " + passed);
        System.out.println("Tests échoués : " + failed);
    }
}

