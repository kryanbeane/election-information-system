package test;

import electionSystem.Controller;
import electionSystem.List;
import electionSystem.Politician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Politician pol1;
    Politician pol2;
    List<Politician> polList = new List<>();
    @BeforeEach
    void setUp() throws FileNotFoundException {

        pol1 = new Politician("R6","Hugh Hefner", "Fine Gael", "2020-12-12", "Kilkenny","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );
        pol2 = new Politician("C9","Leo Varadkar", "Fine Fail", "2020-12-12", "Dublin","https://pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg" );
        polList.addNode(pol1);
        polList.addNode(pol2);
    }

    @Test
    void testGenerateID() {

    }

    @Test
    void getElection() {
    }

    @Test
    void getPolitician() {

    }

    @Test
    void politicianExists() {
    }

    @Test
    void electionExists() {
    }
}