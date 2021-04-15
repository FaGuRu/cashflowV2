package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sample.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Cashflow_Controller implements Initializable {

    @FXML
    private Text inciales_text;

    @FXML
    private Text nombre_text;

    @FXML
    private Button button_categorias;

    @FXML
    private Button button_reports;

    @FXML
    private TableView<?> content_table;

    @FXML
    private TableColumn<?, ?> column_fecha;

    @FXML
    private TableColumn<?, ?> column_descripcion;

    @FXML
    private TableColumn<?, ?> column_categoria;

    @FXML
    private TableColumn<?, ?> column_subcategoria;

    @FXML
    private CheckBox check_entrada;

    @FXML
    private CheckBox check_salida;

    @FXML
    private ComboBox<?> menu_categorias;

    @FXML
    private TextField input_descripcion;

    @FXML
    private TextField input_cantidad;

    @FXML
    private Button button_guardar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            button_categorias.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/tag_icon.png"))));
            button_reports.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/reports_icon.png"))));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        button_categorias.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.setFXML("category", "CashFlow - Categorías");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
