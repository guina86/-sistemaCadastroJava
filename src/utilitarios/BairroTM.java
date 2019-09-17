/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Bairro;

/**
 *
 * @author Leandro Guina
 */
public class BairroTM extends AbstractTableModel {

    private final List<Bairro> linhas;
    private final String[] colunas = new String[]{"Id", "Nome", "Cidade"};
    
    public BairroTM() {
        this.linhas = new ArrayList<>();
    }

    public BairroTM(List<Bairro> lista) {
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
        Bairro bairro = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bairro.getId();
            case 1:
                return bairro.getNome();
            case 2:
                return bairro.getCidade();
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Bairro bairro = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                bairro.setId((Integer)aValue);
                break;
            case 1:
                bairro.setNome(aValue.toString());
                break;
            case 2:
                bairro.setCidade(aValue.toString());
                break;
            default:

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void setValueAt(Bairro aValue, int rowIndex) {
        Bairro bairro = linhas.get(rowIndex);

        bairro.setId(aValue.getId());
        bairro.setNome(aValue.getNome());
        bairro.setCidade(aValue.getCidade());
        
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

    public Bairro get(int indiceLinha) {
        return linhas.get(indiceLinha);
    }
    
    public void add(Bairro bairro) {
        linhas.add(bairro);
        int ultimoIndice = getRowCount() -1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void remove(int indiceLinha){
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
    
       
    public void addLista(List<Bairro> lista){
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
