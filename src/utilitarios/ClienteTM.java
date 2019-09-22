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
import modelo.Cliente;

/**
 *
 * @author Leandro Guina
 */
public class ClienteTM extends AbstractTableModel {

    private final List<Cliente> linhas;
    private final String[] colunas = new String[]{"Id", "Nome", "Endereço", "RG", "CPF", "Bairro"};

    public ClienteTM() {
        this.linhas = new ArrayList<>();
    }

    public ClienteTM(List<Cliente> lista) {
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
                return Bairro.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getEndereco();
            case 3:
                return cliente.getRg();
            case 4:
                return cliente.getCpf();
            case 5:
                return cliente.getBairro();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente cliente = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                cliente.setId((Integer)aValue);
                break;
            case 1:
                cliente.setNome(aValue.toString());
                break;
            case 2:
                cliente.setEndereco(aValue.toString());
                break;
            case 3:
                cliente.setRg(aValue.toString());
                break;
            case 4:
                cliente.setCpf(aValue.toString());
                break;
            case 5:
                cliente.setBairro((Bairro)aValue);
                break;
            default:

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void setValueAt(Cliente aValue, int rowIndex) {
        Cliente cliente = linhas.get(rowIndex);

        cliente.setId(aValue.getId());
        cliente.setNome(aValue.getNome());
        cliente.setEndereco(aValue.getEndereco());
        cliente.setRg(aValue.getRg());
        cliente.setCpf(aValue.getCpf());
        cliente.setBairro(aValue.getBairro());

        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);
        fireTableCellUpdated(rowIndex, 4);
        fireTableCellUpdated(rowIndex, 5);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Cliente get(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void add(Cliente cliente) {
        linhas.add(cliente);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void remove(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addLista(List<Cliente> lista) {
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
