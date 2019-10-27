/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.ConexionController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sergio
 */
public class Partido {

    private int idpartido;
    private int idequipolocal;
    private int idequipovisitante;
    private int golesequipolocal;
    private int golesequipovisitante;
    private LocalDate fechapartido;
    private int idjornada;

    private String nombreequipolocal;
    private String nombreequipovisitante;
    private String nombrejornada;

    private Equipo equipo;

    public Partido() {
    }

    public Partido(int idequipolocal, int idequipovisitante, int golesequipolocal, int golesequipovisitante, LocalDate fechapartido, int idjornada) {
        this.idequipolocal = idequipolocal;
        this.idequipovisitante = idequipovisitante;
        this.golesequipolocal = golesequipolocal;
        this.golesequipovisitante = golesequipovisitante;
        this.fechapartido = fechapartido;
        this.idjornada = idjornada;
    }

    public Partido(int idpartido, int idequipolocal, int idequipovisitante, int golesequipolocal, int golesequipovisitante, LocalDate fechapartido, int idjornada) {
        this.idpartido = idpartido;
        this.idequipolocal = idequipolocal;
        this.idequipovisitante = idequipovisitante;
        this.golesequipolocal = golesequipolocal;
        this.golesequipovisitante = golesequipovisitante;
        this.fechapartido = fechapartido;
        this.idjornada = idjornada;
    }

    public Partido(int idpartido, int idequipolocal, int idequipovisitante, int golesequipolocal, int golesequipovisitante, LocalDate fechapartido, int idjornada, String nombreequipolocal, String nombreequipovisitante, String nombrejornada) {
        this.idpartido = idpartido;
        this.idequipolocal = idequipolocal;
        this.idequipovisitante = idequipovisitante;
        this.golesequipolocal = golesequipolocal;
        this.golesequipovisitante = golesequipovisitante;
        this.fechapartido = fechapartido;
        this.idjornada = idjornada;
        this.nombreequipolocal = nombreequipolocal;
        this.nombreequipovisitante = nombreequipovisitante;
        this.nombrejornada = nombrejornada;
    }

    public int getIdpartido() {
        return idpartido;
    }

    public void setIdpartido(int idpartido) {
        this.idpartido = idpartido;
    }

    public int getIdequipolocal() {
        return idequipolocal;
    }

    public void setIdequipolocal(int idequipolocal) {
        this.idequipolocal = idequipolocal;
    }

    public int getIdequipovisitante() {
        return idequipovisitante;
    }

    public void setIdequipovisitante(int idequipovisitante) {
        this.idequipovisitante = idequipovisitante;
    }

    public int getGolesequipolocal() {
        return golesequipolocal;
    }

    public void setGolesequipolocal(int golesequipolocal) {
        this.golesequipolocal = golesequipolocal;
    }

    public int getGolesequipovisitante() {
        return golesequipovisitante;
    }

    public void setGolesequipovisitante(int golesequipovisitante) {
        this.golesequipovisitante = golesequipovisitante;
    }

    public LocalDate getFechapartido() {
        return fechapartido;
    }

    public void setFechapartido(LocalDate fechapartido) {
        this.fechapartido = fechapartido;
    }

    public int getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(int idjornada) {
        this.idjornada = idjornada;
    }

    public String getNombreequipolocal() {
        return nombreequipolocal;
    }

    public void setNombreequipolocal(String nombreequipolocal) {
        this.nombreequipolocal = nombreequipolocal;
    }

    public String getNombreequipovisitante() {
        return nombreequipovisitante;
    }

    public void setNombreequipovisitante(String nombreequipovisitante) {
        this.nombreequipovisitante = nombreequipovisitante;
    }

    public String getNombrejornada() {
        return nombrejornada;
    }

    public void setNombrejornada(String nombrejornada) {
        this.nombrejornada = nombrejornada;
    }

