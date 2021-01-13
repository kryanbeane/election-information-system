package test;

import electionSystem.Controller;
import electionSystem.List;
import electionSystem.Politician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Politician pol1, pol2, pol3, pol4;
    List<Politician> polList = new List<>();
    List<Politician> sortedList = new List<>();
    Controller ctrlr;
    @BeforeEach
    void setUp() throws FileNotFoundException {
        ctrlr = new Controller();
        pol1 = new Politician("R6","Hugh Hefner", "Fine Gael", "2020-12-12", "Kilkenny","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );
        pol2 = new Politician("C9","Leo Varadkar", "Fine Fail", "2020-12-12", "Dublin","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );
        pol3 = new Politician("Y2","Able Abby", "Fine Fail", "2020-12-12", "Dublin","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );
        pol4 = new Politician("C9","Xacharias Zachy", "Independants", "2020-12-12", "Dublin","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );

        polList.addNode(pol1);
        polList.addNode(pol2);
        polList.addNode(pol3);
        polList.addNode(pol4);

        sortedList = ctrlr.polAlphabeticalNameSelectionSort(polList);

    }

    @Test
    void polAlphabeticalNameSelectionSort() {
        List<Politician> comparableList = new List<Politician>();
        comparableList.addNode(pol3);
        comparableList.addNode(pol1);
        comparableList.addNode(pol2);
        comparableList.addNode(pol4);
        assertEquals(comparableList, sortedList);
    }

    @Test
    void polAlphabeticalPartySelectionSort() {
    }

    @Test
    void polAlphabeticalCountySelectionSort() {
    }


}