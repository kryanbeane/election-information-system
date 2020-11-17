package electionSystem;

public class Election {

    String electionType;
    String location;
    int dayOfMonth;
    int month;
    int year;
    int numberOfWinners;

    public Election(String electionType, String location, int dayOfMonth, int month, int year, int numberOfWinners) {
        this.electionType = electionType;
        this.location = location;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.numberOfWinners = numberOfWinners;
    }
}
