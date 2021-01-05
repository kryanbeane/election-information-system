package electionSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.Random;

public class Controller {


    // Size and hash tables //
    int size = 800;
    electionSystem.HashTable<Politician> polNameHashTable = new electionSystem.HashTable<Politician>(size);
    electionSystem.HashTable<Politician> polCountyHashTable = new electionSystem.HashTable<Politician>(size);
    electionSystem.HashTable<Politician> polPartyHashTable = new electionSystem.HashTable<Politician>(size);
    electionSystem.HashTable<Candidate> candNameHashTable = new electionSystem.HashTable<Candidate>(size);
    electionSystem.HashTable<Candidate> candCountyHashTable = new electionSystem.HashTable<Candidate>(size);
    electionSystem.HashTable<Candidate> candPartyHashTable = new electionSystem.HashTable<Candidate>(size);
    electionSystem.HashTable<Election> elecLocationHashTable = new electionSystem.HashTable<Election>(size);
    electionSystem.HashTable<Election> elecTypeHashTable = new electionSystem.HashTable<Election>(size);


    // Search Politician Methods //
    @FXML
    TextField searchPolName;
    public List<Politician> searchPolByName() {
        String name = searchPolName.getText();
        // Hash = hash of search
        int hash = polNameHashTable.hashFunction(name);
        // New list to return search results
        List<Politician> namedPols = new List<>();

        // Loops through length of list at hash position
        for (int i = 0; i < polNameHashTable.hashSize(hash); i++) {
            for (int listI = 0; listI < polPartyHashTable.hashTableList.length; listI++) {
                Politician tempPol = polNameHashTable.getHash(hash, i);
                if (name.equals(tempPol.name)) {
                    // Add each politician to namedPols list and return the list to a table in GUI?
                    currPolitician = polNameHashTable.getHash(hash, i);
                    namedPols.addNode(currPolitician);
                } else {
                    System.out.println("There was no politician by that name.");
                }
            }
        }
        return namedPols;
    }

    @FXML TextField searchPolCounty;
    public List<Politician> searchPolByCounty() {
        String county = searchPolCounty.getText();
        // Hash = hash of search
        int hash = polCountyHashTable.hashFunction(county);
        // New list to return search results
        List<Politician> countyPols = new List<>();

        // Loops through length of list at hash position
        for (int i = 0; i < polCountyHashTable.hashSize(hash); i++) {
            for (int listI = 0; listI < polCountyHashTable.hashTableList.length; listI++) {
                Politician tempPol = polNameHashTable.getHash(hash, i);
                if (county.equals(tempPol.homeCounty)) {
                    // Add each politician to countyPols list and return the list to a table in GUI?
                    currPolitician = polCountyHashTable.getHash(hash, i);
                    countyPols.addNode(currPolitician);
                } else {
                    System.out.println("There were no politicians in that county.");
                }
            }
        }
        return countyPols;
    }

    @FXML TextField searchPolParty;
    public List<Politician> searchPolByParty() {
        String party = searchPolParty.getText();
        // Hash = hash of search
        int hash = polPartyHashTable.hashFunction(party);
        // New list to return search results
        List<Politician> partyPols = new List<>();

        // Loops through length of list at hash position
        for (int i = 0; i < polPartyHashTable.hashSize(hash); i++) {
            for (int listI = 0; listI < polPartyHashTable.hashTableList.length; listI++) {
                Politician tempPol = polPartyHashTable.getHash(hash, i);
                if (party.equals(tempPol.currentParty)) {
                    // Add each politician to namedPols list and return the list to a table in GUI?
                    currPolitician = polPartyHashTable.getHash(hash, i);
                    partyPols.addNode(currPolitician);
                } else {
                    System.out.println("There were no politicians in that party.");
                }
            }
        }
        return partyPols;
    }

    @FXML TextField searchCandName;
    public List<Candidate> searchCandByName() {
        String name = searchCandName.getText();
        // Hash = hash of search
        int hash = candNameHashTable.hashFunction(name);
        // New list to return search results
        List<Candidate> namedCands = new List<>();

        // Loops through length of list at hash position
        for (int i = 0; i < candNameHashTable.hashSize(hash); i++) {
            for (int listI = 0; listI < candNameHashTable.hashTableList.length; listI++) {
                Candidate tempCand = candNameHashTable.getHash(hash, i);
                if (name.equals(tempCand.name)) {
                    // Add each candidate to namedCands list and return the list to a table in GUI?
                    currCandidate = candNameHashTable.getHash(hash, i);
                    namedCands.addNode(currCandidate);
                } else {
                    System.out.println("There were no candidates by that name.");
                }
            }
        }
        return namedCands;
    }

