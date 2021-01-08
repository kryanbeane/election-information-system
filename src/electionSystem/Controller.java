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
    @FXML TextField textElectionType, textElectionLocation, textElectionNumberOfWinners, electionID;
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
    @FXML VBox polVBox, elVBox, polSearchVBox;

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
        if (!namedPols.isEmpty()) {
            sortSearchedPoliticians();
        } else {
            System.out.println("namedPols is empty");
        }

        updatePoliticianSearchVBox();
        namedPols.clear();
    }

    List<Politician> countyPols = new List<>();
    @FXML TextField searchPolCounty;
    public void searchPolByCounty() {
        countyPols.clear();
        String county = searchPolCounty.getText();
        int hash = polCountyHashTable.hashFunction(county);
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
        if (!namedPols.isEmpty()) {
            sortSearchedPoliticians();
        } else {
            System.out.println("countyPols is empty");
        }
        updatePoliticianSearchVBox();
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
        if (!partyPols.isEmpty()) {
            sortSearchedPoliticians();
        } else {
            System.out.println("partyPols is empty");
        }

        updatePoliticianSearchVBox();
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
        if (!namedCands.isEmpty()) {
            sortSearchedCandidates();
        } else {
            System.out.println("namedCands is empty");
        }

        updateCandidateSearchVBox();
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
        if (!countyCands.isEmpty()) {
            sortSearchedCandidates();
        } else {
            System.out.println("countyCands is empty");
        }
        updateCandidateSearchVBox();
    }

    List<Candidate> partyCands = new List<>();
    @FXML TextField searchCandParty;
    public void searchCandByParty() {
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
        if (!partyCands.isEmpty()) {
            sortSearchedCandidates();
        } else {
            System.out.println("partyCands is empty");
        }

        updateCandidateSearchVBox();
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
        if (!locationElec.isEmpty()) {
            sortSearchedElections();
        } else {
            System.out.println("locationElec is empty");
        }

        updateElectionSearchVBox();
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
        if (!typeElec.isEmpty()) {
            sortSearchedElections();
        } else {
            System.out.println("typeElec is empty");
        }

        updateElectionSearchVBox();
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////// Generate Methods ////////////////////////
    /////////////////////////////////////////////////////////////////

    /**
     * Generates ID to be assigned to Politicians, Candidates and Elections.
     *
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

        Main.electionsList.addNode(new Election(generateID(), electionType, electionLocation, electionDate, electionNumberOfWinners));
        updateElectionVBox();



        textElectionType.clear();
        textElectionLocation.clear();
        textElectionDatePicker.getEditor().clear();
        textElectionNumberOfWinners.clear();
    }

    /**
     * Adds candidate to list of candidates.
     * @throws FileNotFoundException -
     */
    public void addCandidate() throws FileNotFoundException {
        Politician pol = candidateSelectionTable.getSelectionModel().getSelectedItem();
        String name = pol.name;
        // Creates new candidate object from a selected politician.




        if (currElection2!=null) {
            Candidate cand = new Candidate(pol.getId(), name, pol.currentParty, pol.DOB, pol.homeCounty, pol.photoUrl);
            currElection2.electionCandidateList.addNode(cand);
            updateCandidatesTable();

            // Hashes candidates when it's added
            candNameHashTable.insertHash(cand.name, cand);
            polCountyHashTable.insertHash(cand.homeCounty, cand);
            polPartyHashTable.insertHash(cand.currentParty, cand);
        }

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
                if (polList.accessAtIndex(i).getContents().getId().equals(polID.getText())) {
                    Main.politicianList.removeNode(i);
                    System.out.println("Removed Politician at index" + i);
                    updatePoliticianVBox();


                }
            }
        } catch (Exception e) {
            System.out.println("You have not entered a valid ID!");
        }
    }

    /**
     *  Deletes candidate from the candidate list.
     */
    public void deleteCandidate() {
        try {
            currCandidate = candidateTable.getSelectionModel().getSelectedItem();
            List<Candidate> canList = Main.candidatesList;

            for (int i = 0; i < canList.length(); i++) {
                if (canList.accessAtIndex(i).getContents().getId().equals(currCandidate.getId())) {
                    Main.candidatesList.removeNode(i);
                    System.out.println("Removed Politician at index" + i);
                    updateCandidatesTable();
                    System.out.println("Removed Aisle at index" + i);
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
                if (electionList.accessAtIndex(i).getContents().getId().equals(electionID.getText())) {
                    Main.electionsList.removeNode(i);
                    System.out.println("Removed Election at index" + i);
                    updateElectionVBox();

                }
            }
        } catch (Exception e) {
            System.out.println("You have not chosen a Election!");
        }
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
    public void updateCandidateVBox() {

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
    public void updatePoliticianSearchVBox(){
        polSearchVBox.getChildren().clear();
        polSearchVBox.getChildren().add(new Text("Politicians in Database:"));
        for(Politician pol: namedPols){
            String polString = pol.toString()+"\n";
            Text polText = new Text();
            polText.setText(polString);
            ImageView polImageView = pol.getPolImage();
            polSearchVBox.getChildren().add(polImageView);
            polSearchVBox.getChildren().add(polText);

        }
    }

    /**
     *
     */
    public void updateCandidateSearchVBox(){
        polSearchVBox.getChildren().clear();
        polSearchVBox.getChildren().add(new Text("Politicians in Database:"));
        for(Politician pol: namedPols){
            String polString = pol.toString()+"\n";
            Text polText = new Text();
            polText.setText(polString);
            ImageView polImageView = pol.getPolImage();
            polSearchVBox.getChildren().add(polImageView);
            polSearchVBox.getChildren().add(polText);

        }
    }

    /**
     *
     */
    public void updateElectionSearchVBox(){
        polSearchVBox.getChildren().clear();
        polSearchVBox.getChildren().add(new Text("Politicians in Database:"));
        for(Politician pol: namedPols){
            String polString = pol.toString()+"\n";
            Text polText = new Text();
            polText.setText(polString);
            ImageView polImageView = pol.getPolImage();
            polSearchVBox.getChildren().add(polImageView);
            polSearchVBox.getChildren().add(polText);

        }
    }


    /////////////////////////////////////////////////////////////////
    //////////////////  Sort Politician Methods  ////////////////////
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
     * @param length -
     * @return -
     */
    public int findLargestPoliticianPartyPos(List<Politician> polList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(polList.accessAtIndex(i).getContents().getCurrentParty().compareTo(polList.accessAtIndex(largestPos).getContents().getCurrentParty())>0) {
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
    public List<Politician> polAlphabeticalSelectionSort(List<Politician> polList) {
        for (int i = polList.length(); i > 0; i--) {
            int posLargest = findLargestPoliticianPartyPos(polList, i);
            polList.swapContents(posLargest, i - 1);
            System.out.println(polList);
        }
        return polList;
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
        //updatePoliticiansTables();
    }

    /**
     *
     */
    public void sortSearchedPoliticians(){
        namedPols = polAlphabeticalSelectionSort(namedPols);
    }

    /**
     *
     */
    public void sortSearchedCandidates() {

    }

    /**
     *
     */
    public void sortSearchedElections() {

    }


    /////////////////////////////////////////////////////////////////
    ///////////////////  Sort Candidate Methods  ////////////////////
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
            if(candList.accessAtIndex(i).getContents().getName().compareTo(candList.accessAtIndex(largestPos).getContents().getName())>0){
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
    ///////////////////  Sort Election Methods  /////////////////////
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
