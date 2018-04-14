/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.CtrlMatrizTransiciones;
import Modelo.AutomataPila;
import Utils.ConversorAutomata;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import Control.CtrlVentanaPrincipal;

/**
 *
 * @author Usuario externo
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    JFileChooser seleccionar= new JFileChooser();
    File archivo;
    FileImageInputStream entrada;
    FileImageInputStream salida;
    CtrlMatrizTransiciones matrizT;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        matrizT = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_estadoInicial = new javax.swing.JTextField();
        txt_estados = new javax.swing.JTextField();
        txt_simbolosDeEntrada = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_confInicial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_simbolosEnLaPila = new javax.swing.JTextField();
        cargarArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMatrizT = new javax.swing.JTable();
        btnGenerarMatrizT = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Simbolos de entrada:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));
        jPanel1.add(txt_estadoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 170, 28));
        jPanel1.add(txt_estados, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 170, 28));

        txt_simbolosDeEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_simbolosDeEntradaActionPerformed(evt);
            }
        });
        jPanel1.add(txt_simbolosDeEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 170, 28));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Simbolos en la Pila:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Configuración inicial de la Pila:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Estados:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));
        jPanel1.add(txt_confInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 170, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Estado Inicial:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        txt_simbolosEnLaPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_simbolosEnLaPilaActionPerformed(evt);
            }
        });
        jPanel1.add(txt_simbolosEnLaPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 170, 28));

        cargarArchivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cargarArchivo.setText("Cargar archivo");
        cargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarArchivoActionPerformed(evt);
            }
        });
        jPanel1.add(cargarArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        tblMatrizT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblMatrizT);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 350, 150));

        btnGenerarMatrizT.setText("Generar tabla(s) de transiciones");
        btnGenerarMatrizT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarMatrizTActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerarMatrizT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 190, -1));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(5);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTextArea1.setRows(2);
        jTextArea1.setTabSize(20);
        jTextArea1.setText("    A continuación ingrese cada uno de los datos de su \nautómata de pila o carguelos desde un archivo de texto");
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextArea1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextArea1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 370, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 410, 530));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Hilera:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 210, -1));

        jButton2.setText("Evaluar");
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));
        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 30, 310));

        jLabel7.setText("La hilera ingresada es : ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jTextField2.setText("válida/inválida");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 90, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 300, 530));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Autómatas de pila");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarArchivoActionPerformed
        //CtrlVentanaPrincipal ctrlVP;
        JFileChooser abrir;       
        abrir = new JFileChooser();
        abrir.showOpenDialog(this);
        archivo = abrir.getSelectedFile();
        if(archivo != null){
        try {
            ConversorAutomata conversor = new ConversorAutomata();
            AutomataPila automata = conversor.convertir(archivo);
            //matrizT = new CtrlMatrizTransiciones(automata.getMatrizTransiciones());
            //tblMatrizT.setModel(matrizT);
            String simbolos = "";
            for(String i : automata.getSimbolosEntrada()){
                simbolos += i + " ";
            }
            txt_simbolosDeEntrada.setText(simbolos);
            simbolos = "";
            for(String i : automata.getSimbolosPila()){
                simbolos += i + " ";
            }
            txt_simbolosEnLaPila.setText(simbolos);
            simbolos = "";
            for(String i : automata.getConfiguracionInicial()){
                simbolos += i + " ";
            }
            txt_confInicial.setText(simbolos);
            txt_estadoInicial.setText(automata.getEstadoInicial());
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Su archivo no se ha leido correctamente", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_cargarArchivoActionPerformed

    private void txt_simbolosDeEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_simbolosDeEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_simbolosDeEntradaActionPerformed

    private void txt_simbolosEnLaPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_simbolosEnLaPilaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_simbolosEnLaPilaActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void btnGenerarMatrizTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarMatrizTActionPerformed
          
    }//GEN-LAST:event_btnGenerarMatrizTActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarMatrizT;
    private javax.swing.JButton cargarArchivo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblMatrizT;
    private javax.swing.JTextField txt_confInicial;
    private javax.swing.JTextField txt_estadoInicial;
    private javax.swing.JTextField txt_estados;
    private javax.swing.JTextField txt_simbolosDeEntrada;
    private javax.swing.JTextField txt_simbolosEnLaPila;
    // End of variables declaration//GEN-END:variables
}
