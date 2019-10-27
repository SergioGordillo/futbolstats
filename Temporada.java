/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.ConexionController;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sergio
 */
public class Temporada {

    private int idtemporada;
    private String nombretemporada;
    private int idliga;
    
    private String nombreliga; //Lo creo para que en la tabla liga me muestre el nombre de la liga y no el id

    public Temporada() {
    }

    public Temporada(String nombretemporada) {
        this.nombretemporada = nombretemporada;
    }

    public Temporada(String nombretemporada, int idliga) {
        this.nombretemporada = nombretemporada;
        this.idliga = idliga;
    }

    public Temporada(int idtemporada, String nombretemporada, int idliga) {
        this.idtemporada = idtemporada;
        this.nombretemporada = nombretemporada;
        this.idliga = idliga;
    }

    public Temporada(int idtemporada, String nombretemporada, int idliga, String nombreliga) {
        this.idtemporada = idtemporada;
        this.nombretemporada = nombretemporada;
        this.idliga = idliga;
        this.nombreliga = nombreliga;
    }

    public int getIdtemporada() {
        return idtemporada;
    }

    public void setIdtemporada(int idtemporada) {
        this.idtemporada = idtemporada;
    }

    public String getNombretemporada() {
        return nombretemporada;
    }

    public void setNombretemporada(String nombretemporada) {
        this.nombretemporada = nombretemporada;
    }

    public int getIdliga() {
        return idliga;
    }

    public void setIdliga(int idliga) {
        this.idliga = idliga;
    }

    public String getNombreliga() {
        return nombreliga;
    }

    public void setNombreliga(String nombreliga) {
        this.nombreliga = nombreliga;
    }
    
    

    public void insertarTemporada() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();
        String SQL = "insert into temporada (nombretemporada, idliga) values ('" + nombretemporada + "', "+idliga+")";
        System.out.println(SQL);
        c.ejecutarInstruccion(SQL);
    }

    public ObservableList<Temporada> cargarTemporada() throws ClassNotFoundException, SQLException { //Con este método devuelvo un ObservableList, que voy a necesitar 
        //para meter los datos en el Combobox correspondiente

        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        ObservableList<Temporada> obtemporada = FXCollections.observableArrayList();//Crear ArrayList

        String SQL = "select t.idtemporada, t.nombretemporada, t.idliga, l.nombreliga "
                + "FROM temporada t, liga l "
                + "WHERE t.idliga=l.idliga";

        ResultSet rs = c.ejecutarConsulta(SQL); //Almaceno los datos en el ResultSet

        while (rs.next()) { //Cojo los datos
            int idtemporada = rs.getInt("idtemporada");
            String nombretemporada = rs.getString("nombretemporada");
            int idliga = rs.getInt("idliga");
            String nombreliga=rs.getString("nombreliga");

            Temporada temporada = new Temporada(idtemporada, nombretemporada, idliga, nombreliga);
            obtemporada.add(temporada);
        }
        return obtemporada;

    }
    
    public ObservableList<Temporada> cargarTemporada(int idliga) throws ClassNotFoundException, SQLException{ //Método para filtrar por idliga
        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        ObservableList<Temporada> obtemporada = FXCollections.observableArrayList();//Crear ArrayList

        String SQL = "select t.idtemporada, t.nombretemporada, t.idliga, l.nombreliga "
                + "FROM temporada t, liga l "
                + "WHERE t.idliga=l.idliga "
                + "AND t.idliga= " + idliga; //Con esto le digo que filtre por el idliga que yo seleccione
        System.out.println(SQL);

        ResultSet rs = c.ejecutarConsulta(SQL); //Almaceno los datos en el ResultSet
        
        while (rs.next()) { //Cojo los datos
            int idtemporada = rs.getInt("idtemporada");
            String nombretemporada = rs.getString("nombretemporada");
            String nombreliga=rs.getString("nombreliga");

            Temporada temporada = new Temporada(idtemporada, nombretemporada, idliga, nombreliga);
            obtemporada.add(temporada);
        }
        return obtemporada;
        
    }

    @Override
    public String toString() {
        return  nombretemporada;
    }
    
    

}
