package electionSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Random;

import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;

public class Controller {

    ObservableList<Politician> myPoliticianObsList = FXCollections.observableArrayList();
    ObservableList<Election> myElectionObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList = FXCollections.observableArrayList();
    Politician currPolitician2Candidate;
    @FXML TextField textCurrentParty, textPoliticianName, textDateOfBirth, textHomeCounty, textImageURL;
    @FXML TextField textElectionType, textElectionLocation, textElectionDate, textElectionNumberOfWinners;
    @FXML TableColumn<Politician, String> sex;
    @FXML TableColumn<Politician, String> currentPartyColumn, pNameColumn, pDOBColumn, pHomeCountyColumn, pPhotoURLColumn, pSelectionNameColumn;
    @FXML TableColumn<Election, String>  electionTypeColumn, electionLocationColumn, electionDateColumn, electionNumberOfWInnersColumn;
    @FXML TableColumn<Candidate, String> candidateNameColumn;
    @FXML TableView<Politician> politicianTable, candidateSelectionTable;
    @FXML TableView<Election> electionTable;
    @FXML TableView<Candidate> candidateTable;

    public String generatePoliticianID(){
        //generates an id
        String ID;
        Random rand = new Random();
        int intPart = rand.nextInt(10);
        Random rand2 = new Random();
        int stringIndex = rand2.nextInt(26);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char charPart = alpha.charAt(stringIndex);
        String returnValue = String.valueOf(charPart) + intPart;

        //checks to see if ID already used.
        for(Politician pol: Main.politicianList){
            if (pol.getId().equals(returnValue)) {
                generatePoliticianID();
            }
        }
        return returnValue;
    }

    /**
     * Adds politician to list of politicians.
     */
    public void addPolitician() throws FileNotFoundException {
        String currentParty = textCurrentParty.getText();
        String name = textPoliticianName.getText();
        String DOB = textDateOfBirth.getText();
        String homeCounty = textHomeCounty.getText();
        String photoURL = textImageURL.getText();

        Image img = new Image(new FileInputStream(photoURL));
        ImageView imageView = new ImageView(img);

        Main.politicianList.addNode(new Politician(generatePoliticianID(), currentParty, name, DOB, homeCounty, photoURL));
        updatePoliticiansTables();
        System.out.println(Main.politicianList.printList());
        // Need to figure out Image URL
        textCurrentParty.clear();
        textPoliticianName.clear();
        textDateOfBirth.clear();
        textHomeCounty.clear();
        textImageURL.clear();
    }

    /**
     * Updates politician table with recently added politicians.
     */
    public void updatePoliticiansTables(){
        myPoliticianObsList.clear();
        sex.setCellValueFactory(new PropertyValueFactory<Politician, String>("id"));
        pDOBColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("DOB"));
        pNameColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("name"));
        pHomeCountyColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("homeCounty"));
        pPhotoURLColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("photoURL"));
        currentPartyColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("currentParty"));
        pSelectionNameColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("name"));

        for(Politician pol: Main.politicianList){
            myPoliticianObsList.add(pol);
        }
        politicianTable.setItems(myPoliticianObsList.sorted());
        candidateSelectionTable.setItems(myPoliticianObsList.sorted());
    }

    /**
     * Adds election to the list of elections.
     */
    public void addElection(){
        String electionType = textElectionType.getText();
        String electionLocation = textElectionLocation.getText();
        String electionDate = textElectionDate.getText();
        int electionNumberOfWinners = Integer.parseInt(textElectionNumberOfWinners.getText());

        Main.electionsList.addNode(new Election(electionType, electionLocation, electionDate, electionNumberOfWinners));
        updateElectionTable();
        textElectionType.clear();
        textElectionLocation.clear();
        textElectionDate.clear();
        textElectionNumberOfWinners.clear();
    }

    /**
     * Updates election table with recently added elections.
     */
    public void updateElectionTable() {
        myElectionObsList.clear();
        electionTypeColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("electionType"));
        electionDateColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("date"));
        electionLocationColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("location"));
        electionNumberOfWInnersColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("numberOfWinners"));

        for(Election election: Main.electionsList){
            myElectionObsList.add(election);
        }
        electionTable.setItems(myElectionObsList);

    }

    public void deleteElection() {

    }

    /**
     *
     */
    public void selectCandidate(){
        currPolitician2Candidate = candidateSelectionTable.getSelectionModel().getSelectedItem();
        System.out.println("Politician chosen is: " + currPolitician2Candidate);
        /*updateAisleTable();
        updateShelfTable();
        updatePalletTable();*/
        /*updateWarnings( "You have selected floor: " + currPolitician2Candidate.getName());*/
    }

    /**
     *
     */
    public void updateCandidateTable() {
        myCandidateObsList.clear();
        candidateNameColumn.setCellValueFactory(new PropertyValueFactory<Candidate,String >("name"));
        Politician pol = candidateSelectionTable.getSelectionModel().getSelectedItem();

        Candidate cand = new Candidate(pol.getId(),pol.name, pol.currentParty, pol.DOB, pol.homeCounty, pol.photoURL);
        myCandidateObsList.add(cand);

        candidateTable.setItems(myCandidateObsList);

    }

    /**
     *
     */
    public void deleteCandidate() {

    }

}
