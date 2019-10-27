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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Equipo;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class AgregarEquipoController implements Initializable {

    @FXML
    private TextField tfnombreequipo;
    @FXML
    private Button btagregarequipo;
    @FXML
    private Button btatras;
    @FXML
    private Button btagregarequiponuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void agregarEquipo(ActionEvent event) {
        try {
            String nombre = this.tfnombreequipo.getText();
            Equipo equipo = new Equipo(nombre);

            if (equipo.insertarEquipo()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setContentText("El equipo se ha insertado de forma correcta");
                alert.showAndWait();
                alert.close();

                Stage Mystage = (Stage) this.btatras.getScene().getWindow(); //Para cerrar la ventana
                Mystage.close(); // Para cerrar la ventana, tengo que poner las dos líneas de código

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("El equipo se ha insertado de forma incorrecta");
                alert.showAndWait();
                alert.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarEquipoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgregarEquipoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void atras(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/menuPrincipal.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("MenuPrincipal");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();

            // cierro
            Stage Mystage = (Stage) this.btatras.getScene().getWindow();
            Mystage.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void añadirNuevoEquipo(ActionEvent event) {
        try {
            String nombre = this.tfnombreequipo.getText();
            Equipo equipo = new Equipo(nombre);

            if (equipo.insertarEquipo()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setContentText("El equipo se ha insertado de forma correcta");
                alert.showAndWait();
                alert.close();
                
                this.tfnombreequipo.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("El equipo se ha insertado de forma incorrecta");
                alert.showAndWait();
                alert.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarEquipoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgregarEquipoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
