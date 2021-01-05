import java.io.*;

public class FootBallClub extends SportsClub implements Serializable, Comparable<FootBallClub> {
    private int win;
    private int loss;
    private int draw;
    private int points;
    private int totalMatch;
    private int receivedGoals;
    private int scoredGoals;
    private int goalDifference;

    public FootBallClub(String name, String location) {
        super(name, location);
        this.win = 0;
        this.loss = 0;
        this.draw = 0;
        this.points = 0;
        this.receivedGoals = 0;
        this.scoredGoals = 0;
        this.goalDifference = 0;
        this.totalMatch =0;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotalMatch() {
        totalMatch = win + loss + draw;
        return totalMatch;
    }

    public void setTotalMatch(int totalMatch) {
        this.totalMatch = totalMatch;
    }

    public int getReceivedGoals() {
        return receivedGoals;
    }

    public void setReceivedGoals(int receivedGoals) {
        this.receivedGoals = receivedGoals;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int compareToGoals(FootBallClub o) {
        int compareGoals = o.getScoredGoals();
        return this.getScoredGoals() - compareGoals;
    }

    public int compareToWins(FootBallClub o) {
        int compareWins = o.getWin();
        return win - compareWins;
    }

    @Override
    public int compareTo(FootBallClub o) {
        if( points > o.getPoints()){
            return 1;
        }
        else if(points < o.getPoints()){
            return -1;
        }
        else if ( goalDifference > o.getGoalDifference()){
            return 1;
        }
        else if ( goalDifference < o.getGoalDifference()){
            return -1;
        }
        return 0;
    }

}
