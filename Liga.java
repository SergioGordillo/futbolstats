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
public class Liga {

    private int idliga;
    private String nombreliga;
    private String pais;
    private String escudoliga;

    public Liga() {
    }

    public Liga(String nombreliga, String pais, String escudoliga) {
        this.nombreliga = nombreliga;
        this.pais = pais;
        this.escudoliga = escudoliga;
    }

    public Liga(int idliga, String nombreliga, String pais, String escudoliga) {
        this.idliga = idliga;
        this.nombreliga = nombreliga;
        this.pais = pais;
        this.escudoliga = escudoliga;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEscudoliga() {
        return escudoliga;
    }

    public void setEscudoliga(String escudoliga) {
        this.escudoliga = escudoliga;
    }

    @Override
    public String toString() {
        return nombreliga;
    }

    public void insertarLiga() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();
        String SQL = "insert into liga (nombreliga, pais, escudoliga) values ('" + nombreliga + "', '" + pais + "', '" + escudoliga + "')";
        System.out.println(SQL);
        c.ejecutarInstruccion(SQL);
    }

    public void modificarLiga() throws SQLException, ClassNotFoundException {
        ConexionController c = ConexionController.getInstancia();
        String SQL = "update liga set nombreliga= '" + nombreliga + "', pais='" + pais + "', escudoliga='" + escudoliga + "'"
                + "where idliga=" + idliga;
        System.out.println(SQL);
        c.ejecutarInstruccion(SQL);

    }
    
        public void eliminarLiga() throws SQLException, ClassNotFoundException {
        ConexionController c = ConexionController.getInstancia();
        String SQL = "DELETE FROM liga WHERE idliga=" + idliga; 
        System.out.println(SQL);
        c.ejecutarInstruccion(SQL);

    }

    public ObservableList<Liga> cargarLiga() throws ClassNotFoundException, SQLException { //Con este método devuelvo un ObservableList, que voy a necesitar 
        //para meter los datos en el Combobox correspondiente

        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        ObservableList<Liga> obliga = FXCollections.observableArrayList();//Crear ArrayList

        String SQL = "select idliga, nombreliga, pais, escudoliga "
                + "FROM liga ";

        ResultSet rs = c.ejecutarConsulta(SQL); //Almaceno los datos en el ResultSet

        while (rs.next()) { //Cojo los datos
            int idliga = rs.getInt("idliga");
            String nombreliga = rs.getString("nombreliga");
            String pais = rs.getString("pais");
            String escudoliga = rs.getString("escudoliga");

            Liga liga = new Liga(idliga, nombreliga, pais, escudoliga);
            obliga.add(liga);
        }
        return obliga;

    }

   /* public ObservableList<Liga> cargarLiga(nombreliga, pais, escudoliga) throws ClassNotFoundException, SQLException { //Con este método devuelvo un ObservableList, que voy a necesitar 
        //para meter los datos en el Combobox correspondiente

        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        ObservableList<Liga> obliga = FXCollections.observableArrayList();//Crear ArrayList

        String SQL = "select idliga, nombreliga, pais, escudoliga "
                + "FROM liga ";

        ResultSet rs = c.ejecutarConsulta(SQL); //Almaceno los datos en el ResultSet

        while (rs.next()) { //Cojo los datos
            int idliga = rs.getInt("idliga");
            String nombreliga = rs.getString("nombreliga");
            String pais = rs.getString("pais");
            String escudoliga = rs.getString("escudoliga");

            Liga liga = new Liga(idliga, nombreliga, pais, escudoliga);
            obliga.add(liga);
        }
        return obliga;

}*/

}
