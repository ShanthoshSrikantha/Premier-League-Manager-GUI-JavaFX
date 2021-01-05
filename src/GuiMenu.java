import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;


public class GuiMenu {
    private static List<FootBallClub> listClubs = new ArrayList<>();
    private TableView tableView = new TableView();

    private PremierLeagueManager plm = new PremierLeagueManager();

    public GuiMenu(PremierLeagueManager plm) {
        this.plm = plm;
    }

    public GuiMenu() {
    }

    public PremierLeagueManager getPlm() {
        return plm;
    }

    public void setPlm(PremierLeagueManager plm) {
        this.plm = plm;
    }

    public void displayMenu() {
        //Anchor pane 1 Main GUI
        AnchorPane anchorPane = new AnchorPane();

        // Show Match Details GUI
        Stage stage2 = new Stage();
        AnchorPane anchorPane2 = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        Scene matchScene2 = new Scene(scrollPane, 800, 600);

        Label labelTitle = new Label("Premier League Manager");
        labelTitle.setFont(Font.font(30));
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setLayoutX(200);
        labelTitle.setLayoutY(50);

        TextField tfSearchDate = new TextField();
        tfSearchDate.setLayoutX(100);
        tfSearchDate.setLayoutY(100);
        tfSearchDate.setPromptText("Enter Date ");
        tfSearchDate.setPadding(new Insets(5, 15, 5, 15));

        TextField tfSearchMonth = new TextField();
        tfSearchMonth.setLayoutX(100);
        tfSearchMonth.setLayoutY(135);
        tfSearchMonth.setPromptText("Enter Month  ");
        tfSearchMonth.setPadding(new Insets(5, 15, 5, 15));

        Button btnSearch = new Button("Search");
        btnSearch.setPadding(new Insets(15, 15, 15, 15));
        btnSearch.setLayoutX(350);
        btnSearch.setLayoutY(100);
        btnSearch.setOnAction(event -> {
            int dateSelect = 0;
            int monthSelect = 0;
            List<FootBallMatch> selectedDateMatchList = new ArrayList<>();
            if (tfSearchDate.getText() == null || (tfSearchMonth.getText() == null || tfSearchDate.getText().trim().isEmpty() || tfSearchMonth.getText().trim().isEmpty())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please Enter Integer Value Before Search \n");
                alert.showAndWait();
            } else {

                try {
                    dateSelect = Integer.parseInt(tfSearchDate.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Enter Integer Value \n");
                    alert.showAndWait();

                }
                try {
                    monthSelect = Integer.parseInt(tfSearchMonth.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Enter Integer Value \n");
                    alert.showAndWait();
//                    Stage stage1 = (Stage) btnSearch.getScene().getWindow();
//                    stage1.show();
                }

                int x = 100;
                int y = 60;
                int j = 1;
                for (FootBallMatch match : plm.getMatches()) {
                    if (match.getRandomDate().getDate() == dateSelect && match.getRandomDate().getMonth() == monthSelect) {
                        selectedDateMatchList.add(match);
                    }
                    for (FootBallMatch m : selectedDateMatchList) {
                        Label label1 = new Label();
                        label1.setText(j + ") Team 1 : " + m.getTeam1().getClubName() + "Goals  : " + m.getTeam1Goals() + "\n");
                        Label label2 = new Label();
                        label2.setText("      Team 2 : " + m.getTeam2().getClubName() + "Goals  : " + m.getTeam2Goals() + "\n");
                        Label label3 = new Label();
                        label3.setText("      Date : " + m.getRandomDate() + "\n");
                        label1.setLayoutX(x);
                        label1.setLayoutY(y * j);
                        label2.setLayoutX(x);
                        label2.setLayoutY((y * j) + 20);
                        label3.setLayoutX(x);
                        label3.setLayoutY((y * j) + 40);
                        anchorPane2.getChildren().add(label1);
                        anchorPane2.getChildren().add(label2);
                        anchorPane2.getChildren().add(label3);
                        j++;
                    }

                }
                
                stage2.setScene(matchScene2);
                scrollPane.setContent(anchorPane2);
                stage2.show();


            }
            Stage stage1 = (Stage) btnSearch.getScene().getWindow();
            stage1.show();
        });

        //adding random match generate button
        Button btnAdd = new Button("Add Random Played Match");
        btnAdd.setPadding(new Insets(10, 10, 10, 10));
        btnAdd.setLayoutX(150);
        btnAdd.setLayoutY(200);
        btnAdd.setOnAction(event -> {
            if (plm.getClubs().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Clubs in the League to Add Match \n");
                alert.showAndWait();
            } else {
                FootBallMatch match = plm.addRandomMatch();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(match.toString());
                tableView.refresh();
                alert.showAndWait();
            }
        });

        Button btnSortGoals = new Button("Sort By Goals");
        btnSortGoals.setPadding(new Insets(10, 10, 10, 10));
        btnSortGoals.setLayoutX(380);
        btnSortGoals.setLayoutY(200);
        btnSortGoals.setOnAction(event -> {
            if (plm.getClubs().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Clubs in the League to Add Match \n");
                alert.showAndWait();
            } else {
                plm.sortTeams(plm.getClubs(), "g");
                tableView.getItems().clear();
                tableView.setItems(loadTableClub());
            }
        });

        Button btnSortWins = new Button("Sort By Wins");
        btnSortWins.setPadding(new Insets(10, 10, 10, 10));
        btnSortWins.setLayoutX(500);
        btnSortWins.setLayoutY(200);
        btnSortWins.setOnAction(event -> {
            if (plm.getClubs().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No Clubs in the League to Add Match \n");
                alert.showAndWait();
            } else {
                plm.sortTeams(plm.getClubs(), "w");
                tableView.getItems().clear();
                tableView.setItems(loadTableClub());
            }
        });

        Button btnAllMatch = new Button("Display All Matches");
        btnAllMatch.setPadding(new Insets(10, 10, 10, 10));
        btnAllMatch.setLayoutX(150);
        btnAllMatch.setLayoutY(250);
        btnAllMatch.setOnAction(event -> {
            int x = 100;
            int y = 100;
            int j = 1;

            for (FootBallMatch match : plm.getMatches()) {

                Label label1 = new Label();
                label1.setText(j + ")  Team 1 : " + match.getTeam1().getClubName() + "      Goals  : " + match.getTeam1Goals() + "\n");
                Label label2 = new Label();
                label2.setText("      Team 2 : " + match.getTeam2().getClubName() + "       Goals  : " + match.getTeam2Goals() + "\n");
                Label label3 = new Label();
                label3.setText("      Date : " + match.getRandomDate() + "\n");
                Label label4 = new Label();
                label4.setText(" ==============================================================");
                label1.setLayoutX(x);
                label1.setLayoutY(y * j);
                label2.setLayoutX(x);
                label2.setLayoutY((y * j) + 25);
                label3.setLayoutX(x);
                label3.setLayoutY((y * j) + 50);
                label4.setLayoutX(x);
                label4.setLayoutY((y * j) + 70);
                anchorPane2.getChildren().add(label1);
                anchorPane2.getChildren().add(label2);
                anchorPane2.getChildren().add(label3);
                anchorPane2.getChildren().add(label4);
                j++;
            }
            stage2.setScene(matchScene2);
            scrollPane.setContent(anchorPane2);
            stage2.show();

        });

        Button btnClose = new Button("Close");
        btnClose.setPadding(new Insets(10, 10, 10, 10));
        btnClose.setLayoutX(700);
        btnClose.setLayoutY(200);
        btnClose.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Match Details Are Saved !! ");
            plm.saveToFile();
            Stage stage1 = (Stage) btnClose.getScene().getWindow();
            stage1.close();
        });


//        //Getting Table.. .
        VBox vboxReturn = getTable(plm.getClubs());

        anchorPane.getChildren().addAll(labelTitle, tfSearchDate, tfSearchMonth, btnSearch, btnAdd, btnAllMatch, btnSortGoals, btnClose, vboxReturn, btnSortWins);

        Stage stage1 = new Stage();
        stage1.setTitle("Premier League Manager");
        Scene scene = new Scene(anchorPane, 1000, 800);
        stage1.setOnCloseRequest(event -> {
            stage1.close();
            plm.saveToFile();
        });
        stage1.setScene(scene);
        stage1.show();
    }

