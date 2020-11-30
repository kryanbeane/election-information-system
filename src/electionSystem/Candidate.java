package electionSystem;

public class Candidate extends Politician {

    String id;
    String name;
    String currentParty;
    String DOB;
    String homeCounty;
    String photoURL;

    public Candidate(String id,String name, String currentParty, String DOB, String homeCounty, String photoURL) {
        super(id ,name, currentParty, DOB, homeCounty, photoURL);
    }

}
