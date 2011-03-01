/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cobrador;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
//import org.netbeans.microedition.lcdui.pda.FileBrowser;

/**
 * @author Admin
 */
public class MainMidlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    private String Message;

    private boolean isSafeToExit = false;
    private String sFileApp = null;

    private ConnectionHandler connectionhandler;
    private Rutas RUTAS;
    private Ruta CurrRuta;

    private Enumeration enumeration;

    private int iIndexListFechas = 0;
    private int iIndexListExpendios = 0;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">
    private List ListProducto;
    private List ListExpendios;
    private List ListFechas;
    private Form FormFecha;
    private TextField textFieldEntDev;
    private TextField textFieldVUValor;
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

    private Expendio CurrExpendio;
    private Fecha CurrFecha;
    //</editor-fold>

    /**
     * The MainMidlet constructor.
     */
    public MainMidlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {
        // write pre-initialize user code here
        connectionhandler = new ConnectionHandler();

        // write post-initialize user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {
        // write pre-action user code here
        switchDisplayable(null, getFileBrowser());

        // write post-action user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {
        // write pre-action user code here

        // write post-action user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {
        // write pre-switch user code here
        Display display = getDisplay();
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }
        // write post-switch user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {
        // write pre-action user code here
        if (displayable == FormCompendio) {
            if (command == okCommand) {
                // write pre-action user code here
                switchDisplayable(null, getListExpendios());
                // write post-action user code here
            }
        } else if (displayable == FormFecha) {
            if (command == avanzarCommand) {
                // write pre-action user code here
                int iDevueltos = Integer.parseInt(textFieldEntDev.getString());
                if (iDevueltos>CurrFecha.GetEntregados()) {
                    getAlert().setTitle("ERROR");
                    getAlert().setString("Valor inválido para campo 'Devueltos'.");
                    switchDisplayable(null, getAlert());
                }
                CurrFecha.SetDevueltos(iDevueltos);
                //int iValor = Integer.parseInt(textFieldVUValor.getString());
                int iValor = Integer.parseInt(stringItemValor.getText());
                CurrFecha.SetValor(iValor);
                switchDisplayable(null, getListFechas());
                // write post-action user code here
            } else if (command == backCommand) {
                // write pre-action user code here
                String sFormFechaEntDev = textFieldEntDev.getString();
                int iDevueltos = 0;
                if (sFormFechaEntDev != null && !sFormFechaEntDev.equalsIgnoreCase("")) {
                    iDevueltos = Integer.parseInt(sFormFechaEntDev);
                }

                if (iDevueltos > CurrFecha.GetEntregados()) {
                    getAlert().setTitle("ERROR");
                    getAlert().setString("Valor inválido para campo 'Devueltos'.");
                    getAlert().setType(AlertType.INFO);
                    switchDisplayable(null, getAlert());
                } else {
                    CurrFecha.SetDevueltos(iDevueltos);

                    String sFormFechaFaltantes = textFieldFaltantes.getString();
                    int iFaltantes = 0;
                    if (sFormFechaFaltantes != null && !sFormFechaFaltantes.equalsIgnoreCase("")) {
                        iFaltantes = Integer.parseInt(sFormFechaFaltantes);
                    }
                    CurrFecha.SetFaltantes(iFaltantes);

                    String sFormFechaSobrantes = textFieldSobrantes.getString();
                    int iSobrantes = 0;
                    if (sFormFechaSobrantes != null && !sFormFechaSobrantes.equalsIgnoreCase("")) {
                        iSobrantes = Integer.parseInt(sFormFechaSobrantes);
                    }
                    CurrFecha.SetSobrantes(iSobrantes);

                    CurrFecha.SetCobrado(choiceGroupCheck.getSelectedIndex());

                    if (CurrFecha.GetCobrado() == 1) {
                        CurrFecha.SetValor(CalcFechaValor());
                    }

                    switchDisplayable(null, getListFechas());
                }

                // write post-action user code here

            } else if (command == calcCommand) {
                // write pre-action user code here
                String sFormFechaEntDev = textFieldEntDev.getString();
                int iDevueltos = 0;
                if (sFormFechaEntDev != null && !sFormFechaEntDev.equalsIgnoreCase("")) {
                    iDevueltos = Integer.parseInt(sFormFechaEntDev);
                }

                if (iDevueltos > CurrFecha.GetEntregados()) {
                    getAlert().setTitle("ERROR");
                    getAlert().setString("Valor inválido para campo 'Devueltos'.");
                    getAlert().setType(AlertType.INFO);
                    switchDisplayable(null, getAlert());
                } else {
                    CurrFecha.SetDevueltos(iDevueltos);

                    String sFormFechaFaltantes = textFieldFaltantes.getString();
                    int iFaltantes = 0;
                    if (sFormFechaFaltantes != null && !sFormFechaFaltantes.equalsIgnoreCase("")) {
                        iFaltantes = Integer.parseInt(sFormFechaFaltantes);
                    }
                    CurrFecha.SetFaltantes(iFaltantes);

                    String sFormFechaSobrantes = textFieldSobrantes.getString();
                    int iSobrantes = 0;
                    if (sFormFechaSobrantes != null && !sFormFechaSobrantes.equalsIgnoreCase("")) {
                        iSobrantes = Integer.parseInt(sFormFechaSobrantes);
                    }
                    CurrFecha.SetSobrantes(iSobrantes);

                    CurrFecha.SetCobrado(choiceGroupCheck.getSelectedIndex());

                    int iFechaValorCalculed = CalcFechaValor();
                    if (CurrFecha.GetCobrado() == 1) {
                        CurrFecha.SetValor(iFechaValorCalculed);
                    }

                    int iFechaValor = iFechaValorCalculed;
                    String sFechaValor = Integer.toString(iFechaValor);
                    stringItemValor.setText(sFechaValor);
                }
                // write post-action user code here
            }
        } else if (displayable == FormStartGauge) {
            if (command == startCommand) {
                // write pre-action user code here

                // write post-action user code here
            }
        } else if (displayable == ListExpendios) {
            if (command == List.SELECT_COMMAND) {
                // write pre-action user code here
                ListExpendiosAction();
                // write post-action user code here
            } else if (command == backCommand) {
                // write pre-action user code here
                switchDisplayable(null, getListProducto());
                // write post-action user code here
            } else if (command == saveCommand) {
                // write pre-action user code here
                WriteChanges();

                // write post-action user code here
            }
        } else if (displayable == ListFechas) {
            if (command == List.SELECT_COMMAND) {
                // write pre-action user code here
                ListFechasAction();
                // write post-action user code here
            } else if (command == backCommand) {
                // write pre-action user code here
                switchDisplayable(null, getFormCompendio());

                // write post-action user code here
            }
        } else if (displayable == ListProducto) {
            if (command == List.SELECT_COMMAND) {
                // write pre-action user code here
                ListProductoAction();
                // write post-action user code here
            } else if (command == exitCommand) {
                // write pre-action user code here
                exitMIDlet();
                // write post-action user code here
            }
        } else if (displayable == ListStart) {
            if (command == List.SELECT_COMMAND) {
                // write pre-action user code here
                ListStartAction();
                // write post-action user code here
            } else if (command == exitCommand) {
                // write pre-action user code here
                exitMIDlet();
                // write post-action user code here
            }
        } else if (displayable == fileBrowser) {
            if (command == FileBrowser.SELECT_FILE_COMMAND) {
                // write pre-action user code here
                sFileApp = fileBrowser.getSelectedFileURL();
                System.out.println(sFileApp);
                switchDisplayable(null, getFormStartGauge());
                (new Thread() {

                    public void run() {
                        FetchLocalFile(sFileApp);
                    }
                }).start();

                // write post-action user code here
            } else if (command == exitCommand) {
                // write pre-action user code here
                exitMIDlet();
                // write post-action user code here
            }
        }
        // write post-action user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ListProducto ">
    /**
     * Returns an initiliazed instance of ListProducto component.
     * @return the initialized component instance
     */
    public List getListProducto() {
        //if (ListProducto == null) {
        // write pre-init user code here
        ListProducto = new List("Escoja Producto", Choice.IMPLICIT);
        ListProducto.addCommand(getExitCommand());
        ListProducto.setCommandListener(this);
        // write post-init user code here
        try {
            Image image = null;
            enumeration = RUTAS.GetVectorRutas().elements();
            while (enumeration.hasMoreElements()) {
                Ruta curr_ruta = (Ruta) enumeration.nextElement();
                String sProducto = curr_ruta.GetProducto();
                if (sProducto.equalsIgnoreCase("La Patria")) {
                    image = Image.createImage("/lapatria.png");

                }
                if (sProducto.equalsIgnoreCase("Nuevo Estadio")) {
                    image = Image.createImage("/nuevoestadio.png");

                }
                if (sProducto.equalsIgnoreCase("QHubo")) {
                    image = Image.createImage("/qhubo.png");

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

        iIndexListExpendios = 0;
        //}
        return ListProducto;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: ListProductoAction ">
    /**
     * Performs an action assigned to the selected list element in the ListProducto component.
     */
    public void ListProductoAction() {
        // enter pre-action user code here
        String __selectedString = ListProducto.getString(ListProducto.getSelectedIndex());
        //String __selectedString = getListProducto().getString(getListProducto().getSelectedIndex());
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
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {
            // write pre-init user code here
            exitCommand = new Command("Salir", Command.EXIT, 1);
            // write post-init user code here
        }
        return exitCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ListExpendios ">
    /**
     * Returns an initiliazed instance of ListExpendios component.
     * @return the initialized component instance
     */
    public List getListExpendios() {
        //if (ListExpendios == null) {
        // write pre-init user code here
        ListExpendios = new List("Escoja Expendio", Choice.IMPLICIT);
        ListExpendios.addCommand(getBackCommand());
        ListExpendios.addCommand(getSaveCommand());
        ListExpendios.setCommandListener(this);
        // write post-init user code here
        Image image = null;
        try {
            enumeration = CurrRuta.GetVectorExpendios().elements();
            while (enumeration.hasMoreElements()) {
                Expendio expendio = (Expendio) enumeration.nextElement();
                if (expendio.GetVisitado() == 1) {
                    image = Image.createImage("/visitado.png");
                } else {
                    image = Image.createImage("/novisitado.png");
                }
                ListExpendios.append(expendio.GetNombre(), image);
            }
        } catch (IOException ioex) {
            SetMessage(ioex.toString());
            ioex.printStackTrace();
        } catch (Exception ex) {
            SetMessage(ex.toString());
            ex.printStackTrace();
        }

        iIndexListFechas = 0;
        ListExpendios.setSelectedIndex(iIndexListExpendios, true);
        //}
        return ListExpendios;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: ListExpendiosAction ">
    /**
     * Performs an action assigned to the selected list element in the ListExpendios component.
     */
    public void ListExpendiosAction() {
        // enter pre-action user code here
        iIndexListExpendios = ListExpendios.getSelectedIndex();
        String __selectedString = ListExpendios.getString(ListExpendios.getSelectedIndex());
        //String __selectedString = getListExpendios().getString(getListExpendios().getSelectedIndex());
        // enter post-action user code here
        enumeration = CurrRuta.GetVectorExpendios().elements();
        while (enumeration.hasMoreElements()) {
            Expendio expe_current = (Expendio) enumeration.nextElement();
            if (expe_current.GetNombre().equalsIgnoreCase(__selectedString)) {
                CurrExpendio = expe_current;
                //CurrExpendio.SetFechaValores();
                break;
            }
        }
        CurrExpendio.SetVisitado(1);

        switchDisplayable(null, getListFechas());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {
            // write pre-init user code here
            backCommand = new Command("Avanzar", "Avanzar", Command.SCREEN, 1);
            // write post-init user code here
        }
        return backCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ListFechas ">
    /**
     * Returns an initiliazed instance of ListFechas component.
     * @return the initialized component instance
     */
    public List getListFechas() {
        //if (ListFechas == null) {
        // write pre-init user code here
        ListFechas = new List("Escoja Fecha", Choice.IMPLICIT);
        ListFechas.addCommand(getBackCommand());
        ListFechas.setCommandListener(this);
        // write post-init user code here
        Image image;
        try {
            enumeration = CurrExpendio.GetVectorFechas().elements();
            while (enumeration.hasMoreElements()) {
                Fecha fecha = (Fecha) enumeration.nextElement();
                if (fecha.GetVisitado() == 1) {
                    image = Image.createImage("/visitado.png");
                } else {
                    image = Image.createImage("/novisitado.png");
                }
                ListFechas.append(fecha.GetFecha(), image);
            }
        } catch (IOException ioex) {
            SetMessage(ioex.toString());
            ioex.printStackTrace();
        } catch (Exception ex) {
            SetMessage(ex.toString());
            ex.printStackTrace();
        }

        ListFechas.setSelectedIndex(iIndexListFechas, true);
        //}
        return ListFechas;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: ListFechasAction ">
    /**
     * Performs an action assigned to the selected list element in the ListFechas component.
     */
    public void ListFechasAction() {
        // enter pre-action user code here
        iIndexListFechas = ListFechas.getSelectedIndex();
        String __selectedString = ListFechas.getString(ListFechas.getSelectedIndex());
        //String __selectedString = getListFechas().getString(getListFechas().getSelectedIndex());
        // enter post-action user code here
        enumeration = CurrExpendio.GetVectorFechas().elements();
        while (enumeration.hasMoreElements()) {
            Fecha fecha_current = (Fecha) enumeration.nextElement();
            if (fecha_current.GetFecha().equalsIgnoreCase(__selectedString)) {
                CurrFecha = fecha_current;
                break;
            }
        }
        CurrFecha.SetVisitado(1);

        switchDisplayable(null, getFormFecha());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FormFecha ">
    /**
     * Returns an initiliazed instance of FormFecha component.
     * @return the initialized component instance
     */
    public Form getFormFecha() {
        //if (FormFecha == null) {
            // write pre-init user code here
            FormFecha = new Form(CurrFecha.GetFecha(), new Item[] { getTextFieldEntDev(), getTextFieldFaltantes(), getTextFieldSobrantes(), getStringItemValor(), getChoiceGroupCheck() });
            FormFecha.addCommand(getCalcCommand());
            FormFecha.addCommand(getBackCommand());
            //FormFecha.addCommand(getCalcCommand());
            FormFecha.setCommandListener(this);
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
            /*if (iFechaFaltantes == 0) {
                textFieldFaltantes.setString("");
            }
            else {
                String sFechaFaltantes = Integer.toString(iFechaFaltantes);
                textFieldFaltantes.setString(sFechaFaltantes);
            }*/
            String sFechaFaltantes = Integer.toString(iFechaFaltantes);
            textFieldFaltantes.setString(sFechaFaltantes);

            int iFechaSobrantes = CurrFecha.GetSobrantes();
            String sFechaSobrantes = Integer.toString(iFechaSobrantes);
            textFieldSobrantes.setString(sFechaSobrantes);

            int iFechaValor = CurrFecha.GetValor();
            if (iFechaValor == 0) {
                //textFieldVUValor.setString("");
                stringItemValor.setText("");
            }
            else {
                String sFechaValor = Integer.toString(iFechaValor);
                stringItemValor.setText(sFechaValor);
            }

            choiceGroupCheck.setSelectedIndex(CurrFecha.GetCobrado(), true);
        //}
        return FormFecha;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldEntDev ">
    /**
     * Returns an initiliazed instance of textFieldEntDev component.
     * @return the initialized component instance
     */
    public TextField getTextFieldEntDev() {
        //if (textFieldEntDev == null) {
            // write pre-init user code here
            textFieldEntDev = new TextField("Ent.: "+CurrFecha.GetEntregados()+" / Dev.:", "", 3, TextField.NUMERIC);
            // write post-init user code here
        //}
        return textFieldEntDev;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldVUValor ">
    /**
     * Returns an initiliazed instance of textFieldVUValor component.
     * @return the initialized component instance
     */
    public TextField getTextFieldVUValor() {
        //if (textFieldVUValor == null) {
            // write pre-init user code here
            textFieldVUValor = new TextField("Valor:", "", 6, TextField.NUMERIC);
            // write post-init user code here
        //}
        return textFieldVUValor;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: avanzarCommand ">
    /**
     * Returns an initiliazed instance of avanzarCommand component.
     * @return the initialized component instance
     */
    public Command getAvanzarCommand() {
        if (avanzarCommand == null) {
            // write pre-init user code here
            //avanzarCommand = new Command("Avanzar", Command.SCREEN, 1);
            avanzarCommand = new Command("Avanzar", Command.SCREEN, 1);
            // write post-init user code here
        }
        return avanzarCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: saveCommand ">
    /**
     * Returns an initiliazed instance of saveCommand component.
     * @return the initialized component instance
     */
    public Command getSaveCommand() {
        if (saveCommand == null) {
            // write pre-init user code here
            saveCommand = new Command("Guardar", Command.SCREEN, 1);
            // write post-init user code here
        }
        return saveCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Alert ">
    /**
     * Returns an initiliazed instance of Alert component.
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (Alert == null) {
            // write pre-init user code here
            Alert = new Alert("", "", null, null);
            Alert.setTimeout(Alert.FOREVER);
            // write post-init user code here
        }
        return Alert;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldFaltantes ">
    /**
     * Returns an initiliazed instance of textFieldFaltantes component.
     * @return the initialized component instance
     */
    public TextField getTextFieldFaltantes() {
        //if (textFieldFaltantes == null) {
            // write pre-init user code here
            textFieldFaltantes = new TextField("Faltantes:", "", 3, TextField.NUMERIC);
            // write post-init user code here
        //}
        return textFieldFaltantes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textBox ">
    /**
     * Returns an initiliazed instance of textBox component.
     * @return the initialized component instance
     */
    public TextBox getTextBox() {
        if (textBox == null) {
            // write pre-init user code here
            textBox = new TextBox("textBox", null, 40000, TextField.ANY);
            // write post-init user code here
            textBox.setString(GetMessage());
        }
        return textBox;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ListStart ">
    /**
     * Returns an initiliazed instance of ListStart component.
     * @return the initialized component instance
     */
    public List getListStart() {
        if (ListStart == null) {
            // write pre-init user code here
            ListStart = new List("COBRADOR", Choice.IMPLICIT);
            ListStart.append("Seleccionar archivo de datos", null);
            ListStart.addCommand(getExitCommand());
            ListStart.setCommandListener(this);
            ListStart.setSelectedFlags(new boolean[] { false });
            // write post-init user code here
        }
        return ListStart;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: ListStartAction ">
    /**
     * Performs an action assigned to the selected list element in the ListStart component.
     */
    public void ListStartAction() {
        // enter pre-action user code here
        String __selectedString = getListStart().getString(getListStart().getSelectedIndex());
        if (__selectedString != null) {
            if (__selectedString.equals("Seleccionar archivo de datos")) {
                // write pre-action user code here
                switchDisplayable(null, getFileBrowser());
                // write post-action user code here
            }
        }
        // enter post-action user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: calcCommand ">
    /**
     * Returns an initiliazed instance of calcCommand component.
     * @return the initialized component instance
     */
    public Command getCalcCommand() {
        if (calcCommand == null) {
            // write pre-init user code here
            calcCommand = new Command("Calcular", Command.SCREEN, 2);
            // write post-init user code here
        }
        return calcCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemValor ">
    /**
     * Returns an initiliazed instance of stringItemValor component.
     * @return the initialized component instance
     */
    public StringItem getStringItemValor() {
        //if (stringItemValor == null) {
            // write pre-init user code here
            stringItemValor = new StringItem("Valor:", "");
            // write post-init user code here
        //}
        return stringItemValor;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FormCompendio ">
    /**
     * Returns an initiliazed instance of FormCompendio component.
     * @return the initialized component instance
     */
    public Form getFormCompendio() {
        //if (FormCompendio == null) {
            // write pre-init user code here
            FormCompendio = new Form("COMPENDIO: "+CurrExpendio.GetNombre(), new Item[]{});
            FormCompendio.addCommand(getOkCommand());
            FormCompendio.setCommandListener(this);
            // write post-init user code here
            int iCompendio = CalcCompendio();
            String sCompendio = Integer.toString(iCompendio);
            CurrExpendio.SetCompendio(iCompendio);


            System.out.println("CalcCompendio(): "+CalcCompendio());
            //CurrExpendio.CalcExpendioCompendio();
            System.out.println("CurrExpendio.GetCompendio(): "+CurrExpendio.GetCompendio());


            String sCompendioFull = "";
            enumeration = CurrExpendio.GetVectorFechas().elements();
            while (enumeration.hasMoreElements()) {
                Fecha fecha = (Fecha) enumeration.nextElement();

                //if (fecha.GetVisitado() == 1) {
                if (fecha.GetCobrado() == 1) {
                    StringItem siTemp = new StringItem(fecha.GetFecha() + ":", fecha.GetEntregados() + " - " + fecha.GetDevueltos() + " - " + fecha.GetValor());
                    FormCompendio.append(siTemp);
                }
            }
            StringItem siCompendio = getStringItemCompendio();
            siCompendio.setLabel("TOTAL:");
            siCompendio.setText(sCompendio);
            FormCompendio.append(siCompendio);
            //stringItemCompendio.setText(sCompendioFull+"TOTAL: "+sCompendio);
        //}
        return FormCompendio;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemCompendio ">
    /**
     * Returns an initiliazed instance of stringItemCompendio component.
     * @return the initialized component instance
     */
    public StringItem getStringItemCompendio() {
        //if (stringItemCompendio == null) {
            // write pre-init user code here
            stringItemCompendio = new StringItem("Compendio:", "");
            // write post-init user code here
        //}
        return stringItemCompendio;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);
            // write post-init user code here
        }
        return okCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FormStartGauge ">
    /**
     * Returns an initiliazed instance of FormStartGauge component.
     * @return the initialized component instance
     */
    public Form getFormStartGauge() {
        if (FormStartGauge == null) {
            // write pre-init user code here
            FormStartGauge = new Form("CARGANDO", new Item[] { getGauge() });
            //FormStartGauge.addCommand(getStartCommand());
            FormStartGauge.setCommandListener(this);
            // write post-init user code here
        }
        else {
            gauge.setValue(Gauge.CONTINUOUS_RUNNING);
        }
        return FormStartGauge;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: gauge ">
    /**
     * Returns an initiliazed instance of gauge component.
     * @return the initialized component instance
     */
    public Gauge getGauge() {
        if (gauge == null) {
            // write pre-init user code here
            gauge = new Gauge("Espere por favor...", false, Gauge.INDEFINITE, Gauge.CONTINUOUS_RUNNING);
            // write post-init user code here
        }
        /*else {
            // write pre-init user code here
            gauge = new Gauge("Leer...", false, Gauge.INDEFINITE, Gauge.CONTINUOUS_RUNNING);
            // write post-init user code here
        }*/
        return gauge;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: startCommand ">
    /**
     * Returns an initiliazed instance of startCommand component.
     * @return the initialized component instance
     */
    public Command getStartCommand() {
        if (startCommand == null) {
            // write pre-init user code here
            startCommand = new Command("Iniciar", "Iniciar", Command.SCREEN, 1);
            // write post-init user code here
        }
        return startCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: fileBrowser ">
    /**
     * Returns an initiliazed instance of fileBrowser component.
     * @return the initialized component instance
     */
    public FileBrowser getFileBrowser() {
        if (fileBrowser == null) {
            // write pre-init user code here
            fileBrowser = new FileBrowser(getDisplay());
            fileBrowser.setTitle("Explorar");
            fileBrowser.setTicker(getTicker1());
            fileBrowser.setCommandListener(this);
            fileBrowser.addCommand(FileBrowser.SELECT_FILE_COMMAND);
            fileBrowser.addCommand(getExitCommand());
            // write post-init user code here
            //fileBrowser.openDir("c:/predefgallery/predeffilereceived/");
        }
        return fileBrowser;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ticker1 ">
    /**
     * Returns an initiliazed instance of ticker1 component.
     * @return the initialized component instance
     */
    public Ticker getTicker1() {
        if (ticker1 == null) {
            // write pre-init user code here
            ticker1 = new Ticker("Escoja el archivo de datos.");
            // write post-init user code here
        }
        return ticker1;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceGroupCheck ">
    /**
     * Returns an initiliazed instance of choiceGroupCheck component.
     * @return the initialized component instance
     */
    public ChoiceGroup getChoiceGroupCheck() {
        //if (choiceGroupCheck == null) {
            // write pre-init user code here
            choiceGroupCheck = new ChoiceGroup("Cobr\u00F3?", Choice.EXCLUSIVE);
            choiceGroupCheck.append("No", null);
            choiceGroupCheck.append("Si", null);
            choiceGroupCheck.setSelectedFlags(new boolean[] { true, false });
            // write post-init user code here
        //}
        return choiceGroupCheck;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldSobrantes ">
    /**
     * Returns an initiliazed instance of textFieldSobrantes component.
     * @return the initialized component instance
     */
    public TextField getTextFieldSobrantes() {
        //if (textFieldSobrantes == null) {
            // write pre-init user code here
            textFieldSobrantes = new TextField("Sobrantes", "", 3, TextField.NUMERIC);
            // write post-init user code here
        //}
        return textFieldSobrantes;
    }
    //</editor-fold>

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
                //String sOldOutFile, sUpdatedJSON;
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
                    getAlert().setType(AlertType.INFO);
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
        /*String sFechaDevueltos = textFieldEntDev.getString();
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

        return iResult;*/

        int iEntregados = CurrFecha.GetEntregados();
        int iDevueltos = CurrFecha.GetDevueltos();
        int iFaltantes = CurrFecha.GetFaltantes();
        int iSobrantes = CurrFecha.GetSobrantes();
        int iRinde = 0;

        if (iSobrantes!=0 && iDevueltos==0) {
            iRinde = iEntregados+iSobrantes;
        }
        if (iSobrantes!=0 && iDevueltos!=0) {
            iRinde = iEntregados-iDevueltos;
        }
        if (iSobrantes==0) {
            iRinde = iEntregados-iDevueltos-iFaltantes;
        }

        int iResult = iRinde*CurrFecha.GetValorUnitario();

        return iResult;
    }

    
    private int CalcCompendio() {
        int iCompendio = 0;
        enumeration = CurrExpendio.GetVectorFechas().elements();
        while (enumeration.hasMoreElements()) {
            Fecha fecha = (Fecha) enumeration.nextElement();

            /*if (fecha.GetVisitado() == 1) {
                iCompendio = iCompendio + fecha.GetValor();
            }*/
            if (fecha.GetCobrado() == 1) {
                iCompendio = iCompendio + fecha.GetValor();
            }
        }
        return iCompendio;
    }

    private void CurrFechaSetFields() {

    }

}
