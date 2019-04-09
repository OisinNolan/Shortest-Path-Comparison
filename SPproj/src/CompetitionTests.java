/**
 * @author oisinnolan 
 * Oisín Nolan 
 * 17327530
 * 
 * Discussion of Assignment:
 * 
 * Data structure used to store graph is a 2d array, ie a matrix.
 * Each element in the matrix, (u,v) where u, v are nodes, stores the cost
 * associated with the edge linking u to v. If (u,v) = 0, there does not exist
 * an edge from u to v.
 * 
 * This data structure was chosen because:
 * 	- it is easy to use and intuitive
 * 	- storage of edges is ideal for adjacency in directed graphs,
 * 	  i.e (u,v) does not necessarily equal (v,u).
 * 	- Not as efficient as adjacency list, however the file is small enough that
 * 	  this was not taken into consideration.
 * 
 * Differences between Floyd-Warshall and Dijkstra:
 * 
 * Floyd-Warshall computes the shortest path between all pairs of vertices in the graph,
 * stored appropriately in a 2-d array. This algorithm has a time complexity of O(V^3), and 
 * thus is appropriate to choose for high density graphs.
 * 
 * Dijkstra computes the shortest paths from a given source vertex to every other vertex in
 * the graph, stored appropriately in a 1-d array in which the index identifies the 
 * vertex and the value represents the distance from the source to that vertex. Dijkstra's
 * algorithm, as implemented in this assignment (adjacency matrix, priority queue) has
 * a time complexity of O(v^2 + e log v) and thus becomes slower as the graph becomes
 * more dense.
 * 
 */


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
    		CompetitionDijkstra cd = new CompetitionDijkstra("tinyEWD.txt", 100, 75, 50);
    		int time = cd.timeRequiredforCompetition();
    		System.out.println(time);
    		int expectedResult = 38;
    		assertEquals(expectedResult, time);
    }

    @Test
    public void testFWConstructor() {
    		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("tinyEWD.txt", 100, 75, 50);
		int time = cfw.timeRequiredforCompetition();
		System.out.println(time);
		int expectedResult = 38;
		assertEquals(expectedResult, time);
    }
}
