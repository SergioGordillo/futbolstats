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
public class Jornada {

    private int idjornada;
    private String nombre;
    private int idtemporada;

    public Jornada() {
    }

    public Jornada(int idjornada, String nombre, int idtemporada) {
        this.idjornada = idjornada;
        this.nombre = nombre;
        this.idtemporada = idtemporada;
    }

    public int getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(int idjornada) {
        this.idjornada = idjornada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdtemporada() {
        return idtemporada;
    }

    public void setIdtemporada(int idtemporada) {
        this.idtemporada = idtemporada;
    }

    public boolean insertarJornada(int numerojornadas, Temporada temporada) throws SQLException, ClassNotFoundException {
        ConexionController c = ConexionController.getInstancia();
        String SQL = "";
        for (int i = 1; i <= numerojornadas; i++) {
            SQL = "insert into jornadas (nombre, idtemporada) values ('" + i + "', " + temporada.getIdtemporada() + ");";
            int filas = c.ejecutarInstruccion(SQL); //Ejecutar instrucción devuelve un número de filas
        }

        System.out.println(SQL);
        return true;
        /*if (filas>0){
            return true;
        } else {
            return false; 
        }*/

    }

    public ObservableList<Jornada> cargarJornada(int idtemporada) throws ClassNotFoundException, SQLException { //Método para filtrar por idliga
        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        ObservableList<Jornada> objornada = FXCollections.observableArrayList();//Crear ArrayList

        String SQL = "select j.idjornada, j.nombre, t.idtemporada "
                + "FROM temporada t, jornadas j "
                + "WHERE j.idtemporada=t.idtemporada "
                + "AND t.idtemporada= " + idtemporada; //Con esto le digo que filtre por el idtemporada que yo seleccione
        System.out.println(SQL);

        ResultSet rs = c.ejecutarConsulta(SQL); //Almaceno los datos en el ResultSet

        while (rs.next()) { //Cojo los datos
            int idjornada = rs.getInt("idjornada");
            String nombre = rs.getString("nombre");
            int idtemporadabd = rs.getInt("idtemporada");

            Jornada jornada = new Jornada(idjornada, nombre, idtemporada);
            objornada.add(jornada);
        }
        return objornada;

    }

    @Override
    public String toString() {
        return nombre;
    }

}
