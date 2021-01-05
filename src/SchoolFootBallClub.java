import java.io.Serializable;

public class SchoolFootBallClub extends FootBallClub implements Serializable {
    private String schoolName;
    private String ageCategory;

    public SchoolFootBallClub(String name, String location, String schoolName, String ageCategory) {
        super(name, location);
        this.schoolName = schoolName;
        this.ageCategory = ageCategory;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    @Override
    public String toString() {
        return this.getSchoolName() + "\n" + super.getClubName() +
                "\nAge Category : " + this.ageCategory;
    }
}
