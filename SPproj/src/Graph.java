/**
	@author oisinnolan

	Graph class implements boolean
	adjacency matrix.
 
 **/


public class Graph {
	private double adj[][];
	
	public Graph(int v) {
		adj = new double[v][v];
		for(int i=0; i<adj.length; i++) {
			for(int j=0; j<adj.length; j++) {
				adj[i][j] = -1;
			}
		}
	}
	
	public void addEdge(int v, int w, double cost) {
		adj[v][w] = cost;
	}
	
	public double getEdge(int v, int w) {
		return adj[v][w];
	}
	
	public int getV() {
		return adj.length;
	}
	
	// Function used to give idea of graph structure visually
	/*
	public void printGraph() {
		System.out.print("\nV   ");
		for(int i=0; i<adj.length; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n   ");
		for(int i=0; i<adj.length; i++) {
			System.out.print("--");
		}
		System.out.print("\n");
		for(int i=0; i<adj.length; i++) {
			System.out.print(i + " | ");
			for(int j=0; j<adj[i].length; j++) {
				if(adj[i][j] != 0) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.print("\n");
		}
	}
*/
}