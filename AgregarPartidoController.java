/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Equipo;
import modelo.Jornada;
import modelo.Liga;
import modelo.Partido;
import modelo.Temporada;

/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class AgregarPartidoController implements Initializable {

    @FXML
    private ComboBox<Equipo> cbequipolocal;
    @FXML
    private ComboBox<Equipo> cbequipovisitante;
    @FXML
    private TextField tfequipolocal;
    @FXML
    private TextField tfequipovisitante;
    @FXML
    private DatePicker dpfechapartido;
    @FXML
    private ComboBox<Jornada> cbjornada;
    @FXML
    private Button btagregarpartidos;
    @FXML
    private Button btatras;
    @FXML
    private ComboBox<Liga> cbliga;
    @FXML
    private ComboBox<Temporada> cbtemporada;

    private ObservableList<Liga> obliga;
    private ObservableList<Temporada> obtemporada;
    private ObservableList<Jornada> objornada;
    private ObservableList<Equipo> obequipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarEquipoLocal();
        cargarEquipoVisitante();
        cargarLigaControlador();
    }

    @FXML
    private void agregarPartidos(ActionEvent event) {
        try {
            Equipo equipo=this.cbequipolocal.getValue();
            int idequipolocal=equipo.getId();
            Equipo equipovisitante=this.cbequipovisitante.getValue();
            int idequipovisitante=equipovisitante.getId();
            int golesequipolocal=Integer.parseInt(this.tfequipolocal.getText());
            int golesequipovisitante=Integer.parseInt(this.tfequipovisitante.getText());
            LocalDate fechapartido= this.dpfechapartido.getValue();
            Jornada jornada=this.cbjornada.getValue();
            int idjornada=jornada.getIdjornada();
            
            Partido partido=new Partido(idequipolocal, idequipovisitante, golesequipolocal, golesequipovisitante, fechapartido, idjornada);
            
            if (jornada!=null && partido.insertarPartido()){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setContentText("El partido se ha insertado de forma correcta");
                alert.showAndWait();
                alert.close();
                
            }else {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setContentText("El partido no se ha insertado de forma correcta");
                alert.showAndWait();
                alert.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarPartidoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgregarPartidoController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void filtrarLiga(ActionEvent event) {
        try {
            Liga liga = this.cbliga.getValue(); //Cojo el combobox
            Temporada temporada = new Temporada(); //Creo un objeto temporada
            obtemporada = temporada.cargarTemporada(liga.getIdliga()); //Cargo la temporada con el idliga que le corresponde
            this.cbtemporada.setItems(obtemporada);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void filtrarTemporada(ActionEvent event) {
        try {
            Temporada temporada = this.cbtemporada.getValue();
            Jornada jornada = new Jornada();
            objornada = jornada.cargarJornada(temporada.getIdtemporada());
            this.cbjornada.setItems(objornada);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarEquipoLocal() {
        try {

            Equipo equipo = new Equipo();  //Creo objetos para el combobox
            obequipo = equipo.cargarEquipo();
            this.cbequipolocal.setItems(obequipo);
            this.cbequipolocal.getSelectionModel().getSelectedItem(); //Con esto dejo seleccionado el primero para que no aparezca el combobox vacío

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarEquipoVisitante() {
        try {

            Equipo equipo = new Equipo();  //Creo objetos para el combobox
            obequipo = equipo.cargarEquipo();
            this.cbequipovisitante.setItems(obequipo);
            this.cbequipovisitante.getSelectionModel().getSelectedItem(); //Con esto dejo seleccionado el primero para que no aparezca el combobox vacío

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