    @FXML TextField searchCandCounty;
    public List<Candidate> searchCandByCounty() {
        String county = searchCandCounty.getText();
        // Hash = hash of search
        int hash = candCountyHashTable.hashFunction(county);
        // New list to return search results
        List<Candidate> countyCands = new List<>();

        // Loops through length of list at hash position
        for (int i = 0; i < candCountyHashTable.hashSize(hash); i++) {
            for (int listI = 0; listI < candCountyHashTable.hashTableList.length; listI++) {
                Candidate tempCand = candCountyHashTable.getHash(hash, i);
                if (county.equals(tempCand.homeCounty)) {
                    // Add each candidate to countyCands list and return the list to a table in GUI?
                    currCandidate = candCountyHashTable.getHash(hash, i);
                    countyCands.addNode(currCandidate);
                } else {
                    System.out.println("There was no politician by that name.");
                }
            }
        }
        return countyCands;
    }

    @FXML TextField searchCandParty;
    public List<Candidate> searchCandByParty() {

        String party = searchCandParty.getText();
        // Hash = hash of search
        int hash = candPartyHashTable.hashFunction(party);
        // New list to return search results
        List<Candidate> partyCands = new List<>();

        // Loops through length of list at hash position
        for (int i = 0; i < candPartyHashTable.hashSize(hash); i++) {
            for (int listI = 0; listI < candPartyHashTable.hashTableList.length; listI++) {
                Candidate tempCand = candPartyHashTable.getHash(hash, i);
                if (party.equals(tempCand.currentParty)) {
                    // Add each candidate to partyCands list and return the list to a table in GUI?
                    currCandidate = candCountyHashTable.getHash(hash, i);
                    partyCands.addNode(currCandidate);
                } else {
                    System.out.println("There were no candidate in that party.");
                }
            }

        }
        return partyCands;
    }

    @FXML TextField searchElecLocation;
    public List<Election> searchElectionByLocation() {
        String location = searchElecLocation.getText();
        // Hash = hash of search
        int hash = elecLocationHashTable.hashFunction(location);
        // New list to return search results
        List<Election> locationElec = new List<>();

        // Loops through length of list at hash position
        for (int i = 0; i < elecLocationHashTable.hashSize(hash); i++) {

            for (int listI = 0; listI < elecLocationHashTable.hashTableList.length; listI++) {
                Election tempElec = elecLocationHashTable.getHash(hash, i);
                if (location.equals(tempElec.location)) {
                    // Add each candidate to partyCands list and return the list to a table in GUI?
                    currElection = elecLocationHashTable.getHash(hash, i);
                    locationElec.addNode(currElection);
                } else {
                    System.out.println("There were no candidate in that party.");
                }
            }

        }
        return locationElec;
    }

    @FXML TextField searchElecType;
    public List<Election> searchElectionByType() {
        String type = searchElecType.getText();
        // Hash = hash of search
        int hash = elecTypeHashTable.hashFunction(type);
        // New list to return search results
        List<Election> typeElec = new List<>();

        // Loops through length of list at hash position
        for (int i = 0; i < elecTypeHashTable.hashSize(hash); i++) {

            for (int listI = 0; listI < elecTypeHashTable.hashTableList.length; listI++) {
                Election tempElec = elecTypeHashTable.getHash(hash, i);

                if (type.equals(tempElec.electionType)) {
                    // Add each candidate to partyCands list and return the list to a table in GUI?
                    currElection = elecTypeHashTable.getHash(hash, i);
                    typeElec.addNode(currElection);
                } else {
                    System.out.println("There were no candidate in that party.");
                }

            }

        }
        return typeElec;
    }

    ObservableList<Politician> searchedPolsObsList = FXCollections.observableArrayList();
    ObservableList<Politician> myPoliticianObsList = FXCollections.observableArrayList();
    ObservableList<Election> myElectionObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList2 = FXCollections.observableArrayList();
    Politician currPolitician;
    Politician currPolitician2Candidate;
    Election currElection, currElection2;
    Candidate currCandidate;
    @FXML
    TextField textCurrentParty, textPoliticianName, textDateOfBirth, textHomeCounty, textImageURL, polID;
    @FXML
    TextField textElectionType, textElectionLocation, textElectionNumberOfWinners;
    @FXML
    TableColumn<Politician, String> pIDColumn, pIDColumn1;
    @FXML
    TableColumn<Politician, String> currentPartyColumn, currentPartyColumn1;
    @FXML
    TableColumn<Politician, String> pNameColumn, pNameColumn1;
    @FXML
    TableColumn<Politician, String> pDOBColumn, pDOBColumn1;
    @FXML
    TableColumn<Politician, String> pHomeCountyColumn, pHomeCountyColumn1;
    @FXML
    TableColumn<Politician, ImageView> pPhotoURLColumn, pPhotoURLColumn1;
    @FXML
    TableColumn<Election, String> electionIDColumn, electionTypeColumn, electionLocationColumn, electionDateColumn, electionNumberOfWInnersColumn;
    @FXML
    TableColumn<Election, String> electionIDColumn1, electionTypeColumn1, electionLocationColumn1, electionDateColumn1, electionNumberOfWInnersColumn1;
    @FXML
    TableColumn<Candidate, String> candidateNameColumn;
    @FXML
    TableView<Politician> politicianTable, candidateSelectionTable, searchedPolsTable;
    @FXML
    TableView<Election> electionTable, electionTable2;
    @FXML
    TableView<Candidate> candidateTable;
    @FXML
    DatePicker polDatePicker, textElectionDatePicker;

