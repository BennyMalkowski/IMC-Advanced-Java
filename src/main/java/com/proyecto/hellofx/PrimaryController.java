package com.proyecto.hellofx;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PrimaryController implements Initializable {

    final double OBESIDAD = 30;
    final double SOBREPESOTOP = 29.9;
    final double SOBREPESOMIN = 25;
    final double NORMALTOP = 24.9;
    final double NORMALMIN = 18.5;
    final double DELGADEZ = 18.4;
    double IMC = 0;

    @FXML
    private TextField etiquetaResultado;
    @FXML
    private Button botonCalcular;
    @FXML
    private TextField etiquetaAltura;
    @FXML
    private TextField etiquetaPeso;

    @FXML
    private Slider sliderAltura;
    @FXML
    private Slider sliderPeso;
    @FXML
    private ListView<String> listPesos;

    ObservableList<String> pesos = FXCollections.observableArrayList("Obesidad", "Sobrepeso", "Normal", "Delgadez"); //declaramos fuera de cualquier metodo

    @FXML
    private void alPulsar(ActionEvent event) {
        String info;
        double alturaUser = sliderAltura.getValue(); //en los radiobutton, el valor nos lo da con un double
        double pesoUser = sliderPeso.getValue();
        final double ALTURATOP = 2.20;
        final double ALTURAMIN = 0.40;
        final double PESOTOP = 180;
        final double PESOMIN = 20;

        try {
            double altura = alturaUser / 100;
            double peso = pesoUser;
            etiquetaAltura.setText(String.valueOf(String.format("%.2f", altura)) + " m"); //convertimos el double en un String
            etiquetaPeso.setText(String.valueOf(String.format("%.2f", peso)) + " Kg");

            if ((altura > ALTURAMIN && altura < ALTURATOP) && (peso > PESOMIN && peso < PESOTOP)) {
                IMC = peso / (altura * altura);
                
                grupoBotones(IMC);
                etiquetaResultado.setText(String.format("%.2f", IMC));
                calcularIMC(IMC);
                

            } else {
                etiquetaResultado.setText("ERROR");

            }

        } catch (NumberFormatException e) {
            info = "Error";
        }

//        etiquetaResultado.setText(info);
    }

    public void calcularIMC(double IMC) {

        Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING); //Creamos un alert

        if (IMC > OBESIDAD) {
            dialogoAlerta.setTitle("PELIGRO!");
            dialogoAlerta.setHeaderText("Igual estás un poquito fondón");
            dialogoAlerta.setContentText("Deberías comer menos y hacer más ejercicio");
            etiquetaResultado.setStyle("-fx-background-color: red;"); //cambiamos color de fondo de la etiqueta resultado

        } else if (IMC > SOBREPESOMIN && IMC < SOBREPESOTOP) {
            dialogoAlerta.setTitle("CUIDADO!");
            dialogoAlerta.setHeaderText("Igual estás bajo de forma");
            dialogoAlerta.setContentText("Controla esas cervezas y muevete un poco más");
        } else if (IMC > NORMALMIN && IMC < NORMALTOP) {
            dialogoAlerta.setTitle("NORMAL!");
            dialogoAlerta.setHeaderText("Estás en tu peso ideal");
            dialogoAlerta.setContentText("Sigue con esos hábitos que te conservas genial");
        } else if (IMC < DELGADEZ) {
            dialogoAlerta.setTitle("COME MÁS!");
            dialogoAlerta.setHeaderText("Igual estás un poquito delgado");
            dialogoAlerta.setContentText("Deberías comer más y ponerte en forma");
            etiquetaResultado.setStyle("-fx-background-color: red;");
        }

        dialogoAlerta.showAndWait();

    }

    private void grupoBotones(double resultado) {
        if (resultado > OBESIDAD) {
//            botonRadioObesidad.setSelected(true);
//            botonRadioDelgadez.setSelected(false);
//            botonRadioNormal.setSelected(false);
//            botonRadioSobrepeso.setSelected(false);
//            botonRadioObesidad.isSelected();

            listPesos.getSelectionModel().select(0);
        } else if (resultado > SOBREPESOMIN && resultado < SOBREPESOTOP) {
//            botonRadioObesidad.setSelected(false);
//            botonRadioDelgadez.setSelected(false);
//            botonRadioNormal.setSelected(false);
//            botonRadioSobrepeso.setSelected(true);
//            botonRadioSobrepeso.isSelected();

            listPesos.getSelectionModel().select(1);

        } else if (resultado > NORMALMIN && resultado < NORMALTOP) {
//            botonRadioObesidad.setSelected(false);
//            botonRadioDelgadez.setSelected(false);
//            botonRadioNormal.setSelected(true);
//            botonRadioSobrepeso.setSelected(false);
//            botonRadioNormal.isSelected();

            listPesos.getSelectionModel().select(2);
        } else if (resultado < DELGADEZ) {
//            botonRadioObesidad.setSelected(false);
//            botonRadioDelgadez.setSelected(true);
//            botonRadioNormal.setSelected(false);
//            botonRadioSobrepeso.setSelected(false);

            listPesos.getSelectionModel().select(3);
        }
    }
//    
//    public void lista(String IMC){
//     
//     
//    
//     
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listPesos.setItems(pesos);
    }
//    
//    

    @FXML
    private void cambioPeso(MouseEvent event) {

        //POSICION DEL ELEMENTO SELECCIONADO
        int pesoSeleccionado = listPesos.getSelectionModel().getSelectedIndex();

        //CADA ELEMENTO EN LISTACICLOS Y LISTADESCRIPCIONCICLOS ESTAN ASOCIADOS POR POSICION
        //SE LOCALIZA EL ELEMENTO DE LA POSICION SELECCIONADA
        String seleccionado = pesos.get(pesoSeleccionado);

        // MARCAMOS LO SELECCIONADO
//    listPesos.setText(seleccionado);
        listPesos.getSelectionModel().select(pesoSeleccionado);

    }

}
