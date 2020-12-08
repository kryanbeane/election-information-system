package electionSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Hashtable;
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
        int hash=polHashTable.hashFunction(key);
        int x=polHashTable.hashTableList[hash].length();
        Node temp=polHashTable.hashTableList[hash].head;

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
    HashTable<Politician> polHashTable = new HashTable<Politician>(size);
    HashTable<Candidate> candHashTable = new HashTable<Candidate>(size);


    // Search Politician Methods //
    public Politician searchPolByName(String name) {
        currPolitician=polHashTable.getHash(polHashTable.hashFunction(name), getListIndex(name));
        System.out.println(currPolitician);
        return currPolitician;
    }

    public Politician searchPolByCounty(String county) {
        currPolitician=polHashTable.getHash(polHashTable.hashFunction(county), getListIndex(county));
        System.out.println(currPolitician);
        return currPolitician;
    }

    public Politician searchPolByParty(String party) {
        currPolitician=polHashTable.getHash(polHashTable.hashFunction(party), getListIndex(party));
        System.out.println(currPolitician);
        return currPolitician;
    }


    // Search Candidate Methods //
    public Candidate searchCandByName(String name) {
        currCandidate=candHashTable.getHash(polHashTable.hashFunction(name), getListIndex(name));
        System.out.println(currCandidate);
        return currCandidate;
    }

    public Candidate searchCandByCounty(String county) {
        currCandidate=candHashTable.getHash(polHashTable.hashFunction(county), getListIndex(county));
        System.out.println(currCandidate);
        return currCandidate;
    }

    public Candidate searchCandByParty(String party) {
        currCandidate=candHashTable.getHash(polHashTable.hashFunction(party), getListIndex(party));
        System.out.println(currCandidate);
        return currCandidate;
    }






    ObservableList<Politician> myPoliticianObsList = FXCollections.observableArrayList();
    ObservableList<Election> myElectionObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList = FXCollections.observableArrayList();
    Politician currPolitician;
    Politician currPolitician2Candidate;
    Election currElection, currElection2;
    Candidate currCandidate;
    @FXML
    TextField textCurrentParty, textPoliticianName, textDateOfBirth, textHomeCounty, textImageURL;
    @FXML
    TextField textElectionType, textElectionLocation, textElectionDate, textElectionNumberOfWinners;
    @FXML
    TableColumn<Politician, String> sex;
    @FXML
    TableColumn<Politician, String> currentPartyColumn, pNameColumn, pDOBColumn, pHomeCountyColumn, pPhotoURLColumn, pSelectionNameColumn;
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
        String ID;
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
    public void addPolitician() {
        String currentParty = textCurrentParty.getText();
        String name = textPoliticianName.getText();
        String DOB = polDatePicker.getValue().toString();
        String homeCounty = textHomeCounty.getText();
        String photoURL = textImageURL.getText();

        // Creates a new politician with the above values.
        Politician politician = (new Politician(generateID(), name, currentParty, DOB, homeCounty, photoURL));
        // Adds that politician to the politicianList.
        Main.politicianList.addNode(politician);
        updatePoliticiansTables();
        System.out.println(Main.politicianList.printList());


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
        textImageURL.setText(currPolitician.getPhotoURL());
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
        //checks to see if ID already used.
        for(Election election: Main.electionsList){
            if (election.getId().equals(generateID())) {
                generateID();
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
    public void addCandidate() {
        Election elec = currElection2;
        Politician pol = candidateSelectionTable.getSelectionModel().getSelectedItem();
        String name = pol.name;
        // Creates new candidate object from a selected politician.

        if (elec!=null) {
            Candidate cand = new Candidate(pol.getId(), name, pol.currentParty, pol.DOB, pol.homeCounty, pol.photoURL);
            elec.electionCandidateList.addNode(cand);
            updateCandidateTable();
        }

    }

    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 1) //checking click
        {
            currElection2 = electionTable2.getSelectionModel().getSelectedItem();
            System.out.println(currElection2);
            updateCandidateTable();

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
    public void updateCandidateTable() {
        Election elec = currElection2;
        myCandidateObsList.clear();
        candidateNameColumn.setCellValueFactory(new PropertyValueFactory<Candidate,String >("name"));
        for(Candidate candidate: elec.electionCandidateList) {
            myCandidateObsList.add(candidate);

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
                    updateCandidateTable();
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
        updateCandidateTable();
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
