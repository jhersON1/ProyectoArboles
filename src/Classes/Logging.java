/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Arrays;

/**
 *
 * @author neON
 */
public class Logging implements Comparable<Logging>, java.io.Serializable {

    private final int ciDelUsuario;
    private String usuario;
    private char[] contraseña;
    private boolean esAdmin;
    private boolean comparacionCi;

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public Logging(String usuario, char[] contraseña, int ci, boolean esAdmin) {
        this.ciDelUsuario = ci;
        this.esAdmin = esAdmin;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.comparacionCi = false;
    }

    private boolean verificarContraseñaValida(char[] pass) {
        if (pass.length < 4 || pass.length > 16) {
            return false;
        }
        boolean mayuscula = false;
        boolean minuscula = false;
        boolean numero = false;
        for (int i = 0; i < pass.length; i++) {
            if (pass[i] == 0x20) {
                return false;
            }
            if (!numero && pass[i] >= 0x30 && pass[i] <= 0x39) {
                numero = true;
            } else if (!mayuscula && pass[i] >= 0x41 && pass[i] <= 0x5A) {
                mayuscula = true;
            } else if (!minuscula && pass[i] >= 0x61 && pass[i] <= 0x7A) {
                minuscula = true;
            }
        }
        return mayuscula && minuscula && numero;
    }

    public String getUsuario() {
        return usuario;
    }

    public char[] getContraseña() {
        return contraseña;
    }

    public boolean siEsAdmin() {
        return esAdmin;
    }

    public int getCiDelUsuario() {
        return ciDelUsuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(char[] contraseña) throws ExceptionContraseñaNoValida {
        if (this.verificarContraseñaValida(contraseña)) {
            this.contraseña = contraseña;
        } else {
            throw new ExceptionContraseñaNoValida("Contraseña Invalida");
        }
    }

    public boolean logging(String usuario, char[] contraseña) {
        return this.usuario.toUpperCase().equals(usuario.toUpperCase()) && Arrays.equals(this.contraseña, contraseña);
    }

    @Override
    public int compareTo(Logging otro) {
        if (this.comparacionCi) {
            return this.ciDelUsuario - otro.ciDelUsuario;
        } else {
            return this.usuario.toUpperCase().compareTo(otro.usuario.toUpperCase());
        }
        //return this.ciDelUsuario - otro.ciDelUsuario;
    }

    public boolean isComparacionCi() {
        return comparacionCi;
    }

    public void setComparacionCi(boolean comparacionCi) {
        this.comparacionCi = comparacionCi;
    }

}
