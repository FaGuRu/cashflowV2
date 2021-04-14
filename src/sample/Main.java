package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {

    private static Scene scene;

    private static Stage primaryStage;

    private static Stage popUp;

    private static Parent root;

    private static FXMLLoader loader = new FXMLLoader();

    @Override
    public void start(Stage _primaryStage) throws Exception{
        root = loader.load(getClass().getResource("../views/login.fxml"));
        primaryStage = _primaryStage;
        primaryStage.setTitle("CashFlow");
        scene = new Scene((root));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(new Image(new FileInputStream("src/assets/images/logo_sin_texto.png")));
        primaryStage.show();


    }

    public static void setFXML(String fxml, String title) throws IOException {
        scene.setRoot((loadFXML(fxml)));
        primaryStage.setTitle(title);

    }

    public static FXMLLoader getLoader(){
        return loader;
    }

    public static Parent loadFXML(String fxml) throws IOException {
        loader = new FXMLLoader(Main.class.getResource("../views/" + fxml + ".fxml"));
        return loader.load();

    }


    //load popUp windows

    public static void popUp(String fxml, String title) {

        try {

            Parent node = loadFXML(fxml);
            popUp = new Stage();
            Scene scene = new Scene(node);
            popUp.setScene(scene);
            popUp.setTitle(title);
            popUp.initOwner(primaryStage);
            popUp.initModality(Modality.WINDOW_MODAL);
            popUp.centerOnScreen();
            popUp.setResizable(false);
            popUp.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //used to close the popUp using Main.getPopUp().close();

    public static Stage getPopUp(){
        return popUp;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
