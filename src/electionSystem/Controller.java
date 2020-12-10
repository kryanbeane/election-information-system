package electionSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.util.Random;

public class Controller {

    // Searching //
    /**
     * Method to find the index of the hashed object user is looking for.
     * @param key - String field of object being searched for.
     * @return - The index that object is hashed to.
     */
    public int getListIndex(String key) {
        int index=0;
        int hash=polNameHashTable.hashFunction(key);
        int x=polNameHashTable.hashTableList[hash].length();
        Node temp=polNameHashTable.hashTableList[hash].head;

        for(int i=0; i<=x; i++) {
            if(temp.getContents().equals(key)) {
                index=i;
            }
            temp=temp.next;
        }
        return index;
    }

    // Size and hash tables //
    int size=800;
    HashTable<Politician> polNameHashTable = new HashTable<Politician>(size);
    HashTable<Politician> polCountyHashTable = new HashTable<Politician>(size);
    HashTable<Politician> polPartyHashTable = new HashTable<Politician>(size);
    HashTable<Candidate> candNameHashTable = new HashTable<Candidate>(size);
    HashTable<Candidate> candCountyHashTable = new HashTable<Candidate>(size);
    HashTable<Candidate> candPartyHashTable = new HashTable<Candidate>(size);

    @FXML TextField searchPolName;
    // Search Politician Methods //
    public List<Politician> searchPolByName() {
        String name = searchPolName.getText();
        // Hash = hash of search
        int hash = polNameHashTable.hashFunction(name);
        // New list to return search results
        List<Politician> namedPols = new List<>();


        for(int i = 0; i < polNameHashTable.hashSize(hash); i++){
            if(name.equals(currPolitician.name)) {
                // Add each politician to namedPols list and return the list to a table in GUI?
                currPolitician=polNameHashTable.getHash(hash, i);
                namedPols.addNode(currPolitician);
            }
            System.out.println("There was no politician by that name.");
            return null;
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


        for(int i = 0; i < polCountyHashTable.hashSize(hash); i++){
            if(county.equals(currPolitician.homeCounty)) {
                // Add each politician to countyPols list and return the list to a table in GUI?
                currPolitician=polCountyHashTable.getHash(hash, i);
                countyPols.addNode(currPolitician);
            }
            System.out.println("There are no politicians in that county.");
            return null;
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


        for(int i = 0; i < polPartyHashTable.hashSize(hash); i++){
            if(party.equals(currPolitician.currentParty)) {
                // Add each politician to partyPols list and return the list to a table in GUI?
                currPolitician=polPartyHashTable.getHash(hash, i);
                partyPols.addNode(currPolitician);
            }
            System.out.println("There are no politicians in that party.");
            return null;
        }
        return partyPols;
    }

    // Search Candidate Methods //
    public List<Candidate> searchCandByName(String name) {
        // Hash = hash of search
        int hash = candNameHashTable.hashFunction(name);
        // New list to return search results
        List<Candidate> namedCands = new List<>();


        for(int i = 0; i < candNameHashTable.hashSize(hash); i++){
            if(name.equals(currCandidate.name)) {
                // Add each politician to namedPols list and return the list to a table in GUI?
                currCandidate=candNameHashTable.getHash(hash, i);
                namedCands.addNode(currCandidate);
            }
            System.out.println("There was no candidate by that name.");
            return null;
        }
        return namedCands;
    }

    public List<Candidate> searchCandByCounty(String county) {
        // Hash = hash of search
        int hash = candCountyHashTable.hashFunction(county);
        // New list to return search results
        List<Candidate> countyCands = new List<>();


        for(int i = 0; i < candCountyHashTable.hashSize(hash); i++){
            if(county.equals(currCandidate.homeCounty)) {
                // Add each politician to countyPols list and return the list to a table in GUI?
                currCandidate=candCountyHashTable.getHash(hash, i);
                countyCands.addNode(currCandidate);
            }
            System.out.println("There are no candidates in that county.");
            return null;
        }
        return countyCands;
    }

    public List<Candidate> searchCandByParty(String party) {
        // Hash = hash of search
        int hash = candPartyHashTable.hashFunction(party);
        // New list to return search results
        List<Candidate> partyCands = new List<>();


        for(int i = 0; i < candPartyHashTable.hashSize(hash); i++){
            if(party.equals(currCandidate.currentParty)) {
                // Add each politician to partyCands list and return the list to a table in GUI?
                currCandidate=candPartyHashTable.getHash(hash, i);
                partyCands.addNode(currCandidate);
            }
            System.out.println("There are no candidates in that party.");
            return null;
        }
        return partyCands;
    }



    ObservableList<Politician> myPoliticianObsList = FXCollections.observableArrayList();
    ObservableList<Election> myElectionObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList2 = FXCollections.observableArrayList();
    Politician currPolitician;
    Politician currPolitician2Candidate;
    Election currElection, currElection2;
    Candidate currCandidate;
    @FXML
    TextField textCurrentParty, textPoliticianName, textDateOfBirth, textHomeCounty, textImageURL;
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
    TableView<Politician> politicianTable, candidateSelectionTable;
    @FXML
    TableView<Election> electionTable, electionTable2;
    @FXML
    TableView<Candidate> candidateTable;
    @FXML
    DatePicker polDatePicker, textElectionDatePicker;

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
        Image image = new Image("file:resources/" + textImageURL.getText());


        // Creates a new politician with the above values.
        Politician politician = (new Politician(generateID(), name, currentParty, DOB, homeCounty, image));
        // Adds that politician to the politicianList.
        Main.politicianList.addNode(politician);
        updatePoliticiansTables();
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
            currPolitician = politicianTable.getSelectionModel().getSelectedItem();
            List<Politician> polList = Main.politicianList;

            for (int i = 0; i < polList.length(); i++) {
                if (polList.accessAtIndex(i).getContents().getId().equals(currPolitician.id)) {
                    Main.politicianList.removeNode(i);
                    System.out.println("Removed Politician at index" + i);
                    updatePoliticiansTables();
                    System.out.println("Removed Aisle at index" + i);

                }
            }
        } catch (Exception e) {
            System.out.println("You have not chosen a Politician!");
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
            Candidate cand = new Candidate(pol.getId(), name, pol.currentParty, pol.DOB, pol.homeCounty, pol.photo);
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
