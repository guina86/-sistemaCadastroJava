/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Estado;

/**
 *
 * @author Leandro Guina
 */
public class EstadoTM extends AbstractTableModel {

    private final List<Estado> linhas;
    private final String[] colunas = new String[]{"Id", "Nome", "Sigla"};

    public EstadoTM() {
        this.linhas = new ArrayList<>();
    }

    public EstadoTM(List<Estado> lista) {
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
            case 2:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Estado estado = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return estado.getId();
            case 1:
                return estado.getNome();
            case 2:
                return estado.getSigla();
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Estado estado = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                estado.setId((Integer)aValue);
                break;
            case 1:
                estado.setNome(aValue.toString());
                break;
            case 2:
                estado.setSigla(aValue.toString());
                break;
            default:

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void setValueAt(Estado aValue, int rowIndex) {
        Estado estado = linhas.get(rowIndex);

        estado.setId(aValue.getId());
        estado.setNome(aValue.getNome());
        estado.setSigla(aValue.getSigla());
        
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

    public Estado get(int indiceLinha) {
        return linhas.get(indiceLinha);
    }
    
    public void add(Estado estado) {
        linhas.add(estado);
        int ultimoIndice = getRowCount() -1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void remove(int indiceLinha){
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
    
       
    public void addLista(List<Estado> lista){
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
