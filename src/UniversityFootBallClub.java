import java.io.*;

public class UniversityFootBallClub extends FootBallClub implements Serializable {
    private String universityName;

    public UniversityFootBallClub(String name, String location, String universityName) {
        super(name, location);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "University Name : " + this.getUniversityName() + "\nClub Name : " + super.getClubName();
    }
}