    public boolean insertarPartido() throws SQLException, ClassNotFoundException {
        ConexionController c = ConexionController.getInstancia();

        String SQL = "INSERT INTO partidos (idequipolocal, idequipovisitante, golesequipolocal, golesequipovisitante, fechapartido, idjornada)values(" + idequipolocal
                + "," + idequipovisitante + ","
                + "" + golesequipolocal + ","
                + "" + golesequipovisitante + ","
                + "'" + fechapartido + "',"
                + "" + idjornada + ")";

        System.out.println(SQL);
        int filas = c.ejecutarInstruccion(SQL);

        if (filas == 0) {
            return false;
        } else {
            return true;
        }
    }

    public ObservableList<Partido> cargarPartido(int idjornada) throws ClassNotFoundException, SQLException { //Método para filtrar por idliga
        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        ObservableList<Partido> obpartido = FXCollections.observableArrayList();//Crear ArrayList

        String SQL = "select p.idpartido, p.idequipolocal, p.idequipovisitante, p.golesequipolocal, p.golesequipovisitante, p.fechapartido, p.idjornada, l.nombre, v.nombre, j.nombre "
                + "FROM partidos p, equipos l, equipos v, jornadas j "
                + "WHERE p.idequipolocal=l.id "
                + "AND p.idequipovisitante=v.id "
                + "AND p.idjornada=j.idjornada "
                + "AND p.idjornada= " + idjornada; //Con esto le digo que filtre por el idjornada que yo seleccione

        System.out.println(SQL);

        ResultSet rs = c.ejecutarConsulta(SQL); //Almaceno los datos en el ResultSet

        while (rs.next()) { //Cojo los datos

            int idpartido = rs.getInt("p.idpartido");
            int idequipolocal = rs.getInt("p.idequipolocal");
            int idequipovisitante = rs.getInt("p.idequipovisitante");
            int golesequipolocal = rs.getInt("p.golesequipolocal");
            int golesequipovisitante = rs.getInt("p.golesequipovisitante");
            LocalDate fechapartido = rs.getDate("p.fechapartido").toLocalDate();
            int idjornadabd = rs.getInt("p.idjornada");
            String nombreequipolocal = rs.getString("l.nombre");
            String nombreequipovisitante = rs.getString("v.nombre");
            String nombrejornada = rs.getString("j.nombre");

            Partido partido = new Partido(idpartido, idequipolocal, idequipovisitante, golesequipolocal, golesequipovisitante, fechapartido, idjornada, nombreequipolocal, nombreequipovisitante, nombrejornada);
            obpartido.add(partido);
        }
        return obpartido;

    }

