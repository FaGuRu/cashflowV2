package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import persistencia.Category;
import sample.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class create_category_controller implements Initializable {

    @FXML
    private TextField create_input_nombre;

    @FXML
    private TextField create_input_subcategoria;

    @FXML
    private ComboBox<String> create_input_clasificacion;

    @FXML
    private Button button_guardar;

    @FXML
    void guarddar_OnMouseClicked(MouseEvent event) {
        String nombre = create_input_nombre.getText();
        String subcategoria = create_input_subcategoria.getText();
        String clasificacion = create_input_clasificacion.getValue();
        Category category = new Category(nombre, subcategoria, clasificacion);
        System.out.println(category.toString());
        Main.getPopUp().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_input_clasificacion.setItems(FXCollections.observableList(new ArrayList<String>(Arrays.asList("GAO","Ingreso","Costo-Venta"))));
    }
}
