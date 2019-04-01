import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
	
	private int N, S, sA, sB, sC;
	private Graph G;
	
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
	    	this.sA = sA; this.sB = sB; this.sC = sC;
			// Creating Graph from file
			
			try {
			Scanner fileScanner = new Scanner(new File(filename));
			N = fileScanner.nextInt(); // total number of intersections (vertices)
			S = fileScanner.nextInt(); // total number of streets (edges)
			G = new Graph(N);
			while(fileScanner.hasNextLine()) {
				int v = fileScanner.nextInt();
				int w = fileScanner.nextInt();
				double cost = fileScanner.nextDouble();
				if(fileScanner.hasNextLine()) {
					fileScanner.nextLine();
				}
				
				G.addEdge(v, w, cost);
			}
			System.out.println("Graph constructed:");
			System.out.println("#V = " + G.getV());
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//getShortestPath(random stuff), if time is bigger, set max to time.
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    		int maxTime = -1;
		
		// slowest speed:
		int slowest = sA;
		if(sB < sA && sB < sC) {
			slowest = sB;
		} else if(sC < sA && sC < sB) {
			slowest = sC;
		}
		
		double maxDist = 0;
		
		double[][] dist = fw(G);
		for(int i=0; i<dist.length; i++) {
			for(int j=0; j<dist[i].length; j++) {
				if(dist[i][j] > maxDist) {
					maxDist = dist[i][j];
				}
			}
		}
		
		// convert dist from kilometers to meters,
		// divide by meters per minute.
		maxTime = (int) Math.ceil(1000 * maxDist / slowest);
		
        return maxTime;
    }
    
    public double[][] fw(Graph G) {
    		int V = G.getV();
    		double[][] dist = new double[V][V];
    		int[][] prev = new int[V][V];
    		
    		// set all nodes as infinitely far away
    		// from one another
    		for(int v=0; v<V; v++) {
    			for(int w=0; w<V; w++) {
        			dist[v][w] = Double.POSITIVE_INFINITY;
        		}
    		}
    		
    		// fill dist[][] and prev[][] with initial values
    		for(int v=0; v<V; v++) {
    			for(int w=0; w<V; w++) {
    				if(G.getEdge(v, w) > 0) {
    					dist[v][w] = G.getEdge(v, w);
    					prev[v][w] = w;
    				}
    				// fill diagonal with 0s
    				if(dist[v][v] >= 0) {
    					dist[v][v] = 0;
    					prev[v][v] = -1;
    				}
    			}
    		}
    		
    		for(int i=0; i<V; i++) {
    			for(int v=0; v<V; v++) {
    				if(prev[v][i] == -1) {
    					continue;
    				}
    				for(int w=0; w<V; w++) {
    					if(dist[v][w] > dist[v][i] + dist[i][w]) {
    						dist[v][w] = dist[v][i] + dist[i][w];
    						prev[v][w] = prev[i][w];
    					}
    				}
    			}
    		}
    		return dist;
    	}

}