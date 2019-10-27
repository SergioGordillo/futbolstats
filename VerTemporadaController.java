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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Temporada;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class VerTemporadaController implements Initializable {

    @FXML
    private MenuItem itagregartemporada;
    @FXML
    private MenuItem itmodificartemporada;
    @FXML
    private MenuItem iteliminartemporada;
    @FXML
    private TableView<Temporada> tbtemporada;
    @FXML
    private TableColumn<Temporada, Integer> colidtemporada;
    @FXML
    private TableColumn<Temporada, String> colnombretemporada;
    @FXML
    private TableColumn<Temporada, String> colnombreliga;
    @FXML
    private Button btatras;
    
    private ObservableList<Temporada> obtemporada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.obtemporada = FXCollections.observableArrayList(); //Creo el observable list
            
            this.colidtemporada.setCellValueFactory(new PropertyValueFactory <Temporada, Integer>("idtemporada")); //Asocia columna con el valor
            this.colnombretemporada.setCellValueFactory(new PropertyValueFactory <Temporada, String>("nombretemporada"));
            this.colnombreliga.setCellValueFactory(new PropertyValueFactory<Temporada, String>("nombreliga"));
            
            
            Temporada temporada=new Temporada(); //Creo un objeto para llamar al método porque no es estático
            this.obtemporada=temporada.cargarTemporada();
            
            this.tbtemporada.setItems(obtemporada);//Con esto le digo que los datos de la tabla los cojo del Observable List de Temporada
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerTemporadaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VerTemporadaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void agregarTemporada(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/agregarTemporada.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); //La hago modal
            stage.setTitle("Agregar Temporada");
            stage.setScene(scene);

            // Muestro la ventana
            stage.showAndWait();
            Temporada temporada = new Temporada(); //Creo un objeto para llamar al método porque no es estático
            this.obtemporada = temporada.cargarTemporada(); 

            this.tbtemporada.setItems(obtemporada);//Con esto le digo que los datos de la tabla los cojo del Observable List de Personas
            
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerTemporadaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VerTemporadaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modificarTemporada(ActionEvent event) {
    }

    @FXML
    private void eliminarTemporada(ActionEvent event) {
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
            stage.setTitle("Menú Principal");
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
    
}
