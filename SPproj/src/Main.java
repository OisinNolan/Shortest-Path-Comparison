
public class Main {
	public static void main(String[] args) {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("tinyEWD.txt", 55, 65, 75);
		CompetitionDijkstra cd = new CompetitionDijkstra("tinyEWD.txt", 55, 75, 100);
		System.out.println(cd.timeRequiredforCompetition());
		System.out.println(cfw.timeRequiredforCompetition());
	}
}
