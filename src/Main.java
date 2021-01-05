import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Main extends Application {
    private List<FootBallClub> leagueClubs = new ArrayList<>();
    private List<FootBallMatch> leagueMatches = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        welcome();
    }

    public void welcome() {
        leagueClubs = readFile("src/TeamDetails.txt");
        leagueMatches = readFile("src/MatchDetails.txt");
        //todo confirm and prove its loaded delete before the sub mission
//        System.out.println(leagueClubs);
//        System.out.println(leagueMatches);
        PremierLeagueManager plm = new PremierLeagueManager(leagueClubs, leagueMatches);

        Boolean run = true;
        while (run) {
            //Main menu
            System.out.println("Select \"C\" Open Command Line Application (CLI)");
            System.out.println("Select \"G\" Open Graphical User Interface Application (GUI)");
            System.out.println("Select \"Q\" Quit the Program");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Your Option : ");
            String userOption = scanner.nextLine().toLowerCase();
            switch (userOption) {
                case ("c"):
                    plm.displayMenu();
                    break;
                case ("g"):
                    GuiMenu guiMenu = new GuiMenu();
                    guiMenu.setPlm(plm);
                    guiMenu.displayMenu();
                    run = false;
                    break;
                case ("q"):
                    System.out.println("==============================================");
                    System.out.println(" ---   Are u sure You want to quit ??   ----  ");
                    System.out.print(" ---   Press \"Y\" to Quit --- \n ---   Press Any Key To Back to Program  --- \n Are U Want to Quit  : ");
                    String option = scanner.next().toLowerCase();
                    if (option.equals("y")) {
                        System.out.println("==============================================");
                        System.out.println("    ----    Program Closing Now..        ---  ");
                        System.out.println("    ----         Thank you               ---  ");
                        System.out.println("**********************************************");
                        System.exit(0);
                    }
                    break;
            }
        }
    }

    // Reading file
    public static <T> List<T> readFile(String fileName) {
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        List<T> clubList = new ArrayList<T>();
        try {
            fin = new FileInputStream(fileName);
            ois = new ObjectInputStream(fin);
            clubList = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(" Database File was not Found !!! ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if ((fin != null) && (ois != null)) {   // to avoid NUll pointer error
                    fin.close();
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clubList;
    }
}
