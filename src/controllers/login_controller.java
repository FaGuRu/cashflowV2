package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import persistencia.User;
import persistencia.UserDAO;

import java.nio.file.FileSystemNotFoundException;


public class login_controller {

    @FXML
    private TextField entrada_nombre_de_usuario;

    @FXML
    private PasswordField entrada_contraseña;

    @FXML
    private Button boton_ingresar;

    @FXML
    void ingresar_on_clicked(MouseEvent event) {
        if(auth()){
            System.out.println("SI FUNCIONA USUARIO Y PASSWORD CORRECTOS ");
        }else{
            System.out.println("SI FUNCIONAR PERO ESTA MAL LA CONTRASEÑA , EL USUARIO O NO FUNCIONA LA CONEXIÓN JEEJE XDDXDDXXD");
        }
    }

    public boolean auth(){
        UserDAO dao = new UserDAO("hibernate.cfg.xml");
        String username = entrada_nombre_de_usuario.getText();
        String password = entrada_contraseña.getText();
        User user = dao.getUser(username);
        if (user != null) {
            if(user.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }
}
