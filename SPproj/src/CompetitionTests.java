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
 */


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
    		CompetitionDijkstra cd = new CompetitionDijkstra("1000EWD.txt", 100, 75, 50);
    		int time = cd.timeRequiredforCompetition();
    		int expectedResult = 20;
    		assertEquals(expectedResult, time);
    }

    @Test
    public void testFWConstructor() {
    		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("1000EWD.txt", 100, 75, 50);
		int time = cfw.timeRequiredforCompetition();
		int expectedResult = 20;
		assertEquals(expectedResult, time);
    }
    
    /*
    @Test
    public void testFileNotFound() {
    		//test exception
    		CompetitionDijkstra cd = new CompetitionDijkstra("test.txt", 50, 75, 100);
    		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("test.txt", 50, 75, 100);
    }
    */
}
