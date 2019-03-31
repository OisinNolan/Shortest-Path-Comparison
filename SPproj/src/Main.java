
public class Main {
	public static void main(String[] args) {
		CompetitionDijkstra cd = new CompetitionDijkstra("1000EWD.txt", 55, 65, 75);
		System.out.println(cd.timeRequiredforCompetition());
	}
}
