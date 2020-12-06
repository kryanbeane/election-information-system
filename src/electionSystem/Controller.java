package electionSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Random;

public class Controller {

    ObservableList<Politician> myPoliticianObsList = FXCollections.observableArrayList();
    ObservableList<Election> myElectionObsList = FXCollections.observableArrayList();
    ObservableList<Candidate> myCandidateObsList = FXCollections.observableArrayList();
    Politician currPolitician;
    Politician currPolitician2Candidate;
    Election currElection;
    Candidate currCandidate;
    @FXML TextField textCurrentParty, textPoliticianName, textDateOfBirth, textHomeCounty, textImageURL;
    @FXML TextField textElectionType, textElectionLocation, textElectionDate, textElectionNumberOfWinners;
    @FXML TableColumn<Politician, String> sex;
    @FXML TableColumn<Politician, String> currentPartyColumn, pNameColumn, pDOBColumn, pHomeCountyColumn, pPhotoURLColumn, pSelectionNameColumn;
    @FXML TableColumn<Election, String>  electionIDColumn, electionTypeColumn, electionLocationColumn, electionDateColumn, electionNumberOfWInnersColumn;
    @FXML TableColumn<Candidate, String> candidateNameColumn;
    @FXML TableView<Politician> politicianTable, candidateSelectionTable;
    @FXML TableView<Election> electionTable;
    @FXML TableView<Candidate> candidateTable;

    /**
     * Generates ID to be assigned to Politicians, Candidates and Elections.
     * @return - Randomly generated ID.
     */
    public String generateID(){
        //generates an id
        String ID;
        Random rand = new Random();
        int intPart = rand.nextInt(10);
        Random rand2 = new Random();
        int stringIndex = rand2.nextInt(26);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char charPart = alpha.charAt(stringIndex);
        String returnValue = String.valueOf(charPart) + intPart;
        return returnValue;
    }

    /**
     * Adds politician to list of politicians.
     */
    public void addPolitician() {
        String currentParty = textCurrentParty.getText();
        String name = textPoliticianName.getText();
        String DOB = textDateOfBirth.getText();
        String homeCounty = textHomeCounty.getText();
        String photoURL = textImageURL.getText();

        Main.politicianList.addNode(new Politician(generateID(),name,currentParty, DOB, homeCounty, photoURL));
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
        politicianTable.setItems(myPoliticianObsList);
        candidateSelectionTable.setItems(myPoliticianObsList);
    }

    /**
     *
     */
    public void deletePolitician() {
        try {
        currPolitician = politicianTable.getSelectionModel().getSelectedItem();
        List<Politician> polList = Main.politicianList;

        for (int i = 0; i < polList.length(); i++) {
            if (polList.accessAtIndex(i).getContents().getId() == currPolitician.id) {
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

    public Politician getPolitician() {
        // stuff


        return null;
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
        String electionDate = textElectionDate.getText();
        int electionNumberOfWinners = Integer.parseInt(textElectionNumberOfWinners.getText());

        Main.electionsList.addNode(new Election(generateID(), electionType, electionLocation, electionDate, electionNumberOfWinners));
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
        electionIDColumn.setCellValueFactory(new PropertyValueFactory<Election, String >("id"));
        electionTypeColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("electionType"));
        electionDateColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("date"));
        electionLocationColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("location"));
        electionNumberOfWInnersColumn.setCellValueFactory(new PropertyValueFactory<Election,String >("numberOfWinners"));

        for(Election election: Main.electionsList){
            myElectionObsList.add(election);
        }
        electionTable.setItems(myElectionObsList);
    }

    /**
     *
     */
    public void deleteElection() {
        try {
            currElection = electionTable.getSelectionModel().getSelectedItem();
            List<Election> electionList = Main.electionsList;

            for (int i = 0; i < electionList.length(); i++) {
                if (electionList.accessAtIndex(i).getContents().getId() == currElection.getId()) {
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

    public Election getElection() {
        // stuff

        return null;
    }

    /**
     *
     */
    public void addCandidate() {
        Politician pol = candidateSelectionTable.getSelectionModel().getSelectedItem();
        Candidate cand = new Candidate(pol.getId(), pol.name, pol.currentParty, pol.DOB, pol.homeCounty, pol.photoURL);

        Main.candidatesList.addNode(cand);

        updateCandidateTable();

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
        for(Candidate candidate: Main.candidatesList) {
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
                if (canList.accessAtIndex(i).getContents().getId() == currCandidate.getId()) {
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

    public Candidate getCandidate() {
        // stuff

        return null;
    }

    public void sortCandidateList(){
        Main.candidatesList = candidateSelectionSort(Main.candidatesList);
        updateCandidateTable();
    }

    //Insertionsort
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

    public Node<Politician> partitionLast(Node<Politician> start, Node<Politician> end)
    {
        if(start == end)
            return start;

        Node<Politician> pivot_prev = start;
        Node<Politician> curr = start;
        Politician pivot = end.getContents();

        // iterate till one before the end,
        // no need to iterate till the end
        // because end is pivot
        while(start != end )
        {
            if(start.getContents().getName().compareTo(pivot.getName()) < 0) {
                // keep tracks of last modified item
                pivot_prev = curr;
                Politician temp = curr.getContents();
                curr.setContents(start.getContents());
                start.setContents(temp);
                curr = curr.next;
            }
            start = start.next;
        }

        // swap the position of curr i.e.
        // next suitable index and pivot
        Politician temp = curr.getContents();
        curr.setContents(pivot);
        end.setContents(temp);

        // return one previous to current
        // because current is now pointing to pivot
        return pivot_prev;
    }

    void polInsertionSort(Node<Politician> start, Node<Politician> end)
    {
        if(start == end )
            return;

        // split list and partion recurse
        Node<Politician> pivot_prev = partitionLast(start, end);
        polInsertionSort(start, pivot_prev);

        // if pivot is picked and moved to the start,
        // that means start and pivot is same
        // so pick from next of pivot
        if( pivot_prev != null &&
                pivot_prev == start )
            polInsertionSort(pivot_prev.next, end);

            // if pivot is in between of the list,
            // start from next of pivot,
            // since we have pivot_prev, so we move two nodes
        else if(pivot_prev != null &&
                pivot_prev.next != null)
            polInsertionSort(pivot_prev.next.next, end);
    }

    public void insertionSortPoliticianList(){
        Node<Politician> headNode = Main.politicianList.head;
        System.out.println(headNode.getContents());
        Node<Politician> endNode = Main.politicianList.accessAtIndex(Main.politicianList.length()-1);
        System.out.println(endNode.getContents());
        System.out.println("Before:" + Main.politicianList.printList());
        polInsertionSort(headNode, endNode);
        System.out.println("After:" + Main.politicianList.printList());

        updatePoliticiansTables();
    }






}
