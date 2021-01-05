import java.io.*;
import java.util.*;

public abstract class SportsClub implements Serializable {
    private String clubName;
    private String clubLocation;

    public SportsClub(String name, String location) {
        this.clubName = name;
        this.clubLocation = location;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    @Override
    public String toString() {
        return this.clubName + "    ";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FootBallClub)) {
            return false;
        }
        FootBallClub c = (FootBallClub) o;
        return this.getClubName().equals(c.getClubName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName);
    }

}
