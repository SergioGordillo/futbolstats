/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Liga;
import modelo.Temporada;


/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class AgregarTemporadaController implements Initializable {

    @FXML
    private TextField tftemporada;
    @FXML
    private Button btOK;
    @FXML
    private Button btcancelar;
    @FXML
    private MenuItem itatras;
    @FXML
    private ComboBox<Liga> cbliga;
    private ObservableList<Liga> obliga;
     private ObservableList<Temporada> obtemporada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarLigaControlador();
    }    

    @FXML
    private void agregarTemporada(ActionEvent event) {
        
        try {
            //Creo Liga en el controller
            String nombretemporada=this.tftemporada.getText();
            Liga liga=this.cbliga.getValue(); //Creo el objeto para coger la información del combobox
            int idliga=liga.getIdliga();

            Temporada temporada = new Temporada(nombretemporada,idliga);
            temporada.insertarTemporada();//Este inserta la Temporada en la BBDD

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("Se ha añadido la temporada correctamente");
            alert.showAndWait();
            
            Stage Mystage = (Stage) this.btOK.getScene().getWindow();
            Mystage.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/verTemporada.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Agregar Temporada");
            stage.setScene(scene);

            // Muestro la ventana
            stage.showAndWait();
            

            // cierro
            Stage Mystage = (Stage) this.btOK.getScene().getWindow();
            Mystage.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void atras(ActionEvent event) {
            Stage Mystage = (Stage) this.btOK.getScene().getWindow();
            Mystage.close();

    }
    
    private void cargarLigaControlador() {
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
        
    
}
