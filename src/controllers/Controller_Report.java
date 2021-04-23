package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.hibernate.type.IntegerType;
import org.hibernate.type.descriptor.java.BinaryStreamImpl;
import persistencia.*;
import sample.Main;

import java.awt.print.Printable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller_Report implements Initializable {

    private String name;
    private String last_name;
    private String role;
    private int mes_seleccionado;
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
    private Button informes_button;

    @FXML
    private TableView<PrintableRecord> tabla_por_cobrar;

    @FXML
    private TableColumn<PrintableRecord, ?> razon_social_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana1_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana2_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana3_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana4_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana5_column;

    @FXML
    private TableColumn<PrintableRecord, ?> final_column;

    @FXML
    private Text total_por_cobrar_semana_1;

    @FXML
    private Text total_por_cobrar_semana_2;

    @FXML
    private Text total_por_cobrar_semana_3;

    @FXML
    private Text total_por_cobrar_semana_4;

    @FXML
    private Text total_por_cobrar_semana_5;

    @FXML
    private Text total_por_cobrar_final;

    @FXML
    private MenuButton meses_menu;

    @FXML
    private MenuItem enero_button;

    @FXML
    private MenuItem febrero_button;

    @FXML
    private MenuItem marzo_button;

    @FXML
    private MenuItem abril_button;

    @FXML
    private MenuItem mayo_button;

    @FXML
    private MenuItem junio_button;

    @FXML
    private MenuItem julio_button;

    @FXML
    private MenuItem agosto_button;

    @FXML
    private MenuItem septiembre_button;

    @FXML
    private MenuItem octubre_button;

    @FXML
    private MenuItem noviembre_button;

    @FXML
    private MenuItem diciembre_button;

    @FXML
    private TableView<PrintableRecord> tabla_por_pagar;

    @FXML
    private TableColumn<PrintableRecord, ?> razon_social_por_pagar_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana1_por_pagar_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana2_por_pagar_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana3_por_pagar_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana4_por_pagar_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana5_por_pagar_column;

    @FXML
    private TableColumn<PrintableRecord, ?> final_por_pagar_column;

    @FXML
    private Text total_por_pagar_semana_1;

    @FXML
    private Text total_por_pagar_semana_2;

    @FXML
    private Text total_por_pagar_semana_3;

    @FXML
    private Text total_por_pagar_semana_4;

    @FXML
    private Text total_por_pagar_semana_5;

    @FXML
    private Text total_por_pagar_final;

    @FXML
    private Text total_bancos_numero1;

    @FXML
    private Text total_bancos_semana_2;

    @FXML
    private Text total_bancos_semana_3;

    @FXML
    private Text total_bancos_semana_4;

    @FXML
    private Text total_bancos_semana_5;

    @FXML
    private Text total_bancos_final;

    @FXML
    private TableView<PrintableRecord> bancos_table;

    @FXML
    private TableColumn<PrintableRecord, ?> razon_social_bancos_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana1_bancos_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana2_bancos_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana3_bancos_column;

    @FXML
    private TableColumn<PrintableRecord, ?> semana4_bancos_column;

    @FXML
    private TableColumn<PrintableRecord, ?> column5_bancos_column;

    @FXML
    private TableColumn<PrintableRecord, ?> final_bancos_column;

    @FXML
    private TableView<PrintableFlow> cashflow_salida_table;

    @FXML
    private TableColumn<PrintableFlow, ?> descripcion_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana1_cashflow_salida_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana2_cashflow_salida_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana3_cashflow_salida_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana4_cashflow_salida_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana5_cashflow_salida_column;

    @FXML
    private TableColumn<PrintableFlow, ?> total_cashflow_salida_column;

    @FXML
    private TableView<PrintableFlow> cashflow_entrada_table;

    @FXML
    private TableColumn<PrintableFlow, ?> descripcion_entrada_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana1_cashflow_entrada_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana2_cashflow_entrada_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana3_cashflow_entrada_column;

    @FXML
    private TableColumn<PrintableFlow, ?> semana4_cashflow_entrada_column;

    @FXML
    private TableColumn<PrintableFlow,?> semana5_cashflow_entrada_column;

    @FXML
    private TableColumn<PrintableFlow, ?> final_cashflow_entrada_column;

    @FXML
    private Text text_diff_semana1;

    @FXML
    private Text text_diff_semana2;

    @FXML
    private Text text_diff_semana3;

    @FXML
    private Text text_diff_semana4;

    @FXML
    private Text text_diff_semana5;

    @FXML
    private Text text_diff_total;

    @FXML
    private Text text_marge_rentabilidad_semana1;

    @FXML
    private Text text_marge_rentabilidad_semana2;

    @FXML
    private Text text_marge_rentabilidad_semana3;

    @FXML
    private Text text_marge_rentabilidad_semana4;

    @FXML
    private Text text_marge_rentabilidad_semana5;

    @FXML
    private Text text_marge_rentabilidad_total;


    @FXML
    private Text flow_total_semana1_entrada;

    @FXML
    private Text flow_total_semana2_entrada;

    @FXML
    private Text flow_total_semana3_entrada;

    @FXML
    private Text flow_total_semana4_entrada;

    @FXML
    private Text flow_total_semana5_entrada;

    @FXML
    private Text flow_total_entrada;

    @FXML
    private Text flow_total_semana1_salida;

    @FXML
    private Text flow_total_semana2_salida;

    @FXML
    private Text flow_total_semana3_salida;

    @FXML
    private Text flow_total_semana4_salida;

    @FXML
    private Text flow_total_semana5_salida;

    @FXML
    private Text flow_total_salida;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            reportes_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/reports_icon.png"))));
            cashflow_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/cashflow_icon.png"))));
            categorias_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/tag_icon.png"))));
            informes_button.setGraphic(new ImageView(new Image(new FileInputStream("src/assets/icons/report_icon.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        cashflow_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.setFXML("Cashflow", "CashFlow - Flujo de dinero");
                    FXMLLoader loader = Main.getLoader();
                    Cashflow_Controller controller = loader.getController();
                    controller.setUserLogged(name, last_name, role);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        categorias_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.setFXML("Category", "CashFlow - Categorías");
                    FXMLLoader loader = Main.getLoader();
                    Category_controller controller = loader.getController();
                    controller.setUserLogged(name, last_name, role);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        reportes_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.setFXML("Record", "CashFlow - Indicadores de dinero");
                    FXMLLoader loader = Main.getLoader();
                    Record_controller controller = loader.getController();
                    controller.setUserLogged(name, last_name, role);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //asignación de mes seleccionado en formato entero
        enero_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 1;
                actualizarTabla();
            }
        });

        febrero_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 2;
                actualizarTabla();
            }
        });

        marzo_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 3;
                actualizarTabla();
            }
        });

        abril_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 4;
                actualizarTabla();
            }
        });

        mayo_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 5;
                actualizarTabla();
            }
        });

        junio_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 6;
                actualizarTabla();
            }
        });

        julio_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 7;
                actualizarTabla();
            }
        });

        agosto_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 8;
                actualizarTabla();
            }
        });

        septiembre_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 9;
                actualizarTabla();
            }
        });

        octubre_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 10;
                actualizarTabla();
            }
        });

        noviembre_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 11;
                actualizarTabla();
            }
        });

        diciembre_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mes_seleccionado = 12;
                actualizarTabla();
            }
        });

    }

    public void setUserLogged(String name, String last_name, String role){
        this.name = name;
        this.last_name = last_name;
        this.role = role;
        inciales_text.setText(String.valueOf(this.name.charAt(0)) + String.valueOf(this.last_name.charAt(0)));
        nombre_completo_text.setText(String.valueOf(name + " " + last_name));
    }

    public void actualizarTabla(){
        int suma_sem1_por_cobrar = 0;
        int suma_sem2_por_cobrar = 0;
        int suma_sem3_por_cobrar = 0;
        int suma_sem4_por_cobrar = 0;
        int suma_sem5_por_cobrar = 0;
        int suma_total_final_por_cobrar = 0;

        int suma_sem1_por_pagar = 0;
        int suma_sem2_por_pagar = 0;
        int suma_sem3_por_pagar = 0;
        int suma_sem4_por_pagar = 0;
        int suma_sem5_por_pagar = 0;
        int suma_total_final_porpagar = 0;

        int suma_sem1_bancos = 0;
        int suma_sem2_bancos = 0;
        int suma_sem3_bancos = 0;
        int suma_sem4_bancos = 0;
        int suma_sem5_bancos = 0;
        int suma_total_final_bancos = 0;
        //TABLA POR COBRAR
        RecordDAO recordDAO = new RecordDAO("hibernateSQL.cfg.xml");
        CashFlowDAO cashFlowDAO = new CashFlowDAO("hibernatePostgre.cfg.xml");
        tabla_por_cobrar.getColumns().clear();
        bancos_table.getColumns().clear();
        tabla_por_pagar.getColumns().clear();
        List<PrintableRecord> lista_por_cobrar = recordDAO.getMonthRecord(mes_seleccionado, "Por Cobrar");
        List<PrintableRecord> lista_por_pagar = recordDAO.getMonthRecord(mes_seleccionado, "Por Pagar");
        List<PrintableRecord> lista_bancos = recordDAO.getMonthRecord(mes_seleccionado, "Bancos");

        razon_social_column.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        semana1_column.setCellValueFactory(new PropertyValueFactory<>("week1"));
        semana2_column.setCellValueFactory(new PropertyValueFactory<>("week2"));
        semana3_column.setCellValueFactory(new PropertyValueFactory<>("week3"));
        semana4_column.setCellValueFactory(new PropertyValueFactory<>("week4"));
        semana5_column.setCellValueFactory(new PropertyValueFactory<>("week5"));
        final_column.setCellValueFactory(new PropertyValueFactory<>("suma"));
        tabla_por_cobrar.getColumns().addAll(razon_social_column, semana1_column,semana2_column, semana3_column,semana4_column,semana5_column, final_column);
        //**************************************************************************************
        //TABLA BANCOS
        razon_social_bancos_column.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        semana1_bancos_column.setCellValueFactory(new PropertyValueFactory<>("week1"));
        semana2_bancos_column.setCellValueFactory(new PropertyValueFactory<>("week2"));
        semana3_bancos_column.setCellValueFactory(new PropertyValueFactory<>("week3"));
        semana4_bancos_column.setCellValueFactory(new PropertyValueFactory<>("week4"));
        column5_bancos_column.setCellValueFactory(new PropertyValueFactory<>("week5"));
        final_bancos_column.setCellValueFactory(new PropertyValueFactory<>("suma"));
        bancos_table.getColumns().addAll(razon_social_bancos_column, semana1_bancos_column, semana2_bancos_column, semana3_bancos_column,semana4_bancos_column,column5_bancos_column, final_bancos_column);
        //***************************************************************************************
        //TABLA POR PAGAR
        razon_social_por_pagar_column.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        semana1_por_pagar_column.setCellValueFactory(new PropertyValueFactory<>("week1"));
        semana2_por_pagar_column.setCellValueFactory(new PropertyValueFactory<>("week2"));
        semana3_por_pagar_column.setCellValueFactory(new PropertyValueFactory<>("week3"));
        semana4_por_pagar_column.setCellValueFactory(new PropertyValueFactory<>("week4"));
        semana5_por_pagar_column.setCellValueFactory(new PropertyValueFactory<>("week5"));
        final_por_pagar_column.setCellValueFactory(new PropertyValueFactory<>("suma"));
        tabla_por_pagar.getColumns().addAll(razon_social_por_pagar_column, semana1_por_pagar_column, semana2_por_pagar_column, semana3_por_pagar_column,semana4_por_pagar_column,semana5_por_pagar_column, final_por_pagar_column);

        tabla_por_cobrar.setItems(FXCollections.observableList(lista_por_cobrar));
        tabla_por_pagar.setItems(FXCollections.observableList(lista_por_pagar));
        bancos_table.setItems(FXCollections.observableList(lista_bancos));

        for (PrintableRecord record:lista_por_cobrar) {
            suma_sem1_por_cobrar = Integer.valueOf((int) (suma_sem1_por_cobrar + record.getWeek1()));
            suma_sem2_por_cobrar = Integer.valueOf((int) (suma_sem2_por_cobrar + record.getWeek2()));
            suma_sem3_por_cobrar = Integer.valueOf((int) (suma_sem3_por_cobrar + record.getWeek3()));
            suma_sem4_por_cobrar = Integer.valueOf((int) (suma_sem4_por_cobrar + record.getWeek4()));
            suma_sem5_por_cobrar = Integer.valueOf((int) (suma_sem5_por_cobrar + record.getWeek5()));
            suma_total_final_por_cobrar = Integer.valueOf((int) (suma_total_final_por_cobrar + record.getSuma()));
        }
        for (PrintableRecord record:lista_por_pagar) {
            suma_sem1_por_pagar = Integer.valueOf((int)(suma_sem1_por_pagar + record.getWeek1()));
            suma_sem2_por_pagar = Integer.valueOf((int)(suma_sem2_por_pagar + record.getWeek2()));
            suma_sem3_por_pagar = Integer.valueOf((int)(suma_sem3_por_pagar + record.getWeek3()));
            suma_sem4_por_pagar = Integer.valueOf((int)(suma_sem4_por_pagar + record.getWeek4()));
            suma_sem5_por_pagar = Integer.valueOf((int)(suma_sem5_por_pagar + record.getWeek5()));
            suma_total_final_porpagar = Integer.valueOf((int)(suma_total_final_porpagar+ record.getWeek1()));
        }
        for (PrintableRecord record:lista_bancos) {
            suma_sem1_bancos = Integer.valueOf((int)(suma_sem1_bancos + record.getWeek1()));
            suma_sem2_bancos = Integer.valueOf((int)(suma_sem2_bancos + record.getWeek2()));
            suma_sem3_bancos = Integer.valueOf((int)(suma_sem3_bancos + record.getWeek3()));
            suma_sem4_bancos = Integer.valueOf((int)(suma_sem4_bancos + record.getWeek4()));
            suma_sem5_bancos = Integer.valueOf((int)(suma_sem5_bancos + record.getWeek5()));
            suma_total_final_bancos = Integer.valueOf((int)(suma_total_final_bancos + record.getSuma()));
        }

        total_por_cobrar_final.setText(String.valueOf(suma_sem1_por_cobrar));
        total_por_cobrar_semana_1.setText(String.valueOf(suma_sem1_por_cobrar));
        total_por_cobrar_semana_2.setText(String.valueOf(suma_sem2_por_cobrar));
        total_por_cobrar_semana_3.setText(String.valueOf(suma_sem3_por_cobrar));
        total_por_cobrar_semana_4.setText(String.valueOf(suma_sem4_por_cobrar));
        total_por_cobrar_semana_5.setText(String.valueOf(suma_sem5_por_cobrar));
        total_por_cobrar_final.setText(String.valueOf(suma_total_final_por_cobrar));

        total_por_pagar_final.setText(String.valueOf(suma_sem1_por_pagar));
        total_por_pagar_semana_1.setText(String.valueOf(suma_sem1_por_pagar));
        total_por_pagar_semana_2.setText(String.valueOf(suma_sem2_por_pagar));
        total_por_pagar_semana_3.setText(String.valueOf(suma_sem3_por_pagar));
        total_por_pagar_semana_4.setText(String.valueOf(suma_sem4_por_pagar));
        total_por_pagar_semana_5.setText(String.valueOf(suma_sem5_por_pagar));
        total_por_pagar_final.setText(String.valueOf(suma_total_final_porpagar));

        total_bancos_final.setText(String.valueOf(suma_sem1_por_pagar));
        total_bancos_numero1.setText(String.valueOf(suma_sem1_por_pagar));
        total_bancos_semana_2.setText(String.valueOf(suma_sem2_por_pagar));
        total_bancos_semana_3.setText(String.valueOf(suma_sem3_por_pagar));
        total_bancos_semana_4.setText(String.valueOf(suma_sem4_por_pagar));
        total_bancos_semana_5.setText(String.valueOf(suma_sem5_por_pagar));
        total_bancos_final.setText(String.valueOf(suma_total_final_porpagar));

        //TABLAS DE CASHFLOW
        List<PrintableFlow> lista_salida = cashFlowDAO.getCashFlowSalida(mes_seleccionado);
        List<PrintableFlow> lista_entrada = cashFlowDAO.getCashFlowEntrada(mes_seleccionado);

        cashflow_salida_table.getColumns().clear();
        cashflow_entrada_table.getColumns().clear();

        descripcion_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        semana1_cashflow_salida_column.setCellValueFactory(new PropertyValueFactory<>("week1"));
        semana2_cashflow_salida_column.setCellValueFactory(new PropertyValueFactory<>("week2"));
        semana3_cashflow_salida_column.setCellValueFactory(new PropertyValueFactory<>("week3"));
        semana4_cashflow_salida_column.setCellValueFactory(new PropertyValueFactory<>("week4"));
        semana5_cashflow_salida_column.setCellValueFactory(new PropertyValueFactory<>("week5"));
        total_cashflow_salida_column.setCellValueFactory(new PropertyValueFactory<>("total"));

        descripcion_entrada_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        semana1_cashflow_entrada_column.setCellValueFactory(new PropertyValueFactory<>("week1"));
        semana2_cashflow_entrada_column.setCellValueFactory(new PropertyValueFactory<>("week2"));
        semana3_cashflow_entrada_column.setCellValueFactory(new PropertyValueFactory<>("week3"));
        semana4_cashflow_entrada_column.setCellValueFactory(new PropertyValueFactory<>("week4"));
        semana5_cashflow_entrada_column.setCellValueFactory(new PropertyValueFactory<>("week5"));
        final_cashflow_entrada_column.setCellValueFactory(new PropertyValueFactory<>("total"));

        cashflow_entrada_table.getColumns().addAll(descripcion_entrada_column,semana1_cashflow_entrada_column,semana2_cashflow_entrada_column,semana3_cashflow_entrada_column,semana4_cashflow_entrada_column,semana5_cashflow_entrada_column,final_cashflow_entrada_column);
        cashflow_salida_table.getColumns().addAll(descripcion_column,semana1_cashflow_salida_column,semana2_cashflow_salida_column,semana3_cashflow_salida_column,semana4_cashflow_salida_column,semana5_cashflow_salida_column,total_cashflow_salida_column);
        cashflow_salida_table.setItems(FXCollections.observableList(lista_salida));
        cashflow_entrada_table.setItems(FXCollections.observableList(lista_entrada));
        /*
        Calcular margen de rentabilidad (Margen porcentual)
                (UTILIDAD / INGRESOS TOTALES) * 100
         */
        float suma_sem1_entrada = 0;
        float suma_sem2_entrada = 0;
        float suma_sem3_entrada = 0;
        float suma_sem4_entrada = 0;
        float suma_sem5_entrada = 0;
        float suma_total_entrada = 0;

        float suma_sem1_salida = 0;
        float suma_sem2_salida = 0;
        float suma_sem3_salida = 0;
        float suma_sem4_salida = 0;
        float suma_sem5_salida = 0;
        float suma_total_salida = 0;


        for (PrintableFlow flow: lista_salida) {
            suma_sem1_salida = suma_sem1_salida + flow.getWeek1();
            suma_sem2_salida = suma_sem2_salida + flow.getWeek2();
            suma_sem3_salida = suma_sem3_salida + flow.getWeek3();
            suma_sem4_salida = suma_sem4_salida + flow.getWeek4();
            suma_sem5_salida = suma_sem5_salida + flow.getWeek5();
            suma_total_salida = suma_total_salida + flow.getTotal();
        }

        for (PrintableFlow flow: lista_entrada) {
            suma_sem1_entrada = suma_sem1_entrada + flow.getWeek1();
            suma_sem2_entrada = suma_sem2_entrada + flow.getWeek2();
            suma_sem3_entrada = suma_sem3_entrada + flow.getWeek3();
            suma_sem4_entrada = suma_sem4_entrada + flow.getWeek4();
            suma_sem5_entrada = suma_sem5_entrada + flow.getWeek5();
            suma_total_entrada = suma_total_entrada + flow.getTotal();
        }


        float diff_semana1 = suma_sem1_entrada - suma_sem1_salida;
        float diff_semana2 = suma_sem2_entrada - suma_sem2_salida;
        float diff_semana3 = suma_sem3_entrada - suma_sem3_salida;
        float diff_semana4 = suma_sem4_entrada - suma_sem4_salida;
        float diff_semana5 = suma_sem5_entrada - suma_sem5_salida;
        float diff_total = suma_total_entrada - suma_total_salida;

        int margen_rentabilidad_sem1 = (int) ((diff_semana1 / suma_sem1_entrada) * 100);
        int margen_rentabilidad_sem2 = (int)((diff_semana2 / suma_sem2_entrada) * 100);
        int margen_rentabilidad_sem3 = (int)((diff_semana3 / suma_sem3_entrada) * 100);
        int margen_rentabilidad_sem4 = (int) ((diff_semana4 / suma_sem4_entrada) * 100);
        int margen_rentabilidad_sem5 = (int) ((diff_semana5 / suma_sem5_entrada) * 100);
        int margen_rentabilidad_total = (int) ((diff_total / suma_total_entrada) * 100);

        text_diff_semana1.setText(String.valueOf(diff_semana1));
        text_diff_semana2.setText(String.valueOf(diff_semana2));
        text_diff_semana3.setText(String.valueOf(diff_semana3));
        text_diff_semana4.setText(String.valueOf(diff_semana4));
        text_diff_semana5.setText(String.valueOf(diff_semana5));
        text_diff_total.setText(String.valueOf(diff_total));

        text_marge_rentabilidad_semana1.setText(String.valueOf(margen_rentabilidad_sem1)+" %");
        text_marge_rentabilidad_semana2.setText(String.valueOf(margen_rentabilidad_sem2)+" %");
        text_marge_rentabilidad_semana3.setText(String.valueOf(margen_rentabilidad_sem3)+" %");
        text_marge_rentabilidad_semana4.setText(String.valueOf(margen_rentabilidad_sem4)+" %");
        text_marge_rentabilidad_semana5.setText(String.valueOf(margen_rentabilidad_sem5)+" %");
        text_marge_rentabilidad_total.setText(String.valueOf(margen_rentabilidad_total)+" %");

        flow_total_semana1_entrada.setText(String.valueOf(suma_sem1_entrada));
        flow_total_semana2_entrada.setText(String.valueOf(suma_sem2_entrada));
        flow_total_semana3_entrada.setText(String.valueOf(suma_sem3_entrada));
        flow_total_semana4_entrada.setText(String.valueOf(suma_sem4_entrada));
        flow_total_semana5_entrada.setText(String.valueOf(suma_sem5_entrada));
        flow_total_entrada.setText(String.valueOf(suma_total_entrada));

        flow_total_semana1_salida.setText(String.valueOf(suma_sem1_salida));
        flow_total_semana2_salida.setText(String.valueOf(suma_sem2_salida));
        flow_total_semana3_salida.setText(String.valueOf(suma_sem3_salida));
        flow_total_semana4_salida.setText(String.valueOf(suma_sem4_salida));
        flow_total_semana5_salida.setText(String.valueOf(suma_sem5_salida));
        flow_total_salida.setText(String.valueOf(suma_total_salida));



    }

}
