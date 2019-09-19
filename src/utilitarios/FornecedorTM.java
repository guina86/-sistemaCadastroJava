/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Fornecedor;

/**
 *
 * @author Leandro Guina
 */
public class FornecedorTM extends AbstractTableModel {

    private final List<Fornecedor> linhas;
    private final String[] colunas = new String[]{"Id", "Nome", "cnpj", "Endereço", "Bairro", "Cidade", "Estado"};

    public FornecedorTM() {
        this.linhas = new ArrayList<>();
    }

    public FornecedorTM(List<Fornecedor> lista) {
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
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornecedor fornecedor = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return fornecedor.getId();
            case 1:
                return fornecedor.getNome();
            case 2:
                return fornecedor.getCnpj();
            case 3:
                return fornecedor.getEndereco();
            case 4:
                return fornecedor.getBairro();
            case 5:
                return fornecedor.getCidade();
            case 6:
                return fornecedor.getEstado();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Fornecedor fornecedor = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                fornecedor.setId((Integer)aValue);
                break;
            case 1:
                fornecedor.setNome(aValue.toString());
                break;
            case 2:
                fornecedor.setCnpj(aValue.toString());
                break;
            case 3:
                fornecedor.setEndereco(aValue.toString());
                break;
            case 4:
                fornecedor.setBairro(aValue.toString());
                break;
            case 5:
                fornecedor.setCidade(aValue.toString());
                break;
            case 6:
                fornecedor.setEstado(aValue.toString());
                break;
            default:

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void setValueAt(Fornecedor aValue, int rowIndex) {
        Fornecedor fornecedor = linhas.get(rowIndex);

        fornecedor.setId(aValue.getId());
        fornecedor.setNome(aValue.getNome());
        fornecedor.setCnpj(aValue.getCnpj());
        fornecedor.setEndereco(aValue.getEndereco());
        fornecedor.setBairro(aValue.getBairro());
        fornecedor.setCidade(aValue.getCidade());
        fornecedor.setEstado(aValue.getEstado());

        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);
        fireTableCellUpdated(rowIndex, 4);
        fireTableCellUpdated(rowIndex, 5);
        fireTableCellUpdated(rowIndex, 6);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Fornecedor get(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void add(Fornecedor fornecedor) {
        linhas.add(fornecedor);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void remove(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addLista(List<Fornecedor> lista) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(lista);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

}
