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
public class Rutas {
    
    private String Message;

    //private Vector VectorRutas;
    public Vector VectorRutas;


    public Rutas(String sJSON) {
        JSONStringToObject(sJSON);
    }

    public Rutas() {
    }


    public String GetMessage() {
        return this.Message;
    }

    public void SetMessage(String message) {
        this.Message = message;
    }


    public Vector GetVectorRutas() {
        return this.VectorRutas;
    }

    public void SetVectorRutas(Vector vectorrutas) {
        this.VectorRutas = vectorrutas;
    }


    public void JSONStringToObject(String sJSONin) {
        try {
            JSONObject main = new JSONObject(sJSONin);
            if (main != null) {
                JSONArray jaRutas = main.getJSONArray("rutas");
                Vector vRutas = new Vector();
                if (jaRutas != null) {
                    for (int i = 0; i < jaRutas.length(); i++) {
                        JSONObject joRuta = jaRutas.getJSONObject(i);

                        JSONArray jaExpendios = joRuta.getJSONArray("expendios");
                        Vector vExpendios = new Vector();
                        if (jaExpendios != null) {
                            //Vector vExpendios = new Vector();
                            for (int j = 0; j < jaExpendios.length(); j++) {
                                JSONObject joExpendio = jaExpendios.getJSONObject(j);

                                JSONArray jaFechas = joExpendio.getJSONArray("fechas");
                                Vector vFechas = new Vector();
                                if (jaFechas != null) {
                                    //Vector vFechas = new Vector();
                                    for (int k = 0; k < jaFechas.length(); k++) {
                                        JSONObject joFecha = jaFechas.getJSONObject(k);

                                        Fecha fecha = new Fecha();

                                        String sFecha = joFecha.getString("FECHA");
                                        fecha.SetFecha(sFecha);

                                        int iEntregados = 0;
                                        //String sEntregados = (String) joFecha.get("ENTREGADOS");
                                        iEntregados = joFecha.getInt("ENTREGADOS");
                                        //if (!(sEntregados == null) && !(sEntregados.equalsIgnoreCase(""))) {
                                            //iEntregados = Integer.parseInt(sEntregados);
                                        //}
                                        fecha.SetEntregados(iEntregados);

                                        int iDevueltos = 0;
                                        //String sDevueltos = (String) joFecha.get("DEVUELTOS");
                                        iDevueltos = joFecha.getInt("DEVUELTOS");
                                        //if (!(sDevueltos == null) && !(sDevueltos.equalsIgnoreCase(""))) {
                                            //iDevueltos = Integer.parseInt(sDevueltos);
                                        //}
                                        fecha.SetDevueltos(iDevueltos);

                                        int iFaltantes = 0;
                                        //String sDevueltos = (String) joFecha.get("DEVUELTOS");
                                        iFaltantes = joFecha.getInt("FALTANTES");
                                        //if (!(sDevueltos == null) && !(sDevueltos.equalsIgnoreCase(""))) {
                                            //iDevueltos = Integer.parseInt(sDevueltos);
                                        //}
                                        fecha.SetFaltantes(iFaltantes);

                                        int iSobrantes = 0;
                                        //String sDevueltos = (String) joFecha.get("DEVUELTOS");
                                        iSobrantes = joFecha.getInt("SOBRANTES");
                                        //if (!(sDevueltos == null) && !(sDevueltos.equalsIgnoreCase(""))) {
                                            //iDevueltos = Integer.parseInt(sDevueltos);
                                        //}
                                        fecha.SetSobrantes(iSobrantes);

                                        int iValorUnitario = 0;
                                        //String sFaltantes = (String) joFecha.get("VALOR UNITARIO");
                                        iValorUnitario = joFecha.getInt("VALOR UNITARIO");
                                        //if (!(sFaltantes == null) && !(sFaltantes.equalsIgnoreCase(""))) {
                                            //iFaltantes = Integer.parseInt(sFaltantes);
                                        //}
                                        fecha.SetValorUnitario(iValorUnitario);

                                        int iValor = 0;
                                        //String sValor = (String) joFecha.get("VALOR");
                                        iValor = joFecha.getInt("VALOR");
                                        //if (!(sValor == null) && !(sValor.equalsIgnoreCase(""))) {
                                            //iValor = Integer.parseInt(sValor);
                                        //}
                                        fecha.SetValor(iValor);

                                        fecha.SetVisitado(joFecha.getInt("VISITADO"));

                                        fecha.SetCobrado(joFecha.getInt("COBRADO"));

                                        vFechas.addElement(fecha);
                                    }
                                }

                                Expendio expendio = new Expendio();

                                int iId = joExpendio.getInt("ID");
                                expendio.SetId(iId);

                                String sNombre = joExpendio.getString("NOMBRE");
                                expendio.SetNombre(sNombre);

                                String sDireccion = joExpendio.getString("DIRECCION");
                                expendio.SetDireccion(sDireccion);

                                String sCodigoCliente = joExpendio.getString("CODIGOCLIENTE");
                                expendio.SetCodigoCliente(sCodigoCliente);

                                int iCompendio = joExpendio.getInt("COMPENDIO");
                                expendio.SetCompendio(iCompendio);

                                expendio.SetVisitado(joExpendio.getInt("VISITADO"));

                                expendio.SetVectorFechas(vFechas);

                                vExpendios.addElement(expendio);
                            }
                        }

                        Ruta ruta = new Ruta();

                        String sCobrador = joRuta.getString("COBRADOR");
                        ruta.SetCobrador(sCobrador);

                        String sZona = joRuta.getString("ZONA");
                        ruta.SetZona(sZona);

                        String sFecha = joRuta.getString("FECHA");
                        ruta.SetFecha(sFecha);

                        String sProducto = joRuta.getString("PRODUCTO");
                        ruta.SetProducto(sProducto);

                        ruta.SetVectorExpendios(vExpendios);

                        vRutas.addElement(ruta);
                    }
                }

                SetVectorRutas(vRutas);
            }

            SetMessage("[*] JSONStringToObject() executed.");
        } catch (JSONException jsonex) {
            SetMessage(jsonex.toString());
            jsonex.printStackTrace();
        } catch (Exception ex) {
            SetMessage(ex.toString());
            ex.printStackTrace();
        }
    }


