/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Clases.mysql;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author yan_t
 */
public final class genTicker extends javax.swing.JInternalFrame {

    /**
     * Creates new form genTicker
     */
    private int cod_user;
    public genTicker() {
        initComponents();
        imprimir.setVisible(false);
        aviso.setText("AVISO! \n No Somos responsables de robo o perdida \n de su vehiculo");
        llenarcombo();
    }
 
    public void llenarcombo(){
        String query = "select codigo,descripcion from tipos_parqueos where estado = 1";
        mysql cn = new mysql();
        ResultSet rs = cn.ejecutar(query);
        
        try {
            tipo_parqueo.addItem("Seleccione...");
            while(rs.next()){
                tipo_parqueo.addItem(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(genTicker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ParqueosDisponibles(String _tipo_parqueo){
        String query = "select cantidad - (select count(t.numero) from tickets as t " +
                        "inner join tipos_parqueos as tp on t.tipo_parqueo = tp.codigo " +
                        "where tp.descripcion = '"+_tipo_parqueo+"' and t.estado = 1) - "+
                        "(select count(id) from parqueos_averiados where estado_parqueo = 2) as parqueos_disponibles " +
                        "from tipos_parqueos where estado = 1 and descripcion = '"+_tipo_parqueo+"';";
        mysql cn = new mysql();
        ResultSet rs = cn.ejecutar(query);
        
        try {
            if(rs.next()){
               parqueos_disponibles.setText(rs.getString("parqueos_disponibles"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(genTicker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    public void setCodigoUsuario(int codigo){
        this.cod_user = codigo;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGenticker = new javax.swing.JPanel();
        tipo_parqueo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        generar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        Limpiar = new javax.swing.JButton();
        fecha = new javax.swing.JLabel();
        numero = new javax.swing.JLabel();
        tipo = new javax.swing.JLabel();
        costo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        aviso = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        num_ticket = new javax.swing.JLabel();
        parqueos_disponibles = new javax.swing.JLabel();
        imprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("General Ticker");
        setToolTipText("");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        tipo_parqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_parqueoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Tipos Parqueos");

        generar.setBackground(new java.awt.Color(0, 102, 204));
        generar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        generar.setForeground(new java.awt.Color(255, 255, 255));
        generar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/process.png"))); // NOI18N
        generar.setText("Generar");
        generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Generar Ticket");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Información de Ticket");

        Limpiar.setBackground(new java.awt.Color(0, 102, 255));
        Limpiar.setForeground(new java.awt.Color(51, 102, 255));
        Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clear.png"))); // NOI18N
        Limpiar.setToolTipText("Imprimir");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        fecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fecha.setText("Fecha:");

        numero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        numero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numero.setText("Numero");

        tipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tipo.setText("Tipo Parqueo: ");

        costo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        costo.setText("Costo/Hora:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ParkFast");

        aviso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        aviso.setForeground(new java.awt.Color(0, 102, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Parqueos Disponibles");

        num_ticket.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        num_ticket.setForeground(new java.awt.Color(0, 51, 204));
        num_ticket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        num_ticket.setText("--");
        num_ticket.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        parqueos_disponibles.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        parqueos_disponibles.setForeground(new java.awt.Color(0, 51, 204));
        parqueos_disponibles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        parqueos_disponibles.setText("--");
        parqueos_disponibles.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        imprimir.setBackground(new java.awt.Color(0, 102, 255));
        imprimir.setForeground(new java.awt.Color(51, 102, 255));
        imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Print.png"))); // NOI18N
        imprimir.setToolTipText("Imprimir");

        javax.swing.GroupLayout panelGentickerLayout = new javax.swing.GroupLayout(panelGenticker);
        panelGenticker.setLayout(panelGentickerLayout);
        panelGentickerLayout.setHorizontalGroup(
            panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(aviso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelGentickerLayout.createSequentialGroup()
                .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGentickerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(parqueos_disponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tipo_parqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGentickerLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(62, 62, 62))
                    .addGroup(panelGentickerLayout.createSequentialGroup()
                        .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGentickerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelGentickerLayout.createSequentialGroup()
                                        .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tipo, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                            .addComponent(costo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(25, 25, 25))
                                    .addGroup(panelGentickerLayout.createSequentialGroup()
                                        .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(13, 13, 13))))
                            .addGroup(panelGentickerLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGentickerLayout.createSequentialGroup()
                                .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(num_ticket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        panelGentickerLayout.setVerticalGroup(
            panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGentickerLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGentickerLayout.createSequentialGroup()
                        .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(parqueos_disponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipo_parqueo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(generar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelGentickerLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(panelGentickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelGentickerLayout.createSequentialGroup()
                        .addComponent(fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(costo))
                    .addGroup(panelGentickerLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(numero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(num_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGenticker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(panelGenticker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarActionPerformed
        if(tipo_parqueo.getSelectedIndex()>0){
            tipo_parqueo.setSelectedItem("Motocicletas");
            String _tipo_parqueo = tipo_parqueo.getSelectedItem().toString();
            String query = "call p_generar_ticker('"+_tipo_parqueo+"')";
            mysql cn = new mysql();
            ResultSet rs = cn.ejecutar(query);
            try {
                if(rs.next()){
                   tipo_parqueo.setSelectedIndex(0);
                   fecha.setText("Fecha: "+rs.getString("fecha_completa"));
                   tipo.setText("Tipo Parqueo: "+rs.getString("tipo_parqueo"));
                   costo.setText("Costo/Hora: "+rs.getString("precio"));
                   num_ticket.setText(rs.getString("numero"));
                   imprimir.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(genTicker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
           JOptionPane.showMessageDialog(null,"Debe Seleccionar Tipo de Parqueo!");
        }
    }//GEN-LAST:event_generarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        imprimir.setVisible(false);
        tipo_parqueo.setSelectedIndex(0);
        fecha.setText("Fecha:");
        tipo.setText("Tipo Parqueo:");
        costo.setText("Costo/Hora:");
        num_ticket.setText("--");
    }//GEN-LAST:event_LimpiarActionPerformed

    private void tipo_parqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_parqueoActionPerformed
       if(tipo_parqueo.getSelectedIndex()>0){
        String _tipo_parqueo = tipo_parqueo.getSelectedItem().toString();
        ParqueosDisponibles(_tipo_parqueo);
       }else{
           parqueos_disponibles.setText("--");
       }
    }//GEN-LAST:event_tipo_parqueoActionPerformed

    private void setIconImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel aviso;
    private javax.swing.JLabel costo;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton generar;
    private javax.swing.JButton imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel num_ticket;
    private javax.swing.JLabel numero;
    private javax.swing.JPanel panelGenticker;
    private javax.swing.JLabel parqueos_disponibles;
    private javax.swing.JLabel tipo;
    private javax.swing.JComboBox<String> tipo_parqueo;
    // End of variables declaration//GEN-END:variables
}
