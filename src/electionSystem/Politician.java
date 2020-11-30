package electionSystem;

public class Politician {

    String id;
    String currentParty;
    String name;
    String DOB; //go back and divide into day, month, year, and make returnDOBString method.
    String homeCounty;
    String photoURL; //need to figure out how to add to javafx... imageView?

    public Politician(String id, String name, String currentParty, String DOB, String homeCounty, String photoURL) {
        this.id = id;
        this.name = name;
        this.currentParty = currentParty;
        this.DOB = DOB;
        this.homeCounty = homeCounty;
        this.photoURL = photoURL;
    }

    public String getId() { return id; }

    public String getCurrentParty() {
        return currentParty;
    }

    public String getName() {
        return name;
    }

    public String getDOB() {
        return DOB;
    }

    public String getHomeCounty() {
        return homeCounty;
    }

    public String getPhotoURL() {
        return photoURL;
    }
}
