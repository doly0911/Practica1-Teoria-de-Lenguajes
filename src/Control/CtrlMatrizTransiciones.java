/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pao
 */
public class CtrlMatrizTransiciones extends AbstractTableModel{
    String[][] matrizTransiciones;
    
    public CtrlMatrizTransiciones(String[][] matrizTransiciones) {
        this.matrizTransiciones = matrizTransiciones;
    }

    @Override
    public int getRowCount() {
        return matrizTransiciones.length;
    }

    @Override
    public int getColumnCount() {
        return matrizTransiciones[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return matrizTransiciones[rowIndex][columnIndex];
    }
    
}
