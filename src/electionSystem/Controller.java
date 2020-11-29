package electionSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    /**
     * Adds Politician node to list of Politicians
     */
    ObservableList<Politician> myPoliticianObsList = FXCollections.observableArrayList();
    @FXML
    TextField textCurrentParty, textPoliticianName, textDateOfBirth, textHomeCounty, textImageURL;
    @FXML
    TableColumn<Politician, String> currentPartyColumn, pNameColumn, pDOBColumn, pHomeCountyColumn, pPhotoURLColumn;
    @FXML
    TableView<Politician> politicianTable;


    public void addPolitician() {
        String currentParty = textCurrentParty.getText();
        String name = textPoliticianName.getText();
        String DOB = textDateOfBirth.getText();
        String homeCounty = textHomeCounty.getText();
        String photoURL = textImageURL.getText();
        // Need to figure out Image URL

        Main.politicianList.addNode(new Politician(currentParty, name, DOB, homeCounty, photoURL));
        updatePoliticianTable();
        System.out.println(Main.politicianList.printList());
        // Need to figure out Image URL
    }

    public void updatePoliticianTable(){
        myPoliticianObsList.clear();

        pDOBColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("DOB"));
        pNameColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("name"));
        pHomeCountyColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("homeCounty"));
        pPhotoURLColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("photoURL"));
        currentPartyColumn.setCellValueFactory(new PropertyValueFactory<Politician,String >("currentParty"));

        for(Politician pol: Main.politicianList){
            myPoliticianObsList.add(pol);
        }
        politicianTable.setItems(myPoliticianObsList.sorted());
    }
}
