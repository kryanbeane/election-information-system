package electionSystem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class Controller {

    ObservableList<Politician> searchedPolsObsList = FXCollections.observableArrayList();
    ObservableList<Politician> myPoliticianObsList = FXCollections.observableArrayList();
    ObservableList<Election> myElectionObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList2 = FXCollections.observableArrayList();
    Politician currPolitician;
    Politician currPolitician2Candidate;
    Election currElection, currElection2;
    Candidate currCandidate;
    @FXML TextField textCurrentParty, textPoliticianName, textDateOfBirth, textHomeCounty, textImageURL, polID;
    @FXML TextField textElectionType, textElectionLocation, textElectionNumberOfWinners, electionID, electionChooser, polAddChooser, elAddChooser;
    @FXML TableColumn<Politician, String> pIDColumn, pIDColumn1;
    @FXML TableColumn<Politician, String> currentPartyColumn, currentPartyColumn1;
    @FXML TableColumn<Politician, String> pNameColumn, pNameColumn1;
    @FXML TableColumn<Politician, String> pDOBColumn, pDOBColumn1;
    @FXML TableColumn<Politician, String> pHomeCountyColumn, pHomeCountyColumn1;
    @FXML TableColumn<Politician, ImageView> pPhotoURLColumn, pPhotoURLColumn1;
    @FXML TableColumn<Election, String> electionIDColumn, electionTypeColumn, electionLocationColumn, electionDateColumn, electionNumberOfWInnersColumn;
    @FXML TableColumn<Election, String> electionIDColumn1, electionTypeColumn1, electionLocationColumn1, electionDateColumn1, electionNumberOfWInnersColumn1;
    @FXML TableColumn<Candidate, String> candidateNameColumn;
    @FXML TableView<Politician> politicianTable, candidateSelectionTable, searchedPolsTable;
    @FXML TableView<Election> electionTable, electionTable2;
    @FXML TableView<Candidate> candidateTable;
    @FXML DatePicker polDatePicker, textElectionDatePicker;
    @FXML TextArea polTextArea;
    @FXML VBox polVBox, polVBox1, elVBox, polSearchVBox, elSearchVBox, candidateVBox;


    /////////////////////////////////////////////////////////////////
    ///////////////////////    HashTables   /////////////////////////
    /////////////////////////////////////////////////////////////////

    int size = 800;
    HashTable<Politician> polNameHashTable = new HashTable<Politician>(size);
    HashTable<Politician> polCountyHashTable = new HashTable<Politician>(size);
    HashTable<Politician> polPartyHashTable = new HashTable<Politician>(size);
    HashTable<Candidate> candNameHashTable = new HashTable<Candidate>(size);
    HashTable<Candidate> candCountyHashTable = new HashTable<Candidate>(size);
    HashTable<Candidate> candPartyHashTable = new HashTable<Candidate>(size);
    HashTable<Election> elecLocationHashTable = new HashTable<Election>(size);
    HashTable<Election> elecTypeHashTable = new HashTable<Election>(size);


    /////////////////////////////////////////////////////////////////
    ///////////////////////  Search Methods  ////////////////////////
    /////////////////////////////////////////////////////////////////

    // Search Politician Methods //
    List<Politician> namedPols = new List<>();
    @FXML TextField searchPolName;
    public void searchPolByName() {
        namedPols.clear();

        String name = searchPolName.getText();
        int hash = polNameHashTable.hashFunction(name);
        List<Politician> tempList =  polNameHashTable.hashTableList[hash];

        for(int i=0; i<tempList.length(); i++) {
            Politician tempPol = tempList.accessAtIndex(i).getContents();
            if (name.equals(tempPol.name)) {
                namedPols.addNode(tempPol);
            } else {
                System.out.println("There are no politicians by that name.");
            }
        }

        //Little check to see if namedPols is empty
        if (namedPols.isEmpty()) {
            namedPols = polAlphabeticalPartySelectionSort(namedPols);
        } else {
            System.out.println("namedPols is empty");
        }

        updatePoliticianSearchVBox(namedPols);
        namedPols.clear();
    }

    List<Politician> countyPols = new List<>();
    @FXML TextField searchPolCounty;
    public void searchPolByCounty() {
        String county = searchPolCounty.getText();
        int hash = polCountyHashTable.hashFunction(county);
        System.out.println("Running searchPolByCounty");
        List<Politician> tempList =  polCountyHashTable.hashTableList[hash];

        for(int i=0; i<tempList.length(); i++) {
            Politician tempPol = tempList.accessAtIndex(i).getContents();
            if (county.equals(tempPol.homeCounty)) {
                countyPols.addNode(tempPol);
            } else {
                System.out.println("There are no politicians from that county.");
            }
        }

        //Little check to see if countyPols is empty
        if (countyPols.isEmpty()) {
            countyPols = polAlphabeticalPartySelectionSort(countyPols);
        } else {
            System.out.println("countyPols is empty");
        }
        updatePoliticianSearchVBox(countyPols);
        countyPols.clear();
    }

    List<Politician> partyPols = new List<>();
    @FXML TextField searchPolParty;
    public void searchPolByParty() {
        partyPols.clear();
        String party = searchPolParty.getText();
        int hash = polPartyHashTable.hashFunction(party);
        List<Politician> tempList =  polPartyHashTable.hashTableList[hash];

        for(int i=0; i<tempList.length(); i++) {
            Politician tempPol = tempList.accessAtIndex(i).getContents();
            if (party.equals(tempPol.currentParty)) {
                partyPols.addNode(tempPol);
            } else {
                System.out.println("There are no politicians belonging to that party.");
            }
        }

        //Little check to see if partyPols is empty
        if (partyPols.isEmpty()) {
            partyPols = polAlphabeticalNameSelectionSort(partyPols);
        } else {
            System.out.println("partyPols is empty");
        }

        updatePoliticianSearchVBox(partyPols);
    }

    List<Candidate> namedCands = new List<>();
    @FXML TextField searchCandName;
    public void searchCandByName() {
        namedCands.clear();
        String name = searchCandName.getText();
        int hash = candNameHashTable.hashFunction(name);
        List<Candidate> tempList =  candNameHashTable.hashTableList[hash];

        for(int i=0; i<tempList.length(); i++) {
            Candidate tempCand = tempList.accessAtIndex(i).getContents();
            if (name.equals(tempCand.name)) {
                namedCands.addNode(tempCand);
            } else {
                System.out.println("There are no candidates by that name.");
            }
        }

        //Little check to see if namedCands is empty
        if (namedCands.isEmpty()) {
            namedCands = candAlphabeticalPartySelectionSort(namedCands);
        } else {
            System.out.println("namedCands is empty");
        }

        updateCandidateSearchVBox(namedCands);
    }

    List<Candidate> countyCands = new List<>();
    @FXML TextField searchCandCounty;
    public void searchCandByCounty() {
        countyCands.clear();
        String county = searchCandParty.getText();
        int hash = candCountyHashTable.hashFunction(county);
        List<Candidate> tempList =  candCountyHashTable.hashTableList[hash];

        for(int i=0; i<tempList.length(); i++) {
            Candidate tempCand = tempList.accessAtIndex(i).getContents();
            if (county.equals(tempCand.homeCounty)) {
                countyCands.addNode(tempCand);
            } else {
                System.out.println("There are no candidates from that county.");
            }
        }

        //Little check to see if countyCands is empty
        if (countyCands.isEmpty()) {
            countyCands = candAlphabeticalNameSelectionSort(countyCands);
        } else {
            System.out.println("countyCands is empty");
        }
        updateCandidateSearchVBox(countyCands);
    }

    List<Candidate> partyCands = new List<>();
    @FXML TextField searchCandParty;
    public void searchCandByParty() {
        System.out.println("Search cand by party called");

        partyCands.clear();
        String party = searchCandParty.getText();
        int hash = candPartyHashTable.hashFunction(party);
        List<Candidate> tempList =  candPartyHashTable.hashTableList[hash];

        for(int i=0; i<tempList.length(); i++) {
            Candidate tempCand = tempList.accessAtIndex(i).getContents();
            if (party.equals(tempCand.currentParty)) {
                partyCands.addNode(tempCand);
            } else {
                System.out.println("There are no candidates belonging to that party.");
            }
        }

        //Little check to see if partyCands is empty
        if (partyCands.isEmpty()) {
            partyCands = candAlphabeticalCountySelectionSort(partyCands);
        } else {
            System.out.println("partyCands is empty");
        }

        updateCandidateSearchVBox(partyCands);
    }

    List<Election> locationElec = new List<>();
    @FXML TextField searchElecLocation;
    public void searchElectionByLocation() {
        locationElec.clear();
        String location = searchElecLocation.getText();
        int hash = elecLocationHashTable.hashFunction(location);
        List<Election> tempList =  elecLocationHashTable.hashTableList[hash];

        for(int i=0; i<tempList.length(); i++) {
            Election tempElec = tempList.accessAtIndex(i).getContents();
            if (location.equals(tempElec.location)) {
                locationElec.addNode(tempElec);
            } else {
                System.out.println("There are no elections in that county.");
            }
        }

        //Little check to see if locationElec is empty
        if (locationElec.isEmpty()) {
            locationElec = electionNumberOfWinnersSelectionSort(locationElec);
        } else {
            System.out.println("locationElec is empty");
        }

        updateElectionSearchVBox(locationElec);
    }

    List<Election> typeElec = new List<>();
    @FXML TextField searchElecType;
    public void searchElectionByType() {
        typeElec.clear();
        String type = searchElecType.getText();
        int hash = elecTypeHashTable.hashFunction(type);
        List<Election> tempList =  elecTypeHashTable.hashTableList[hash];

        for(int i=0; i<tempList.length(); i++) {
            Election tempElec = tempList.accessAtIndex(i).getContents();
            if (type.equals(tempElec.electionType)) {
                typeElec.addNode(tempElec);
            } else {
                System.out.println("There are no elections of that type.");
            }
        }

        //Little check to see if typeElec is empty
        if (typeElec.isEmpty()) {
            typeElec = electionNumberOfWinnersSelectionSort(typeElec);
        } else {
            System.out.println("typeElec is empty");
        }

        updateElectionSearchVBox(typeElec);
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////// Generate Methods ////////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Generates ID to be assigned to Politicians, Candidates and Elections.
     * @return - Randomly generated ID.
     */
    public String generateID() {
        //generates an id
        Random rand = new Random();
        int intPart = rand.nextInt(10);
        Random rand2 = new Random();
        int stringIndex = rand2.nextInt(26);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char charPart = alpha.charAt(stringIndex);
        return String.valueOf(charPart) + intPart;


    }

    /////////////////////////////////////////////////////////////////
    ///////////////////////    Add Methods   ////////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Adds politician to list of politicians.
     */
    public void addPolitician() throws FileNotFoundException {
        polVBox.setMaxHeight(Double.MAX_VALUE);
        candidateVBox.setMaxHeight(Double.MAX_VALUE);
        String currentParty = textCurrentParty.getText();
        String name = textPoliticianName.getText();
        String DOB = polDatePicker.getValue().toString();
        String homeCounty = textHomeCounty.getText();
        String image = textImageURL.getText();


        // Creates a new politician with the above values.
        Politician politician = (new Politician(generateID(), name, currentParty, DOB, homeCounty, image));
        // Adds that politician to the politicianList.
        Main.politicianList.addNode(politician);
        //THIS NEEDS TO BE REPLACED WITH UPDATE POLITICIAN TEXT.
        updatePoliticianVBox();

        System.out.println(Main.politicianList.printList());

        //hash politician values when it's added.
        polNameHashTable.insertHash(politician.name, politician);
        polCountyHashTable.insertHash(politician.homeCounty, politician);
        polPartyHashTable.insertHash(politician.currentParty, politician);

        // Need to figure out Image URL
        textCurrentParty.clear();
        textPoliticianName.clear();
        polDatePicker.getEditor().clear();
        textHomeCounty.clear();
        textImageURL.clear();
    }

    /**
     * Adds election to the list of elections.
     */
    public void addElection() {
        String iD=generateID();
        for(Election election: Main.electionsList){
            if (election.getId().equals(iD)) {
                iD=generateID();
            }
        }


        String electionType = textElectionType.getText();
        String electionLocation = textElectionLocation.getText();
        String electionDate = textElectionDatePicker.getValue().toString();
        int electionNumberOfWinners = Integer.parseInt(textElectionNumberOfWinners.getText());

        Election election = new Election(generateID(), electionType, electionLocation, electionDate, electionNumberOfWinners);
        Main.electionsList.addNode(election);
        updateElectionVBox();

        elecTypeHashTable.insertHash(election.electionType, election);
        elecLocationHashTable.insertHash(election.location, election);

        textElectionType.clear();
        textElectionLocation.clear();
        textElectionDatePicker.getEditor().clear();
        textElectionNumberOfWinners.clear();
    }

    Politician tempPol;
    Election tempElec;
    Candidate candidate;
    public void addCandidate() {

        String polID = polAddChooser.getText();
        String elID = elAddChooser.getText();

        if(politicianExists(polID) && electionExists(elID)) {
            tempPol = getPolitician(polID);
            tempElec = getElection(elID);
            candidate = new Candidate(tempPol.id, tempPol.name, tempPol.currentParty, tempPol.DOB, tempPol.homeCounty, tempPol.photoUrl);

            if (!tempElec.electionCandidateList.isEmpty()) {
                for (Candidate candidate123 : tempElec.electionCandidateList) {
                    if (candidate123.id.equals(candidate.id)) {
                        System.out.println("This candidate already exists");
                        return;
                    }
                }
            }
            tempElec.electionCandidateList.addNode(candidate);
            candNameHashTable.insertHash(tempPol.name, candidate);
            candCountyHashTable.insertHash(tempPol.homeCounty, candidate);
            candPartyHashTable.insertHash(tempPol.currentParty, candidate);
        } else {
            System.out.println("Either Election or Politician does not exist");
        }

        updateCandidateVBox(tempElec);

    }

    public Election getElection(String electionID) {
        if(Main.electionsList.isEmpty()) {
            return null;
        } else {
            for(Election election: Main.electionsList) {
                if(election.getId().equals(electionID)) {
                    return election;
                }
            }
        }
        return null;
    }

    public Politician getPolitician(String politicianID) {

        if(Main.politicianList.isEmpty()) {
            return null;
        } else {
            for(Politician politician: Main.politicianList) {
                if(politician.getId().equals(politicianID)) {
                    return politician;
                }
            }
        }
        return null;

    }

    public Boolean politicianExists(String polID){
        for(Politician pol: Main.politicianList){
            if (polID.equals(pol.getId())){
                System.out.println("That Politician Exists!");
                return true;
            }
        }
        return false;
    }

    public Boolean electionExists(String elID){
        for(Election el: Main.electionsList){
            if (elID.equals(el.getId())){
                System.out.println("That Election Exists!");
                return true;
            }
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////
    ///////////////////////  Delete Methods  ////////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Deletes politician from the politician list.
     */
    public void deletePolitician() {
        try {
            List<Politician> polList = Main.politicianList;

            for (int i = 0; i < polList.length(); i++) {
                Politician pol = polList.accessAtIndex(i).getContents();
                if (pol.getId().equals(polID.getText())) {
                    Main.politicianList.removeNode(i);
                    polNameHashTable.removeHash(pol.name, pol);
                    polPartyHashTable.removeHash(pol.currentParty, pol);
                    polCountyHashTable.removeHash(pol.homeCounty, pol);
                    System.out.println("Removed Politician at index" + i);
                    updatePoliticianVBox();
                    polID.clear();

                }
            }
        } catch (Exception e) {
            System.out.println("You have not entered a valid ID!");
        }
    }

    /**
     * Deletes candidate from the candidate list.
     */
    public void deleteCandidate() {
        try {
            currCandidate = candidateTable.getSelectionModel().getSelectedItem();
            List<Candidate> canList = Main.candidatesList;

            for (int i = 0; i < canList.length(); i++) {
                Candidate cand = canList.accessAtIndex(i).getContents();
                if (cand.getCandId().equals(currCandidate.getCandId())) {
                    Main.candidatesList.removeNode(i);
                    candNameHashTable.removeHash(cand.name, cand);
                    candPartyHashTable.removeHash(cand.currentParty, cand);
                    candCountyHashTable.removeHash(cand.currentParty, cand);
                    System.out.println("Removed Politician at index" + i);
                    updateCandidatesTable();

                }
            }
        } catch (Exception e) {
            System.out.println("You have not chosen a Politician!");
        }
    }

    /**
     * Deletes election from the election list.
     */
    public void deleteElection() {
        try {
            List<Election> electionList = Main.electionsList;

            for (int i = 0; i < electionList.length(); i++) {
                Election elec = electionList.accessAtIndex(i).getContents();
                if (elec.getId().equals(electionID.getText())) {
                    Main.electionsList.removeNode(i);
                    elecLocationHashTable.removeHash(elec.location, elec);
                    elecTypeHashTable.removeHash(elec.electionType, elec);
                    System.out.println("Removed Election at index" + i);
                    updateElectionVBox();
                    electionID.clear();
                }
            }
        } catch (Exception e) {
            System.out.println("You have not chosen a Election!");
        }
    }

    /**
     *
     */
    Politician updatePol;
    public void populatePolitician() throws ParseException {
        String soughtID=polID.getText();

        for(Politician pol: Main.politicianList) {
            if(soughtID.equals(pol.id)) {
                updatePol=pol;
            } else {
                System.out.println("That Politician doesn't exist!");
                return;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate localDate = LocalDate.parse(updatePol.DOB, formatter);

        textPoliticianName.setText(updatePol.name);
        polDatePicker.setValue(localDate);
        textCurrentParty.setText(updatePol.currentParty);
        textHomeCounty.setText(updatePol.homeCounty);
        textImageURL.setText(updatePol.photoUrl);
        polID.clear();
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////  Update VBox Methods  /////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Updates politician VBox with recently added politicians.
     */
    public void updatePoliticianVBox(){
        polVBox.getChildren().clear();
        polVBox.getChildren().add(new Text("Politicians in Database:"));

        for(Politician pol: Main.politicianList){
            String polString = pol.toString()+"\n";
            Text polText = new Text();
            polText.setText(polString);
            ImageView polImageView = pol.getPolImage();
            polVBox.getChildren().add(polImageView);
            polVBox.getChildren().add(polText);
        }
    }

    /**
     * Updates candidate VBox with recently added candidates.
     */
    public void updateCandidateVBox(Election election) {
        candidateVBox.getChildren().clear();
        for(Candidate candidate: election.electionCandidateList) {
            String candString = candidate.toString()+"\n";
            Text candText = new Text();
            candText.setText(candString);
            ImageView candImageView = candidate.getCandImage();
            candidateVBox.getChildren().add(candImageView);
            candidateVBox.getChildren().add(candText);
        }
    }

    /**
     * Updates election VBox with recently added elections.
     */
    public void updateElectionVBox(){
        elVBox.getChildren().clear();
        elVBox.getChildren().add(new Text("Elections in Database:"));
        for(Election el: Main.electionsList){
            String elString = el.toString() + "\n";
            Text elText = new Text();
            elText.setText(elString);
            elVBox.getChildren().add(elText);

        }
    }

    /**
     *
     */
    public void updatePoliticianSearchVBox(List<Politician> politicianList){
        polSearchVBox.getChildren().clear();
        polSearchVBox.getChildren().add(new Text("Politicians in Database:"));
        for(Politician pol: politicianList){
            String polString = pol.toString()+"\n";
            Text polText = new Text();
            polText.setText(polString);
            ImageView polImageView = pol.getPolImage();
            polSearchVBox.getChildren().add(polImageView);
            polSearchVBox.getChildren().add(polText);

        }
    }

    @FXML VBox candSearchVBox;
    /**
     *
     */
    public void updateCandidateSearchVBox(List<Candidate> candidateList){
        candSearchVBox.getChildren().clear();
        candSearchVBox.getChildren().add(new Text("Candidates in Database:"));
        for(Candidate cand: candidateList) {
            String candString = cand.toString()+"\n";
            Text candText = new Text();
            candText.setText(candString);
            ImageView candImageView = cand.getCandImage();
            candSearchVBox.getChildren().add(candImageView);
            candSearchVBox.getChildren().add(candText);
        }
    }

    /**
     *
     */
    public void updateElectionSearchVBox(List<Election> electionList){
        elSearchVBox.getChildren().clear();
        elSearchVBox.getChildren().add(new Text("Politicians in Database:"));
        for(Election el: electionList){
            String elString = el.toString()+"\n";
            Text elText = new Text();
            elText.setText(elString);


            elSearchVBox.getChildren().add(elText);

        }
    }


    public void viewPoliticians(){
        candidateVBox.getChildren().clear();
        candidateVBox.getChildren().add(new Text("Politicians in Database:"));
        for(Politician pol: Main.politicianList){
            String polString = pol.toString()+"\n";
            Text polText = new Text();
            polText.setText(polString);
            ImageView polImageView = pol.getPolImage();
            candidateVBox.getChildren().add(polImageView);
            candidateVBox.getChildren().add(polText);
        }
    }

    Election currentElection;
    public void viewCandidates() {
        candidateVBox.getChildren().clear();


        String chosenElectionID = electionChooser.getText();

        for (Election el : Main.electionsList) {
            if (chosenElectionID.equals(el.getId())) {
                currentElection = el;
                candidateVBox.getChildren().add(new Text("Candidates for Election:" + el.getId()));
                for (Candidate cand : currentElection.electionCandidateList) {
                    String candString = cand.toString() + "\n";
                    Text candText = new Text();
                    candText.setText(candString);
                    ImageView candImageView = cand.getCandImage();
                    candidateVBox.getChildren().add(candImageView);
                    candidateVBox.getChildren().add(candText);
                }
            } else {
                currentElection = null;
                System.out.println(" That election does not exist");
                return;
            }
        }
    }

    public void viewElections(){
        candidateVBox.getChildren().clear();
        candidateVBox.getChildren().add(new Text(" Elections in Database:" + "\n"));
        for(Election el: Main.electionsList){
            String elString = el.toString()+"\n";
            Text elText = new Text();
            elText.setText(elString);
            candidateVBox.getChildren().add(elText);
        }
    }

    public void viewJustElections() {
        elVBox.getChildren().clear();
        elVBox.getChildren().add(new Text(" Elections in Database:" + "\n"));
        for (Election el : Main.electionsList) {
            String elString = el.toString() + "\n";
            Text elText = new Text();
            elText.setText(elString);
            elVBox.getChildren().add(elText);
        }
    }

    /////////////////////////////////////////////////////////////////
    //////////////  Sort Searched Politician Methods  ///////////////
    /////////////////////////////////////////////////////////////////

    /**
     *
     * @param polList -
     * @return -
     */
    public List<Politician> polAlphabeticalNameSelectionSort(List<Politician> polList) {
        // Loops through entire length of polList
        for (int i = polList.length(); i > 0; i--) {
            int posLargest = findLargestPoliticianNamePos(polList, i);
            polList.swapContents(posLargest, i - 1);
            System.out.println(polList.accessAtIndex(i));
        }
        return polList;
    }

    /**
     *
     * @param polList -
     * @return -
     */
    public List<Politician> polAlphabeticalPartySelectionSort(List<Politician> polList) {
        // Loops through entire length of polList
        for (int i = polList.length(); i > 0; i--) {
            int posLargest = findLargestPoliticianPartyPos(polList, i);
            polList.swapContents(posLargest, i - 1);
            System.out.println(polList.accessAtIndex(i));
        }
        return polList;
    }

    /**
     *
     * @param polList -
     * @return -
     */
    public List<Politician> polAlphabeticalCountySelectionSort(List<Politician> polList) {
        // Loops through entire length of polList
        for (int i = polList.length(); i > 0; i--) {
            int posLargest = findLargestPoliticianCountyPos(polList, i);
            polList.swapContents(posLargest, i - 1);
            System.out.println(polList.accessAtIndex(i));
        }
        return polList;
    }

    /**
     *
     * @param polList -
     * @param length -
     * @return -
     */
    public int findLargestPoliticianNamePos(List<Politician> polList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(polList.accessAtIndex(i).getContents().getName().compareTo(polList.accessAtIndex(largestPos).getContents().getName())>0){
                largestPos = i;
            }
        }
        return largestPos;
    }

    /**
     *
     * @param polList -
     * @param length -
     * @return -
     */
    public int findLargestPoliticianPartyPos(List<Politician> polList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(polList.accessAtIndex(i).getContents().getCurrentParty().compareTo(polList.accessAtIndex(largestPos).getContents().getCurrentParty()) > 0) {
                largestPos = i;
            }
        }
        return largestPos;
    }

    /**
     *
     * @param polList -
     * @param length -
     * @return -
     */
    public int findLargestPoliticianCountyPos(List<Politician> polList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(polList.accessAtIndex(i).getContents().getHomeCounty().compareTo(polList.accessAtIndex(largestPos).getContents().getHomeCounty()) > 0) {
                largestPos = i;
            }
        }
        return largestPos;
    }


    /////////////////////////////////////////////////////////////////
    ///////////////  Politician SORT Button Methods  ////////////////
    /////////////////////////////////////////////////////////////////

    /**
     *
     * @param polList -
     * @param length -
     * @return -
     */
    public int findLargestPoliticianPos(List<Politician> polList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(polList.accessAtIndex(i).getContents().getName().compareTo(polList.accessAtIndex(largestPos).getContents().getName())>0){
                largestPos = i;
            }
        }
        return largestPos;
    }

    /**
     *
     * @param polList -
     * @return -
     */
    public List<Politician> politicianSelectionSort(List<Politician> polList) {
        for (int i = polList.length(); i > 0; i--) {
            int posLargest = findLargestPoliticianPos(polList, i);
            polList.swapContents(posLargest, i - 1);
            System.out.println(polList);
        }
        return polList;
    }

    /**
     *
     */
    public void sortPoliticianList(){
        Main.politicianList = politicianSelectionSort(Main.politicianList);
        updatePoliticianVBox();
    }


    /////////////////////////////////////////////////////////////////
    //////////////  Sort Searched Candidates Methods  ///////////////
    /////////////////////////////////////////////////////////////////

    /**
     *
     * @param candList -
     * @return -
     */
    public List<Candidate> candAlphabeticalNameSelectionSort(List<Candidate> candList) {
        // Loops through entire length of polList
        for (int i = candList.length(); i > 0; i--) {
            int posLargest = findLargestCandidateNamePos(candList, i);
            candList.swapContents(posLargest, i - 1);
            System.out.println(candList.accessAtIndex(i));
        }
        return candList;
    }

    /**
     *
     * @param candList -
     * @return -
     */
    public List<Candidate> candAlphabeticalPartySelectionSort(List<Candidate> candList) {
        // Loops through entire length of polList
        for (int i = candList.length(); i > 0; i--) {
            int posLargest = findLargestCandidatePartyPos(candList, i);
            candList.swapContents(posLargest, i - 1);
            System.out.println(candList.accessAtIndex(i));
        }
        return candList;
    }

    /**
     *
     * @param candList -
     * @return -
     */
    public List<Candidate> candAlphabeticalCountySelectionSort(List<Candidate> candList) {
        // Loops through entire length of polList
        for (int i = candList.length(); i > 0; i--) {
            int posLargest = findLargestCandidateCountyPos(candList, i);
            candList.swapContents(posLargest, i - 1);
            System.out.println(candList.accessAtIndex(i));
        }
        return candList;
    }

    /**
     *
     * @param candList -
     * @param length -
     * @return -
     */
    public int findLargestCandidateNamePos(List<Candidate> candList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(candList.accessAtIndex(i).getContents().getCandName().compareTo(candList.accessAtIndex(largestPos).getContents().getCandName())>0){
                largestPos = i;
            }
        }
        return largestPos;
    }

    /**
     *
     * @param candList -
     * @param length -
     * @return -
     */
    public int findLargestCandidatePartyPos(List<Candidate> candList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(candList.accessAtIndex(i).getContents().getCandCurrentParty().compareTo(candList.accessAtIndex(largestPos).getContents().getCandCurrentParty()) > 0) {
                largestPos = i;
            }
        }
        return largestPos;
    }

    /**
     *
     * @param candList -
     * @param length -
     * @return -
     */
    public int findLargestCandidateCountyPos(List<Candidate> candList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(candList.accessAtIndex(i).getContents().getCandHomeCounty().compareTo(candList.accessAtIndex(largestPos).getContents().getCandHomeCounty()) > 0) {
                largestPos = i;
            }
        }
        return largestPos;
    }

    /////////////////////////////////////////////////////////////////
    ///////////////  Candidate SORT Button Methods  /////////////////
    /////////////////////////////////////////////////////////////////

    /**
     *
     * @param candList -
     * @param length -
     * @return -
     */
    public int findLargestCandidatePos(List<Candidate> candList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(candList.accessAtIndex(i).getContents().getCandName().compareTo(candList.accessAtIndex(largestPos).getContents().getCandName())>0){
                largestPos = i;
            }
        }
        return largestPos;
    }

    /**
     *
     * @param candList -
     * @return -
     */
    public List<Candidate> candidateSelectionSort(List<Candidate> candList) {
        for (int i = candList.length(); i > 0; i--) {
            int posLargest = findLargestCandidatePos(candList, i);
            candList.swapContents(posLargest, i - 1);
            System.out.println(candList);
        }
        return candList;
    }

    /**
     *
     */
    public void sortCandidateList(){
        Main.candidatesList = candidateSelectionSort(Main.candidatesList);
        updateCandidatesTable();
    }


    /////////////////////////////////////////////////////////////////
    //////////////  Sort Searched Elections Methods  ////////////////
    /////////////////////////////////////////////////////////////////

    /**
     *
     * @param electionList -
     * @return -
     */
    public List<Election> electionNumberOfWinnersSelectionSort(List<Election> electionList) {
        // Loops through entire length of polList
        for (int i = electionList.length(); i > 0; i--) {
            int posLargest = findLargestElectionWinners(electionList, i);
            electionList.swapContents(posLargest, i - 1);
            System.out.println(electionList.accessAtIndex(i));
        }
        return electionList;
    }

    /**
     *
     * @param electionList -
     * @param length -
     * @return -
     */
    public int findLargestElectionWinners(List<Election> electionList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(electionList.accessAtIndex(i).getContents().getNumberOfWinners()>electionList.accessAtIndex(largestPos).getContents().getNumberOfWinners()){
                largestPos = i;
            }
        }
        return largestPos;
    }


    /////////////////////////////////////////////////////////////////
    ///////////////  Election SORT Button Methods  //////////////////
    /////////////////////////////////////////////////////////////////



    /////////////////////////////////////////////////////////////////
    ///////////////////     LEFTOVER Methods    /////////////////////
    /////////////////////////////////////////////////////////////////

    public void editPolitician() {
        currPolitician = politicianTable.getSelectionModel().getSelectedItem();

        textPoliticianName.setText(currPolitician.getName());
        textDateOfBirth.setText(currPolitician.getDOB());
        textHomeCounty.setText(currPolitician.getCurrentParty());
        textImageURL.setText(currPolitician.getPolImage().getImage().toString());
    }

    public void updatePoliticiansTables(){
        myPoliticianObsList.clear();
        pIDColumn.setCellValueFactory(new PropertyValueFactory<Politician, String>("id"));
        pDOBColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("DOB"));
        pNameColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("name"));
        pHomeCountyColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("homeCounty"));
        pPhotoURLColumn.setCellValueFactory(new PropertyValueFactory<Politician, ImageView>("polImage"));
        currentPartyColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("currentParty"));
        pIDColumn1.setCellValueFactory(new PropertyValueFactory<Politician, String>("id"));
        pDOBColumn1.setCellValueFactory(new PropertyValueFactory<Politician,String >("DOB"));
        pNameColumn1.setCellValueFactory(new PropertyValueFactory<Politician,String >("name"));
        pHomeCountyColumn1.setCellValueFactory(new PropertyValueFactory<Politician,String >("homeCounty"));
        pPhotoURLColumn1.setCellValueFactory(new PropertyValueFactory<Politician, ImageView>("polImage"));
        currentPartyColumn1.setCellValueFactory(new PropertyValueFactory<Politician,String >("currentParty"));


        for(Politician pol: Main.politicianList){
            myPoliticianObsList.add(pol);

        }

        politicianTable.setItems(myPoliticianObsList);
        candidateSelectionTable.setItems(myPoliticianObsList);
    }

    public void updateElectionTable() {
        myElectionObsList.clear();
        electionIDColumn.setCellValueFactory(new PropertyValueFactory<Election, String >("id"));
        electionTypeColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("electionType"));
        electionDateColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("date"));
        electionLocationColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("location"));
        electionNumberOfWInnersColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("numberOfWinners"));
        electionIDColumn1.setCellValueFactory(new PropertyValueFactory<Election, String >("id"));
        electionTypeColumn1.setCellValueFactory(new PropertyValueFactory<Election,String >("electionType"));
        electionDateColumn1.setCellValueFactory(new PropertyValueFactory<Election,String >("date"));
        electionLocationColumn1.setCellValueFactory(new PropertyValueFactory<Election,String >("location"));
        electionNumberOfWInnersColumn1.setCellValueFactory(new PropertyValueFactory<Election,String >("numberOfWinners"));


        for(Election election: Main.electionsList){
            myElectionObsList.add(election);
        }
        electionTable.setItems(myElectionObsList);
        electionTable2.setItems(myElectionObsList);
    }

    public void updateCandidatesTable() {
        myCandidateObsList.clear();
        myCandidateObsList2.clear();
        candidateNameColumn.setCellValueFactory(new PropertyValueFactory<Candidate,String >("name"));
        for(Candidate candidate: currElection2.electionCandidateList) {
            myCandidateObsList.add(candidate);

        }
        candidateTable.setItems(myCandidateObsList);


        if (currElection2 != null);
        {
            for (Candidate candidate : currElection2.electionCandidateList) {
                myCandidateObsList2.add(candidate);

            }
        }
        candidateTable.setItems(myCandidateObsList);

    }

    public void clickItem(MouseEvent event) {
        if (event.getClickCount() == 1) //checking click
        {
            currElection2 = electionTable2.getSelectionModel().getSelectedItem();
            System.out.println(currElection2);
            updateCandidatesTable();

        }

    }

    public void selectCandidate(){
        currPolitician2Candidate = candidateSelectionTable.getSelectionModel().getSelectedItem();
        System.out.println("Politician chosen is: " + currPolitician2Candidate);
        /*updateAisleTable();
        updateShelfTable();
        updatePalletTable();*/
        /*updateWarnings( "You have selected floor: " + currPolitician2Candidate.getName());*/
    }


    /////////////////////////////////////////////////////////////////
    ////////////////////   Save Load and Reset   ////////////////////
    /////////////////////////////////////////////////////////////////

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("ElectionInformationSystem.xml"));
        out.writeObject(Main.politicianList);
        out.writeObject(Main.candidatesList);
        out.writeObject(Main.electionsList);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("ElectionInformationSystem.xml"));
        Main.politicianList = (List<Politician>) is.readObject();
        Main.candidatesList = (List<Candidate>) is.readObject();
        Main.electionsList = (List<Election>) is.readObject();
        is.close();
        updatePoliticianVBox();

    }

    public void reset() {
        Main.politicianList.emptyList();
        Main.candidatesList.emptyList();
        Main.electionsList.emptyList();
    }

}
