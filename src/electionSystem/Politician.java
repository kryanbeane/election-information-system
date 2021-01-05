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
    String photoUrl;
    Image photo;

    public Politician(String id, String name, String currentParty, String DOB, String homeCounty,String photoUrl) throws FileNotFoundException {
        this.id = id;
        this.name = name;
        this.currentParty = currentParty;
        this.DOB = DOB;
        this.homeCounty = homeCounty;;
        this.photoUrl=photoUrl;
        this.photo = new Image(photoUrl, 100, 100, true, false);
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
        return  "ID: " + id + "      " +
                "Current Party: " + currentParty + "      " +
                "Name: " + name + "      " +
                "DOB: " + DOB + "      " +
                "Home County: " + homeCounty;
    }
}
