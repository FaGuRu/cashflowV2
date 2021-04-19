package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import persistencia.CashFlow;
import persistencia.CashFlowDAO;
import persistencia.Category;
import persistencia.CategoryDAO;
import sample.Main;

import javax.sound.midi.SysexMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class Cashflow_Controller implements Initializable {
    private String name;
    private String last_name;
    private String type;
    private CashFlowDAO cashFlowDAO = new CashFlowDAO("hibernatePostgre.cfg.xml");
    private CategoryDAO categoryDAO = new CategoryDAO("hibernatePostgre.cfg.xml");
    @FXML
    private Text inciales_text;

    @FXML
    private Text nombre_completo_text;

    @FXML
    private Button categorias_button;

    @FXML
    private Button reportes_button;

    @FXML
    private Button informes_button;

    @FXML
    private TableView<CashFlow> content_table;

    @FXML
    private TableColumn<CashFlow, Date> column_fecha;

    @FXML
    private TableColumn<CashFlow, String> column_descripcion;

    @FXML
    private TableColumn<CashFlow, String> column_categoria;

    @FXML
    private TableColumn<CashFlow, String> column_subcategoria;

    @FXML
    private CheckBox check_box_entrada;

    @FXML
    private CheckBox check_box_salida;

    @FXML
    private ComboBox<Category> menu_categorias;

    @FXML
    private TextField input_descripcion;

    @FXML
    private TextField input_cantidad;

    @FXML
    private Button button_guardar;
    @FXML
    private Button cashflow_button;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            categorias_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/tag_icon.png"))));
            reportes_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/reports_icon.png"))));
            cashflow_button.setGraphic(new ImageView(new Image( new FileInputStream("src/assets/icons/cashflow_icon.png"))));
            informes_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/report_icon.png"))));
            List<Category> lista_categorias = categoryDAO.getCategory();
            ObservableList<Category> lista_observable = FXCollections.observableList(lista_categorias);
            menu_categorias.setItems(lista_observable);
            reportes_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        Main.setFXML("Record", "CashFlow - Indicadores de dinero");
                        FXMLLoader loader = Main.getLoader();
                        Record_controller controller = loader.getController();
                        controller.setUserLogged(name, last_name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            check_box_entrada.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    check_box_salida.setSelected(false);
                    type = check_box_entrada.getText();
                }
            });
            check_box_salida.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    check_box_entrada.setSelected(false);
                    type= check_box_salida.getText();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

        informes_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.setFXML("Report", "CashFlow - Informes");
                    FXMLLoader loader = Main.getLoader();
                    Controller_Report controller = loader.getController();
                    controller.setUserLogged(name, last_name);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        //Textfield de solo número enteros
        input_cantidad.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    input_cantidad.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        actualizarTabla();
    }


    public void setUserLogged(String name, String last_name){
        this.name = name;
        this.last_name = last_name;
        inciales_text.setText(String.valueOf(this.name.charAt(0)) + String.valueOf(this.last_name.charAt(0)));
        nombre_completo_text.setText(this.name + " " + this.last_name);
    }

    @FXML
    void guardar_OnMouseClicked(MouseEvent event) {
;
        Date date = Date.valueOf(LocalDate.now());
        Category category = menu_categorias.getValue();
        String concept = input_descripcion.getText();
        int cantidad = Integer.valueOf(input_cantidad.getText());
        Calendar calendario=Calendar.getInstance();
        calendario.setTime(date);
        int week_num= calendario.WEEK_OF_MONTH;
        CashFlow cashflow_to_save = new CashFlow(type, concept, cantidad, date, category,week_num);
        cashflow_to_save.toString();
        cashFlowDAO.addCashFlow(cashflow_to_save);
        actualizarTabla();

    }

    public void actualizarTabla(){
        ObservableList<CashFlow> lista = FXCollections.observableList(cashFlowDAO.getCashFlow());
        content_table.getColumns().clear();
        column_fecha.setCellValueFactory(new PropertyValueFactory<>("date"));
        column_descripcion.setCellValueFactory(new PropertyValueFactory<>("concept"));
        column_categoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
        column_subcategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getSubcategory()));

        content_table.getColumns().addAll(column_fecha,column_descripcion,column_categoria,column_subcategoria);
        content_table.setItems(lista);
    }
}
