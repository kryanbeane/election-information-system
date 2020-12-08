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

    public Election(String Id, String electionType, String location, String date, int numberOfWinners) {
        this.Id = Id;
        this.electionType = electionType;
        this.location = location;
        this.date = date;
        this.numberOfWinners = numberOfWinners;
    }

    @Override
    public String toString() {
        return "Election{" +
                "electionType='" + electionType + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", numberOfWinners=" + numberOfWinners +
                '}';
    }
}