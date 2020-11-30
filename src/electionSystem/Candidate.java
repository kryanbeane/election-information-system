package electionSystem;

public class Candidate extends Politician {

    String name;
    String currentParty;
    String DOB;
    String homeCounty;
    String photoURL;

    public Candidate(String name, String currentParty, String DOB, String homeCounty, String photoURL) {
        super(name, currentParty, DOB, homeCounty, photoURL);
    }

}
