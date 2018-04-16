/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Dao.AutomataPilaDAO;
import Dao.IAutomataPilaDAO;
import Modelo.AutomataPila;
import Utils.ConversorAutomata;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import Modelo.Estado;
import Utils.ReconocedorHilera;
import excepcion.AutomataPilaExcepcion;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.table.DefaultTableModel;
import Control.CtrlVentanaPrincipal;

/**
 *
 * @author Usuario externo
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    File archivo;
    public AutomataPila automata;
    IAutomataPilaDAO automataDAO;
    public static ArrayList<Estado> estados;  //no es necesario que sea global
    public static String estadoSeleccionado;
    private DefaultTableModel defaultTableModel;
    private Stack<String> pila;
    private ReconocedorHilera reconocedorHilera;
    private int estadoHilera;
    private int numeroCaracter;
    private String hilera;
    private String caracterAcual;
    private CtrlVentanaPrincipal ctrlVentanaPrincipal;
    
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        defaultTableModel = new DefaultTableModel(10, 1);
        tbl_pila.setModel(defaultTableModel);
        ctrlVentanaPrincipal = new CtrlVentanaPrincipal();
        estadoHilera = 0;
        numeroCaracter = 0;
        hilera = "";
        caracterAcual = "";
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
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_confInicial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cargarArchivo = new javax.swing.JButton();
        cboEstados = new javax.swing.JComboBox<>();
        btnVerModificarEstado = new javax.swing.JButton();
        btnAñadirEstado = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_simbolosDeEntrada = new javax.swing.JTextField();
        cboSimEntrada = new javax.swing.JComboBox<>();
        btnAgregarSimEntrada = new javax.swing.JButton();
        btnModificarSimEntrada = new javax.swing.JButton();
        btnEliminarSimEntrada = new javax.swing.JButton();
        txt_nuevoSimboloEntrada = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_simbolosEnLaPila = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboSimPila = new javax.swing.JComboBox<>();
        btnAgregarSimPila = new javax.swing.JButton();
        btnModificarSimPila = new javax.swing.JButton();
        btnEliminarSimPila = new javax.swing.JButton();
        txt_nuevoSimboloPila = new javax.swing.JTextField();
        txtEstadoInicial = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btn_iniciarPila = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea_hilera = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_pila = new javax.swing.JTable();
        btn_siguienteCaracter = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Configuración inicial de la Pila:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Estados:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));
        jPanel1.add(txt_confInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, 190, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Estado Inicial:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        cargarArchivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cargarArchivo.setText("Cargar archivo");
        cargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarArchivoActionPerformed(evt);
            }
        });
        jPanel1.add(cargarArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        jPanel1.add(cboEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 80, 30));

        btnVerModificarEstado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVerModificarEstado.setForeground(new java.awt.Color(255, 255, 255));
        btnVerModificarEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu_show.png"))); // NOI18N
        btnVerModificarEstado.setToolTipText("Ver o Editar");
        btnVerModificarEstado.setBorder(null);
        btnVerModificarEstado.setBorderPainted(false);
        btnVerModificarEstado.setContentAreaFilled(false);
        btnVerModificarEstado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVerModificarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerModificarEstadoActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerModificarEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 30, 30));

        btnAñadirEstado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAñadirEstado.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadirEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add_dark.png"))); // NOI18N
        btnAñadirEstado.setToolTipText("Añadir");
        btnAñadirEstado.setBorder(null);
        btnAñadirEstado.setBorderPainted(false);
        btnAñadirEstado.setContentAreaFilled(false);
        btnAñadirEstado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAñadirEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirEstadoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAñadirEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 30, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("autómata de pila o carguelos desde un archivo de texto");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("A continuación ingrese cada uno de los datos de su ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Simbolos de entrada:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txt_simbolosDeEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_simbolosDeEntradaActionPerformed(evt);
            }
        });
        jPanel3.add(txt_simbolosDeEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 200, 28));

        jPanel3.add(cboSimEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 30));

        btnAgregarSimEntrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregarSimEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarSimEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add_dark.png"))); // NOI18N
        btnAgregarSimEntrada.setToolTipText("Agregar");
        btnAgregarSimEntrada.setBorder(null);
        btnAgregarSimEntrada.setBorderPainted(false);
        btnAgregarSimEntrada.setContentAreaFilled(false);
        btnAgregarSimEntrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregarSimEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSimEntradaActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregarSimEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 30, 30));

        btnModificarSimEntrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificarSimEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarSimEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit.png"))); // NOI18N
        btnModificarSimEntrada.setToolTipText("Modificar");
        btnModificarSimEntrada.setBorder(null);
        btnModificarSimEntrada.setBorderPainted(false);
        btnModificarSimEntrada.setContentAreaFilled(false);
        btnModificarSimEntrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificarSimEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarSimEntradaActionPerformed(evt);
            }
        });
        jPanel3.add(btnModificarSimEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 30, 30));

        btnEliminarSimEntrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminarSimEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarSimEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/showdeleted.png"))); // NOI18N
        btnEliminarSimEntrada.setToolTipText("Eliminar");
        btnEliminarSimEntrada.setBorder(null);
        btnEliminarSimEntrada.setBorderPainted(false);
        btnEliminarSimEntrada.setContentAreaFilled(false);
        btnEliminarSimEntrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminarSimEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSimEntradaActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminarSimEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 30, 30));
        jPanel3.add(txt_nuevoSimboloEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 90, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Seleccione un símbolo para realizar la operación que desee:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 380, 110));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Simbolos en la Pila:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txt_simbolosEnLaPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_simbolosEnLaPilaActionPerformed(evt);
            }
        });
        jPanel4.add(txt_simbolosEnLaPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 200, 28));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Seleccione un símbolo para realizar la operación que desee:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jPanel4.add(cboSimPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, 30));

        btnAgregarSimPila.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregarSimPila.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarSimPila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add_dark.png"))); // NOI18N
        btnAgregarSimPila.setToolTipText("Agregar");
        btnAgregarSimPila.setBorder(null);
        btnAgregarSimPila.setBorderPainted(false);
        btnAgregarSimPila.setContentAreaFilled(false);
        btnAgregarSimPila.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregarSimPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSimPilaActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregarSimPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 30, 30));

        btnModificarSimPila.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificarSimPila.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarSimPila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit.png"))); // NOI18N
        btnModificarSimPila.setToolTipText("Modificar");
        btnModificarSimPila.setBorder(null);
        btnModificarSimPila.setBorderPainted(false);
        btnModificarSimPila.setContentAreaFilled(false);
        btnModificarSimPila.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificarSimPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarSimPilaActionPerformed(evt);
            }
        });
        jPanel4.add(btnModificarSimPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 30, 30));

        btnEliminarSimPila.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminarSimPila.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarSimPila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/showdeleted.png"))); // NOI18N
        btnEliminarSimPila.setToolTipText("Eliminar");
        btnEliminarSimPila.setBorder(null);
        btnEliminarSimPila.setBorderPainted(false);
        btnEliminarSimPila.setContentAreaFilled(false);
        btnEliminarSimPila.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminarSimPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSimPilaActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminarSimPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 30, 30));
        jPanel4.add(txt_nuevoSimboloPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 90, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 380, 120));
        jPanel1.add(txtEstadoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 80, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 420, 660));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Hilera:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btn_iniciarPila.setText("Iniciar pila");
        btn_iniciarPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_iniciarPilaActionPerformed(evt);
            }
        });
        jPanel2.add(btn_iniciarPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        txtArea_hilera.setColumns(20);
        txtArea_hilera.setRows(5);
        jScrollPane1.setViewportView(txtArea_hilera);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 200, 40));

        tbl_pila.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_pila);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 50, 340));

        btn_siguienteCaracter.setText("Siguiente carácter");
        btn_siguienteCaracter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguienteCaracterActionPerformed(evt);
            }
        });
        jPanel2.add(btn_siguienteCaracter, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 300, 530));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Autómatas de pila");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarArchivoActionPerformed
        JFileChooser abrir;       
        abrir = new JFileChooser();
        abrir.showOpenDialog(this);
        archivo = abrir.getSelectedFile();
        if(archivo != null){
        try {
            ConversorAutomata conversor = new ConversorAutomata();
            automata = conversor.convertir(archivo);
            llenarFormulario();    
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

    private void btnAñadirEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAñadirEstadoActionPerformed

    private void btnVerModificarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerModificarEstadoActionPerformed
        estadoSeleccionado = cboEstados.getSelectedItem().toString();
        VistaEditarEstado vee = new VistaEditarEstado(automata);
        vee.setVisible(true);
        
    }//GEN-LAST:event_btnVerModificarEstadoActionPerformed

    private void btnAgregarSimEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSimEntradaActionPerformed
        automataDAO = new AutomataPilaDAO();
        try {
            automata = automataDAO.agregarSimboloEntrada(automata, txt_nuevoSimboloEntrada.getText());
            llenarFormulario();
        } catch (AutomataPilaExcepcion ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar simbolo de entrada: "+ ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarSimEntradaActionPerformed

    private void btnModificarSimEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarSimEntradaActionPerformed
        automataDAO = new AutomataPilaDAO();
        String simbolo = (String) this.cboSimEntrada.getSelectedItem();
        String nuevoSimbolo = this.txt_nuevoSimboloEntrada.getText();
        try {
            automata = automataDAO.modificarSimboloEntrada(automata, simbolo, nuevoSimbolo);
            llenarFormulario();
        } catch (AutomataPilaExcepcion ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar simbolo de entrada: "+ ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarSimEntradaActionPerformed

    private void btnEliminarSimEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSimEntradaActionPerformed
        automataDAO = new AutomataPilaDAO();
        String simbolo = (String) this.cboSimEntrada.getSelectedItem();
        try {
            automata = automataDAO.eliminarSimboloEntrada(automata, simbolo);
            llenarFormulario();
        } catch (AutomataPilaExcepcion ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar simbolo de entrada: "+ ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarSimEntradaActionPerformed

    private void btn_iniciarPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_iniciarPilaActionPerformed
       hilera = this.txtArea_hilera.getText();

       ArrayList<String> configuracionInicial = automata.getConfiguracionInicial(); // obtengo la config de la pila
       int numeroSimbolos = configuracionInicial.size();
       defaultTableModel.setColumnCount(1);
       defaultTableModel.setRowCount(numeroSimbolos); //que se configure segun los simbolos a apilar en la conf inicial
       reconocedorHilera = new ReconocedorHilera(automata); //constructor que me apila la config inicial
       pila = reconocedorHilera.getPila();
       List<String> pilaCopia = new ArrayList<>(pila); //hago copia de la pila para usarla en btn_siguienteCaracter
       llenarPila(pilaCopia); //mapea los valores de mi defaultTableModel a la pila Copia
       
       estadoHilera =0;
       numeroCaracter =0;
    }//GEN-LAST:event_btn_iniciarPilaActionPerformed

    private void btn_siguienteCaracterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguienteCaracterActionPerformed
        //0:avance, 1:acepte, 2:rechace, 3: retenga
       List<String> pilaCopia;
       switch (estadoHilera) {

           case 0:
               caracterAcual = String.valueOf(hilera.charAt(numeroCaracter));
               numeroCaracter++;
               estadoHilera = reconocedorHilera.recorrerCaracter(caracterAcual); // si es true significa que si existe en mi reocnomiento 
               pila = reconocedorHilera.getPila(); // actualizo la pila  
               pilaCopia = new ArrayList<>(pila);  //actualizo la pila
               llenarPila(pilaCopia); //la lleno con los valores actuales                
               break;

           case 1:
               JOptionPane.showMessageDialog(null, "Hilera Aceptada", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
               break;

           case 2:
               JOptionPane.showMessageDialog(null, "Hilera Rechazada", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
               break;

           case 3:
               estadoHilera = reconocedorHilera.recorrerCaracter(caracterAcual); // si es true significa que si existe en mi reocnomiento 
               pila = reconocedorHilera.getPila(); // actualizo la pila  
               pilaCopia = new ArrayList<>(pila);  //actualizo la pila
               llenarPila(pilaCopia); //la lleno con los valores actuales
               break;

       }
    }//GEN-LAST:event_btn_siguienteCaracterActionPerformed

    private void btnAgregarSimPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSimPilaActionPerformed
        automataDAO = new AutomataPilaDAO();
        try {
            automata = automataDAO.agregarSimboloPila(automata, txt_nuevoSimboloPila.getText());
            llenarFormulario();
        } catch (AutomataPilaExcepcion ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar simbolo de entrada: "+ ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarSimPilaActionPerformed

    private void btnModificarSimPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarSimPilaActionPerformed
        automataDAO = new AutomataPilaDAO();
        String simbolo = (String) this.cboSimPila.getSelectedItem();
        String nuevoSimbolo = this.txt_nuevoSimboloPila.getText();
        try {
            automata = automataDAO.modificarSimboloPila(automata, simbolo, nuevoSimbolo);
            llenarFormulario();
        } catch (AutomataPilaExcepcion ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar simbolo de pila: "+ ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarSimPilaActionPerformed

    private void btnEliminarSimPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSimPilaActionPerformed
        automataDAO = new AutomataPilaDAO();
        String simbolo = (String) this.cboSimPila.getSelectedItem();
        try {
            automata = automataDAO.eliminarSimboloPila(automata, simbolo);
            llenarFormulario();
        } catch (AutomataPilaExcepcion ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar simbolo de pila: "+ ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarSimPilaActionPerformed

    private void llenarFormulario(){
        
        String simbolos = "";
        cboSimEntrada.removeAllItems();
        for(String i : automata.getSimbolosEntrada()){
            simbolos += i + " ";
            cboSimEntrada.addItem(i);
        }
        txt_simbolosDeEntrada.setText(simbolos);
        
        simbolos = "";
        cboSimPila.removeAllItems();
        for(String i : automata.getSimbolosPila()){
            simbolos += i + " ";
            cboSimPila.addItem(i);
        }
        txt_simbolosEnLaPila.setText(simbolos);
        
        simbolos = "";
        for(String i : automata.getConfiguracionInicial()){
            simbolos += i + " ";
        }
        
        txt_confInicial.setText(simbolos);
        
        txtEstadoInicial.setText(automata.getEstadoInicial());
        
        cboEstados.removeAllItems();
        estados = automata.getEstados();
        for(Estado e : estados){
            cboEstados.addItem(e.getNombre());
        } 
    }
    
    private void llenarPila(List<String> pila) {
       int col = defaultTableModel.getColumnCount();
       defaultTableModel.setRowCount(pila.size());
       int fil = defaultTableModel.getRowCount();
       for (int i = 0; i < fil; i++) {
           for (int j = 0; j < col; j++) {
               defaultTableModel.setValueAt(pila.get(i), i, j); // me modifica en defaultTable (objeto a agregar, en la fila y columna dada)
           }
       }
   }
    
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
    private javax.swing.JButton btnAgregarSimEntrada;
    private javax.swing.JButton btnAgregarSimPila;
    private javax.swing.JButton btnAñadirEstado;
    private javax.swing.JButton btnEliminarSimEntrada;
    private javax.swing.JButton btnEliminarSimPila;
    private javax.swing.JButton btnModificarSimEntrada;
    private javax.swing.JButton btnModificarSimPila;
    private javax.swing.JButton btnVerModificarEstado;
    private javax.swing.JButton btn_iniciarPila;
    private javax.swing.JButton btn_siguienteCaracter;
    private javax.swing.JButton cargarArchivo;
    private javax.swing.JComboBox<String> cboEstados;
    private javax.swing.JComboBox<String> cboSimEntrada;
    private javax.swing.JComboBox<String> cboSimPila;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_pila;
    private javax.swing.JTextArea txtArea_hilera;
    private javax.swing.JTextField txtEstadoInicial;
    private javax.swing.JTextField txt_confInicial;
    private javax.swing.JTextField txt_nuevoSimboloEntrada;
    private javax.swing.JTextField txt_nuevoSimboloPila;
    private javax.swing.JTextField txt_simbolosDeEntrada;
    private javax.swing.JTextField txt_simbolosEnLaPila;
    // End of variables declaration//GEN-END:variables
}
