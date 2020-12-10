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
    String photoName; //need to figure out how to add to javafx... imageView?
    ImageView photo;

    public Politician(String id, String name, String currentParty, String DOB, String homeCounty, ImageView photo) throws FileNotFoundException {
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

    public String getPhotoURL() {
        return photoName;
    }

    public ImageView getPolImage(){return photo; }

    @Override
    public String toString() {
        return "Politician{" +
                "id='" + id + '\'' +
                ", currentParty='" + currentParty + '\'' +
                ", name='" + name + '\'' +
                ", DOB='" + DOB + '\'' +
                ", homeCounty='" + homeCounty + '\'' +
                ", photoName='" + photoName + '\'' +
                '}';
    }
}
