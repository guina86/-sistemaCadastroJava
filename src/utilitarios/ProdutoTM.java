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
import modelo.Produto;

/**
 *
 * @author Leandro Guina
 */
public class ProdutoTM extends AbstractTableModel {

    private final List<Produto> linhas;
    private final String[] colunas = new String[]{"Id", "Nome", "Preco Compra", "Preco venda", "Quantidade", "Fornecedor"};

    public ProdutoTM() {
        this.linhas = new ArrayList<>();
    }

    public ProdutoTM(List<Produto> lista) {
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
                return Double.class;
            case 3:
                return Double.class;
            case 4:
                return Integer.class;
            case 5:
                return Fornecedor.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return produto.getId();
            case 1:
                return produto.getNome();
            case 2:
                return produto.getPrecoCompra();
            case 3:
                return produto.getPrecoVenda();
            case 4:
                return produto.getQuantidade();
            case 5:
                return produto.getFornecedor();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Produto produto = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                produto.setId((Integer)aValue);
                break;
            case 1:
                produto.setNome(aValue.toString());
                break;
            case 2:
                produto.setPrecoCompra((Double)aValue);
                break;
            case 3:
                produto.setPrecoVenda((Double)aValue);
                break;
            case 4:
                produto.setQuantidade((Integer)aValue);
                break;
            case 5:
                produto.setFornecedor((Fornecedor)aValue);
                break;
            default:

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void setValueAt(Produto aValue, int rowIndex) {
        Produto produto = linhas.get(rowIndex);

        produto.setId(aValue.getId());
        produto.setNome(aValue.getNome());
        produto.setPrecoCompra(aValue.getPrecoCompra());
        produto.setPrecoVenda(aValue.getPrecoVenda());
        produto.setQuantidade(aValue.getQuantidade());
        produto.setFornecedor(aValue.getFornecedor());

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

    public Produto get(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void add(Produto produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void remove(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addLista(List<Produto> lista) {
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
    
    public int indexOf(Produto produto){
        return linhas.indexOf(produto);
    }
    
}
