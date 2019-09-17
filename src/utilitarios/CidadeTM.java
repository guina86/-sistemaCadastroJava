/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Cidade;

/**
 *
 * @author Leandro Guina
 */
public class CidadeTM extends AbstractTableModel {

    private final List<Cidade> linhas;
    private final String[] colunas = new String[]{"Id", "Nome", "Estado"};
    
    public CidadeTM() {
        this.linhas = new ArrayList<>();
    }

    public CidadeTM(List<Cidade> lista) {
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
        Cidade cidade = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cidade.getId();
            case 1:
                return cidade.getNome();
            case 2:
                return cidade.getEstado();
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cidade cidade = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                cidade.setId((Integer)aValue);
                break;
            case 1:
                cidade.setNome(aValue.toString());
                break;
            case 2:
                cidade.setEstado(aValue.toString());
                break;
            default:

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void setValueAt(Cidade aValue, int rowIndex) {
        Cidade cidade = linhas.get(rowIndex);

        cidade.setId(aValue.getId());
        cidade.setNome(aValue.getNome());
        cidade.setEstado(aValue.getEstado());
        
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

    public Cidade get(int indiceLinha) {
        return linhas.get(indiceLinha);
    }
    
    public void add(Cidade cidade) {
        linhas.add(cidade);
        int ultimoIndice = getRowCount() -1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void remove(int indiceLinha){
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
    
       
    public void addLista(List<Cidade> lista){
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
