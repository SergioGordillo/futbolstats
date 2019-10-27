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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class PantallaPrincipalController implements Initializable {

    @FXML
    private Button btinicio;
    @FXML
    private Button btsalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void iniciarPrograma(ActionEvent event) throws IOException {
         try {
            // Abro la ventana
            
            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/menuPrincipal.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Menu Principal");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();
            
            // cierro
            Stage Mystage = (Stage) this.btsalir.getScene().getWindow();
            Mystage.close();   
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0); //Método que me permite salir del programa
    }
}
