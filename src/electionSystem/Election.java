package electionSystem;

public class Election {

    String electionType;
    String location;
    String date;
    int numberOfWinners;


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

    public Election(String electionType, String location, String date, int numberOfWinners) {
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