
import java.io.*;
import java.util.*;

public class PremierLeagueManager implements ILeagueManager {

    private List<FootBallClub> clubs = new ArrayList<>();
    private List<FootBallMatch> matches = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public PremierLeagueManager() {
    }

    public PremierLeagueManager(List<FootBallClub> clubs, List<FootBallMatch> matches) {
        this.clubs = clubs;
        this.matches = matches;
    }

    public List<FootBallClub> getClubs() {
        return clubs;
    }

    public void setClubs(List<FootBallClub> clubs) {
        this.clubs = clubs;
    }

    public List<FootBallMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<FootBallMatch> matches) {
        this.matches = matches;
    }

    public void displayMenu() {
        this.setClubs(Main.readFile("src/TeamDetails.txt"));
        this.setMatches(Main.readFile("src/MatchDetails.txt"));
        String option;
        do {
            System.out.println("================================================================");
            System.out.println("====== 	Welcome to Premier League Manager ======================");
            System.out.println("================================================================");
            System.out.println("================================================================");
            System.out.println("Press \"A\" For Add a Club to Premier League Manager ");
            System.out.println("Press \"D\" For Delete a Club from Premier League Manager");
            System.out.println("Press \"E\" For Display Statistics of a Club ");
            System.out.println("Press \"S\" For Save All the Details to a  File ");
            System.out.println("Press \"T\" For Display Table of Statistics of All the Clubs");
            System.out.println("Press \"P\" For Add played Match Details : ");
            System.out.println("Press \"O\" For Sort Tables : ");
            System.out.println("Press \"Q\" For Quit the Program");
            System.out.println("****************************************************************");
            System.out.println("================================================================");

            System.out.print("Enter Your Option : ");
            option = scanner.nextLine().toLowerCase();
            handleOption(option);
        } while (!option.equals("q"));
    }

    public void handleOption(String option) {
        switch (option) {
            case ("a"):
                System.out.println("==============================================");
                System.out.println("        Add Club to Premier League            ");
                System.out.println("**********************************************");
                System.out.println("==============================================\n");
                this.addClub();
                break;

            case ("d"):
                System.out.println("==============================================");
                System.out.println("        Delete Club From Premier League       ");
                System.out.println("**********************************************");
                System.out.println("==============================================\n");
                System.out.print("Enter club Name you want to Delete : ");
                String delName = scanner.nextLine();
                this.deleteClub(delName);
                break;
            case ("e"):
                System.out.println("==============================================");
                System.out.println("   Statistics of Club From Premier League     ");
                System.out.println("**********************************************");
                System.out.println("==============================================\n");
                System.out.print("Enter Club Name to Show Statics : ");
                String statClub = scanner.nextLine();
                FootBallClub club = this.searchClub(statClub);
                this.displayStatistics(club);
                break;
            case ("s"):
                System.out.println("==============================================");
                System.out.println("    ----  All Details save to the File   ---  ");
                this.saveToFile();
                break;
            case ("t"):
                System.out.println("==============================================");
                System.out.println(" Table of All Statistics From Premier League  ");
                System.out.println("**********************************************");
                System.out.println("==============================================");
                this.displayTable(this.getClubs());
                break;

//            case ("m"):
//                System.out.print("Enter Date : ");
//                int dateMatch = scanner.nextInt();
//                System.out.print("Enter Month : ");
//                int monthMatch = scanner.nextInt();
//                monthMatch = scanner.nextInt();
//                RandomDate searchDate = new RandomDate(dateMatch, monthMatch);
//                this.searchMatch(searchDate, this.getMatches());
//                break;
            case ("p"):
                System.out.println("==============================================");
                System.out.println("   Insert Played Match to Premier League      ");
                System.out.println("**********************************************");
                System.out.println("==============================================");
                System.out.println("Available  Teams to Play Match ");
                System.out.println(" ==================================== ");
                this.displayClubs(this.getClubs());

                //Entering date

                System.out.println("Enter Match date In First Date a format!! \nOnly Date and Month Needed...\nFirst Enter the Date Then month \n");
                System.out.print("Enter Match Date {Enter Month In Next Input} (DD) : ");
                int uDate = 0;
                try {
                    uDate = scanner.nextInt();
                    if (uDate < 1 || uDate > 31) {
                        System.out.println("Invalid Data  Enter Match Details Again!!!!");
                        this.displayMenu();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entered Date not a valid Number!!! \nEnter Match Details Again ..\n");
                    this.displayMenu();
                }
                System.out.print("Enter Match Month (MM) :");
                int uMonth = 0;
                try {
                    uMonth = scanner.nextInt();
                    if (uMonth < 0 || uMonth > 12) {
                        System.out.println("Invalid Month Please Enter Match details again!!!");
                        this.displayMenu();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entered Month not a valid Number!!! \nEnter Match Details Again ..\n");
                    this.displayMenu();
                }

                RandomDate userDate = new RandomDate(uDate, uMonth, 2020);

                System.out.println(userDate);
                System.out.println("====================================================================================");
                System.out.println("                 Select Club Number to Add Played Match Details                     ");
                System.out.println("====================================================================================");
                System.out.println("                            NO Need to Type Club Name                               \n" );
              // Team One Selection
                System.out.print("Enter Team 1 Number: ");
                int team1Number = 0;

                try {
                    team1Number = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Entered Goals not a valid Number!!! \nEnter Match Details Again ..\n");
                    this.displayMenu();
                }
                // method get club with  Number
                FootBallClub team1 = getClubWithNum(team1Number);
                if (team1 == null) {
                    System.out.println("Enter Valid Number !!");
                    this.displayMenu();
                }
                // team 1 Goals
                System.out.print("Enter " + team1.getClubName() + " Goal Score : ");
                int team1Goal = 0;
                try {
                    team1Goal = scanner.nextInt();
                    if (team1Goal < 0) {
                        System.out.println("Goals can be 0 or Greater than 0\n Enter Match Details Again ...\n ");
                        this.displayMenu();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entered Goals not a valid Number!!! \nEnter Match Details Again ..\n");
                    this.displayMenu();
                }

                //getting team  2 details
                System.out.print("Enter Team 2 Number: ");

                int team2Number = 0;

                try {
                    team2Number = scanner.nextInt();
                    if (team1Number == team2Number) {
                        System.out.println("Cant Assign A Match with Same Team !!! \nEnter Different team.  ");
                        this.displayMenu();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entered Goals not a valid Number!!! \nEnter Match Details Again ..");
                    this.displayMenu();
                }
                FootBallClub team2 = getClubWithNum(team2Number);
                if (team2 == null) {
                    System.out.println("Enter Valid Number !!");
                    this.displayMenu();
                }
                // team 2 Goals
                int team2Goal = 0;
                System.out.print("Enter " + team2 + " Goal Score : ");
                try {
                    team2Goal = scanner.nextInt();
                    if (team2Goal < 0) {
                        System.out.println("Goals can be 0 or Greater than 0\n Enter Match Details Again ... ");
                        this.displayMenu();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entered Goals not a valid Number!!! \nEnter Match Details Again ..");
                    this.displayMenu();
                }

                // Match summary Confirmation
                System.out.println(" =================================== ");
                System.out.println("Match Summary ");
                System.out.println("Team 1 : " + team1 + " Goals : " + team1Goal);
                System.out.println("Team 2 : " + team2 + " Goals : " + team2Goal);
                System.out.println("Match date : " + userDate);

                System.out.println("Press \"Y\" to insert Match press any other Key to go Back to Menu ...");

                String addOption = scanner.next().toLowerCase();
                if (addOption.equals("y")) {
                    this.addPlayedMatch(team1, team1Goal, team2, team2Goal, userDate);

                } else {
                    System.out.println("Match details Not Added to the Premier League Manager !!");
                    this.displayMenu();
                }
                break;

            case ("o"):
                System.out.println("==============================================");
                System.out.println(" Sort Table of Statistics From Premier League ");
                System.out.println("**********************************************");
                System.out.println("==============================================");
                System.out.println("Sorting Will be Descending Order");
                System.out.println("==============================================");
                System.out.println("Press \"P\" For Sort Clubs By Number of Points Scored Premier League ");
                System.out.println("Press \"W\" For Sort Clubs By Number of Wins Premier League ");
                System.out.println("Press \"G\" For Sort Clubs By Number of Goals Scored Premier League ");

                System.out.print("Enter your Sort By Order Option :  ");
                String userSort = scanner.nextLine().toLowerCase();
                this.sortTeams(this.getClubs(), userSort);
                break;
            default:
                System.out.println("Incorrect Option !!! \nPlease Select one from below ==> \n ");
                this.displayMenu();
                break;
            case ("q"):
                System.out.println("==============================================");
                System.out.println(" ---   Are u sure You want to quit ??   ----  ");
                System.out.print(" ---   Press \"Y\" to Quit --- \n ---   Press Any Key To Back to Program  --- \n Are U Want to Quit  : ");
                String userOption = scanner.next().toLowerCase();
                if (userOption.equals("y")) {
                    System.out.println("==============================================");
                    System.out.println("    ----    Program Closing Now..        ---  ");
                    System.out.println("    ----         Thank you               ---  ");
                    System.out.println("**********************************************");
                    System.exit(0);
                } else {
                    this.displayMenu();
                }
                break;
        }
    }

    public FootBallClub getClubWithNum(int number) {
        FootBallClub team1 = null;
        try {
            if (number < 1 || number > (this.getClubs().size())) {
                System.out.println("Invalid choice of team !!! \nPlease select From Menu Again ...\n");
                this.displayMenu();
            } else {
                team1 = this.getClubs().get((number - 1));
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter valid Team Number !!! \nPlease select From Menu Again ..\n");
            this.displayMenu();
        }

        return team1;
    }

    @Override
    public void addClub() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Club Name :  ");
        String name = scanner.nextLine();
        System.out.print("Enter Club Location :  ");
        String location = scanner.nextLine();
        System.out.println("Club Details Confirmation ");
        System.out.println("Club Name : " + name + "\nLocation  : " + location);
        String confirm = getConfirmation();
        FootBallClub club = null;
        if (confirm.equals("y")) {
            club = new FootBallClub(name, location);
            if (!this.getClubs().isEmpty()) {
                if (!this.getClubs().contains(club)) {
                    this.getClubs().add(club);
                    System.out.println("Club " + club.getClubName() + " Added to Premier League Manager Successfully");
                } else {
                    System.out.println("Club Already Added to Premier League ");
                }
            } else {
                System.out.println("No clubs in the league");
                this.getClubs().add(club);
                System.out.println("Club " + club.getClubName() + " Added to Premier League Manager Successfully");
            }
            this.saveToFile();
        } else {
            System.out.println("Process Terminated");
        }
    }

    @Override
    public void deleteClub(String delName) {
        boolean found = false;
        if (this.getClubs().isEmpty()) {
            System.out.println("Premier League Is Empty !!\nNo Clubs available to Delete!!!");
        } else {
            for (SportsClub club : this.getClubs()) {
                if (club.getClubName().equals(delName)) {
                    this.getClubs().remove(club);
                    System.out.println("Premier League Club " + club.getClubName() + " is Deleted Form Manager.!!!");
                    found = true;
                    break;
                }
            }
            if (found == false) {
                System.out.println(delName + " Not Available in the Premier League!!!");
                System.out.println("Clubs in Premiere League \n==========================================");
                for (SportsClub i : this.getClubs()) {
                    System.out.println(i.getClubName());
                }
            }
        }
        this.saveToFile();
    }

    @Override
    public void displayStatistics(FootBallClub footBallClub) {
        if (footBallClub != null) {
            String name = footBallClub.getClubName();
            System.out.println("Club Name : " + name);
            System.out.println(name + " Location : " + footBallClub.getClubLocation());
            System.out.println(name + " Wins : " + footBallClub.getWin());
            System.out.println(name + " Lost : " + footBallClub.getLoss());
            System.out.println(name + " Draws : " + footBallClub.getDraw());
            System.out.println(name + " Points : " + footBallClub.getPoints());
        } else {
            System.out.println("<<    Club Does not Exist !!!   >>\n <<   Enter Correct Name From Below ==>> >> \n");
            this.displayClubs(this.getClubs());
        }
    }

    @Override
    public void displayTable(List<FootBallClub> list) {
        if (list.isEmpty()) {
            System.out.println("No clubs in the Premier league Manager");
            this.displayMenu();
        }
        int count = 1;
        System.out.printf("%10s %20s %35s %10s %10s %10s %15s %10s %15s %15s %15s  %n", "Club Number", "Club Name",
                "Club Location", "wins", "Lost", "Draw", "Total Match", "Points", "Scored Goals", "Received Goals", "Goal Difference");
        System.out.println("=========================================================================================" +
                "=============================================================================");
        for (FootBallClub club : list) {
            System.out.printf("%10s %20s %35s %10s %10s %10s %15s %10s %15s %15s %n", count, club.getClubName(),
                    club.getClubLocation(), club.getWin(), club.getLoss(), club.getDraw(),
                    club.getTotalMatch(), club.getPoints(), club.getScoredGoals(),
                    club.getReceivedGoals(), club.getScoredGoals() - club.getReceivedGoals());
            count++;
        }
    }

    public void addPlayedMatch(FootBallClub club1, int club1Goals, FootBallClub club2, int club2Goals, RandomDate date) {
        FootBallMatch footBallMatch = new FootBallMatch(club1, club1Goals, club2, club2Goals, date);
        //Seting up Goals for each teams
        club1.setScoredGoals(club1.getScoredGoals() + club1Goals);
        club1.setReceivedGoals(club1.getReceivedGoals() + club2Goals);
        club1.setTotalMatch(club1.getTotalMatch() + 1);

        club2.setScoredGoals(club2.getScoredGoals() + club2Goals);
        club2.setReceivedGoals(club2.getReceivedGoals() + club1Goals);
        club2.setTotalMatch(club2.getTotalMatch() + 1);
        //setting up goal differecnce for each team
        club1.setGoalDifference(club1.getScoredGoals() - club1.getReceivedGoals());
        club2.setGoalDifference(club2.getScoredGoals() - club2.getReceivedGoals());

        if (club1Goals < club2Goals) {
            club1.setLoss(club1.getLoss() + 1);
            club2.setWin(club2.getWin() + 1);
            club2.setPoints(club2.getPoints() + 3);

        } else if (club1Goals > club2Goals) {
            club1.setWin(club1.getWin() + 1);
            club1.setPoints(club1.getPoints() + 3);
            club2.setLoss(club1.getLoss() + 1);
        } else {
            club1.setDraw(club1.getDraw() + 1);
            club1.setPoints(club1.getPoints() + 1);
            club2.setDraw(club2.getDraw() + 1);
            club2.setPoints(club2.getPoints() + 1);
        }

        // add to League Matches
        this.getMatches().add(footBallMatch);
        this.saveToFile();
    }

    // Search FootBallClub with name
    public FootBallClub searchClub(String clubName) {
        FootBallClub clubFound = null;
        boolean found = false;

        if (this.getClubs().isEmpty()) {
            System.out.println("Premier League Is Empty !! " +
                    "\n NO Foot Ball Club registered!!.Cant get Foot BallClub!!!");
        } else {
            for (FootBallClub club : this.getClubs()) {
                if (club.getClubName().equals(clubName)) {
                    clubFound = club;
                    found = true;
                }
            }
        }
        if (found == false) {
            System.out.println(clubName + " Does not exists in Premier League Manager !!! ");
            System.out.println("Clubs in Premiere League \n==========================================\n");
//            this.displayClubs(this.getClubs());
//                for (FootBallClub i : this.getClubs()) {
//                    System.out.println(i.getClubName());
        }
        return clubFound;
    }

    // Sort teams with 3 different ways
    public void sortTeams(List<FootBallClub> clubs, String userSort) {
        switch (userSort) {
            case ("p"):
                Collections.sort(clubs, Collections.reverseOrder());
                System.out.println("    Sorting Clubs Descending By Wins    ");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                this.displayTable(clubs);
                break;
            case ("w"):
                //Descending Order
                Collections.sort(clubs, Collections.reverseOrder(FootBallClub::compareToWins));
                //Ascending Order
//                Collections.sort(clubs, SportsClub::compareToWins);
                System.out.println("    Sorting Clubs Descending Order By Wins");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                this.displayTable(clubs);
                break;
            case ("g"):
                Collections.sort(clubs, Collections.reverseOrder(FootBallClub::compareToGoals));
                System.out.println("    Sorting Clubs Descending Order By Goals Scored    ");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                this.displayTable(clubs);
                break;
            default:
                System.out.println(" Incorrect Sorting Option!!! Enter Correct Sorting Option !!!");
                this.displayMenu();
                break;
        }
    }

    // Dis[lay available clubs with Index
    public void displayClubs(List<FootBallClub> list) {
        int count = 1;
        System.out.printf("%15s %25s %n", "Club Number", "Club Name");
        for (FootBallClub club : list) {
            System.out.printf("%15s %25s %n", count, club.getClubName());
            count++;
        }
    }

    // to confirm to make the process validation
    public String getConfirmation() {
        System.out.println("============================================");
        System.out.println("Press \"Y\" to Confirm the Process \nPress any other Key to go Back to Menu ...\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you Sure ? : ");
        String userOption = scanner.next().toLowerCase();
        return userOption;
    }

    // save clubs and matches to file after every manipulation
    public void saveToFile() {
        // Todo Save to file code
        List<FootBallClub> clubList = this.getClubs();
        List<FootBallMatch> matchList = this.getMatches();
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        System.out.println("File Start to write ...");

        try {
            fout = new FileOutputStream("src/TeamDetails.txt");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(clubList);

        } catch (FileNotFoundException e) {
            System.out.println("TeamDetails.txt File not found !!!!");
            this.displayMenu();
        } catch (Exception e) {
            System.out.println("TeamDetails.txt File Exception !!!!");
            this.displayMenu();
//            e.printStackTrace();
        }
        try {
            fout = new FileOutputStream("src/MatchDetails.txt");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(matchList);

        } catch (FileNotFoundException e) {
            System.out.println("MatchDetails.txt File not found !!!!");
            this.displayMenu();
        } catch (Exception e) {
            System.out.println("MatchDetails.txt File Exception !!!!");
            this.displayMenu();
//            e.printStackTrace();
        } finally {

            try {
                oos.close();
                fout.close();
            } catch (IOException e) {
                System.out.println("File Not Found!!");
                this.displayMenu();
            }

            System.out.println("**********************************************");
            System.out.println("    ----        File Saved               ---  ");
            System.out.println("**********************************************");

        }
    }

    // TO generate random match and update stat and save to file in continuous methods
    public FootBallMatch addRandomMatch() {
        int team1No;
        int team2No;
        RandomDate randomDate;
        int team1Goals = getRandomNumber(8);
        int team2Goals = getRandomNumber(8);
        FootBallMatch match;
        do {
            team1No = getRandomNumber(this.getClubs().size());
            team2No = getRandomNumber(this.getClubs().size());
            randomDate = new RandomDate();
        }
        while (team1No == team2No);
//        Creating a random match
        match = new FootBallMatch(this.getClubs().get(team1No), team1Goals, this.getClubs().get(team2No), team2Goals, randomDate);
        // Update Statistics
        this.addPlayedMatch(this.getClubs().get(team1No), team1Goals, this.getClubs().get(team2No), team2Goals, randomDate);

        return match;
    }

    // to generate a random number
    public static int getRandomNumber(int upperBound) {
        Random rand = new Random();
        int randomNum = rand.nextInt(upperBound);
        return randomNum;
    }
}
