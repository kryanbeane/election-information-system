package electionSystem;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Controller {

    /**
     * Adds Politician node to list of Politicians
     */
    @FXML
    TextField textCurrentParty, textPoliticianName, textDateOfBirth, textPoliticianParty, textHomeCounty, textPhotoURL;
    TableColumn textCurrentPartyColumn, textPNameColumn, textPDOBColumn, textPPolPartyColumn, textPHomeCountyColumn, textPPhotoURLColumn;
    public void addPolitician() {
        String currentParty = textCurrentParty.getText();
        String name = textPoliticianName.getText();
        String DOB = textDateOfBirth.getText();
        String politicalParty = textPoliticianParty.getText();
        String homeCounty = textHomeCounty.getText();
        String photoURL = textPhotoURL.getText();
        // Need to figure out Image URL

        Main.politicianList.addNode(new Politician(currentParty, name, DOB, politicalParty, homeCounty, photoURL));
        System.out.println(Main.politicianList.printList());

        textCurrentPartyColumn.setText(String.valueOf(textCurrentParty));
        textPNameColumn.setText(String.valueOf(textPoliticianName));
        textPDOBColumn.setText(String.valueOf(textDateOfBirth));
        textPPolPartyColumn.setText(String.valueOf(textPoliticianName));
        textPHomeCountyColumn.setText(String.valueOf(textPHomeCountyColumn));
        textPPhotoURLColumn.setText(String.valueOf(textPhotoURL));
        // Need to figure out Image URL
    }
}
