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


    /*private void JSONStringToObject(String sJSONin) {
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
                                        String sEntregados = (String) joFecha.get("ENTREGADOS");
                                        if (!(sEntregados == null) && !(sEntregados.equalsIgnoreCase(""))) {
                                            iEntregados = Integer.parseInt(sEntregados);
                                        }
                                        fecha.SetEntregados(iEntregados);

                                        int iDevueltos = 0;
                                        String sDevueltos = (String) joFecha.get("DEVUELTOS");
                                        if (!(sDevueltos == null) && !(sDevueltos.equalsIgnoreCase(""))) {
                                            iDevueltos = Integer.parseInt(sDevueltos);
                                        }
                                        fecha.SetDevueltos(iDevueltos);

                                        int iFaltantes = 0;
                                        String sFaltantes = (String) joFecha.get("VALOR UNITARIO");
                                        if (!(sFaltantes == null) && !(sFaltantes.equalsIgnoreCase(""))) {
                                            iFaltantes = Integer.parseInt(sFaltantes);
                                        }
                                        fecha.SetValorUnitario(iFaltantes);

                                        int iValor = 0;
                                        String sValor = (String) joFecha.get("VALOR");
                                        if (!(sValor == null) && !(sValor.equalsIgnoreCase(""))) {
                                            iValor = Integer.parseInt(sValor);
                                        }
                                        fecha.SetValor(iValor);

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

                                expendio.SetVectorFechas(vFechas);

                                vExpendios.addElement(expendio);
                            }
                        }

                        String sCobrador = joRuta.getString("COBRADOR");
                        SetCobrador(sCobrador);

                        String sZona = joRuta.getString("ZONA");
                        SetZona(sZona);

                        String sFecha = joRuta.getString("FECHA");
                        SetFecha(sFecha);

                        String sProducto = joRuta.getString("PRODUCTO");
                        SetProducto(sProducto);

                        SetVectorExpendios(vExpendios);
                    }
                }

                String sCobrador = (String) main.get("COBRADOR");
                SetCobrador(sCobrador);
                String sZona = (String) main.get("ZONA");
                SetZona(sZona);
                String sFecha = (String) main.get("FECHA");
                SetFecha(sFecha);

                SetVectorExpendios(vExpendios);
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
            main.put("COBRADOR", GetCobrador());
            main.put("ZONA", GetZona());
            main.put("FECHA", GetFecha());

            JSONArray jaExpendios = new JSONArray();
            for (int i = 0; i < GetVectorExpendios().size(); i++) {
                Expendio expendio = (Expendio) GetVectorExpendios().elementAt(i);
                JSONObject joExpendio = new JSONObject();
                joExpendio.put("ID", expendio.GetId());
                joExpendio.put("NOMBRE", expendio.GetNombre());

                JSONArray jaProductos = new JSONArray();
                for (int j = 0; j < expendio.GetVectorProductos().size(); j++) {
                    Producto producto = (Producto) expendio.GetVectorProductos().elementAt(j);
                    JSONObject joProducto = new JSONObject();
                    joProducto.put("NOMBRE", producto.GetNombre());
                    joProducto.put("VALOR UNITARIO", producto.GetValorUnitario());
                    joProducto.put("CONSOLIDADO", producto.GetConsolidado());

                    JSONArray jaFechas = new JSONArray();
                    for (int k = 0; k < producto.GetVectorFechas().size(); k++) {
                        Fecha fecha = (Fecha) producto.GetVectorFechas().elementAt(k);
                        JSONObject joFecha = new JSONObject();
                        joFecha.put("FECHA", fecha.GetFecha());
                        joFecha.put("ENTREGADOS", fecha.GetEntregados());
                        joFecha.put("DEVUELTOS", fecha.GetDevueltos());
                        joFecha.put("FALTANTES", fecha.GetFaltantes());
                        joFecha.put("VALOR", fecha.GetValor());

                        jaFechas.put(joFecha);
                    }

                    joProducto.put("fechas", jaFechas);
                    jaProductos.put(joProducto);
                }

                joExpendio.put("productos", jaProductos);
                jaExpendios.put(joExpendio);
            }

            main.put("expendios", jaExpendios);

            System.out.println(main.toString());
        } catch (JSONException jsonex) {
            SetMessage(jsonex.toString());
            jsonex.printStackTrace();
        } catch (Exception ex) {
            SetMessage(ex.toString());
            ex.printStackTrace();
        }

        return main.toString();
    }*/

}
