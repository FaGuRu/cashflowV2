package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import persistencia.User;
import persistencia.UserDAO;

import sample.Main;

import java.io.IOException;


public class login_controller {

    UserDAO dao;
    User user;

    @FXML
    private TextField entrada_nombre_de_usuario;

    @FXML
    private PasswordField entrada_contrasena;

    @FXML
    private Button boton_ingresar;


    @FXML
    void ingresar_on_clicked(MouseEvent event) throws IOException {

        if(auth()){

            System.out.println("Usuario existente");
            if(user.getRole().equals("admin")){
                //Momentanea hasta que se haga la vista principal chida
                Main.setFXML("Cashflow", "CashFlow - Categorías");
                //Obtiene el controller de la vista de categorias para darle el usuario que se loggeo jeje
                FXMLLoader loader = Main.getLoader();
                Cashflow_Controller controller = loader.getController();
                controller.setUserLogged(user.getName(), user.getLast_name());


            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("La vista del usuario no administrador aún no se hace");
                alert.show();
            }


            System.out.println("SI FUNCIONA USUARIO Y PASSWORD CORRECTOS ");

        }else{
            System.out.println("Usuario o contraseña inválida");
            Alert error_usuario = new Alert(Alert.AlertType.ERROR);
            error_usuario.setHeaderText("Error al autenticar");
            error_usuario.setContentText("Usuario o contraseña incorrectos.");
            error_usuario.setTitle("Error");
            error_usuario.show();
        }
    }

    public boolean auth(){
        dao = new UserDAO("hibernate.cfg.xml");
        String username = entrada_nombre_de_usuario.getText();
        String password = entrada_contrasena.getText();
        user = dao.getUser(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }

        return false;
    }
}
