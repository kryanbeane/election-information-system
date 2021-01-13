package test;

import electionSystem.Candidate;
import electionSystem.Controller;
import electionSystem.List;
import electionSystem.Politician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Politician pol1, pol2, pol3, pol4;
    Candidate cand1, cand2, cand3, cand4;
    List<Politician> polList = new List<>();
    List<Politician> sortedListPolNames, sortedListPolParty, sortedListPolCounty = new List<>();
    List<Candidate> candList, sortedListCandNames, sortedListCandParty, sortedListCandCounty = new List<>();

    Controller ctrlr;
    @BeforeEach
    void setUp() throws FileNotFoundException {
        ctrlr = new Controller();
        pol1 = new Politician("R6","Hugh Hefner", "Fine Gael", "2020-12-12", "Kilkenny","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );
        pol2 = new Politician("C9","Leo Varadkar", "Fine Fail", "2020-12-12", "Dublin","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );
        pol3 = new Politician("Y2","Able Abby", "Gay Rights Party", "2020-12-12", "Dublin","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );
        pol4 = new Politician("C9","Xacharias Zachy", "Independants", "2020-12-12", "Dublin","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );

        cand1 = new Candidate(pol1.getId(), pol1.getName(), pol1.getCurrentParty(), pol1.getDOB(), pol1.getHomeCounty(), pol1.getPhotoUrl());
        cand2 = new Candidate(pol2.getId(), pol2.getName(), pol2.getCurrentParty(), pol2.getDOB(), pol2.getHomeCounty(), pol2.getPhotoUrl());
        cand3 = new Candidate(pol3.getId(), pol3.getName(), pol3.getCurrentParty(), pol3.getDOB(), pol3.getHomeCounty(), pol3.getPhotoUrl());
        cand4 = new Candidate(pol4.getId(), pol4.getName(), pol4.getCurrentParty(), pol4.getDOB(), pol4.getHomeCounty(), pol4.getPhotoUrl());

        polList.addNode(pol1);
        polList.addNode(pol2);
        polList.addNode(pol3);
        polList.addNode(pol4);

        candList.addNode(cand1);
        candList.addNode(cand2);
        candList.addNode(cand3);
        candList.addNode(cand4);

        sortedListCandNames = ctrlr.candAlphabeticalNameSelectionSort(candList);
        sortedListCandCounty = ctrlr.candAlphabeticalCountySelectionSort(candList);
        sortedListCandParty = ctrlr.candAlphabeticalPartySelectionSort(candList);

        sortedListPolNames = ctrlr.polAlphabeticalNameSelectionSort(polList);
        sortedListPolCounty = ctrlr.polAlphabeticalCountySelectionSort(polList);
        sortedListPolParty = ctrlr.polAlphabeticalPartySelectionSort(polList);


    }

    @Test
    void polAlphabeticalNameSelectionSort() {
        List<Politician> comparableList = new List<Politician>();
        comparableList.addNode(pol3);
        comparableList.addNode(pol1);
        comparableList.addNode(pol2);
        comparableList.addNode(pol4);
        assertEquals(comparableList, sortedListPolNames);
    }

    @Test
    void polAlphabeticalPartySelectionSort() {
        List<Politician> comparableList = new List<Politician>();
        comparableList.addNode(pol2);
        comparableList.addNode(pol3);
        comparableList.addNode(pol1);
        comparableList.addNode(pol4);
        assertEquals(comparableList, sortedListPolCounty);
    }

    @Test
    void polAlphabeticalCountySelectionSort() {
        List<Politician> comparableList = new List<Politician>();
        comparableList.addNode(pol2);
        comparableList.addNode(pol3);
        comparableList.addNode(pol1);
        comparableList.addNode(pol4);
        assertEquals(comparableList, sortedListPolParty);
    }

    @Test
    void candAlphabeticalNameSelectionSort() {
        List<Candidate> comparableList = new List<>();
        comparableList.addNode(cand1);
        comparableList.addNode(cand2);
        comparableList.addNode(cand3);
        comparableList.addNode(cand4);
        assertEquals(comparableList, sortedListCandNames);
    }

    @Test
    void candAlphabeticalPartySelectionSort() {
        List<Candidate> comparableList = new List<>();
        comparableList.addNode(cand1);
        comparableList.addNode(cand2);
        comparableList.addNode(cand3);
        comparableList.addNode(cand4);
        assertEquals(comparableList, sortedListCandParty);
    }

    @Test
    void candAlphabeticalCountySelectionSort() {
        List<Candidate> comparableList = new List<>();
        comparableList.addNode(cand4);
        comparableList.addNode(cand3);
        comparableList.addNode(cand1);
        comparableList.addNode(cand2);
        assertEquals(comparableList, sortedListCandCounty);
    }


}