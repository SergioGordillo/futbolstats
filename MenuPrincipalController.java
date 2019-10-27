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
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
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
public class MenuPrincipalController implements Initializable {

    @FXML
    private ComboBox<Liga> cbliga;
    @FXML
    private ComboBox<Temporada> cbtemporada;
    @FXML
    private ComboBox<Jornada> cbjornada;
    @FXML
    private ComboBox<Equipo> cbequipo;
    @FXML
    private TableView<Partido> tbtabla;
    @FXML
    private TableColumn<Partido, String> colequipolocal;
    @FXML
    private TableColumn<Partido, Integer> colgolesequipolocal;
    @FXML
    private TableColumn<Partido, Integer> colgolesequipovisitante;
    @FXML
    private TableColumn<Partido, String> colequipovisitante;
    @FXML
    private TableColumn<Partido, String> coljornada;
    @FXML
    private TableColumn<Partido, LocalDate> colfechapartido;
    @FXML
    private MenuItem itagregarliga;
    @FXML
    private MenuItem itverliga;

    private ObservableList<Liga> obliga;
    private ObservableList<Temporada> obtemporada;
    private ObservableList<Jornada> objornada;
    private ObservableList<Partido> obpartido;
    private ObservableList<Equipo> obequipo;

    @FXML
    private MenuItem itagregartemporada;
    @FXML
    private MenuItem itvertemporadas;
    @FXML
    private MenuItem itjornada;
    @FXML
    private MenuItem itagregarequipo;
    @FXML
    private MenuItem itverequipo;
    @FXML
    private MenuItem itagregarpartido;
    @FXML
    private MenuItem itverpartido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarLigaControlador();
        cargarTemporadaControlador();
        cargarEquipo();
        
        //Asigno las columnas a los atributos del modelo 
        this.colequipolocal.setCellValueFactory(new PropertyValueFactory<Partido, String>("nombreequipolocal"));
        this.colgolesequipolocal.setCellValueFactory(new PropertyValueFactory<Partido, Integer>("golesequipolocal"));
        this.colgolesequipovisitante.setCellValueFactory(new PropertyValueFactory<Partido, Integer>("golesequipovisitante"));
        this.colequipovisitante.setCellValueFactory(new PropertyValueFactory<Partido, String>("nombreequipovisitante"));
        this.coljornada.setCellValueFactory(new PropertyValueFactory<Partido, String>("nombrejornada"));
        this.colfechapartido.setCellValueFactory(new PropertyValueFactory<Partido, LocalDate>("fechapartido"));
        
        
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
            if (temporada != null) {
                objornada = jornada.cargarJornada(temporada.getIdtemporada());
                this.cbjornada.setItems(objornada);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void filtrarJornada(ActionEvent event) {
        Jornada jornada = this.cbjornada.getValue();
        Partido partido=new Partido();
        if (jornada!=null){
            try {
                obpartido=partido.cargarPartido(jornada.getIdjornada());
                this.tbtabla.setItems(obpartido);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void filtrarEquipo(ActionEvent event) {
    }

    @FXML
    private void llevarAgregarLiga(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/agregarLiga.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Agregar Liga");
            stage.setScene(scene);

            // Muestro la ventana
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verLiga(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/verLiga.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Consultar Ligas");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();

            // cierro
            Stage Mystage = (Stage) this.cbequipo.getScene().getWindow();
            Mystage.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void cargarTemporadaControlador() {
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
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Agregar Temporada");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verTemporadas(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/verTemporada.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Consultar Temporadas");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();

            // cierro
            Stage Mystage = (Stage) this.cbequipo.getScene().getWindow();
            Mystage.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void llevarAgregarJornada(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/agregarJornada.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Agregar Jornada");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();

            // cierro
            Stage Mystage = (Stage) this.cbequipo.getScene().getWindow();
            Mystage.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void llevarAgregarEquipo(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/agregarEquipo.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Agregar Equipo");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();

            // cierro
            Stage Mystage = (Stage) this.cbequipo.getScene().getWindow();
            Mystage.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void llevarVerEquipo(ActionEvent event) {
    }

    public void cargarEquipo() {
        try {

            Equipo equipo = new Equipo();  //Creo objetos para el combobox
            obequipo = equipo.cargarEquipo();
            this.cbequipo.setItems(obequipo);
            this.cbequipo.getSelectionModel().getSelectedItem(); //Con esto dejo seleccionado el primero para que no aparezca el combobox vacío

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void llevarAgregarPartido(ActionEvent event) {
        try {
            // Abro la ventana

            // Cargamos la vista secundaria
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/agregarPartido.fxml"));

            // Cargo el padre
            Parent root = (Parent) fxmlLoader.load();

            // cargo la ventana secundaria
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Agregar Partido");
            stage.setScene(scene);

            // Muestro la ventana
            stage.show();

            // cierro
            Stage Mystage = (Stage) this.cbequipo.getScene().getWindow();
            Mystage.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void llevarVerPartido(ActionEvent event) {
    }

}
