package electionSystem;

public class Politician {


    String name;
    String currentParty;
    String DOB; //go back and divide into day, month, year, and make returnDOBString method.
    String homeCounty;
    String photoURL; //need to figure out how to add to javafx... imageView?

    public Politician(String name, String currentParty, String DOB, String homeCounty, String photoURL) {
        this.name = name;
        this.currentParty = currentParty;
        this.DOB = DOB;
        this.homeCounty = homeCounty;
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public String getCurrentParty() {
        return currentParty;
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
