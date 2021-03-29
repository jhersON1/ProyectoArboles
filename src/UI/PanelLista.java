/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author neON
 */
public class PanelLista extends javax.swing.JPanel {

    private final FramePrincipal frame;

    /**
     * Creates new form PanelListaEmpleados
     * @param frame Es la variable del frame donde añadiremos este panel.
     * 
     */
    public PanelLista(FramePrincipal frame) {
        this.frame = frame;
        initComponents();
        //super.setBounds(r);
        frame.cargarDatosEnTabla((short) 1, this.tablaEmpleados);
        frame.cargarIconoAtras(iconAtras);
        frame.addPanelesAtras(popupMenuAnterior);
        this.cargarPerfil();
    }

    private void cargarPerfil() {
        textNombre.setText(frame.getUsuario().getNombreCompleto());
        textUsuario.setText(frame.getLogging().getUsuario());
        if (frame.getUsuario().getFotografiaPersonal() != null) {
            this.labelFoto.setIcon(frame.getUsuario().getFotografiaPersonal());
        }
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
        popupMenuAnterior = new javax.swing.JPopupMenu();
        buttonNuevo = new javax.swing.JButton();
        buttonActualizar = new javax.swing.JButton();
        buttonEditar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        panelCabecera = new javax.swing.JPanel();
        iconAtras = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelFoto = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        textNombre = new javax.swing.JTextField();
        textUsuario = new javax.swing.JTextField();

        fotoAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconAtras.png"))); // NOI18N
        fotoAtras.setPreferredSize(new java.awt.Dimension(50, 50));

        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 500));

        buttonNuevo.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        buttonNuevo.setText("Nuevo");
        buttonNuevo.setPreferredSize(new java.awt.Dimension(100, 25));
        buttonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevoActionPerformed(evt);
            }
        });

        buttonActualizar.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        buttonActualizar.setText("Actualizar");
        buttonActualizar.setPreferredSize(new java.awt.Dimension(100, 25));
        buttonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActualizarActionPerformed(evt);
            }
        });

        buttonEditar.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        buttonEditar.setText("Editar");
        buttonEditar.setPreferredSize(new java.awt.Dimension(100, 25));
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });

        buttonEliminar.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        buttonEliminar.setText("Eliminar");
        buttonEliminar.setPreferredSize(new java.awt.Dimension(100, 25));
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(685, 250));

        tablaEmpleados.setAutoCreateRowSorter(true);
        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellidos", "CI", "Cargo", "Sueldo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEmpleados.setPreferredSize(new java.awt.Dimension(685, 280));
        tablaEmpleados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaEmpleados);

        panelCabecera.setBackground(new java.awt.Color(102, 204, 255));
        panelCabecera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelCabecera.setPreferredSize(new java.awt.Dimension(685, 76));

        iconAtras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconAtras.setText("Atras");
        iconAtras.setPreferredSize(new java.awt.Dimension(50, 50));
        iconAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconAtrasMouseClicked(evt);
            }
        });

        labelUsuario.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        labelUsuario.setText("Usuario");

        labelFoto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto.setAlignmentX(0.5F);
        labelFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelFoto.setMaximumSize(new java.awt.Dimension(150, 150));
        labelFoto.setMinimumSize(new java.awt.Dimension(50, 50));
        labelFoto.setPreferredSize(new java.awt.Dimension(70, 70));

        labelNombre.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        labelNombre.setText("Nombre");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        textNombre.setEditable(false);
        textNombre.setBackground(new java.awt.Color(255, 255, 255));
        textNombre.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N

        textUsuario.setEditable(false);
        textUsuario.setBackground(new java.awt.Color(255, 255, 255));
        textUsuario.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
        panelCabecera.setLayout(panelCabeceraLayout);
        panelCabeceraLayout.setHorizontalGroup(
            panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCabeceraLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(iconAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        panelCabeceraLayout.setVerticalGroup(
            panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelCabeceraLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(labelFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCabeceraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(buttonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(buttonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
            .addComponent(panelCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActualizarActionPerformed
        // TODO add your handling code here:
        frame.cargarDatosEnTabla((short) 1, this.tablaEmpleados);
    }//GEN-LAST:event_buttonActualizarActionPerformed

    private void iconAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconAtrasMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON1) { //Click Izquierdo
            this.setVisible(false);
            frame.volverPestañaAtras(frame.getUsuario(), null, frame.getLogging(), false);
            this.setVisible(false);
            this.initComponents();
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {//Click Derecho
            MouseEvent eventoMouse = new MouseEvent(iconAtras, 0, evt.getWhen(),
                    KeyEvent.VK_UNDEFINED, 0, frame.getY() + frame.getY(),
                    1, true);
            popupMenuAnterior.show(evt.getComponent(), evt.getX(), evt.getY() + 20);
            for (int i = 0; i < this.popupMenuAnterior.getComponentCount(); i++) {
            }
        }
    }//GEN-LAST:event_iconAtrasMouseClicked

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        // TODO add your handling code here:
        frame.getPanelesAnteriores().push(this);
        this.setVisible(false);
        frame.setUsuarioAEditar(null);
        PanelRegistro panelAdd = new PanelRegistro(frame,false);
        frame.addPanel(panelAdd);
        this.initComponents();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        // TODO add your handling code here:
        int filaElegida = tablaEmpleados.getSelectedRow();
        if (filaElegida != -1) {
            frame.getPanelesAnteriores().push(this);
            Object ci = tablaEmpleados.getValueAt(filaElegida, 2);
            int CI = (int) ci;
            Empleado usuarioAEditar = frame.getArbolDeEmpleados().buscar(new Empleado(CI));
            frame.setUsuarioAEditar(usuarioAEditar);
            this.setVisible(false);
            PanelRegistro panelAdd = new PanelRegistro(frame,true);
            frame.addPanel(panelAdd);
            this.initComponents();
        }
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        // TODO add your handling code here:
        int filaElegida = tablaEmpleados.getSelectedRow();
        if (filaElegida != -1) {
            Object ci = tablaEmpleados.getValueAt(filaElegida, 2);
            int CI = (int) ci;
            Empleado empleadoAEliminar = frame.getArbolDeEmpleados().buscar(new Empleado(CI));
            Logging buscado = new Logging("", "0000".toCharArray(), empleadoAEliminar.getCI(), false);
            buscado.setComparacionCi(true);
            Logging loggingA = frame.getArbolDeLogging().buscar(buscado);
            buscado.setComparacionCi(false);
            int dialogButton = 0;
            dialogButton = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este usuario?", "Eliminar Usuario", dialogButton);
            if (dialogButton == JOptionPane.YES_OPTION) { //The ISSUE is here
                frame.getArbolDeEmpleados().eliminar(empleadoAEliminar);
                frame.getArbolDeLogging().eliminar(loggingA);
            }
        }
    }//GEN-LAST:event_buttonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonActualizar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JLabel fotoAtras;
    private javax.swing.JLabel iconAtras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelCabecera;
    private javax.swing.JPopupMenu popupMenuAnterior;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textUsuario;
    // End of variables declaration//GEN-END:variables

}