    public VBox getTable(List<FootBallClub> list) {

        VBox vbox = new VBox();

        vbox.setSpacing(5);
        vbox.setLayoutX(100);
        vbox.setLayoutY(300);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        ObservableList<FootBallClub> allClubs = FXCollections.observableArrayList(list);

        tableView.prefHeight(900.0);
        tableView.prefWidth(800.0);

        TableColumn<FootBallClub, String> colClubName = new TableColumn("Club Name");
        colClubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        colClubName.setMinWidth(100);

        TableColumn<FootBallClub, String> colClubLocation = new TableColumn("Club Location");
        colClubLocation.setCellValueFactory(new PropertyValueFactory<>("clubLocation"));
        colClubLocation.setMinWidth(150);

        TableColumn<FootBallClub, String> colWins = new TableColumn("Wins");
        colWins.setCellValueFactory(new PropertyValueFactory<>("win"));
        colWins.setMinWidth(50);

        TableColumn<FootBallClub, String> colLost = new TableColumn("Lost");
        colLost.setMinWidth(50);
        colLost.setCellValueFactory(new PropertyValueFactory<>("loss"));

        TableColumn<FootBallClub, String> colDraws = new TableColumn("Draws");
        colDraws.setCellValueFactory(new PropertyValueFactory<>("draw"));
        colDraws.setMinWidth(50);

        TableColumn<FootBallClub, String> colPoints = new TableColumn("Points");
        colPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        colPoints.setMinWidth(50);

        TableColumn<FootBallClub, String> colGoalsScored = new TableColumn("Goals Scored");
        colGoalsScored.setCellValueFactory(new PropertyValueFactory<>("scoredGoals"));
        colGoalsScored.setMinWidth(50);

        TableColumn<FootBallClub, String> colGoalsReceived = new TableColumn("Goals Received");
        colGoalsReceived.setCellValueFactory(new PropertyValueFactory<>("receivedGoals"));
        colGoalsReceived.setMinWidth(50);

        TableColumn<FootBallClub, String> colGoalsDifference = new TableColumn("Goals Difference");
        colGoalsDifference.setCellValueFactory(new PropertyValueFactory<>("goalDifference"));
        colGoalsDifference.setMinWidth(50);

        tableView.getColumns().addAll(colClubName, colClubLocation, colWins, colLost, colDraws, colPoints, colGoalsScored, colGoalsReceived, colGoalsDifference);
        tableView.setItems(allClubs);

        //setting observable list data to table
        vbox.getChildren().addAll(tableView);

        return vbox;
    }

    // reorder the table according to Wins and Goals Scored
    public ObservableList<FootBallClub> loadTableClub() {
        List<FootBallClub> checkClubs = plm.getClubs();
        listClubs.clear();
        for (FootBallClub club : checkClubs) {
            listClubs.add(club);
        }
        ObservableList<FootBallClub> observableListClub = FXCollections.observableArrayList();
        observableListClub.clear();
        for (FootBallClub club : plm.getClubs()) {
            observableListClub.add(club);
        }
        return observableListClub;
    }

}
