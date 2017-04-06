/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Clases.Hash;
import Clases.Utilidades;
import static Clases.Utilidades.isNumeric;
import Clases.mysql;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yan_t
 */
public final class regUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form regUsuario
     */
    public regUsuario() {
        initComponents();
        textconfclave.setVisible(false);
        this.llenarcombo();
        this.buscarUsuarios("");
        this.editrows();
        labelLogin.setVisible(false);
        labelTipo.setVisible(false);
        labelClave.setVisible(false);
        labelConfClave.setVisible(false);
        labelNumInt.setVisible(false);
    }
    
    private int coduser;
    public void buscarUsuarios(String texto){
        String query = "call p_listarUsuarios('"+texto.trim()+"')";
        mysql cn = new mysql();
        ResultSet rs = cn.ejecutar(query);
        cn.desconectar();
        DefaultTableModel model1 = (DefaultTableModel)tUsuarios.getModel();
        model1.setNumRows(0);
        try {
            while(rs.next()){
                model1.addRow(new Object[]{rs.getString("codigo"),rs.getString("login"),rs.getString("tipo_usuario"),rs.getString("numero_intentos"),rs.getString("estado")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(regUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setCodUser(int coduser){
        this.coduser = coduser;
    }
    private void editrows(){
        tUsuarios.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if(me.getClickCount() == 2){
                    DefaultTableModel model1 = (DefaultTableModel) tUsuarios.getModel();
                    int index = tUsuarios.getSelectedRow();
                    login.setEditable(false);
                    codigo.setText((String) tUsuarios.getValueAt(index, 0));
                    login.setText((String) tUsuarios.getValueAt(index, 1));
                    tipo_usuario.setSelectedItem(tUsuarios.getValueAt(index, 2));
                    numInt.setText((String) tUsuarios.getValueAt(index, 3));
                    boolean estado = (Integer.parseInt(tUsuarios.getValueAt(index,4).toString())==1)?true:false;
                    status.setSelected(estado);
                }
            }
        });
    }
    
    public void limpiar(){
        codigo.setText("0");
        login.setText("");
        tipo_usuario.setSelectedIndex(0);
        clave.setText("");
        confClave.setText("");
        numInt.setText("");
        status.setSelected(true);
        login.setEditable(true);
    }
    private void llenarcombo(){
        String query = "select codigo,descripcion from tipos_usuarios where estado = 1";
        mysql cn = new mysql();
        ResultSet rs = cn.ejecutar(query);
        cn.desconectar();
        try {
            tipo_usuario.addItem("Seleccione...");
            while(rs.next()){
                tipo_usuario.addItem(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(genTicker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userInfoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        login = new javax.swing.JTextField();
        numInt = new javax.swing.JTextField();
        tipo_usuario = new javax.swing.JComboBox<>();
        clave = new javax.swing.JPasswordField();
        confClave = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        textconfclave = new javax.swing.JLabel();
        status = new javax.swing.JCheckBox();
        labelLogin = new javax.swing.JLabel();
        labelClave = new javax.swing.JLabel();
        labelNumInt = new javax.swing.JLabel();
        labelConfClave = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tUsuarios = new JTable(){

            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        txtBuscar = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        limpiar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Registro Usuarios");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registro Usuarios");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Codigo:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Usuario:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tipo Usuario:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Clave:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Confirmar Clave:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("# Intentos:");

        codigo.setEditable(false);
        codigo.setText("0");
        codigo.setToolTipText("Codigo Usuario");

        login.setToolTipText("Login");

        numInt.setToolTipText("Numero Intentos");
        numInt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numIntFocusLost(evt);
            }
        });

        tipo_usuario.setToolTipText("Tipo Usuario");

        clave.setToolTipText("Clave Usuario");

        confClave.setToolTipText("Confirmacion Clave");
        confClave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                confClaveFocusLost(evt);
            }
        });

        textconfclave.setForeground(new java.awt.Color(255, 51, 51));
        textconfclave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textconfclave.setText("Las Claves no condiden");

        status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        status.setSelected(true);
        status.setText("Estado");

        labelLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelLogin.setForeground(new java.awt.Color(255, 0, 0));
        labelLogin.setText("*");

        labelClave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelClave.setForeground(new java.awt.Color(255, 0, 0));
        labelClave.setText("*");

        labelNumInt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelNumInt.setForeground(new java.awt.Color(255, 0, 0));
        labelNumInt.setText("*");

        labelConfClave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelConfClave.setForeground(new java.awt.Color(255, 0, 0));
        labelConfClave.setText("*");

        labelTipo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTipo.setForeground(new java.awt.Color(255, 0, 0));
        labelTipo.setText("*");

        javax.swing.GroupLayout userInfoPanelLayout = new javax.swing.GroupLayout(userInfoPanel);
        userInfoPanel.setLayout(userInfoPanelLayout);
        userInfoPanelLayout.setHorizontalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userInfoPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(userInfoPanelLayout.createSequentialGroup()
                        .addComponent(numInt, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNumInt))
                    .addGroup(userInfoPanelLayout.createSequentialGroup()
                        .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(clave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(login, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLogin)
                            .addComponent(labelClave))))
                .addGap(54, 54, 54)
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userInfoPanelLayout.createSequentialGroup()
                        .addComponent(status)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(userInfoPanelLayout.createSequentialGroup()
                        .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(tipo_usuario, 0, 172, Short.MAX_VALUE)
                            .addComponent(confClave)
                            .addComponent(textconfclave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelConfClave)
                            .addComponent(labelTipo))
                        .addGap(0, 38, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2))
            .addGroup(userInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        userInfoPanelLayout.setVerticalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(3, 3, 3)
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelClave, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelConfClave))
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userInfoPanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel7))
                    .addGroup(userInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textconfclave)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status)
                    .addComponent(labelNumInt, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
        );

        tUsuarios.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));
        tUsuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Usuario", "Tipo", "# Intentos", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tUsuarios.setToolTipText("Doble Click (EDITAR)");
        tUsuarios.setRowSelectionAllowed(true);
        tUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tUsuarios);
        if (tUsuarios.getColumnModel().getColumnCount() > 0) {
            tUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tUsuarios.getColumnModel().getColumn(0).setPreferredWidth(10);
            tUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tUsuarios.getColumnModel().getColumn(1).setPreferredWidth(35);
            tUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tUsuarios.getColumnModel().getColumn(2).setPreferredWidth(35);
            tUsuarios.getColumnModel().getColumn(3).setResizable(false);
            tUsuarios.getColumnModel().getColumn(3).setPreferredWidth(10);
            tUsuarios.getColumnModel().getColumn(4).setResizable(false);
            tUsuarios.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        buscar.setToolTipText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        limpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clean.png"))); // NOI18N
        limpiar.setText("Limpiar");
        limpiar.setToolTipText("Limpiar Campos");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        cancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cancel.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.setToolTipText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        guardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setToolTipText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(userInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confClaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confClaveFocusLost
        // TODO add your handling code here:
        String _clave = clave.getText();
        String _confClave = confClave.getText();
        if(!_clave.equals(_confClave)){
            Color color = new Color(255,51,102);
            confClave.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 4, 4), new LineBorder(color, 1)));
            textconfclave.setText("Las Claves no conciden!");
            textconfclave.setVisible(true);
        }else{
            confClave.setBorder(clave.getBorder());
            textconfclave.setVisible(false);
        }
    }//GEN-LAST:event_confClaveFocusLost

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
        String texto = txtBuscar.getText().trim();
        if("".equals(texto)){
            DefaultTableModel model1 = (DefaultTableModel) tUsuarios.getModel();
            model1.setNumRows(0);
            this.buscarUsuarios(texto);
        }
    }//GEN-LAST:event_buscarActionPerformed
    private boolean validar(){
        if(login.getText().trim().isEmpty()){
            labelLogin.setVisible(true);
        }else{
            labelLogin.setVisible(false);
        } 
        if(tipo_usuario.getSelectedIndex()<1){
            labelTipo.setVisible(true);
        }else{
            labelTipo.setVisible(false);
        } 
        if(numInt.getText().trim().isEmpty()){
            labelNumInt.setVisible(true);
        }else{
            labelNumInt.setVisible(false);
            return true;
        }
       return false; 
    }
    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        // TODO add your handling code here:
        this.limpiar();
    }//GEN-LAST:event_limpiarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        this.limpiar();
        this.setVisible(false);
    }//GEN-LAST:event_cancelarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        if(confClave.getText().equals(clave.getText())){
            if(this.validar()){ 
                String passsha1 = "";
                if(!clave.getText().isEmpty())passsha1 = Hash.getSha1(clave.getText());
                String query = "call p_actualizarUsuarios("+codigo.getText().trim()+",'"+login.getText().trim()+"','"+passsha1+"','"+tipo_usuario.getSelectedItem().toString()+"',"+numInt.getText().trim()+","+(status.isSelected())+","+coduser+")";
                mysql cn = new mysql();
                ResultSet rs = cn.ejecutar(query);
                cn.desconectar();
                try {
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, rs.getString("mensaje")+"!");
                        limpiar();
                        this.buscarUsuarios("");
                    }else{
                        JOptionPane.showMessageDialog(null, "Problemas al Insertar los datos!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(genTicker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Los Campos marcados son obligatorios!");
            }
         }else{
            JOptionPane.showMessageDialog(null, "Las Claves no coinciden!");
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void numIntFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numIntFocusLost
        // TODO add your handling code here:
        if(!"".equals(numInt.getText().trim())){
            if(!Utilidades.isNumeric(numInt.getText().trim())){
                numInt.setText("");
                numInt.requestFocus();
                labelNumInt.setVisible(true);
                labelNumInt.setToolTipText("Campo solo Admite numeros!");
            }else{
                labelNumInt.setVisible(false);
            }
        }else{
            labelNumInt.setVisible(false);
        }
    }//GEN-LAST:event_numIntFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JButton cancelar;
    private javax.swing.JPasswordField clave;
    private javax.swing.JTextField codigo;
    private javax.swing.JPasswordField confClave;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelClave;
    private javax.swing.JLabel labelConfClave;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelNumInt;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JButton limpiar;
    private javax.swing.JTextField login;
    private javax.swing.JTextField numInt;
    private javax.swing.JCheckBox status;
    private javax.swing.JTable tUsuarios;
    private javax.swing.JLabel textconfclave;
    private javax.swing.JComboBox<String> tipo_usuario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JPanel userInfoPanel;
    // End of variables declaration//GEN-END:variables
}
