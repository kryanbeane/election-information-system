package electionSystem;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

public class Main extends Application {

    /**
     *
     */
    static List<Politician> politicianList = new List<>();
    static List<Election> electionsList = new List<>();
    static List<Candidate> candidatesList = new List<>();
    static List<Candidate> sortedCandidatesList = new List<>();

    /**
     *
     */
    static int polCandHashTableSize = 800;
    static int electionHashTableSize = 100;
    static HashTable<Politician> politicianHashTable = new HashTable<>(polCandHashTableSize);
    static HashTable<Candidate> candidateHashTable = new HashTable<>(polCandHashTableSize);
    static HashTable<Election> electionHashTable = new HashTable<>(electionHashTableSize);



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("electionSystem.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
