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
public class Equipo {
    private int id;
    private String nombre;
    private String escudo; 

    public Equipo() {
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    public Equipo(int id, String nombre, String escudo) {
        this.id = id;
        this.nombre = nombre;
        this.escudo = escudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }
    
     public boolean insertarEquipo() throws SQLException, ClassNotFoundException{
        ConexionController c = ConexionController.getInstancia();
        String SQL = "INSERT INTO equipos (nombre )values ('" + nombre+ "')";
        System.out.println(SQL);
        int filas=c.ejecutarInstruccion(SQL);
        
        if(filas==0){
            return false;
        } else return true; 
    }
     
         public ObservableList<Equipo> cargarEquipo() throws ClassNotFoundException, SQLException { //Método para filtrar por idliga
        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        ObservableList<Equipo> obequipo = FXCollections.observableArrayList();//Crear ArrayList

        String SQL = "select id, nombre "
                + "FROM equipos "; //Quiero que me cargue los equipos
        System.out.println(SQL);

        ResultSet rs = c.ejecutarConsulta(SQL); //Almaceno los datos en el ResultSet

        while (rs.next()) { //Cojo los datos
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            
            Equipo equipo=new Equipo (id, nombre);
            obequipo.add(equipo);
        }
        return obequipo;

    }

    @Override
    public String toString() {
        return nombre;
    }
         
         
    
    
    
}
