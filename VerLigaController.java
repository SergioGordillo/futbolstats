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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Liga;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class VerLigaController implements Initializable {

    @FXML
    private MenuItem itaccionagregarliga;
    @FXML
    private MenuItem itaccionmodificarliga;
    @FXML
    private MenuItem itaccioneliminarliga;
    @FXML
    private MenuItem itaccionatras;
    @FXML
    private TableView<Liga> tbligas;
    @FXML
    private TableColumn<Liga, Integer> colidliga;
    @FXML
    private TableColumn<Liga, String> colnombreliga;
    @FXML
    private TableColumn<Liga, String> colpais;
    @FXML
    private TableColumn<Liga, String> colescudoliga;
    @FXML
    private Button btatras;

    private ObservableList<Liga> obliga;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.obliga = FXCollections.observableArrayList(); //Creo el observable list

            this.colidliga.setCellValueFactory(new PropertyValueFactory<Liga, Integer>("idliga")); //Asocia columna con el valor
            this.colnombreliga.setCellValueFactory(new PropertyValueFactory<Liga, String>("nombreliga"));
            this.colpais.setCellValueFactory(new PropertyValueFactory("pais"));
            this.colescudoliga.setCellValueFactory(new PropertyValueFactory("escudoliga"));

            Liga liga = new Liga(); //Creo un objeto para llamar al método porque no es estático
            this.obliga = liga.cargarLiga();

            tbligas.setItems(obliga);//Con esto le digo que los datos de la tabla los cojo del Observable List de Personas

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerLigaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VerLigaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // TODO

    @FXML
    private void agregarLiga(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/agregarLiga.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); //Hago la ventana modal
            stage.setTitle("Agregar Liga");
            stage.setScene(scene);

            // Muestro la ventana
            stage.showAndWait();

            Liga liga = new Liga(); //Creo un objeto para llamar al método porque no es estático
            this.obliga = liga.cargarLiga();

            tbligas.setItems(obliga);//Con esto le digo que los datos de la tabla los cojo del Observable List de Personas

        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerLigaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VerLigaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modificarLiga(ActionEvent event) {
        try {
            // Abro la ventana

            Liga liga = this.tbligas.getSelectionModel().getSelectedItem();

            if (liga == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Tienes que seleccionar una liga");
                alert.showAndWait();
            } else {
                // Cargamos la vista secundaria
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/agregarLiga.fxml"));

                // Cargo el padre
                Parent root = (Parent) fxmlLoader.load();
                AgregarLigaController controlador = fxmlLoader.getController(); //Con esto me das el controlador de la clase que quiero llamar
                controlador.initAttributes(liga); //Con esto le paso al controlador los datos del objeto que he seleccionado 

                // cargo la ventana secundaria
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modificar Liga");
                stage.setScene(scene);

                // Muestro la ventana
                stage.showAndWait();

                this.tbligas.refresh(); //Con esto refresco la tabla para que se actualice automáticamente
            }

        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eliminarLiga(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Abro la ventana

        Liga liga = this.tbligas.getSelectionModel().getSelectedItem();
        if (liga == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Tienes que seleccionar una liga");
            alert.showAndWait();
        } else {
            liga.eliminarLiga();
            this.obliga.remove(liga); 
            this.tbligas.refresh(); //Con esto refresco la tabla para que se actualice automáticamente
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
            stage.setTitle("Menu Principal");
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

    /*private void cargarLigaModificar(ActionEvent event) {
        try {
            //Cojo los datos que están en la tabla que muestro en la vista
            String nombreliga = this.colnombreliga.getText();
            String pais = this.colpais.getText();
            String escudoliga = this.colescudoliga.getText();

            Liga liga = new Liga(nombreliga, pais, escudoliga); //Creo un objeto
            this.obliga = liga.this.personas = persona.cargarPersonas(fechaNacimientoInicio, fechaNacimientoFinal, nombreBusqueda, pro, sexo);
            this.tbtabla.setItems(personas);
            liga.insertarLiga();//Este inserta la Liga en la BBDD

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("Se ha añadido la liga correctamente");
            alert.showAndWait();

        } catch (ClassNotFoundException | SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }*/
}