    public String JSONObjectToString() {
        JSONObject main = new JSONObject();

        try {

            JSONArray jaRutas = new JSONArray();
            for (int i = 0; i < GetVectorRutas().size(); i++) {
                Ruta ruta = (Ruta) GetVectorRutas().elementAt(i);
                JSONObject joRuta = new JSONObject();
                joRuta.put("COBRADOR", ruta.GetCobrador());
                joRuta.put("ZONA", ruta.GetZona());
                joRuta.put("FECHA", ruta.GetFecha());
                joRuta.put("PRODUCTO", ruta.GetProducto());

                JSONArray jaExpendios = new JSONArray();
                for (int j = 0; j < ruta.GetVectorExpendios().size(); j++) {
                    Expendio expendio = (Expendio) ruta.GetVectorExpendios().elementAt(j);
                    JSONObject joExpendio = new JSONObject();
                    joExpendio.put("ID", expendio.GetId());
                    joExpendio.put("NOMBRE", expendio.GetNombre());
                    joExpendio.put("DIRECCION", expendio.GetDireccion());
                    joExpendio.put("CODIGOCLIENTE", expendio.GetCodigoCliente());
                    joExpendio.put("COMPENDIO", expendio.GetCompendio());
                    joExpendio.put("VISITADO", expendio.GetVisitado());

                    JSONArray jaFechas = new JSONArray();
                    for (int k = 0; k < expendio.GetVectorFechas().size(); k++) {
                        Fecha fecha = (Fecha) expendio.GetVectorFechas().elementAt(k);
                        JSONObject joFecha = new JSONObject();
                        joFecha.put("FECHA", fecha.GetFecha());
                        joFecha.put("ENTREGADOS", fecha.GetEntregados());
                        joFecha.put("DEVUELTOS", fecha.GetDevueltos());
                        joFecha.put("FALTANTES", fecha.GetFaltantes());
                        joFecha.put("SOBRANTES", fecha.GetSobrantes());
                        joFecha.put("VALOR UNITARIO", fecha.GetValorUnitario());
                        joFecha.put("VALOR", fecha.GetValor());
                        joFecha.put("VISITADO", fecha.GetVisitado());
                        joFecha.put("COBRADO", fecha.GetCobrado());

                        jaFechas.put(joFecha);
                    }

                    joExpendio.put("fechas", jaFechas);
                    jaExpendios.put(joExpendio);
                }

                joRuta.put("expendios", jaExpendios);
                jaRutas.put(joRuta);
            }

            main.put("rutas", jaRutas);

            //System.out.println(main.toString());
        } catch (JSONException jsonex) {
            SetMessage(jsonex.toString());
            jsonex.printStackTrace();
        } catch (Exception ex) {
            SetMessage(ex.toString());
            ex.printStackTrace();
        }

        return main.toString();
    }

}