    @FXML
    TextArea polTextArea;

    @FXML
    VBox polVBox;
    Image currPolImage;
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
//        polNameHashTable.insertHash(politician.name, politician);
//        polCountyHashTable.insertHash(politician.homeCounty, politician);
//        polPartyHashTable.insertHash(politician.currentParty, politician);

        // Need to figure out Image URL
        textCurrentParty.clear();
        textPoliticianName.clear();
        polDatePicker.getEditor().clear();
        textHomeCounty.clear();
        textImageURL.clear();
    }

    public void updatePoliticianVBox(){
        polVBox.getChildren().clear();
        polVBox.getChildren().add(new Text("Politicians in Database:"));
        for(Politician pol: Main.politicianList){
            String polString = pol.toString();
            Text polText = new Text();
            polText.setText(polString);
            ImageView polImageView = pol.getPolImage();
            polVBox.getChildren().add(polImageView);
            polVBox.getChildren().add(polText);

        }
    }

    public void editPolitician() {
        currPolitician = politicianTable.getSelectionModel().getSelectedItem();

        textPoliticianName.setText(currPolitician.getName());
        textDateOfBirth.setText(currPolitician.getDOB());
        textHomeCounty.setText(currPolitician.getCurrentParty());
        textImageURL.setText(currPolitician.getPolImage().getImage().toString());
    }





    /**
     * Updates politician table with recently added politicians.
     */
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
     * Adds election to the list of elections.
     */
    public void addElection(){
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
        updateElectionTable();



        textElectionType.clear();
        textElectionLocation.clear();
        textElectionDatePicker.getEditor().clear();
        textElectionNumberOfWinners.clear();
    }

    /**
     * Updates election table with recently added elections.
     */
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


    /**
     *
     */
    public void deleteElection() {
        try {
            currElection = electionTable.getSelectionModel().getSelectedItem();
            List<Election> electionList = Main.electionsList;

            for (int i = 0; i < electionList.length(); i++) {
                if (electionList.accessAtIndex(i).getContents().getId().equals(currElection.getId())) {
                    Main.electionsList.removeNode(i);
                    System.out.println("Removed Politician at index" + i);
                    updateElectionTable();
                    System.out.println("Removed Aisle at index" + i);
                }
            }
        } catch (Exception e) {
            System.out.println("You have not chosen a Politician!");
        }
    }

    /**
     *
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

    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 1) //checking click
        {
            currElection2 = electionTable2.getSelectionModel().getSelectedItem();
            System.out.println(currElection2);
            updateCandidatesTable();

        }

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




    /**
     *
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





    // InsertionSort //

   /* public void sort(List<Candidate> candlist){
        int n= candlist.length();
        for(int i=1 ; i<n ; i++){
            String key = candlist.accessAtIndex(i).getContents().getName();
            int j = i-1;
            while(j>=0 && candlist.accessAtIndex(j).getContents().getName().compareToIgnoreCase(key)>0){
                Node<Candidate> temp = candlist.accessAtIndex(j+1);
                temp=candlist.accessAtIndex(j);
                temp.previous = candlist.accessAtIndex(j-1)

                 =candlist.accessAtIndex(j);
                j=j-1;
            }

        }
    }*/

    public int findLargestCandidatePos(List<Candidate> candList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(candList.accessAtIndex(i).getContents().getName().compareTo(candList.accessAtIndex(largestPos).getContents().getName())>0){
                largestPos = i;
            }
        }
        return largestPos;
    }

    public List<Candidate> candidateSelectionSort(List<Candidate> candList) {
        for (int i = candList.length(); i > 0; i--) {
            int posLargest = findLargestCandidatePos(candList, i);
            candList.swapContents(posLargest, i - 1);
            System.out.println(candList);
        }
        return candList;
    }

    public void sortCandidateList(){
        Main.candidatesList = candidateSelectionSort(Main.candidatesList);
        updateCandidatesTable();
    }

    public int findLargestPoliticianPos(List<Politician> polList, int length){
        int largestPos = 0;
        for(int i = 1; i<length;i++){
            if(polList.accessAtIndex(i).getContents().getName().compareTo(polList.accessAtIndex(largestPos).getContents().getName())>0){
                largestPos = i;
            }
        }
        return largestPos;
    }

    public List<Politician> politicianSelectionSort(List<Politician> polList) {
        for (int i = polList.length(); i > 0; i--) {
            int posLargest = findLargestPoliticianPos(polList, i);
            polList.swapContents(posLargest, i - 1);
            System.out.println(polList);
        }
        return polList;
    }

    public void sortPoliticianList(){
        Main.politicianList = politicianSelectionSort(Main.politicianList);
        updatePoliticiansTables();
    }






}
