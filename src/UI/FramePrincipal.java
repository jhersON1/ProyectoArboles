/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.*;
import arbolbin.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import static java.util.Collections.swap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willy
 */
public class FramePrincipal extends javax.swing.JFrame {

    private final Stack<JPanel> panelesAnteriores;
    static int ALTO_FRAME = 500;
    static int ANCHO_FRAME = 700;
    static Rectangle dimensiones = new Rectangle(FramePrincipal.ANCHO_FRAME, FramePrincipal.ALTO_FRAME);
    
    private IArbolBusqueda<Empleado, Empleado> arbolDeEmpleados;
    private IArbolBusqueda<Logging, Logging> arbolDeLogging;
    private Logging logging;
    private Empleado usuario;
    private Empleado usuarioAEditar;
    private Logging loggingAEditar;

    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
        panelesAnteriores = new Stack<>();
        super.setBounds(FramePrincipal.dimensiones);
        super.setLocationRelativeTo(null);
        PanelEleccionArbol panelEleccion = new PanelEleccionArbol(this);
        this.addPanel(panelEleccion);
    }

    public boolean logear(String user, char[] pass) throws ExceptionContraseñaNoValida {
        Logging loggingBase = new Logging(user, pass, 0, true);
        Logging loggingEncontrado = arbolDeLogging.buscar(loggingBase);
        return loggingEncontrado != null && loggingEncontrado.logging(user, pass);
    }

    private void insertarAdmin() throws ExceptionContraseñaNoValida {
        Empleado administrador = new Empleado(11341907);
        Logging logginAdmin = new Logging("admin", "1234".toCharArray(), administrador.getCI(), true);
        arbolDeEmpleados.insertar(administrador,administrador);
        arbolDeLogging.insertar(logginAdmin,logginAdmin);
    }

    public void cargarDatos(IArbolBusqueda<Empleado, Empleado> arbolEmpl, IArbolBusqueda<Logging, Logging> arbolLogg) {
        String rutaDelArchivo = new File("").getAbsolutePath() + "\\empleado.emw";
        File archivo = new File(rutaDelArchivo);
        if (archivo.exists()) {
            //Se lee el objeto de archivo y este debe convertirse al tipo de clase que corresponde
            try {
                FileInputStream file = new FileInputStream(rutaDelArchivo);
                ObjectInputStream fileIn = new ObjectInputStream(file);
                java.util.List<Empleado> listEmpleados;
                java.util.List<Logging> listLogging;
                Object listE = fileIn.readObject();
                Object listL = fileIn.readObject();
                listEmpleados = (java.util.List<Empleado>) listE;
                listLogging = (java.util.List<Logging>) listL;
                listEmpleados.forEach((emp) -> {
                    arbolEmpl.insertar(emp,emp);
                });
                listLogging.forEach((logg) -> {
                    arbolLogg.insertar(logg,logg);
                });
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            //se cierra archivo
        } else {
            try {
                this.insertarAdmin();
            } catch (ExceptionContraseñaNoValida ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void grabarDatos(IArbolBusqueda<Empleado, Empleado> arbolEmpl, IArbolBusqueda<Logging, Logging> arbolLogg) {
        String rutaDelArchivo = new File("").getAbsolutePath() + "\\empleado.emw";
        File archivo = new File(rutaDelArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
        try {
            //Objeto a guardar en archivo *.DAT
            System.out.println(rutaDelArchivo);
            //Se escribe el objeto en archivo
            try ( //Se crea un Stream para guardar archivo
                ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(rutaDelArchivo))) {
                //Se escribe el objeto en archivo
                Object listaE = arbolEmpl.recorridoEnPreOrden();
                Object listaL = arbolLogg.recorridoEnPreOrden();
                fileOut.writeObject(listaE);
                fileOut.writeObject(listaL);
                //se cierra archivo
                fileOut.close();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void ajustarFotografia(JLabel labelFoto, ImageIcon foto) {
        Image image = foto.getImage(); // transform it
        int width = labelFoto.getPreferredSize().width, height = labelFoto.getPreferredSize().width;
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        foto = new ImageIcon(newimg);
        labelFoto.setIcon(foto);
    }

    public List<Empleado> ordenadaSegun(short tipoComparacion, List<Empleado> lista) {
        List<Empleado> list = new LinkedList<>(lista);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Empleado dato1 = list.get(i);
            for (int j = i + 1; j < size; j++) {
                Empleado dato2 = list.get(j);
                if (dato1.ordenacionSegun(dato2, tipoComparacion) > 0) {
                    swap(list, i, j);
                }
            }
        }
        return list;
    }

    public void cargarDatosEnTabla(short tipo, JTable tablaDeEmpleados) {
        if (this.arbolDeEmpleados != null) {
            List<Empleado> listaEmpleados = this.arbolDeEmpleados.recorridoEnInOrden();
            List<Empleado> listaOrdenada = this.ordenadaSegun(tipo, listaEmpleados);
            DefaultTableModel tabla = (DefaultTableModel) tablaDeEmpleados.getModel();
            int longLista = listaOrdenada.size() -1;
            tabla.setRowCount(longLista);
            int cont = 0;
            for (int i = 0; i < longLista; i++) {
                Empleado empleado = listaOrdenada.get(i);
                Logging buscado = new Logging("", "0000".toCharArray(), empleado.getCI(), false);
                buscado.setComparacionCi(true);
                Logging admin = this.arbolDeLogging.buscar(buscado);
                buscado.setComparacionCi(false);
                //if (admin.siEsAdmin()) {
                  //  continue;
                //}
                //tabla.addRow(new Object[]{"", "", "", "", ""});
                String nombres = empleado.getNombre();
                String apellidos = empleado.getApellPaterno() + " " + empleado.getApellMaterno();
                Integer CI = empleado.getCI();
                String cargo = empleado.getCargo();
                Integer sueldo = empleado.getSueldoMensual();
                tablaDeEmpleados.setValueAt(nombres, cont, 0);
                tablaDeEmpleados.setValueAt(apellidos, cont, 1);
                tablaDeEmpleados.setValueAt(CI, cont, 2);
                tablaDeEmpleados.setValueAt(cargo, cont, 3);
                tablaDeEmpleados.setValueAt(sueldo, cont, 4);
                cont++;
            }
        }
    }

    public void cargarIconoAtras(JLabel iconAtras) {
        iconAtras.setText(null);
        this.ajustarFotografia(iconAtras, (ImageIcon) fotoAtras.getIcon());
    }

    public void setLogging(Logging logging) {
        this.logging = logging;
    }

    public void setUsuario(Empleado usuario) {
        this.usuario = usuario;
    }

    public void setUsuarioAEditar(Empleado usuarioAEditar) {
        this.usuarioAEditar = usuarioAEditar;
    }

    public void setLoggingAEditar(Logging loggingAEditar) {
        this.loggingAEditar = loggingAEditar;
    }

    public Stack<JPanel> getPanelesAnteriores() {
        return this.panelesAnteriores;
    }

    public void volverPestañaAtras(Empleado empleadoUser, Empleado empleadoEdit,
            Logging logging, boolean editando){
        JPanel panel = this.panelesAnteriores.pop();
        JPanel panelAdd = null;
        if (panel instanceof PanelInicioSesion) {
            this.usuario = null;
            this.usuarioAEditar = null;
            this.logging = null;
            this.loggingAEditar = null;
            panelAdd = new PanelInicioSesion(this);
        } else if (panel instanceof PanelPerfil) {
            panelAdd = new PanelPerfil(this);
        } else if (panel instanceof PanelLista) {
            panelAdd = new PanelLista(this);
        } else if (panel instanceof PanelLogging) {
            panelAdd = new PanelLogging(this);
        } else if (panel instanceof PanelRegistro) {
            panelAdd = new PanelRegistro(this, this.logging.siEsAdmin());
        }
        if (panelAdd != null) {
            this.addPanel(panelAdd);
        }
    }

    public void addPanelesAtras(JPopupMenu popupMenuAnterior) {
        Stack<JPanel> paneles = this.panelesAnteriores;
        for (int i = paneles.size(); i > 0; i--) {
            JPanel panel = paneles.elementAt(i - 1);
            JMenuItem menu;
            String panelTexto = "";
            if (panel instanceof PanelInicioSesion) {
                panelTexto = "Iniciar Sesion";
            } else if (panel instanceof PanelPerfil) {
                panelTexto = "Perfil Empleado";
            } else if (panel instanceof PanelLogging) {
                panelTexto = "Perfil de Usuario";
            } else if (panel instanceof PanelLista) {
                panelTexto = "Lista De Empleados";
            } else if (panel instanceof PanelRegistro) {
                panelTexto = "Registro de Empleado";
            }
            if (!"".equals(panelTexto)) {
                menu = new JMenuItem(panelTexto);
                menu.addActionListener((ActionEvent ev) -> {
                    String texto = menu.getText();
                    JPanel panel1;
                    switch (texto) {
                        case "Iniciar Sesion":
                            //panel = new PanelInicioSesion(new );
                            break;
                    }
                });
                popupMenuAnterior.add(menu);
            }

        }
    }

    public int indiceEnComboBox(JComboBox<String> combo, String itemABuscar) {
        int indiceCombo = -1;
        itemABuscar = itemABuscar.toUpperCase();
        ComboBoxModel<String> modelCombo = combo.getModel();
        for (int i = 0; i < modelCombo.getSize(); i++) {
            if (itemABuscar.compareTo(modelCombo.getElementAt(i).toUpperCase()) == 0) {
                indiceCombo = i;
                break;
            }
        }
        return indiceCombo;
    }

    public String charToString(char[] pass) {
        String retorno = "";
        for (int i = 0; i < pass.length; i++) {
            char letra = pass[i];
            retorno += letra;
        }
        return retorno;
    }

    public void añadirSoloDigitos(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if (caracter < 48 || caracter > 57 && caracter != '\b') {
            evt.consume();
        }
    }

    public void añadirSoloLetras(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if (caracter >= 48 && caracter <= 57 && caracter != '\b') {
            evt.consume();
        }
    }
    public void cargarDatos() throws ExceptionContraseñaNoValida {
        this.cargarDatos(arbolDeEmpleados, arbolDeLogging);
    }

    public IArbolBusqueda<Empleado, Empleado> getArbolDeEmpleados() {
        return arbolDeEmpleados;
    }

    public IArbolBusqueda<Logging, Logging> getArbolDeLogging() {
        return arbolDeLogging;
    }

    public Logging getLogging() {
        return logging;
    }

    public Empleado getUsuario() {
        return usuario;
    }

    public Empleado getUsuarioAEditar() {
        return usuarioAEditar;
    }

    public Logging getLoggingAEditar() {
        return loggingAEditar;
    }

    public void setArbolEmpl(IArbolBusqueda<Empleado, Empleado> arbolDeEmpleados) {
        this.arbolDeEmpleados = arbolDeEmpleados;
    }

    public void setArbolLogg(IArbolBusqueda<Logging, Logging> arbolDeLogging) {
        this.arbolDeLogging = arbolDeLogging;
    }

    public final void addPanel(JPanel panel) {
        this.add(panel);
        panel.setBounds(FramePrincipal.dimensiones);
        panel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fotoAtras = new javax.swing.JLabel();

        fotoAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconAtras.png"))); // NOI18N
        fotoAtras.setPreferredSize(new java.awt.Dimension(50, 50));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Registro Empleados");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (arbolDeEmpleados != null && arbolDeLogging != null) {
            this.grabarDatos(arbolDeEmpleados, arbolDeLogging);
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(FramePrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            new FramePrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fotoAtras;
    // End of variables declaration//GEN-END:variables
}
