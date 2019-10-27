/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import modelo.Jornada;
import modelo.Liga;
import modelo.Temporada;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class AgregarJornadaController implements Initializable {

    @FXML
    private MenuItem itatras;
    @FXML
    private ComboBox<Liga> cbliga;
    @FXML
    private ComboBox<Temporada> cbtemporada;
    @FXML
    private TextField tfnumerojornadas;
    @FXML
    private Button btOK;

    private ObservableList<Liga> obliga;
    private ObservableList<Temporada> obtemporada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarLiga();
        cargarTemporada();
    }

    @FXML
    private void atras(ActionEvent event) {
    }

    @FXML
    private void filtrarTemporada(ActionEvent event) {

        Liga liga = this.cbliga.getValue(); //Esto me permite coger el valor seleccionado en el combobox

        if (liga == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("Has de seleccionar un valor válido");
            alert.showAndWait();
        } else {
            try {
                Temporada temporada = new Temporada();
                ObservableList<Temporada> obtemporada = temporada.cargarTemporada(liga.getIdliga());
                this.cbtemporada.setItems(obtemporada);
                if (obtemporada.size() > 0) {
                    this.cbtemporada.getSelectionModel().select(0); //Si tienes alguno, pues me seleccionas el primero
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgregarJornadaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AgregarJornadaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void agregarJornadas(ActionEvent event) {

        String njornadas = this.tfnumerojornadas.getText();

        Temporada temporada = this.cbtemporada.getValue(); //Cojo el combobox

        String errores = "";

        if (temporada == null) {
            errores += "La temporada no puede ser nula\n";

        }

        if (njornadas.isEmpty()) {
            errores += "Debes introducir números de jornadas\n";
        }

        if (errores.isEmpty()) {

            try {
                int numerojornadas = Integer.parseInt(njornadas);//Cojo el texto del textfield
                Jornada jornada = new Jornada();

                if (jornada.insertarJornada(numerojornadas, temporada)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("Se han agregado las jornadas seleccionadas");
            alert.showAndWait();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AgregarJornadaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgregarJornadaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText(errores);
            alert.showAndWait();
        }

    }

    private void cargarLiga() {
        try {
            Liga liga = new Liga(); //Creo objetos para el combobox
            obliga = liga.cargarLiga();
            this.cbliga.setItems(obliga);
            this.cbliga.getSelectionModel().select(0); //Con esto dejo seleccionado el primero para que no aparezca el combobox vacío

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarTemporada() {
        try {
            Temporada temporada = new Temporada(); //Creo objetos para el combobox
            obtemporada = temporada.cargarTemporada();
            this.cbtemporada.setItems(obtemporada);
            this.cbtemporada.getSelectionModel().select(0); //Con esto dejo seleccionado el primero para que no aparezca el combobox vacío

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
