/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Telefone;

/**
 *
 * @author Leandro Guina
 */
public class TelefoneTM extends AbstractTableModel {

    private final List<Telefone> linhas;
    private final String[] colunas = new String[]{"Id", "Nome"};
    
    public TelefoneTM() {
        this.linhas = new ArrayList<>();
    }

    public TelefoneTM(List<Telefone> lista) {
        this.linhas = new ArrayList<>(lista);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Telefone telefone = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return telefone.getId();
            case 1:
                return telefone.getNumero();
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Telefone telefone = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                telefone.setId((Integer)aValue);
                break;
            case 1:
                telefone.setNumero(aValue.toString());
                break;
            default:

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void setValueAt(Telefone aValue, int rowIndex) {
        Telefone telefone = linhas.get(rowIndex);

        telefone.setId(aValue.getId());
        telefone.setNumero(aValue.getNumero());
        
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

    public Telefone get(int indiceLinha) {
        return linhas.get(indiceLinha);
    }
    
    public void add(Telefone telefone) {
        linhas.add(telefone);
        int ultimoIndice = getRowCount() -1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void remove(int indiceLinha){
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
    
       
    public void addLista(List<Telefone> lista){
        int tamanhoAntigo = getRowCount();
        linhas.addAll(lista);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
    
    public void limpar(){
        linhas.clear();
        fireTableDataChanged();
    }
    
    public boolean isEmpty(){
        return linhas.isEmpty();
    }
       
}
