package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class login_controller {

    @FXML
    private TextField entrada_nombre_de_usuario;

    @FXML
    private PasswordField entrada_contraseña;

    @FXML
    private Button boton_ingresar;

    @FXML
    void ingresar_on_clicked(MouseEvent event) {

    }

    public boolean auth(){



        return false;
    }
}
