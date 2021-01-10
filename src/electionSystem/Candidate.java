package electionSystem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Candidate {

    String id;
    String currentParty;
    String name;
    String DOB;
    String homeCounty;
    String photoUrl;
    Image photo;

    public Candidate(String id, String name, String currentParty, String DOB, String homeCounty, String photoUrl) {
        this.id = id;
        this.name = name;
        this.currentParty = currentParty;
        this.DOB = DOB;
        this.homeCounty = homeCounty;;
        this.photoUrl=photoUrl;
        this.photo = new Image(photoUrl, 100, 100, true, false);
    }

    public String getCandId() { return id; }

    public String getCandCurrentParty() {
        return currentParty;
    }

    public String getCandName() {
        return name;
    }

    public String getCandDOB() {
        return DOB;
    }

    public String getCandHomeCounty() {
        return homeCounty;
    }

    public ImageView getCandImage(){
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
