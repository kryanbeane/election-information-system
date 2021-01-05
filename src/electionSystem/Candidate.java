package electionSystem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class Candidate extends Politician {

    String id;
    String name;
    String currentParty;
    String DOB;
    String homeCounty;
    String photo;

    public Candidate(String id,String name, String currentParty, String DOB, String homeCounty, String photo) throws FileNotFoundException {
        super(id ,name, currentParty, DOB, homeCounty, photo);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currentParty='" + currentParty + '\'' +
                ", DOB='" + DOB + '\'' +
                ", homeCounty='" + homeCounty + '\'' +
                ", photoURL='" + photo + '\'' +
                '}';
    }
}
