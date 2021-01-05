import java.util.List;

public interface ILeagueManager {
    void addClub();

    void deleteClub(String sportsClubName);

    void displayStatistics(FootBallClub footBallClub);

    void displayTable(List<FootBallClub> list);
}