    /* public int calcularPartidosGanados() throws ClassNotFoundException, SQLException{
             //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();
        
        String SQL= "select COUNT(p.golesequipolocal>p.golesequipovisitante OR p.golesequipolocal<p.golesequipovisitante) "
                + "FROM partidos p, equipos e "
                + "WHERE e.id=p.idequipolocal OR e.id=p.equipovisitante ";
        
        //El COUNT lleva un GROUP BY
        
        return 0;
             
        }
        
        //Sacar todos los goles que ha sacado el equipo como local y luego otro lo mismo pero como visitante
     */
    public int calcularPartidosGanadosLocal() throws ClassNotFoundException, SQLException {
        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE golesequipolocal>golesequipovisitante "
                + "AND idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            return rs.getInt("numeropartidos");

        } else {
            return 0;
        }

    }

    public int calcularPartidosGanadosVisitante() throws ClassNotFoundException, SQLException {
        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE golesequipovisitante>golesequipolocal "
                + "AND idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            return rs.getInt("numeropartidos");

        } else {
            return 0;
        }
    }

    public int calcularPartidosGanadosTotal() throws ClassNotFoundException, SQLException {
        int partidosGanadosLocal = calcularPartidosGanadosLocal();
        int partidosGanadosVisitante = calcularPartidosGanadosVisitante();

        int partidosGanadosTotal = partidosGanadosLocal + partidosGanadosVisitante;

        return partidosGanadosTotal;

    }

    public int calcularPartidosEmpatadosLocal() throws ClassNotFoundException, SQLException {
        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE golesequipolocal=golesequipovisitante "
                + "AND idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            return rs.getInt("numeropartidos");

        } else {
            return 0;
        }

    }

    public int calcularPartidosEmpatadosVisitante() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE golesequipovisitante=golesequipolocal "
                + "AND idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            return rs.getInt("numeropartidos");

        } else {
            return 0;
        }
    }

    public int calcularPartidosEmpatadosTotal() throws ClassNotFoundException, SQLException {
        int partidosempatadoslocal = calcularPartidosEmpatadosLocal();
        int partidosempatadosvisitante = calcularPartidosEmpatadosVisitante();

        int partidosEmpatadosTotal = partidosempatadoslocal + partidosempatadosvisitante;

        return partidosEmpatadosTotal;

    }

    public int calcularPartidosPerdidosLocal() throws ClassNotFoundException, SQLException {
        //Primero cojo los datos
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE golesequipolocal<golesequipovisitante "
                + "AND idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            return rs.getInt("numeropartidos");

        } else {
            return 0;
        }
    }

    public int calcularPartidosPerdidosVisitante() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE golesequipovisitante<golesequipolocal "
                + "AND idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            return rs.getInt("numeropartidos");

        } else {
            return 0;
        }
    }

    public int calcularPartidosPerdidosTotal() throws ClassNotFoundException, SQLException {
        int partidosperdidoslocal = calcularPartidosEmpatadosLocal();
        int partidosperdidosvisitante = calcularPartidosEmpatadosVisitante();

        int partidosPerdidosTotal = partidosperdidoslocal + partidosperdidosvisitante;

        return partidosPerdidosTotal;

    }

    public double calcularPorcentajePartidosGanadosLocal() throws ClassNotFoundException, SQLException {
        int partidosganadoslocal = calcularPartidosGanadosLocal();

        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            int totalpartidoslocal = rs.getInt("numeropartidos");
            double porcentajePartidosGanadosLocal = partidosganadoslocal / totalpartidoslocal;
            return porcentajePartidosGanadosLocal;
        } else {
            return 0;
        }
    }

    public double calcularPorcentajePartidosGanadosVisitante() throws ClassNotFoundException, SQLException {
        int partidosganadosvisitante = calcularPartidosGanadosVisitante();

        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            int totalpartidosvisitante = rs.getInt("numeropartidos");
            double porcentajePartidosGanadosVisitante = partidosganadosvisitante / totalpartidosvisitante;
            return porcentajePartidosGanadosVisitante;
        } else {
            return 0;
        }

    }

    public double calcularPorcentajePartidosGanadosTotal() throws ClassNotFoundException, SQLException {
        double porcentajePartidosGanadosLocal = calcularPorcentajePartidosGanadosLocal();
        double porcentajePartidosGanadosVisitante = calcularPorcentajePartidosGanadosVisitante();

        double porcentajePartidosGanadosTotal = porcentajePartidosGanadosLocal + porcentajePartidosGanadosVisitante;
        return porcentajePartidosGanadosTotal;
    }

    public double calcularPorcentajePartidosEmpatadosLocal() throws ClassNotFoundException, SQLException {
        int partidosempatadoslocal = calcularPartidosEmpatadosLocal();

        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            int totalpartidoslocal = rs.getInt("numeropartidos");
            double porcentajePartidosEmpatadosLocal = partidosempatadoslocal / totalpartidoslocal;
            return porcentajePartidosEmpatadosLocal;
        } else {
            return 0;
        }

    }

    public double calcularPorcentajePartidosEmpatadosVisitante() throws ClassNotFoundException, SQLException {
        int partidosempatadosvisitante = calcularPartidosEmpatadosVisitante();

        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            int totalpartidosvisitante = rs.getInt("numeropartidos");
            double porcentajePartidosEmpatadosVisitante = partidosempatadosvisitante / totalpartidosvisitante;
            return porcentajePartidosEmpatadosVisitante;
        } else {
            return 0;
        }

    }

    public double calcularPorcentajePartidosEmpatadosTotal() throws ClassNotFoundException, SQLException {
        double porcentajePartidosEmpatadosLocal = calcularPorcentajePartidosEmpatadosLocal();
        double porcentajePartidosEmpatadosVisitante = calcularPorcentajePartidosEmpatadosVisitante();

        double porcentajePartidosEmpatadosTotal = porcentajePartidosEmpatadosLocal + porcentajePartidosEmpatadosVisitante;
        return porcentajePartidosEmpatadosTotal;
    }

    public double calcularPorcentajePartidosPerdidosLocal() throws ClassNotFoundException, SQLException {
        int partidosperdidoslocal = calcularPartidosPerdidosLocal();

        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            int totalpartidoslocal = rs.getInt("numeropartidos");
            double porcentajePartidosPerdidosLocal = partidosperdidoslocal / totalpartidoslocal;
            return porcentajePartidosPerdidosLocal;
        } else {
            return 0;
        }

    }

    public double calcularPorcentajePartidosPerdidosVisitante() throws ClassNotFoundException, SQLException {
        int partidosperdidosvisitante = calcularPartidosPerdidosVisitante();

        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL);

        if (rs.next()) {
            int totalpartidosvisitante = rs.getInt("numeropartidos");
            double porcentajePartidosPerdidosVisitante = partidosperdidosvisitante / totalpartidosvisitante;
            return porcentajePartidosPerdidosVisitante;
        } else {
            return 0;
        }

    }

    public double calcularPorcentajePartidosPerdidosTotal() throws ClassNotFoundException, SQLException {
        double porcentajePartidosPerdidosLocal = calcularPorcentajePartidosPerdidosLocal();
        double porcentajePartidosPerdidosVisitante = calcularPorcentajePartidosPerdidosVisitante();

        double porcentajePartidosPerdidosTotal = porcentajePartidosPerdidosLocal + porcentajePartidosPerdidosVisitante;
        return porcentajePartidosPerdidosTotal;
    }

    public double calcularMediaGolesLocal() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL1 = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL1);

        int totalpartidoslocal = 0;

        if (rs.next()) {
            totalpartidoslocal = rs.getInt("numeropartidos");

        }

        String SQL2 = "SELECT SUM(golesequipolocal) as golesequipolocal "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId();

        ResultSet rs2 = c.ejecutarConsulta(SQL2);

        int golesequipolocal=0;
        
        if (rs2.next()) {
            golesequipolocal = rs.getInt("golesequipolocal");

        } 

        double mediaGolesLocal = golesequipolocal / totalpartidoslocal;
        return mediaGolesLocal;
    }

    public double calcularMediaGolesVisitante() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL1 = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL1);

        int totalpartidosvisitante = 0;

        if (rs.next()) {
            totalpartidosvisitante = rs.getInt("numeropartidos");

        }

        String SQL2 = "SELECT SUM(golesequipovisitante)"
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId();

        ResultSet rs2 = c.ejecutarConsulta(SQL2);
        
        int golesequipovisitante=0;
        
        if (rs2.next()) {
            golesequipovisitante = rs.getInt("golesequipovisitante");

        } 

        double mediaGolesVisitante = golesequipovisitante / totalpartidosvisitante;
        return mediaGolesVisitante;
    }

    public double calcularMediaGolesTotal() throws ClassNotFoundException, SQLException {

        double mediagoleslocal = calcularMediaGolesLocal();
        double mediagolesvisitante = calcularMediaGolesVisitante();
        double mediagolestotal = (mediagoleslocal + mediagolesvisitante) / 2;

        return mediagolestotal;

    }

    public double calcularMediaGolesEnContraLocal() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL1 = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL1);

        int totalpartidoslocal = 0;

        if (rs.next()) {
            totalpartidoslocal = rs.getInt("numeropartidos");

        } 

        String SQL2 = "SELECT SUM(golesequipovisitante) as golesequipovisitante "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId();

        ResultSet rs2 = c.ejecutarConsulta(SQL2);
        
        int golesequipovisitante=0;
        
        if (rs2.next()) {
           golesequipovisitante = rs.getInt("golesequipovisitante");

        } 

        double mediaGolesEnContraLocal = golesequipovisitante / totalpartidoslocal;
        return mediaGolesEnContraLocal;
    }

    public double calcularMediaGolesEnContraVisitante() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL1 = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL1);

        int totalpartidosvisitante = 0;

        if (rs.next()) {
            totalpartidosvisitante = rs.getInt("numeropartidos");

        } 

        String SQL2 = "SELECT SUM(golesequipolocal) as golesequipolocal "
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId();

        ResultSet rs2 = c.ejecutarConsulta(SQL2);

        int golesequipolocal = 0;

        if (rs2.next()) {
            golesequipolocal = rs.getInt("golesequipolocal");
        }

        double mediaGolesEnContraVisitante = golesequipolocal / totalpartidosvisitante;
        return mediaGolesEnContraVisitante;
    }

    public double calcularMediaGolesEnContraTotal() throws ClassNotFoundException, SQLException {

        double mediaGolesEnContraLocal = calcularMediaGolesEnContraLocal();
        double mediaGolesEnContraVisitante = calcularMediaGolesEnContraVisitante();
        double mediaGolesEnContraTotal = (mediaGolesEnContraLocal + mediaGolesEnContraVisitante) / 2;

        return mediaGolesEnContraTotal;

    }

    public double calcularPorcentajePartidosAmbosEquiposMarcanLocal() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL1 = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL1);

        int totalpartidoslocal = 0;

        if (rs.next()) {
            totalpartidoslocal = rs.getInt("numeropartidos");
        }

        String SQL2 = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE golesequipolocal>=1 && golesequipovisitante>=1 "
                + "AND idequipolocal= " + equipo.getId();

        ResultSet rs2 = c.ejecutarConsulta(SQL2);

        int numeropartidoslocalambosmarcan = 0;

        if (rs2.next()) {
            numeropartidoslocalambosmarcan = rs.getInt("numeropartidos");
        }

        double PorcentajePartidosAmbosEquiposMarcanLocal = numeropartidoslocalambosmarcan / totalpartidoslocal;
        return PorcentajePartidosAmbosEquiposMarcanLocal;

    }

    public double calcularPorcentajePartidosAmbosEquiposMarcanVisitante() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL1 = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId();

        ResultSet rs = c.ejecutarConsulta(SQL1);

        int totalpartidosvisitante = 0;

        if (rs.next()) {
            totalpartidosvisitante = rs.getInt("numeropartidos");
        }

        String SQL2 = "SELECT COUNT(idpartido) as numeropartidos "
                + "FROM partidos "
                + "WHERE golesequipolocal>=1 && golesequipovisitante>=1 "
                + "AND idequipovisitante= " + equipo.getId();

        ResultSet rs2 = c.ejecutarConsulta(SQL2);

        int numeropartidosvisitanteambosmarcan = 0;

        if (rs2.next()) {
            numeropartidosvisitanteambosmarcan = rs.getInt("numeropartidos");

        }

        double PorcentajePartidosAmbosEquiposMarcanVisitante = numeropartidosvisitanteambosmarcan / totalpartidosvisitante;
        return PorcentajePartidosAmbosEquiposMarcanVisitante;

    }

    public double calcularPorcentajePartidosAmbosEquiposMarcanAbsoluto() throws ClassNotFoundException, SQLException {
        double PorcentajePartidosAmbosEquiposMarcanLocal = calcularPorcentajePartidosAmbosEquiposMarcanLocal();
        double PorcentajePartidosAmbosEquiposMarcanVisitante = calcularPorcentajePartidosAmbosEquiposMarcanVisitante();
        double PorcentajePartidosAmbosEquiposMarcanAbsoluto = PorcentajePartidosAmbosEquiposMarcanLocal + PorcentajePartidosAmbosEquiposMarcanVisitante;
        return PorcentajePartidosAmbosEquiposMarcanAbsoluto;
    }

    public int calcularRachaInvictoTotal() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL1 = "SELECT * "
                + "FROM partidos "
                + "WHERE idequipovisitante OR idequipolocal= " + equipo.getId()
                + "ORDER BY fechapartido desc";

        ResultSet rs = c.ejecutarConsulta(SQL1);

        int rachainvicto = 0;
        boolean finracha = false;

        while (rs.next() && !finracha) { //Mientras no acabe la racha sigue entrando

            int idequipolocal = rs.getInt("idpequipolocal");
            int idequipovisitante = rs.getInt("idpequipovisitante");
            int golesequipolocal = rs.getInt("golesequipolocal");
            int golesequipovisitante = rs.getInt("golesequipovisitante");

            if (idequipolocal == equipo.getId()) {

                if (golesequipolocal >= golesequipovisitante) {
                    rachainvicto++;
                } else {
                    finracha = true;
                }

            } else {
                if (golesequipovisitante >= golesequipolocal) {
                    rachainvicto++;
                } else {
                    finracha = true;
                }

            }

        }

        return rachainvicto;
    }

    public int calcularRachaInvictoLocal() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT * "
                + "FROM partidos "
                + "WHERE idequipolocal= " + equipo.getId()
                + "ORDER BY fechapartido desc";

        ResultSet rs = c.ejecutarConsulta(SQL);

        int rachainvicto = 0;
        boolean finracha = false;

        while (rs.next() && !finracha) { //Mientras no acabe la racha sigue entrando

            int idequipolocal = rs.getInt("idpequipolocal");
            int idequipovisitante = rs.getInt("idpequipovisitante");
            int golesequipolocal = rs.getInt("golesequipolocal");
            int golesequipovisitante = rs.getInt("golesequipovisitante");

            if (idequipolocal == equipo.getId()){

                if (golesequipolocal >= golesequipovisitante) {
                    rachainvicto++;
                } else {
                    finracha = true;
                }

            }

        }
        return rachainvicto;
    } 
    
