/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cobrador;

import java.io.IOException;
import java.util.Enumeration;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
//import org.netbeans.microedition.lcdui.pda.FileBrowser;

/**
 * @author Admin
 */
public class FlowMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    private String Message;

    private String sFileApp = null;

    private ConnectionHandler connectionhandler;
    private Rutas RUTAS;
    private Ruta CurrRuta;

    private Enumeration enumeration;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private List ListProducto;
    private List ListExpendios;
    private List ListFechas;
    private Form FormFecha;
    private TextField textFieldEntDev;
    private TextField textFieldFaltantes;
    private StringItem stringItemValor;
    private ChoiceGroup choiceGroupCheck;
    private TextField textFieldSobrantes;
    private Alert Alert;
    private List ListStart;
    private TextBox textBox;
    private Form FormCompendio;
    private StringItem stringItemCompendio;
    private Form FormStartGauge;
    private Gauge gauge;
    private FileBrowser fileBrowser;
    private Command exitCommand;
    private Command backCommand;
    private Command avanzarCommand;
    private Command saveCommand;
    private Command calcCommand;
    private Command okCommand;
    private Command startCommand;
    private Ticker ticker1;
    //</editor-fold>//GEN-END:|fields|0|
    private Expendio CurrExpendio;
    private Fecha CurrFecha;
    //</editor-fold>

    /**
     * The FlowMIDlet constructor.
     */
    public FlowMIDlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
        connectionhandler = new ConnectionHandler();
        try {
            RUTAS = new Rutas(connectionhandler.ReadLocalFile());
            SetMessage("[*] Ruta created.");
        }
        catch (IOException ioex) {
            SetMessage(ioex.toString());
            ioex.printStackTrace();
        }
        catch (Exception ex) {
            SetMessage(ex.toString());
            ex.printStackTrace();
        }
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getFileBrowser());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == FormCompendio) {//GEN-BEGIN:|7-commandAction|1|59-preAction
            if (command == okCommand) {//GEN-END:|7-commandAction|1|59-preAction
                // write pre-action user code here
                switchDisplayable(null, getListExpendios());//GEN-LINE:|7-commandAction|2|59-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|37-preAction
        } else if (displayable == FormFecha) {
            if (command == backCommand) {//GEN-END:|7-commandAction|3|37-preAction
                // write pre-action user code here
                String sFormFechaEntDev = textFieldEntDev.getString();
                int iDevueltos = 0;
                if (sFormFechaEntDev!=null && !sFormFechaEntDev.equalsIgnoreCase("")) {
                    iDevueltos = Integer.parseInt(sFormFechaEntDev);
                }
                CurrFecha.SetDevueltos(iDevueltos);

                String sFormFechaFaltantes = textFieldFaltantes.getString();
                int iFaltantes = 0;
                if (sFormFechaFaltantes!=null && !sFormFechaFaltantes.equalsIgnoreCase("")) {
                    iFaltantes = Integer.parseInt(sFormFechaFaltantes);
                }
                CurrFecha.SetFaltantes(iFaltantes);

                //String sFormFechaVUValor = textFieldVUValor.getString();
                /*String sFormFechaVUValor = stringItemValor.getText();
                int iValor = 0;
                if (sFormFechaVUValor!=null && !sFormFechaVUValor.equalsIgnoreCase("")) {
                    iValor = Integer.parseInt(sFormFechaVUValor);
                }
                CurrFecha.SetDevueltos(iValor);*/
                CurrFecha.SetValor(CalcFechaValor());

                switchDisplayable(null, getListFechas());//GEN-LINE:|7-commandAction|4|37-postAction
                // write post-action user code here
            } else if (command == calcCommand) {//GEN-LINE:|7-commandAction|5|54-preAction
                // write pre-action user code here
                CurrFecha.SetValor(CalcFechaValor());
                int iFechaValor = CurrFecha.GetValor();
                String sFechaValor = Integer.toString(iFechaValor);
                stringItemValor.setText(sFechaValor);
//GEN-LINE:|7-commandAction|6|54-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|65-preAction
        } else if (displayable == FormStartGauge) {
            if (command == startCommand) {//GEN-END:|7-commandAction|7|65-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|8|65-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|23-preAction
        } else if (displayable == ListExpendios) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|9|23-preAction
                // write pre-action user code here
                ListExpendiosAction();//GEN-LINE:|7-commandAction|10|23-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|11|26-preAction
                // write pre-action user code here
                switchDisplayable(null, getListProducto());//GEN-LINE:|7-commandAction|12|26-postAction
                // write post-action user code here
            } else if (command == saveCommand) {//GEN-LINE:|7-commandAction|13|45-preAction
                // write pre-action user code here
                WriteChanges();
//GEN-LINE:|7-commandAction|14|45-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|29-preAction
        } else if (displayable == ListFechas) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|15|29-preAction
                // write pre-action user code here
                ListFechasAction();//GEN-LINE:|7-commandAction|16|29-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|17|34-preAction
                // write pre-action user code here
                int iCompendio = 0;
                enumeration = CurrExpendio.GetVectorFechas().elements();
                while (enumeration.hasMoreElements()) {
                    Fecha fecha = (Fecha) enumeration.nextElement();
                    iCompendio = iCompendio+fecha.GetValor();
                }
                CurrExpendio.SetCompendio(iCompendio);
                String sCompendio = Integer.toString(iCompendio);
                getAlert().setTitle("COMPENDIO");
                getAlert().setString("Valor total expendio: "+sCompendio);
                getAlert().setType(AlertType.CONFIRMATION);
                switchDisplayable(null, getAlert());

                switchDisplayable(null, getListExpendios());//GEN-LINE:|7-commandAction|18|34-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|19|16-preAction
        } else if (displayable == ListProducto) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|19|16-preAction
                // write pre-action user code here
                ListProductoAction();//GEN-LINE:|7-commandAction|20|16-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|21|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|22|19-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|23|50-preAction
        } else if (displayable == ListStart) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|23|50-preAction
                // write pre-action user code here
                ListStartAction();//GEN-LINE:|7-commandAction|24|50-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|25|72-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|26|72-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|27|68-preAction
        } else if (displayable == fileBrowser) {
            if (command == FileBrowser.SELECT_FILE_COMMAND) {//GEN-END:|7-commandAction|27|68-preAction
                // write pre-action user code here
                sFileApp = fileBrowser.getSelectedFileURL();
                switchDisplayable(null, getFormStartGauge());
                (new Thread() {

                    public void run() {
                        FetchLocalFile(sFileApp);
                    }
                }).start();
//GEN-LINE:|7-commandAction|28|68-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|29|71-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|30|71-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|31|7-postCommandAction
        }//GEN-END:|7-commandAction|31|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|32|
    //</editor-fold>//GEN-END:|7-commandAction|32|


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ListProducto ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of ListProducto component.
     * @return the initialized component instance
     */
    public List getListProducto() {
        if (ListProducto == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            ListProducto = new List("Escoja Producto", Choice.IMPLICIT);//GEN-BEGIN:|14-getter|1|14-postInit
            ListProducto.addCommand(getExitCommand());
            ListProducto.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
            try {
                enumeration = RUTAS.GetVectorRutas().elements();
                Image image = null;
                while (enumeration.hasMoreElements()) {
                    Ruta ruta = (Ruta) enumeration.nextElement();
                    String sProducto = ruta.GetProducto();
                    if (sProducto.equalsIgnoreCase("La Patria")) {
                        image = Image.createImage("lapatria.png");

                    }
                    if (sProducto.equalsIgnoreCase("Nuevo Estadio")) {
                        image = Image.createImage("nuevoestadio.png");

                    }
                    if (sProducto.equalsIgnoreCase("QHubo")) {
                        image = Image.createImage("qhubo.png");

                    }
                    ListProducto.append(sProducto, image);
                }
            } catch (IOException ioex) {
                SetMessage(ioex.toString());
                ioex.printStackTrace();
            } catch (Exception ex) {
                SetMessage(ex.toString());
                ex.printStackTrace();
            }
        }//GEN-BEGIN:|14-getter|2|
        return ListProducto;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: ListProductoAction ">//GEN-BEGIN:|14-action|0|14-preAction
    /**
     * Performs an action assigned to the selected list element in the ListProducto component.
     */
    public void ListProductoAction() {//GEN-END:|14-action|0|14-preAction
        // enter pre-action user code here
        //String __selectedString = ListProducto.getString(ListProducto.getSelectedIndex());
        String __selectedString = getListProducto().getString(getListProducto().getSelectedIndex());//GEN-LINE:|14-action|1|14-postAction
        // enter post-action user code here
        enumeration = RUTAS.GetVectorRutas().elements();
        while (enumeration.hasMoreElements()) {
            Ruta ruta_current = (Ruta) enumeration.nextElement();
            if (ruta_current.GetProducto().equalsIgnoreCase(__selectedString)) {
                CurrRuta = ruta_current;
                break;
            }
        }

        switchDisplayable(null, getListExpendios());
    }//GEN-BEGIN:|14-action|2|
    //</editor-fold>//GEN-END:|14-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Salir", Command.EXIT, 1);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ListExpendios ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of ListExpendios component.
     * @return the initialized component instance
     */
    public List getListExpendios() {
        if (ListExpendios == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            ListExpendios = new List("Escoja Expendio", Choice.IMPLICIT);//GEN-BEGIN:|22-getter|1|22-postInit
            ListExpendios.addCommand(getBackCommand());
            ListExpendios.addCommand(getSaveCommand());
            ListExpendios.setCommandListener(this);//GEN-END:|22-getter|1|22-postInit
            // write post-init user code here
            enumeration = CurrRuta.GetVectorExpendios().elements();
            while (enumeration.hasMoreElements()) {
                Expendio expendio = (Expendio) enumeration.nextElement();
                ListExpendios.append(expendio.GetNombre(), null);
            }
        }//GEN-BEGIN:|22-getter|2|
        return ListExpendios;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: ListExpendiosAction ">//GEN-BEGIN:|22-action|0|22-preAction
    /**
     * Performs an action assigned to the selected list element in the ListExpendios component.
     */
    public void ListExpendiosAction() {//GEN-END:|22-action|0|22-preAction
        // enter pre-action user code here
        //String __selectedString = ListExpendios.getString(ListExpendios.getSelectedIndex());
        String __selectedString = getListExpendios().getString(getListExpendios().getSelectedIndex());//GEN-LINE:|22-action|1|22-postAction
        // enter post-action user code here
        enumeration = CurrRuta.GetVectorExpendios().elements();
        while (enumeration.hasMoreElements()) {
            Expendio expe_current = (Expendio) enumeration.nextElement();
            if (expe_current.GetNombre().equalsIgnoreCase(__selectedString)) {
                CurrExpendio = expe_current;
                break;
            }
        }

        switchDisplayable(null, getListFechas());
    }//GEN-BEGIN:|22-action|2|
    //</editor-fold>//GEN-END:|22-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            backCommand = new Command("Avanzar", "Avanzar", Command.BACK, 1);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ListFechas ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of ListFechas component.
     * @return the initialized component instance
     */
    public List getListFechas() {
        if (ListFechas == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            ListFechas = new List("Escoja Fecha", Choice.IMPLICIT);//GEN-BEGIN:|28-getter|1|28-postInit
            ListFechas.addCommand(getBackCommand());
            ListFechas.setCommandListener(this);//GEN-END:|28-getter|1|28-postInit
            // write post-init user code here
            enumeration = CurrExpendio.GetVectorFechas().elements();
            while (enumeration.hasMoreElements()) {
                Fecha fecha = (Fecha) enumeration.nextElement();
                ListExpendios.append(fecha.GetFecha(), null);
            }
        }//GEN-BEGIN:|28-getter|2|
        return ListFechas;
    }
    //</editor-fold>//GEN-END:|28-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: ListFechasAction ">//GEN-BEGIN:|28-action|0|28-preAction
    /**
     * Performs an action assigned to the selected list element in the ListFechas component.
     */
    public void ListFechasAction() {//GEN-END:|28-action|0|28-preAction
        // enter pre-action user code here
        //String __selectedString = ListFechas.getString(ListFechas.getSelectedIndex());
        String __selectedString = getListFechas().getString(getListFechas().getSelectedIndex());//GEN-LINE:|28-action|1|28-postAction
        // enter post-action user code here
        enumeration = CurrExpendio.GetVectorFechas().elements();
        while (enumeration.hasMoreElements()) {
            Fecha fecha_current = (Fecha) enumeration.nextElement();
            if (fecha_current.GetFecha().equalsIgnoreCase(__selectedString)) {
                CurrFecha = fecha_current;
                break;
            }
        }

        switchDisplayable(null, getFormFecha());
    }//GEN-BEGIN:|28-action|2|
    //</editor-fold>//GEN-END:|28-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FormFecha ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initiliazed instance of FormFecha component.
     * @return the initialized component instance
     */
    public Form getFormFecha() {
        if (FormFecha == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            FormFecha = new Form(CurrFecha.GetFecha(), new Item[] { getTextFieldEntDev(), getTextFieldFaltantes(), getTextFieldSobrantes(), getStringItemValor(), getChoiceGroupCheck() });//GEN-BEGIN:|36-getter|1|36-postInit
            FormFecha.addCommand(getBackCommand());
            FormFecha.addCommand(getCalcCommand());
            FormFecha.setCommandListener(this);//GEN-END:|36-getter|1|36-postInit
            // write post-init user code here
            int iFechaDevueltos = CurrFecha.GetDevueltos();
            if (iFechaDevueltos == 0) {
                textFieldEntDev.setString("");
            }
            else {
                String sFechaDevueltos = Integer.toString(iFechaDevueltos);
                textFieldEntDev.setString(sFechaDevueltos);
            }

            int iFechaFaltantes = CurrFecha.GetFaltantes();
            if (iFechaFaltantes == 0) {
                textFieldEntDev.setString("");
            }
            else {
                String sFechaFaltantes = Integer.toString(iFechaFaltantes);
                textFieldEntDev.setString(sFechaFaltantes);
            }

            int iFechaValor = CurrFecha.GetValor();
            if (iFechaValor == 0) {
                //textFieldVUValor.setString("");
                stringItemValor.setText("");
            }
            else {
                String sFechaValor = Integer.toString(iFechaValor);
                textFieldEntDev.setString(sFechaValor);
            }
        }//GEN-BEGIN:|36-getter|2|
        return FormFecha;
    }
    //</editor-fold>//GEN-END:|36-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldEntDev ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of textFieldEntDev component.
     * @return the initialized component instance
     */
    public TextField getTextFieldEntDev() {
        if (textFieldEntDev == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            textFieldEntDev = new TextField("Ent.: "+CurrFecha.GetEntregados()+" / Dev.:", "", 3, TextField.NUMERIC);//GEN-LINE:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return textFieldEntDev;
    }
    //</editor-fold>//GEN-END:|40-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: avanzarCommand ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of avanzarCommand component.
     * @return the initialized component instance
     */
    public Command getAvanzarCommand() {
        if (avanzarCommand == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            avanzarCommand = new Command("Avanzar", Command.SCREEN, 1);//GEN-LINE:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return avanzarCommand;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: saveCommand ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initiliazed instance of saveCommand component.
     * @return the initialized component instance
     */
    public Command getSaveCommand() {
        if (saveCommand == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            saveCommand = new Command("Guardar", Command.SCREEN, 2);//GEN-LINE:|44-getter|1|44-postInit
            // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return saveCommand;
    }
    //</editor-fold>//GEN-END:|44-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Alert ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initiliazed instance of Alert component.
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (Alert == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            Alert = new Alert("", "", null, null);//GEN-BEGIN:|46-getter|1|46-postInit
            Alert.setTimeout(Alert.FOREVER);//GEN-END:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return Alert;
    }
    //</editor-fold>//GEN-END:|46-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldFaltantes ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of textFieldFaltantes component.
     * @return the initialized component instance
     */
    public TextField getTextFieldFaltantes() {
        if (textFieldFaltantes == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            textFieldFaltantes = new TextField("Faltantes:", "", 3, TextField.NUMERIC);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return textFieldFaltantes;
    }
    //</editor-fold>//GEN-END:|47-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textBox ">//GEN-BEGIN:|48-getter|0|48-preInit
    /**
     * Returns an initiliazed instance of textBox component.
     * @return the initialized component instance
     */
    public TextBox getTextBox() {
        if (textBox == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            textBox = new TextBox("textBox", null, 100, TextField.ANY);//GEN-LINE:|48-getter|1|48-postInit
            // write post-init user code here
        }//GEN-BEGIN:|48-getter|2|
        return textBox;
    }
    //</editor-fold>//GEN-END:|48-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ListStart ">//GEN-BEGIN:|49-getter|0|49-preInit
    /**
     * Returns an initiliazed instance of ListStart component.
     * @return the initialized component instance
     */
    public List getListStart() {
        if (ListStart == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            ListStart = new List("COBRADOR", Choice.IMPLICIT);//GEN-BEGIN:|49-getter|1|49-postInit
            ListStart.append("Seleccionar archivo de datos", null);
            ListStart.addCommand(getExitCommand());
            ListStart.setCommandListener(this);
            ListStart.setSelectedFlags(new boolean[] { false });//GEN-END:|49-getter|1|49-postInit
            // write post-init user code here
        }//GEN-BEGIN:|49-getter|2|
        return ListStart;
    }
    //</editor-fold>//GEN-END:|49-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: ListStartAction ">//GEN-BEGIN:|49-action|0|49-preAction
    /**
     * Performs an action assigned to the selected list element in the ListStart component.
     */
    public void ListStartAction() {//GEN-END:|49-action|0|49-preAction
        // enter pre-action user code here
        String __selectedString = getListStart().getString(getListStart().getSelectedIndex());//GEN-BEGIN:|49-action|1|52-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Seleccionar archivo de datos")) {//GEN-END:|49-action|1|52-preAction
                // write pre-action user code here
                switchDisplayable(null, getFileBrowser());//GEN-LINE:|49-action|2|52-postAction
                // write post-action user code here
            }//GEN-BEGIN:|49-action|3|49-postAction
        }//GEN-END:|49-action|3|49-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|49-action|4|
    //</editor-fold>//GEN-END:|49-action|4|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: calcCommand ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of calcCommand component.
     * @return the initialized component instance
     */
    public Command getCalcCommand() {
        if (calcCommand == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            calcCommand = new Command("Calcular", Command.SCREEN, 2);//GEN-LINE:|53-getter|1|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return calcCommand;
    }
    //</editor-fold>//GEN-END:|53-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemValor ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initiliazed instance of stringItemValor component.
     * @return the initialized component instance
     */
    public StringItem getStringItemValor() {
        if (stringItemValor == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            stringItemValor = new StringItem("Valor:", "");//GEN-LINE:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return stringItemValor;
    }
    //</editor-fold>//GEN-END:|55-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FormCompendio ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initiliazed instance of FormCompendio component.
     * @return the initialized component instance
     */
    public Form getFormCompendio() {
        if (FormCompendio == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            FormCompendio = new Form("COMPENDIO", new Item[] { getStringItemCompendio() });//GEN-BEGIN:|56-getter|1|56-postInit
            FormCompendio.addCommand(getOkCommand());
            FormCompendio.setCommandListener(this);//GEN-END:|56-getter|1|56-postInit
            // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return FormCompendio;
    }
    //</editor-fold>//GEN-END:|56-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemCompendio ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initiliazed instance of stringItemCompendio component.
     * @return the initialized component instance
     */
    public StringItem getStringItemCompendio() {
        if (stringItemCompendio == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            stringItemCompendio = new StringItem("Compendio:", "");//GEN-LINE:|57-getter|1|57-postInit
            // write post-init user code here
        }//GEN-BEGIN:|57-getter|2|
        return stringItemCompendio;
    }
    //</editor-fold>//GEN-END:|57-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|58-getter|0|58-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|58-getter|1|58-postInit
            // write post-init user code here
        }//GEN-BEGIN:|58-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|58-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FormStartGauge ">//GEN-BEGIN:|61-getter|0|61-preInit
    /**
     * Returns an initiliazed instance of FormStartGauge component.
     * @return the initialized component instance
     */
    public Form getFormStartGauge() {
        if (FormStartGauge == null) {//GEN-END:|61-getter|0|61-preInit
            // write pre-init user code here
            FormStartGauge = new Form("COBRADOR", new Item[] { getGauge() });//GEN-BEGIN:|61-getter|1|61-postInit
            FormStartGauge.addCommand(getStartCommand());
            FormStartGauge.setCommandListener(this);//GEN-END:|61-getter|1|61-postInit
            // write post-init user code here
        }//GEN-BEGIN:|61-getter|2|
        return FormStartGauge;
    }
    //</editor-fold>//GEN-END:|61-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: gauge ">//GEN-BEGIN:|62-getter|0|62-preInit
    /**
     * Returns an initiliazed instance of gauge component.
     * @return the initialized component instance
     */
    public Gauge getGauge() {
        if (gauge == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            gauge = new Gauge("Leyendo...", false, Gauge.INDEFINITE, Gauge.CONTINUOUS_IDLE);//GEN-LINE:|62-getter|1|62-postInit
            // write post-init user code here
        }//GEN-BEGIN:|62-getter|2|
        return gauge;
    }
    //</editor-fold>//GEN-END:|62-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: startCommand ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of startCommand component.
     * @return the initialized component instance
     */
    public Command getStartCommand() {
        if (startCommand == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            startCommand = new Command("Iniciar", "Iniciar", Command.SCREEN, 1);//GEN-LINE:|64-getter|1|64-postInit
            // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return startCommand;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fileBrowser ">//GEN-BEGIN:|66-getter|0|66-preInit
    /**
     * Returns an initiliazed instance of fileBrowser component.
     * @return the initialized component instance
     */
    public FileBrowser getFileBrowser() {
        if (fileBrowser == null) {//GEN-END:|66-getter|0|66-preInit
            // write pre-init user code here
            fileBrowser = new FileBrowser(getDisplay());//GEN-BEGIN:|66-getter|1|66-postInit
            fileBrowser.setTitle("Explorar");
            fileBrowser.setTicker(getTicker1());
            fileBrowser.setCommandListener(this);
            fileBrowser.addCommand(FileBrowser.SELECT_FILE_COMMAND);
            fileBrowser.addCommand(getExitCommand());//GEN-END:|66-getter|1|66-postInit
            // write post-init user code here
            fileBrowser.openDir("root1/");
        }//GEN-BEGIN:|66-getter|2|
        return fileBrowser;
    }
    //</editor-fold>//GEN-END:|66-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ticker1 ">//GEN-BEGIN:|69-getter|0|69-preInit
    /**
     * Returns an initiliazed instance of ticker1 component.
     * @return the initialized component instance
     */
    public Ticker getTicker1() {
        if (ticker1 == null) {//GEN-END:|69-getter|0|69-preInit
            // write pre-init user code here
            ticker1 = new Ticker("Escoja el archivo de datos.");//GEN-LINE:|69-getter|1|69-postInit
            // write post-init user code here
        }//GEN-BEGIN:|69-getter|2|
        return ticker1;
    }
    //</editor-fold>//GEN-END:|69-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceGroupCheck ">//GEN-BEGIN:|75-getter|0|75-preInit
    /**
     * Returns an initiliazed instance of choiceGroupCheck component.
     * @return the initialized component instance
     */
    public ChoiceGroup getChoiceGroupCheck() {
        if (choiceGroupCheck == null) {//GEN-END:|75-getter|0|75-preInit
            // write pre-init user code here
            choiceGroupCheck = new ChoiceGroup("Cobr\u00F3?", Choice.EXCLUSIVE);//GEN-BEGIN:|75-getter|1|75-postInit
            choiceGroupCheck.append("Si", null);
            choiceGroupCheck.append("No", null);
            choiceGroupCheck.setSelectedFlags(new boolean[] { true, false });//GEN-END:|75-getter|1|75-postInit
            // write post-init user code here
        }//GEN-BEGIN:|75-getter|2|
        return choiceGroupCheck;
    }
    //</editor-fold>//GEN-END:|75-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldSobrantes ">//GEN-BEGIN:|78-getter|0|78-preInit
    /**
     * Returns an initiliazed instance of textFieldSobrantes component.
     * @return the initialized component instance
     */
    public TextField getTextFieldSobrantes() {
        if (textFieldSobrantes == null) {//GEN-END:|78-getter|0|78-preInit
            // write pre-init user code here
            textFieldSobrantes = new TextField("Sobrantes", "", 3, TextField.NUMERIC);//GEN-LINE:|78-getter|1|78-postInit
            // write post-init user code here
        }//GEN-BEGIN:|78-getter|2|
        return textFieldSobrantes;
    }
    //</editor-fold>//GEN-END:|78-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }


    private String GetMessage() {
        return this.Message;
    }

    private void SetMessage(String message) {
        this.Message = message;
    }


    private void WriteChanges() {
        (new Thread() {

            public void run() {
                String sOldOutFile, sUpdatedJSON;
                boolean bWrote;

                try {
                    //sOldOutFile = connectionhandler.ReadOutputFile();
                    //sUpdatedJSON = connectionhandler.AddJSONInfo(sOldOutFile, CurrPuntoExpendio);
                    bWrote = connectionhandler.WriteLocalFile(RUTAS.JSONObjectToString(), sFileApp);
                } catch (IOException ioex) {
                    bWrote = false;
                    SetMessage(ioex.toString());
                    ioex.printStackTrace();
                } catch (Exception ex) {
                    bWrote = false;
                    SetMessage(ex.toString());
                    ex.printStackTrace();
                }

                if (bWrote) {
                    System.out.println();
                    getAlert().setTitle("CORRECTO");
                    getAlert().setString("Información de ruta guardada.");
                    getAlert().setType(AlertType.CONFIRMATION);
                    switchDisplayable(null, getAlert());
                } else {
                    System.out.println();
                    getAlert().setTitle("ERROR");
                    getAlert().setString("Error al guardar información de ruta.");
                    getAlert().setType(AlertType.ERROR);
                    switchDisplayable(null, getAlert());
                }
            }
        }).start();
    }


    private void FetchLocalFile(String sParamFileURL) {
        try {
            connectionhandler = new ConnectionHandler();
            String sReadedFile = connectionhandler.ReadLocalFile(sParamFileURL);
            RUTAS = new Rutas(sReadedFile);
            SetMessage("[*] Ruta created.");
            switchDisplayable(null, getListProducto());
        } catch (IOException ioex) {
            SetMessage(ioex.toString());
            ioex.printStackTrace();
        } catch (Exception ex) {
            SetMessage(ex.toString());
            ex.printStackTrace();
        }
    }

    
    private int CalcFechaValor() {
        String sFechaDevueltos = textFieldEntDev.getString();
        String sFechaFaltantes = textFieldFaltantes.getString();

        int iFechaDevueltos = 0;
        if (sFechaDevueltos!=null && !sFechaDevueltos.equalsIgnoreCase("")) {
            iFechaDevueltos = Integer.parseInt(sFechaDevueltos);
        }

        int iFechaFaltantes = 0;
        if (sFechaFaltantes!=null && !sFechaFaltantes.equalsIgnoreCase("")) {
            iFechaFaltantes = Integer.parseInt(sFechaFaltantes);
        }

        int iResult = (CurrFecha.GetEntregados()-iFechaDevueltos-iFechaFaltantes)*CurrFecha.GetValorUnitario();

        return iResult;
    }

}
