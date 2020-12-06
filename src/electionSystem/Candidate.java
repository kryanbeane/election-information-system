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

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currentParty='" + currentParty + '\'' +
                ", DOB='" + DOB + '\'' +
                ", homeCounty='" + homeCounty + '\'' +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }
}
