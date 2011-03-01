/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cobrador;

import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author Admin
 */
public class Ruta {

    private String Cobrador;
    private String Zona;
    private String Fecha;
    private String Producto;
    private Vector VectorExpendios;
    private String Message;


    public Ruta() {
        //JSONStringToObject(sJSON);
    }


    public String GetCobrador() {
        return this.Cobrador;
    }

    public void SetCobrador(String cobrador) {
        this.Cobrador = cobrador;
    }


    public String GetZona() {
        return this.Zona;
    }

    public void SetZona(String zona) {
        this.Zona = zona;
    }


    public String GetFecha() {
        return this.Fecha;
    }

    public void SetFecha(String fecha) {
        this.Fecha = fecha;
    }


    public String GetProducto() {
        return this.Producto;
    }

    public void SetProducto(String producto) {
        this.Producto = producto;
    }


    public Vector GetVectorExpendios() {
        return this.VectorExpendios;
    }

    public void SetVectorExpendios(Vector expendios) {
        this.VectorExpendios = expendios;
    }


    public String GetMessage() {
        return this.Message;
    }

    public void SetMessage(String message) {
        this.Message = message;
    }

}
