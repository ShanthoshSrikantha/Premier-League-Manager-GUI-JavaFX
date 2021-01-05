import java.io.*;


public class FootBallMatch implements Serializable {
    private FootBallClub team1;
    private int team1Goals;
    private FootBallClub team2;
    private int team2Goals;
    private RandomDate randomDate;



    public FootBallMatch(FootBallClub team1, int team1Goals, FootBallClub team2, int team2Goals, RandomDate randomDate) {
        this.team1 = team1;
        this.team1Goals = team1Goals;
        this.team2 = team2;
        this.team2Goals = team2Goals;
        this.randomDate = randomDate;
    }

    public FootBallClub getTeam1() {
        return team1;
    }

    public void setTeam1(FootBallClub team1) {
        this.team1 = team1;
    }

    public int getTeam1Goals() {
        return team1Goals;
    }

    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public FootBallClub getTeam2() {
        return team2;
    }

    public void setTeam2(FootBallClub team2) {
        this.team2 = team2;
    }

    public int getTeam2Goals() {
        return team2Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }

    public RandomDate getRandomDate() {
        return randomDate;
    }

    public void setRandomDate(RandomDate randomDate) {
        this.randomDate = randomDate;
    }

    @Override
    public String toString() {
        String winner = null;
        if(team1Goals>team2Goals){
            winner = team1.getClubName();
        } else if(team1Goals < team2Goals){
            winner = team2.getClubName();
        } else winner = "Draw";
        return team1 + " goals : " + team1Goals + "\n " +
                team2 + " goals : " + team2Goals + "\n " +
                "Date:          " + randomDate +"\n " +
                "Winner: " + winner;

    }
}
