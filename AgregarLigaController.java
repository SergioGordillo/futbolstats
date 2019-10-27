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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Liga;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class AgregarLigaController implements Initializable {

    @FXML
    private MenuItem itaccionatras;
    @FXML
    private TextField tfnombreliga;
    @FXML
    private TextField tfpais;
    @FXML
    private TextField tfescudo;
    @FXML
    private Button btOK;
    @FXML
    private Button btcancelar;

    private Liga liga;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(Liga liga) {
        this.liga = liga; //Con esto ya tengo los datos de la liga del controlador
        this.tfnombreliga.setText(liga.getNombreliga()); //Con esto estoy cargando en los textfields los valores que le he pasado del controlador
        this.tfpais.setText(liga.getPais());
        this.tfescudo.setText(liga.getEscudoliga());

    }

    @FXML
    private void atras(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/verLiga.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Agregar Liga");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();

            // cierro
            Stage Mystage = (Stage) this.btOK.getScene().getWindow();
            Mystage.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void agregarLiga(ActionEvent event) {
        try {
            //Creo Liga en el controller
            String nombreliga = this.tfnombreliga.getText();
            String pais = this.tfpais.getText();
            String escudoliga = this.tfescudo.getText();

            if (liga == null) {

                Liga liga = new Liga(nombreliga, pais, escudoliga);
                liga.insertarLiga();//Este inserta la Liga en la BBDD

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setContentText("Se ha añadido la liga correctamente");
                alert.showAndWait();
            } else {
                
                liga.setNombreliga(nombreliga); //Con esto modifico las propiedades del objeto
                liga.setPais(pais);
                liga.setEscudoliga(escudoliga);
                liga.modificarLiga(); //Con esto llamo al método que me permite modificar liga

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setContentText("Se ha modificado la liga correctamente");
                alert.showAndWait();

            }
            
            
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
       
            // cierro
            Stage Mystage = (Stage) this.btOK.getScene().getWindow();
            Mystage.close();

    }
}
