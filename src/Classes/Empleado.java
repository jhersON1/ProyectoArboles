/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author neON
 */
public class Empleado implements Comparable<Empleado>, Serializable {

    private static short cant = 0;
    public  int CI;                     //Comparacion valida 0 por defecto para ingresar al arbol  
    private final String ciExpedicion;
    private int numeroTelefonico;
    private String nombre;                   //Comparacion valida para ordenar tipo 1
    private String apellidoPaterno;             //Comparacion valida para ordenar tipo 2
    private String apellidoMaterno;
    private static int idInicio = 12102000;
    private final int id;                     //Comparacion valida para ordenar tipo 3
    private String correo;
    private final String paisDeOrigen;
    private String sexo;
    private int sueldoPorHora;
    private String cargo;                   //Comparacion valida para ordenar tipo 4
    private String estadoCivil;
    private String direccion;
    private final Date fechaDeNacimiento;
    private final Date fechaDeEntrada;
    private ImageIcon fotografiaPersonal;

    private int edadCalculada() {
        Date fechaActual = new Date();
        int año = fechaActual.getYear() - fechaDeNacimiento.getYear();
        int mes = fechaActual.getMonth() - fechaDeNacimiento.getMonth() > 0 ? 1 : 0;
        int dia = mes != 0 ? (fechaActual.getDay() - fechaDeNacimiento.getDay() >= 0 ? 1 : 0) : 0;
        return año + dia;
    }

    public Empleado(int CI) {
        this(CI, "SC", 79998375, "Paul", "Rodriguez", "Guzman", "rodriguez98@gmail.com", "Bolivia", "Masculino",
                15, "Administrador de Sistemas", "Soltero", "C/Las Palmeras", new Date(100, 6, 8), new Date(), null);
    }

    public Empleado(int CI, String ciExp) {
        this(CI, ciExp, 79998375, "Paul", "Rodriguez", "Guzman", "rodriguez98@gmail.com", "Bolivia", "Masculino",
                15, "Administrador de Sistemas", "Soltero", "C/Las Palmeras", new Date(100, 6, 8), new Date(), null);
    }

    public Empleado(int CI, String ciExpedicion, int numeroTelefonico, String nombre, String apellPaterno,
            String apellMaterno, String correo, String paisOrigen, String sexo, int sueldo,
            String cargo, String estadoCivil, String direccion, Date fechaNac, Date fechaEntrada,
            ImageIcon fotografiaPersonal) {
        this.CI = CI;
        this.ciExpedicion = ciExpedicion;
        this.numeroTelefonico = numeroTelefonico;
        this.nombre = nombre;
        this.apellidoPaterno = apellPaterno;
        this.apellidoMaterno = apellMaterno;
        this.correo = correo;
        this.paisDeOrigen = paisOrigen;
        this.sexo = sexo;
        this.sueldoPorHora = sueldo;
        this.cargo = cargo;
        this.estadoCivil = estadoCivil;
        this.direccion = direccion;
        this.fechaDeNacimiento = fechaNac;
        this.fechaDeEntrada = fechaEntrada;
        this.fotografiaPersonal = fotografiaPersonal;
        this.id = Empleado.idInicio + Empleado.cant;
        Empleado.cant++;
    }

    @Override
    public int compareTo(Empleado otro) {
        return this.CI - otro.CI;
    }

    public int ordenacionSegun(Empleado otro, int tipo) {
        int retorno = 0;
        switch (tipo) {
            case 0:
                retorno = this.CI - otro.CI;
                break;
            case 1:
                retorno = this.nombre.toUpperCase().compareTo(otro.nombre.toUpperCase());
                break;
            case 2:
                retorno = this.apellidoPaterno.toUpperCase().compareTo(otro.apellidoPaterno.toUpperCase());
                break;
            case 3:
                retorno = this.id - otro.id;
                break;
            case 4:
                retorno = this.cargo.compareTo(otro.cargo);
                break;
        }
        return retorno;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
    }

    public int getSueldoMensual() {
        return this.sueldoPorHora * 8 * 7 * 4;
    }

    public int getCI() {
        return CI;
    }

    public String getCiExpedicion() {
        return ciExpedicion;
    }

    public int getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellPaterno() {
        return apellidoPaterno;
    }

    public String getApellMaterno() {
        return apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPaisOrigen() {
        return paisDeOrigen;
    }

    public String getSexo() {
        return sexo;
    }

    public int getSueldoPorHora() {
        return sueldoPorHora;
    }

    public String getCargo() {
        return cargo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaNac() {
        return fechaDeNacimiento;
    }

    public int getEdad() {
        return this.edadCalculada();
    }

    public Date getFechaEntrada() {
        return fechaDeEntrada;
    }

    public ImageIcon getFotografiaPersonal() {
        return fotografiaPersonal;
    }

    public void setNumeroTelefonico(int numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellPaterno(String apellPaterno) {
        this.apellidoPaterno = apellPaterno;
    }

    public void setApellMaterno(String apellMaterno) {
        this.apellidoMaterno = apellMaterno;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setSueldoPorHora(int sueldoPorHora) {
        this.sueldoPorHora = sueldoPorHora;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFotografiaPersonal(ImageIcon fotografiaPersonal) {
        this.fotografiaPersonal = fotografiaPersonal;
    }

}
