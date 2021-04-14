package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.hibernate.SessionFactory;
import org.jboss.logging.Property;
import persistencia.Category;
import persistencia.CategoryDAO;
import persistencia.User;
import sample.Main;

import javax.persistence.Table;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Category_controller implements Initializable {

    CategoryDAO categoryDAO = new CategoryDAO("hibernatePostgre.cfg.xml");

    private Image add_img = new Image(new FileInputStream("src/assets/icons/add_category_icon.png"));
    private Category category_to_update;
    private Category category_to_delete;

    @FXML
    private Button subir_button;

    @FXML
    private TableView<Category> content_table;

    @FXML
    private TableColumn<Category, ?> column_name;

    @FXML
    private TableColumn<Category, ?> column_subcategoria;

    @FXML
    private TableColumn<Category, ?> column_clasificacion;



    @FXML
    private Text iniciales_text;

    @FXML
    private Text nombre_completo_text;
    @FXML
    private Button reportes_button;

    @FXML
    private Button agregar_button;


    public Category_controller() throws FileNotFoundException {
    }

    @FXML
    void agregar_onMouseClicked(MouseEvent event) {

    }

    @FXML
    void reportes_onMouseClicked(MouseEvent event) {

    }
    @FXML
    private TextField input_nombre;

    @FXML
    private TextField input_subcategoria;

    @FXML
    private ComboBox<String> input_clasificacion;

    @FXML
    private Button actualizar_button;
    @FXML
    private Line line1;

    @FXML
    private Line line2;

    @FXML
    private Line line3;

    /*
        Actualiza la tabla y regresa los botones a su condición inicial.
     */
    @FXML
    void subir_OnMouseClicked(MouseEvent event) {
        String new_name = input_nombre.getText();
        String new_subcategory = input_subcategoria.getText();
        String new_classification = input_clasificacion.getValue();
        category_to_update.setName(new_name);
        category_to_update.setClassification(new_classification);
        category_to_update.setSubcategory(new_subcategory);
        categoryDAO.updateCategory(category_to_update);
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        subir_button.setVisible(false);
        input_nombre.setVisible(false);
        input_subcategoria.setVisible(false);
        input_clasificacion.setVisible(false);
        actualizarTabla();
        System.out.println(category_to_update.toString());

    }
    @FXML
    void update_onMouseClicked(MouseEvent event) {
        actualizarTabla();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        agregar_button.setGraphic(new ImageView(add_img));
        agregar_button.getGraphic().autosize();
        try {
            actualizar_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets//icons/reload_icon.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        agregar_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.popUp("create_category_pop_up", "CashFlow - Create Category");
            }
        });
        try {
            subir_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/actualizar_icon.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input_clasificacion.setItems(FXCollections.observableList(new ArrayList<String>(Arrays.asList("GAO","Ingreso","Costo-Venta"))));
        actualizarTabla();
    }

    public  void actualizarTabla(){
        ObservableList listaCategorioas = FXCollections.observableList(categoryDAO.getCategory());
        content_table.getColumns().clear();
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_subcategoria.setCellValueFactory(new PropertyValueFactory<>("subcategory"));
        column_clasificacion.setCellValueFactory(new PropertyValueFactory<>("classification"));
        content_table.getColumns().addAll(column_name,column_subcategoria,column_clasificacion);
        content_table.setItems(listaCategorioas);
        addEditButton();
        addDeleteButton();
    }
    /*
        Esta función se encarga de agregar a la tabla una columna más; dicha columna contiene un botón con un
        manejador de eventos el cual seleccionar el objeto de esa misma fila para ser eliminado.
     */
    private void addDeleteButton(){
        TableColumn<Category, Void> column_delete_button = new TableColumn("");
        Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = new Callback<TableColumn<Category, Void>, TableCell<Category, Void>>() {
            @Override
            public TableCell<Category, Void> call(TableColumn<Category, Void> categoryVoidTableColumn) {
                final TableCell<Category, Void> cell = new TableCell<Category, Void>(){
                    private final Button btn = new Button("");
                    {
                        btn.setOnAction((ActionEvent event) ->{
                            Category category = getTableView().getItems().get(getIndex());
                            category_to_delete = category;
                        });
                        try {
                            btn.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/delete_icon.png"))));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        btn.setStyle("-fx-background-color: transparent");
                        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                categoryDAO.deleteCategory(category_to_delete);
                                actualizarTabla();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            setGraphic(null);
                        }else{
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        column_delete_button.setEditable(false);
        column_delete_button.setReorderable(false);
        column_delete_button.setResizable(false);
        column_delete_button.setCellFactory(cellFactory);
        content_table.getColumns().add(column_delete_button);
    }

    /*
        Esta función se encarga de agregar una columna con un botón y un manejador de eventos para que cuando se de clic,
        el item seleccionado sea editado llamando a la funcióna actualizar()
     */
    private void addEditButton(){
        TableColumn<Category, Void> column_edit_button = new TableColumn("");
        Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = new Callback<TableColumn<Category, Void>, TableCell<Category, Void>>() {
            @Override
            public TableCell<Category, Void> call(TableColumn<Category, Void> categoryVoidTableColumn) {
                final TableCell<Category, Void> cell = new TableCell<Category, Void>(){
                    private final Button btn = new Button("");
                    {
                        btn.setOnAction((ActionEvent event) ->{
                            category_to_update = getTableView().getItems().get(getIndex());
                            //System.out.println("SELECCIONADO: " + category_to_update.toString());
                            //QUE SE HACE CUANDO PRESIONAS EL BOTÓN
                        });
                        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                input_nombre.setVisible(true);
                                input_clasificacion.setVisible(true);
                                input_subcategoria.setVisible(true);
                                subir_button.setVisible(true);
                                input_nombre.setText(category_to_update.getName());
                                input_subcategoria.setText(category_to_update.getSubcategory());
                                line1.setVisible(true);
                                line2.setVisible(true);
                                line3.setVisible(true);
                            }
                        });
                        try {
                            btn.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/edit_icon.png"))));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        btn.setStyle("-fx-background-color: transparent");
                    }
                    @Override
                    public void updateItem(Void item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            setGraphic(null);
                        }else{
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        column_edit_button.setResizable(false);
        column_edit_button.setEditable(false);
        column_edit_button.setReorderable(false);
        column_edit_button.setCellFactory(cellFactory);
        content_table.getColumns().add(column_edit_button);
    }
}
