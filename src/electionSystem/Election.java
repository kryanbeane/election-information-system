package electionSystem;

public class Election {

    String Id;
    String electionType;
    String location;
    String date;
    int numberOfWinners;
    List<Candidate> electionCandidateList = new List<>();

    public String getId() {
        return Id;
    }

    public String getElectionType() {
        return electionType;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumberOfWinners(int numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    public Election(String Id, String electionType, String location, String date, int numberOfWinners) {
        this.Id = Id;
        this.electionType = electionType;
        this.location = location;
        this.date = date;
        this.numberOfWinners = numberOfWinners;
    }

    @Override
    public String toString() {
        return  "ID: " + Id + "      " +
                "Election Type: " + electionType + "      " +
                "Location: " + location + "      " +
                "Date " + date + "      " +
                "Number Of Winners: " + numberOfWinners;
    }
}