public int calcularRachaInvictoVisitante() throws ClassNotFoundException, SQLException {
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT * "
                + "FROM partidos "
                + "WHERE idequipovisitante= " + equipo.getId()
                + "ORDER BY fechapartido desc";

        ResultSet rs = c.ejecutarConsulta(SQL);

        int rachainvicto = 0;
        boolean finracha = false;

        while (rs.next() && !finracha) { //Mientras no acabe la racha sigue entrando

            int idequipolocal = rs.getInt("idpequipolocal");
            int idequipovisitante = rs.getInt("idpequipovisitante");
            int golesequipolocal = rs.getInt("golesequipolocal");
            int golesequipovisitante = rs.getInt("golesequipovisitante");

            if (idequipovisitante == equipo.getId()){

                if (golesequipovisitante >= golesequipolocal) {
                    rachainvicto++;
                } else {
                    finracha = true;
                }

            }

        }
        return rachainvicto;
    }

    public int calcularPorcentajeResultadoMasRepetidos() throws ClassNotFoundException, SQLException{
        ConexionController c = ConexionController.getInstancia();

        String SQL = "SELECT CONCAT(golesequipolocal, ' ', golesequipovisitante), COUNT(*)\n" 
                     +"FROM `partidos` \n" 
                     +"WHERE idequipolocal= " + equipo.getId()
                     +"GROUP BY CONCAT(golesequipolocal, ' ', golesequipovisitante)\n"
                     +"UNION\n"
                     +"SELECT CONCAT(golesequipolocal, ' ', golesequipovisitante), COUNT(*)\n"
                     +"FROM `partidos` \n" 
                     +"WHERE idequipovisitante= " + + equipo.getId() 
                     +"GROUP BY CONCAT(golesequipolocal, ' ', golesequipovisitante)";
        
        ResultSet rs = c.ejecutarConsulta(SQL);

    ObservableList<Partido> obresultadosmasrepetidos = FXCollections.observableArrayList(); //¿?
    // No sé cómo devolver un conjunto de resultados más su frecuencia, ya que no son objetos partidos como tales
    
    //Código para que no de error
    
    int prueba=0;
    
    return prueba;
    
    }

}

