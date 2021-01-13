package electionSystem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Politician {

    String id;
    String currentParty;
    String name;
    String DOB;
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

    public String getPhotoUrl() {
        return photoUrl;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrentParty(String currentParty) {
        this.currentParty = currentParty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setHomeCounty(String homeCounty) {
        this.homeCounty = homeCounty;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }
}
