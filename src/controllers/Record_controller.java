package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import persistencia.Record;
import persistencia.RecordDAO;
import sample.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Record_controller implements Initializable {
    String name;
    String last_name;

    @FXML
    private Text inciales_text;

    @FXML
    private Text nombre_completo_text;

    @FXML
    private Button categorias_button;

    @FXML
    private Button reportes_button;

    @FXML
    private Button cashflow_button;

    @FXML
    private Button guardar_por_cobrar_button;

    @FXML
    private Button guardar_por_pagar_button;

    @FXML
    private Button guardar_baco;

    @FXML
    private TextField n_semana_por_pagar;

    @FXML
    private TextField razon_social_por_pagar;

    @FXML
    private TextField monto_por_pagar;

    @FXML
    private TextField n_semana_bancos;

    @FXML
    private TextField razon_social_bancos;

    @FXML
    private TextField monto_bancos;

    @FXML
    private TextField n_semana_por_cobrar;

    @FXML
    private TextField razon_social_por_cobrar;

    @FXML
    private TextField monto_por_cobrar;
    //String type, int week_num, java.sql.Date month, String company_name, float amount
    @FXML
    void bancos_OnMouseClicked(MouseEvent event) {
        RecordDAO dao = new RecordDAO("hibernateSQL.cfg.xml");
        int num_semana = Integer.valueOf(n_semana_bancos.getText());
        String razon_social = razon_social_bancos.getText();
        int cantidad = Integer.valueOf(monto_bancos.getText());
        Date date = Date.valueOf(String.valueOf(LocalDate.now()));
        Record record = new Record("Bancos", num_semana,date,razon_social,cantidad);
        dao.addRecord(record);
    }

    @FXML
    void por_cobrar_OnMouseClicked(MouseEvent event) {
        RecordDAO dao = new RecordDAO("hibernateSQL.cfg.xml");
        int num_semana = Integer.valueOf(n_semana_por_cobrar.getText());
        String razon_social = razon_social_por_cobrar.getText();
        int cantidad = Integer.valueOf(monto_por_cobrar.getText());
        Date date = Date.valueOf(String.valueOf(LocalDate.now()));
        Record record = new Record("Por cobrar", num_semana,date,razon_social,cantidad);
        dao.addRecord(record);
    }

    @FXML
    void por_pagar_OnMouseClicked(MouseEvent event) {
        RecordDAO dao = new RecordDAO("hibernateSQL.cfg.xml");
        int num_semana = Integer.valueOf(n_semana_por_pagar.getText());
        String razon_social = razon_social_por_pagar.getText();
        int cantidad = Integer.valueOf(monto_por_pagar.getText());
        Date date = Date.valueOf(String.valueOf(LocalDate.now()));
        Record record = new Record("Por pagar", num_semana,date,razon_social,cantidad);
        dao.addRecord(record);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            reportes_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/reports_icon.png"))));
            cashflow_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/cashflow_icon.png"))));
            categorias_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/tag_icon.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        cashflow_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.setFXML("cashflow","CashFlow - Flujo de Efectivo");
                    FXMLLoader loader = Main.getLoader();
                    Cashflow_Controller controller = loader.getController();
                    controller.setUserLogged(name, last_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        categorias_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.setFXML("category", "CashFlow - Categorías");
                    FXMLLoader loader = Main.getLoader();
                    Category_controller controller = loader.getController();
                    controller.setUserLogged(name, last_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        n_semana_bancos.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    n_semana_bancos.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        n_semana_por_cobrar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    n_semana_por_cobrar.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        n_semana_por_pagar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    n_semana_por_pagar.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }


    public void setUserLogged(String name, String last_name){
        this.name = name;
        this.last_name = last_name;
        inciales_text.setText(String.valueOf(this.name.charAt(0)) + this.last_name.charAt(0));
        nombre_completo_text.setText(this.name + " " + this.last_name);
    }
}

