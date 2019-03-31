import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
	private int N, S, sA, sB, sC;
	private Graph G;
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
    		this.sA = sA; this.sB = sB; this.sC = sC;
    		// Creating Graph from file
    		
    		try {
			Scanner fileScanner = new Scanner(new File(filename));
			N = fileScanner.nextInt(); // total number of intersections (vertices)
			S = fileScanner.nextInt(); // total number of streets (edges)
			G = new Graph(N);
			String line = "";;
			while(fileScanner.hasNextLine()) {
				int v = fileScanner.nextInt();
				int w = fileScanner.nextInt();
				double cost = fileScanner.nextDouble();
				fileScanner.nextLine();
				
				G.addEdge(v, w, cost);
			}
			G.printGraph();
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
    		
    		double[] distA, distB, distC;
    		
    		for(int i=0; i<G.getV(); i++) {
    			for(int j=0; j<G.getV(); j++) {
    				for(int k=0; k<G.getV(); k++) {
    					if(i != j && j != k && i != k) {
	    					distA = dijkstra(G, i);
	    					distB = dijkstra(G, j);
	    					distC = dijkstra(G, k);
	    					if (maxTime < getMaxTime(distA, distB, distC)) {
	    						maxTime = (int) getMaxTime(distA, distB, distC);
	    					}
	    					System.out.println(maxTime);
    					}
    				}
    			}
    		}
    		System.out.println(maxTime);
        return maxTime;
    }
    
    public double getMaxTime(double[] a, double[] b, double[] c) {
    		double max = a[0]*sA/1000 + b[0]*sB/1000 + c[0]*sC/1000;
    		for(int i=0; i<a.length; i++) {
    			for(int j=0; j<b.length; j++) {
    				for(int k=0; k<c.length; k++) {
    	    				if(a[i]*sA/1000 + b[j]*sB/1000 + c[k]*sC/1000 > max) {
    	    					max = a[i]*sA/1000 + b[j]*sB/1000 + c[k]*sC/1000;
    	    				}
    	    			}
        		}
    		}
    		return max;
    }
    
    public double[] dijkstra(Graph G, int source) {
    		int N = G.getV();
    		double[] dist = new double[N];
    		int[] prev = new int[N];
    		
    		IndexMinPQ<Double> pq = new IndexMinPQ<Double>(N);
    		
    		// initialise v in V as infinitely far from source
    		for(int v=0; v<G.getV(); v++) {
    			dist[v] = Double.POSITIVE_INFINITY;
    		}
    		
    		// begin at source
    		dist[source] = 0;
    		pq.insert(source, dist[source]);
    		
    		// loop until all available edges are considered
    		while(!pq.isEmpty()) {
    			int u = pq.delMin();
    			for(int v=0; v<N; v++) {
    				double e = G.getEdge(u, v);
    				// check if v is adjacent to u
    				if(e > 0) {
    					// relax edge if possible
    					if(dist[v] > dist[u] + G.getEdge(u, v)) {
    						dist[v] = dist[u] + G.getEdge(u, v);
    						prev[v] = u;
    						// update pq accordingly
    						if(pq.contains(v)) {
    							pq.decreaseKey(v, dist[v]);
    						} else {
    							pq.insert(v, dist[v]);
    						}
    					}
    				}
    			}
    		}
    		
		return dist;
    }
    
    

}
