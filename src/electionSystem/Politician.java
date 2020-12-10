package electionSystem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Politician {

    String id;
    String currentParty;
    String name;
    String DOB; //go back and divide into day, month, year, and make returnDOBString method.
    String homeCounty;
    Image photo;

    public Politician(String id, String name, String currentParty, String DOB, String homeCounty, Image photo) throws FileNotFoundException {
        this.id = id;
        this.name = name;
        this.currentParty = currentParty;
        this.DOB = DOB;
        this.homeCounty = homeCounty;;
        this.photo = photo;
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

    public ImageView getPolImage(){
        ImageView imagev = new ImageView(photo);
        imagev.setFitWidth(50);
        imagev.setFitHeight(50);
        return imagev; }

    @Override
    public String toString() {
        return "Politician{" +
                "id='" + id + '\'' +
                ", currentParty='" + currentParty + '\'' +
                ", name='" + name + '\'' +
                ", DOB='" + DOB + '\'' +
                ", homeCounty='" + homeCounty + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
